package io.nfteam.nftlab.services;

import io.nfteam.nftlab.contracts.NFTLabStore;
import io.nfteam.nftlab.services.ipfs.IPFSResponses;
import io.nfteam.nftlab.services.ipfs.IPFSService;
import io.nfteam.nftlab.services.smartcontract.NFTID;
import io.nfteam.nftlab.services.smartcontract.NFTLabETHContractService;
import io.nfteam.nftlab.services.smartcontract.UserTuple;

import java.math.BigInteger;
import java.util.List;

//@Component("NFTEthService")
public class NFTEthService implements NFTService {
  private final IPFSService ipfsService;
  private final NFTLabETHContractService contractService;

  public NFTEthService(NFTLabETHContractService contractService, IPFSService ipfsService)
  {
    this.contractService = contractService;
    this.ipfsService = ipfsService;
  }

  public NFTID mint(UserTuple artist, String image) throws Exception
  {
    IPFSResponses.UploadImage uploadedImage = ipfsService.uploadImage(image);
    String hash = uploadedImage.getHash();
    String timestamp = uploadedImage.getTimestamp();

    NFTLabStore.NFTLab newNft = new NFTLabStore.NFTLab(artist.wallet(), artist.id(), hash, timestamp);

    contractService.mint(newNft);
    BigInteger tokenId = contractService.getTokenId(newNft.hash);

    return new NFTID(hash, tokenId);
  }

  public void transfer(
          BigInteger tokenId,
          UserTuple seller,
          UserTuple buyer,
          String price,
          String timestamp) throws Exception
  {
    NFTLabStore.NFTTransaction transaction = new NFTLabStore.NFTTransaction(tokenId, seller.wallet(), seller.id(), buyer.wallet(), buyer.id(), price, timestamp);

    contractService.transfer(transaction);
  }

  public List<NFTLabStore.NFTTransaction> getHistory(BigInteger tokenId) throws Exception {
    return contractService.getHistory(tokenId);
  }
}
