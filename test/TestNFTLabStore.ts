import { ethers } from 'hardhat';
import { expect } from 'chai';
import { NFTLabStore } from 'typechain';
import { BigNumberish } from '@ethersproject/bignumber';

describe('Testing NFTLabStore contract', async () => {
  let nftLabStore: NFTLabStore;

  beforeEach(async () => {
    const signers = await ethers.getSigners();
    const nftLabStoreFactory = await ethers.getContractFactory('NFTLabStore', signers[0]);

    nftLabStore = (await nftLabStoreFactory.deploy('NFTLab', 'NFTL')) as NFTLabStore;
  });

  describe('Constructor', () => {
    it('Should set symbol and name', async () => {
      const signers = await ethers.getSigners();
      const nftLabStoreFactory = await ethers.getContractFactory('NFTLabStore', signers[0]);

      const expectedName: string = 'NFTLab';
      const expectedSymbol: string = 'NFTL';

      const contract: NFTLabStore = (
        await nftLabStoreFactory.deploy(expectedName, expectedSymbol)
      ) as NFTLabStore;

      const actualName: string = await contract.name();
      const actualSymbol: string = await contract.symbol();

      expect(actualName).to.equal(expectedName);
      expect(actualSymbol).to.equal(expectedSymbol);
    });
  });

  describe('Mint', async () => {
    it('Only owner should be able to mint a new NFT', async () => {
      const signers = await ethers.getSigners();

      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      const notOwnerCaller: NFTLabStore = nftLabStore.connect(signers[1]);

      await expect(notOwnerCaller.mint(nft))
        .to.be.revertedWith('Only owner can do this operation.');
    });

    it('Mint a new valid NFT', async () => {
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      await expect(nftLabStore.mint(nft))
        .to.emit(nftLabStore, 'Minted')
        .withArgs(
          nft.artist,
          nft.hash,
          nft.timestamp,
        );
    });

    it('Should not mint an existing NFT', async () => {
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      await nftLabStore.mint(nft);

      await expect(nftLabStore.mint(nft))
        .to.be.revertedWith('Token exists yet.');
    });
  });

  describe('Transfer', async () => {
    it('Only owner should be able to transfer a NFT', async () => {
      const signers = await ethers.getSigners();

      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      const transaction = {
        tokenId: 1,
        seller: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        sellerId: 0,
        buyer: '0x912E3CEf1745F77b3543390abBF965896133896f',
        buyerId: 1,
        price: '1 ETH',
        timestamp: '2021',
      };

      await nftLabStore.mint(nft);

      const notOwnerCaller: NFTLabStore = nftLabStore.connect(signers[1]);

      await expect(notOwnerCaller.transfer(transaction))
        .to.be.revertedWith('Only owner can do this operation.');
    });

    it('Transfer existing NFT', async () => {
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };
      const transaction = {
        tokenId: 1,
        seller: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        sellerId: 0,
        buyer: '0x912E3CEf1745F77b3543390abBF965896133896f',
        buyerId: 1,
        price: '1 ETH',
        timestamp: '2021',
      };

      await nftLabStore.mint(nft);

      await expect(nftLabStore.transfer(transaction))
        .to.emit(nftLabStore, 'Transferred')
        .withArgs(
          transaction.tokenId,
          transaction.seller,
          transaction.buyer,
          transaction.price,
          transaction.timestamp,
        );
    });
  });

  describe('Get token id', async () => {
    it('Only owner should be able to get token id of a NFT', async () => {
      const signers = await ethers.getSigners();

      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      await nftLabStore.mint(nft);

      const notOwnerCaller: NFTLabStore = nftLabStore.connect(signers[1]);

      await expect(notOwnerCaller.getTokenId(nft.hash))
        .to.be.revertedWith('Only owner can do this operation.');
    });

    it('Get token id of a valid NFT', async () => {
      const expectedId: number = 1;
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      await nftLabStore.mint(nft);

      const actualTokenId: number = (await nftLabStore.getTokenId(nft.hash)).toNumber();

      expect(actualTokenId).to.equal(expectedId);
    });

    it('Should not get id of a non existent NFT', async () => {
      const hash: string = 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq';

      await expect(nftLabStore.getTokenId(hash))
        .to.be.revertedWith('Unable to get the ID of a non-existent NFT.');
    });
  });

  describe('Get history of transactions', async () => {
    it('Only owner should be able to get history transactions of a NFT', async () => {
      const signers = await ethers.getSigners();

      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      const transaction = {
        tokenId: 1,
        seller: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        sellerId: 0,
        buyer: '0x912E3CEf1745F77b3543390abBF965896133896f',
        buyerId: 1,
        price: '1 ETH',
        timestamp: '2021',
      };

      await nftLabStore.mint(nft);

      await nftLabStore.transfer(transaction);

      const notOwnerCaller: NFTLabStore = nftLabStore.connect(signers[1]);

      await expect(notOwnerCaller.getHistory(transaction.tokenId))
        .to.be.revertedWith('Only owner can do this operation.');
    });

    it('Get history of an existing NFT', async () => {
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };
      const transaction = {
        tokenId: 1,
        seller: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        sellerId: 0,
        buyer: '0x912E3CEf1745F77b3543390abBF965896133896f',
        buyerId: 1,
        price: '1 ETH',
        timestamp: '2021',
      };

      const expectedHistory = [transaction];

      await nftLabStore.mint(nft);

      await nftLabStore.transfer(transaction);

      const actualHistory = await nftLabStore.getHistory(transaction.tokenId);

      actualHistory.forEach((actualTr, index) => {
        const expectedTr = expectedHistory[index];
        expect(actualTr.tokenId).to.equal(expectedTr.tokenId);
        expect(actualTr.seller).to.equal(expectedTr.seller);
        expect(actualTr.sellerId).to.equal(expectedTr.sellerId);
        expect(actualTr.buyer).to.equal(expectedTr.buyer);
        expect(actualTr.price).to.equal(expectedTr.price);
        expect(actualTr.timestamp).to.equal(expectedTr.timestamp);
      });
    });

    it('Should not get history of a non existent NFT', async () => {
      const tokenId: number = 1;

      await expect(nftLabStore.getHistory(tokenId))
        .to.be.revertedWith('Unable to get the history of a non-existent NFT.');
    });
  });

  describe('Get NFT by id', async () => {
    it('Only owner should be able to get a NFT by its id', async () => {
      const signers = await ethers.getSigners();

      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };
      const tokenId: number = 1;

      await nftLabStore.mint(nft);

      const notOwnerCaller: NFTLabStore = nftLabStore.connect(signers[1]);

      await expect(notOwnerCaller.getNFTById(tokenId))
        .to.be.revertedWith('Only owner can do this operation.');
    });

    it('Get an existing NFT by id', async () => {
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      await nftLabStore.mint(nft);
      const tokenId: BigNumberish = await nftLabStore.getTokenId(nft.hash);

      const gettedNFT = await nftLabStore.getNFTById(tokenId);

      expect(gettedNFT.artist).to.equal(nft.artist);
      expect(gettedNFT.artistId.toNumber()).to.equal(nft.artistId);
      expect(gettedNFT.hash).to.equal(nft.hash);
      expect(gettedNFT.timestamp).to.equal(nft.timestamp);
    });

    it('Should not get a non existent NFT by id', async () => {
      const tokenId: number = 1;

      await expect(nftLabStore.getNFTById(tokenId))
        .to.be.revertedWith('Unable to get a non-existent NFT.');
    });
  });

  describe('Get NFT by hash', async () => {
    it('Only owner should be able to get a NFT by its hash', async () => {
      const signers = await ethers.getSigners();

      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      await nftLabStore.mint(nft);

      const notOwnerCaller: NFTLabStore = nftLabStore.connect(signers[1]);

      await expect(notOwnerCaller.getNFTByHash(nft.hash))
        .to.be.revertedWith('Only owner can do this operation.');
    });

    it('Get an existing NFT by hash', async () => {
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };

      await nftLabStore.mint(nft);

      const gettedNFT = await nftLabStore.getNFTByHash(nft.hash);

      expect(gettedNFT.artist).to.equal(nft.artist);
      expect(gettedNFT.artistId.toNumber()).to.equal(nft.artistId);
      expect(gettedNFT.hash).to.equal(nft.hash);
      expect(gettedNFT.timestamp).to.equal(nft.timestamp);
    });

    it('Should not get a non existent NFT by hash', async () => {
      const hash: string = 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq';

      await expect(nftLabStore.getNFTByHash(hash))
        .to.be.revertedWith('Unable to get a non-existent NFT.');
    });
  });

  describe('Get token URI', async () => {
    it('Get NFT URI by id', async () => {
      const nft = {
        artist: '0x51b1D3246fc4D665A75F77599c82419ab11dEAc4',
        artistId: 0,
        hash: 'QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq',
        timestamp: '2019',
      };
      const expectedTokenURI: string = `https://cloudflare-ipfs.com/ipfs/${nft.hash}`;
      const tokenId: number = 1;

      await nftLabStore.mint(nft);

      const actualTokenURI: string = await nftLabStore.tokenURI(tokenId);

      await expect(actualTokenURI).to.equal(expectedTokenURI);
    });
  });
});
