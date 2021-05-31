// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "../node_modules/@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "../node_modules/@openzeppelin/contracts/utils/Counters.sol";

contract NFTLabStore is ERC721URIStorage {
    address private _owner;

    using Counters for Counters.Counter;
    Counters.Counter private _tokenIds;

    mapping(uint256 => NFTLab) private _nfts;
    mapping(string => uint8) private _existingToken;
    mapping(uint256 => NFTTransaction[]) private _history;

    struct NFTLab {
        address artist;
        string artistId;
        string hash;
        string timestamp;
    }

    struct NFTTransaction {
        uint256 tokenId;
        address seller;
        string sellerId;
        address buyer;
        string buyerId;
        string price;
        string timestamp;
    }

    event Minted(address artist, string artistId, string hash, string timestamp);
    event Transferred(
        uint256 tokenId,
        address seller,
        string sellerId,
        address buyer,
        string buyerId,
        string price,
        string timestamp
    );

    constructor(string memory name, string memory symbol) ERC721(name, symbol) {
        _owner = msg.sender;
    }

    modifier isOwner() {
        require(msg.sender == _owner, "Only owner can do this operation");
        _;
    }

    function mint(NFTLab memory nft) public isOwner returns (uint256) {
        require(_existingToken[nft.hash] == 0, "Token exists yet");

        _tokenIds.increment();

        uint256 newTokenId = _tokenIds.current();

        _safeMint(nft.artist, newTokenId);
        _setTokenURI(newTokenId, nft.hash);

        _nfts[newTokenId] = nft;
        _existingToken[nft.hash] = 1;

        emit Minted(nft.artist, nft.artistId, nft.hash, nft.timestamp);

        return newTokenId;
    }

    function transfer(NFTTransaction memory transaction) public isOwner {
        uint256 tokenId = transaction.tokenId;

        safeTransferFrom(transaction.seller, transaction.buyer, tokenId, "");

        _history[tokenId].push(transaction);

        emit Transferred(
            transaction.tokenId,
            transaction.seller,
            transaction.sellerId,
            transaction.buyer,
            transaction.buyerId,
            transaction.price,
            transaction.timestamp
        );
    }

    function getHistory(uint256 tokenId) public view isOwner returns (NFTTransaction[] memory) {
        return _history[tokenId];
    }

    function _baseURI() internal pure override returns (string memory) {
        return "https://gateway.pinata.cloud/";
    }
}
