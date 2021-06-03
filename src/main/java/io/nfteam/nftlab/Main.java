package io.nfteam.nftlab;

import io.nfteam.nftlab.contracts.NFTLabStore;
import io.nfteam.nftlab.services.NFTEthService;
import io.nfteam.nftlab.services.ipfs.IPFSPinataService;
import io.nfteam.nftlab.services.ipfs.IPFSService;
import io.nfteam.nftlab.services.smartcontract.NFTID;
import io.nfteam.nftlab.services.smartcontract.NFTLabETHContractService;
import io.nfteam.nftlab.services.smartcontract.UserTuple;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        switch (args[0]) {
            case "--mint" -> {
                mint();
            }
            case "--transfer" -> {
                transfer();
            }
            case "--deploy" -> {
                deployEthereumContract();
            }
            case "--history" -> {
                getHistory();
            }
        }
    }

    public static void mint() {
        IPFSService ipfsService = new IPFSPinataService();
        NFTLabETHContractService contractService = new NFTLabETHContractService();

        NFTEthService nftService = new NFTEthService(contractService, ipfsService);

        String filePath = "./images/719uivXGl6L.jpg";

        try {
            NFTID hash = nftService.mint(new UserTuple("0x51b1D3246fc4D665A75F77599c82419ab11dEAc4", BigInteger.valueOf(0)), filePath);
            System.out.println("Hash: " + hash);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void transfer() {
        IPFSService ipfsService = new IPFSPinataService();
        NFTLabETHContractService contractService = new NFTLabETHContractService();

        NFTEthService nftService = new NFTEthService(contractService, ipfsService);

        try {
            nftService.transfer(
                    BigInteger.valueOf(1),
                    new UserTuple("0x51b1D3246fc4D665A75F77599c82419ab11dEAc4", BigInteger.valueOf(0)),
                    new UserTuple("0x8f64E2Cb041D23fC961F300fdB092A59EF38dedc", BigInteger.valueOf(1)),
                    "21",
                    LocalDateTime.now().toString()
            );
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void getHistory() {
        IPFSService ipfsService = new IPFSPinataService();
        NFTLabETHContractService contractService = new NFTLabETHContractService();

        NFTEthService nftService = new NFTEthService(contractService, ipfsService);

        String filePath = "./images/719uivXGl6L.jpg";

        try {
            List<NFTLabStore.NFTTransaction> history = nftService.getHistory(BigInteger.valueOf(1));

            history.forEach((transaction) -> {
                System.out.println(transaction.price);
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
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
