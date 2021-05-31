package io.nfteam.nftlab.services;

import io.nfteam.nftlab.contracts.NFTLabStore;
import io.nfteam.nftlab.services.ipfs.IPFSResponses;
import io.nfteam.nftlab.services.ipfs.IPFSService;
import io.nfteam.nftlab.services.smartcontract.NFTLabETHContractService;

import java.math.BigInteger;
import java.util.List;

public class NFTEthService implements NFTService {
  private final IPFSService ipfsService;
  private final NFTLabETHContractService contractService;

  public NFTEthService(NFTLabETHContractService contractService, IPFSService ipfsService)
  {
    this.contractService = contractService;
    this.ipfsService = ipfsService;
  }

  public NFTID mint(String artist, String artistId, String image) throws Exception
  {
    IPFSResponses.UploadImage uploadedImage = ipfsService.uploadImage(image);
    String hash = uploadedImage.getHash();
    String timestamp = uploadedImage.getTimestamp();

    NFTLabStore.NFTLab newNft = new NFTLabStore.NFTLab(artist, artistId, hash, timestamp);

    BigInteger tokenId = contractService.mint(newNft);

    return new NFTID(hash, tokenId);
  }

  public void transfer(
          BigInteger tokenId,
          String seller,
          String sellerId,
          String buyer,
          String buyerId,
          String price,
          String timestamp) throws Exception
  {
    NFTLabStore.NFTTransaction transaction = new NFTLabStore.NFTTransaction(tokenId, seller, sellerId, buyer, buyerId, price, timestamp);

    contractService.transfer(transaction);
  }

  public List<NFTLabStore.NFTTransaction> getHistory(BigInteger tokenId) throws Exception {
    return contractService.getHistory(tokenId);
  }
}
