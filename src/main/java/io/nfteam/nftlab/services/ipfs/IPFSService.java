package io.nfteam.nftlab.services.ipfs;

import java.io.IOException;

public interface IPFSService {
  IPFSResponses.UploadImage uploadImage(String image) throws IOException;
}
