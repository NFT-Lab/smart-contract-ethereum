package io.nfteam.nftlab;

import io.nfteam.nftlab.services.NFTEthService;
import io.nfteam.nftlab.services.ipfs.IPFSPinataService;
import io.nfteam.nftlab.services.ipfs.IPFSService;
import io.nfteam.nftlab.services.smartcontract.NFTLabETHContractService;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        IPFSService ipfsService = new IPFSPinataService();
        NFTLabETHContractService contractService = new NFTLabETHContractService();

        NFTEthService nftService = new NFTEthService(contractService, ipfsService);

        String filePath = "./images/719uivXGl6L.jpg";

        try {
//            String hash = nftService.mint("0x51b1D3246fc4D665A75F77599c82419ab11dEAc4", "000", filePath);
//            System.out.println("Hash: " + hash);

            nftService.transfer(
                    BigInteger.valueOf(1),
                    "0x51b1D3246fc4D665A75F77599c82419ab11dEAc4",
                    "000",
                    "0x8f64E2Cb041D23fC961F300fdB092A59EF38dedc",
                    "000",
                    "21",
                    LocalDateTime.now().toString()
            );

//            List<NFTLabStore.NFTTransaction> history = nftService.getHistory("QmeK3GCfbMzRp3FW3tWZCg5WVZKM52XZrk6WCTLXWwALbq");
//
//            history.forEach((transaction) -> {
//                System.out.println(transaction.buyer + " " + transaction.seller);
//            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }

//        deployEthereumContract();
    }

    public static void deployEthereumContract() {
        try {
            String address = NFTLabETHContractService.deploy("NFTLab", "NFTL");

            System.out.println("Contract address " + address);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
