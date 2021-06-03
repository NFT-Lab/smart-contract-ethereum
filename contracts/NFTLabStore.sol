// SPDX-License-Identifier: MIT
pragma solidity >=0.5.8;

import "./ERC721URIStorage.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

contract NFTLabStore is ERC721URIStorage {
  address private _owner;

  using Counters for Counters.Counter;
  Counters.Counter private _tokenIds;

  mapping(uint256 => NFTLab) private _nfts;
  mapping(string => uint256) private _hashToId;
  mapping(uint256 => NFTTransaction[]) private _history;

  event Minted(address artist, string hash, string timestamp);
  event Transferred(uint256 tokenId, address seller, address buyer, string price, string timestamp);

  struct NFTLab {
    address artist;
    uint256 artistId;
    string hash;
    string timestamp;
  }

  struct NFTTransaction {
    uint256 tokenId;
    address seller;
    uint256 sellerId;
    address buyer;
    uint256 buyerId;
    string price;
    string timestamp;
  }

  constructor(string memory name, string memory symbol) ERC721(name, symbol) {
    _owner = msg.sender;
  }

  modifier isOwner() {
    require(msg.sender == _owner, "Only owner can do this operation.");
    _;
  }

  function mint(NFTLab memory nft) public isOwner {
    require(_hashToId[nft.hash] == 0, "Token exists yet.");

    _tokenIds.increment();

    uint256 newTokenId = _tokenIds.current();

    _safeMint(nft.artist, newTokenId);
    _setTokenURI(newTokenId, nft.hash);

    _nfts[newTokenId] = nft;
    _hashToId[nft.hash] = newTokenId;

    approve(_owner, newTokenId);

    emit Minted(nft.artist, nft.hash, nft.timestamp);
  }

  function transfer(NFTTransaction memory transaction) public isOwner {
    uint256 tokenId = transaction.tokenId;

    safeTransferFrom(transaction.seller, transaction.buyer, tokenId, "");

    _history[tokenId].push(transaction);

    emit Transferred(
      transaction.tokenId,
      transaction.seller,
      transaction.buyer,
      transaction.price,
      transaction.timestamp
    );
  }

  function getHistory(uint256 tokenId) public view isOwner returns (NFTTransaction[] memory) {
    require(_exists(tokenId), "Unable to get the history of a non-existent NFT.");

    return _history[tokenId];
  }

  function getTokenId(string memory hash) public view isOwner returns (uint256) {
    require(_hashToId[hash] != 0, "Unable to get the ID of a non-existent NFT.");

    return _hashToId[hash];
  }

  function getNFTByHash(string memory hash) public view isOwner returns (NFTLab memory) {
    require(_hashToId[hash] != 0, "Unable to get a non-existent NFT.");

    return _nfts[_hashToId[hash]];
  }

  function getNFTById(uint256 id) public view isOwner returns (NFTLab memory) {
    require(_exists(id), "Unable to get a non-existent NFT.");

    return _nfts[id];
  }

  function _baseURI() internal pure override returns (string memory) {
    return "https://cloudflare-ipfs.com/ipfs/";
  }
}
