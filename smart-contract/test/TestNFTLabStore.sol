// SPDX-License-Identifier: MIT
pragma solidity >=0.5.8;

import "truffle/build/Assert.sol";
import "../contracts/NFTLabStore.sol";

contract TestNFTLabStore {
    NFTLabStore private nftlabstore;

    function beforeEach() public {
        nftlabstore = new NFTLabStore("NFTLab", "NFTL");
    }

    function testMint_ValidNFT_NFTIsMinted() public {
        uint256 expectedTokenId = 1;

        NFTLabStore.NFTLab memory nft =
            NFTLabStore.NFTLab({
                artist: 0x51b1D3246fc4D665A75F77599c82419ab11dEAc4,
                artistId: 0,
                hash: "QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq",
                timestamp: "2019"
            });

        nftlabstore.mint(nft);

        uint256 actualTokenId = nftlabstore.getTokenId(nft.hash);

        Assert.equal(actualTokenId, expectedTokenId, "Token id should be the expected.");
    }

    // function testMintExistingNFT() public {
    //     NFTLabStore.NFTLab memory nft =
    //         NFTLabStore.NFTLab({
    //             artist: 0x51b1D3246fc4D665A75F77599c82419ab11dEAc4,
    //             artistId: 0,
    //             hash: "QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq",
    //             timestamp: "2019"
    //         });

    //     ThrowProxy throwproxy = new ThrowProxy(address(nftlabstore));

    //     NFTLabStore(address(throwproxy)).mint(nft);

    //     bool success1 = throwproxy.execute{gas: 200000}();

    //     Assert.isFalse(success1, "Mint of an existing nft should not be allowed");
    // }

    function testExistingNFTById_ExistingNFT_ReturnNFT() public {
        NFTLabStore.NFTLab memory nft =
            NFTLabStore.NFTLab({
                artist: 0x51b1D3246fc4D665A75F77599c82419ab11dEAc4,
                artistId: 0,
                hash: "QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq",
                timestamp: "2019"
            });

        nftlabstore.mint(nft);

        uint256 tokenId = nftlabstore.getTokenId(nft.hash);

        NFTLabStore.NFTLab memory gettedNFT = nftlabstore.getNFT(tokenId);

        Assert.equal(gettedNFT.artist, nft.artist, "Artist should be the same");
        Assert.equal(gettedNFT.artistId, nft.artistId, "Artist id should be the same");
        Assert.equal(gettedNFT.hash, nft.hash, "Hash should be the same");
        Assert.equal(gettedNFT.timestamp, nft.timestamp, "Timestamp should be the same");
    }

    function testGetNFTByHash_ExistingNFT_ReturnNFT() public {
        NFTLabStore.NFTLab memory nft =
            NFTLabStore.NFTLab({
                artist: 0x51b1D3246fc4D665A75F77599c82419ab11dEAc4,
                artistId: 0,
                hash: "QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq",
                timestamp: "2019"
            });

        nftlabstore.mint(nft);

        NFTLabStore.NFTLab memory gettedNFT = nftlabstore.getNFT(nft.hash);

        Assert.equal(gettedNFT.artist, nft.artist, "Artist should be the same");
        Assert.equal(gettedNFT.artistId, nft.artistId, "Artist id should be the same");
        Assert.equal(gettedNFT.hash, nft.hash, "Hash should be the same");
        Assert.equal(gettedNFT.timestamp, nft.timestamp, "Timestamp should be the same");
    }
}

contract ThrowProxy {
    address public target;
    bytes data;

    constructor(address _target) {
        target = _target;
    }

    //prime the data using the fallback function.
    fallback() external {
        data = msg.data;
    }

    function execute() public returns (bool) {
        (bool success, ) = target.call(data);
        return success;
    }
}
