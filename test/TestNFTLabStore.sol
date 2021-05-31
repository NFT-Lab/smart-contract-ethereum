// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/NFTLabStore.sol";

contract TestNFTLabStore {
    NFTLabStore private nftlabstore;

    function beforeEach() public {
        nftlabstore = NFTLabStore(DeployedAddresses.NFTLabStore());
    }

    function testMintValidNFT() public {
        uint256 expectedTokenId = 1;

        NFTLabStore.NFTLab memory nft =
            NFTLabStore.NFTLab({
                artist: 0x51b1D3246fc4D665A75F77599c82419ab11dEAc4,
                artistId: "0",
                hash: "QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq",
                timestamp: "2019"
            });

        uint256 actualTokenId = nftlabstore.mint(nft);

        Assert.equal(actualTokenId, actualTokenId, "Right");
    }
}
