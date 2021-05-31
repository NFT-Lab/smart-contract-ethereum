package io.nfteam.nftlab.services;

import java.math.BigInteger;
import java.util.List;

public interface NFTService {
    NFTID mint(String artist, String artistId, String image) throws Exception;
    void transfer(
            BigInteger tokenId,
            String seller,
            String sellerId,
            String buyer,
            String buyerId,
            String price,
            String timestamp) throws Exception;

    List getHistory(BigInteger tokenId) throws Exception;
}

record NFTID(String hash, BigInteger tokenId) { }
