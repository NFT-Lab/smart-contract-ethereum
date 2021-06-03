package io.nfteam.nftlab.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class NFTLabStore extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b506040516200228a3803806200228a8339810160408190526200003491620001da565b8151829082906200004d90600090602085019062000081565b5080516200006390600190602084019062000081565b5050600780546001600160a01b031916331790555062000294915050565b8280546200008f9062000241565b90600052602060002090601f016020900481019282620000b35760008555620000fe565b82601f10620000ce57805160ff1916838001178555620000fe565b82800160010185558215620000fe579182015b82811115620000fe578251825591602001919060010190620000e1565b506200010c92915062000110565b5090565b5b808211156200010c576000815560010162000111565b600082601f83011262000138578081fd5b81516001600160401b03808211156200015557620001556200027e565b604051601f8301601f19908116603f011681019082821181831017156200018057620001806200027e565b816040528381526020925086838588010111156200019c578485fd5b8491505b83821015620001bf5785820183015181830184015290820190620001a0565b83821115620001d057848385830101525b9695505050505050565b60008060408385031215620001ed578182fd5b82516001600160401b038082111562000204578384fd5b620002128683870162000127565b9350602085015191508082111562000228578283fd5b50620002378582860162000127565b9150509250929050565b600181811c908216806200025657607f821691505b602082108114156200027857634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b611fe680620002a46000396000f3fe608060405234801561001057600080fd5b506004361061010b5760003560e01c806370a08231116100a2578063a22cb46511610071578063a22cb46514610228578063b88d4fde1461023b578063c8691b2a1461024e578063c87b56dd1461026e578063e985e9c51461028157600080fd5b806370a08231146101e757806393cfca44146101fa57806395d89b411461020d578063a1d09cda1461021557600080fd5b80631e7663bc116100de5780631e7663bc1461018d57806323b872dd146101ae57806342842e0e146101c15780636352211e146101d457600080fd5b806301ffc9a71461011057806306fdde0314610138578063081812fc1461014d578063095ea7b314610178575b600080fd5b61012361011e36600461192c565b6102bd565b60405190151581526020015b60405180910390f35b61014061030f565b60405161012f9190611ce5565b61016061015b366004611b0a565b6103a1565b6040516001600160a01b03909116815260200161012f565b61018b610186366004611903565b61042e565b005b6101a061019b366004611964565b6104b6565b60405190815260200161012f565b61018b6101bc366004611815565b61050a565b61018b6101cf366004611815565b61053b565b6101606101e2366004611b0a565b610556565b6101a06101f53660046117c9565b6105cd565b61018b610208366004611997565b610654565b610140610817565b61018b610223366004611a3e565b610826565b61018b6102363660046118c9565b610976565b61018b610249366004611850565b610a3b565b61026161025c366004611b0a565b610a73565b60405161012f9190611c1a565b61014061027c366004611b0a565b610c71565b61012361028f3660046117e3565b6001600160a01b03918216600090815260056020908152604080832093909416825291909152205460ff1690565b60006001600160e01b031982166380ac58cd60e01b14806102ee57506001600160e01b03198216635b5e139f60e01b145b8061030957506301ffc9a760e01b6001600160e01b03198316145b92915050565b60606000805461031e90611eeb565b80601f016020809104026020016040519081016040528092919081815260200182805461034a90611eeb565b80156103975780601f1061036c57610100808354040283529160200191610397565b820191906000526020600020905b81548152906001019060200180831161037a57829003601f168201915b5050505050905090565b60006103ac82610e0d565b6104125760405162461bcd60e51b815260206004820152602c60248201527f4552433732313a20617070726f76656420717565727920666f72206e6f6e657860448201526b34b9ba32b73a103a37b5b2b760a11b60648201526084015b60405180910390fd5b506000908152600460205260409020546001600160a01b031690565b600061043982610556565b9050806001600160a01b0316836001600160a01b031614156104a75760405162461bcd60e51b815260206004820152602160248201527f4552433732313a20617070726f76616c20746f2063757272656e74206f776e656044820152603960f91b6064820152608401610409565b6104b18383610e2a565b505050565b6007546000906001600160a01b031633146104e35760405162461bcd60e51b815260040161040990611d4a565b600a826040516104f39190611b4e565b90815260200160405180910390205490505b919050565b6105143382610e98565b6105305760405162461bcd60e51b815260040161040990611d7f565b6104b1838383610f7e565b6104b183838360405180602001604052806000815250610a3b565b6000818152600260205260408120546001600160a01b0316806103095760405162461bcd60e51b815260206004820152602960248201527f4552433732313a206f776e657220717565727920666f72206e6f6e657869737460448201526832b73a103a37b5b2b760b91b6064820152608401610409565b60006001600160a01b0382166106385760405162461bcd60e51b815260206004820152602a60248201527f4552433732313a2062616c616e636520717565727920666f7220746865207a65604482015269726f206164647265737360b01b6064820152608401610409565b506001600160a01b031660009081526003602052604090205490565b6007546001600160a01b0316331461067e5760405162461bcd60e51b815260040161040990611d4a565b600a81604001516040516106929190611b4e565b9081526020016040518091039020546000146106e35760405162461bcd60e51b815260206004820152601060248201526f151bdad95b88195e1a5cdd1cc81e595d60821b6044820152606401610409565b6106f1600880546001019055565b60006106fc60085490565b905061070c82600001518261111e565b61071a81836040015161113c565b600081815260096020908152604091829020845181546001600160a01b0319166001600160a01b0390911617815584820151600182015591840151805185939261076b926002850192910190611684565b5060608201518051610787916003840191602090910190611684565b5090505080600a83604001516040516107a09190611b4e565b908152604051908190036020019020556007546107c6906001600160a01b03168261042e565b7fe2406cfd356cfbe4e42d452bde96d27f48c423e5f02b5d78695893308399519d826000015183602001518460400151856060015160405161080b9493929190611bd6565b60405180910390a15050565b60606001805461031e90611eeb565b6007546001600160a01b031633146108505760405162461bcd60e51b815260040161040990611d4a565b8051602080830151606084015160408051938401905260008352610875928490610a3b565b6000818152600b602090815260408083208054600180820183559185529383902086516007909502019384558583015190840180546001600160a01b03199081166001600160a01b0393841617909155918601516002850155606086015160038501805490931691161790556080840151600483015560a08401518051859392610906926005850192910190611684565b5060c08201518051610922916006840191602090910190611684565b5050825160208401516040808601516060870151608088015160a089015160c08a015194517f16d849500e8e562f6813ad826315e35ac4503b3955786d2579a28e808048bc6e985061080b97969590611dd0565b6001600160a01b0382163314156109cf5760405162461bcd60e51b815260206004820152601960248201527f4552433732313a20617070726f766520746f2063616c6c6572000000000000006044820152606401610409565b3360008181526005602090815260408083206001600160a01b03871680855290835292819020805460ff191686151590811790915590519081529192917f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a35050565b610a453383610e98565b610a615760405162461bcd60e51b815260040161040990611d7f565b610a6d848484846111c7565b50505050565b6007546060906001600160a01b03163314610aa05760405162461bcd60e51b815260040161040990611d4a565b6000828152600b6020908152604080832080548251818502810185019093528083529193909284015b82821015610c665760008481526020908190206040805160e081018252600786029092018054835260018101546001600160a01b0390811694840194909452600281015491830191909152600381015490921660608201526004820154608082015260058201805491929160a084019190610b4390611eeb565b80601f0160208091040260200160405190810160405280929190818152602001828054610b6f90611eeb565b8015610bbc5780601f10610b9157610100808354040283529160200191610bbc565b820191906000526020600020905b815481529060010190602001808311610b9f57829003601f168201915b50505050508152602001600682018054610bd590611eeb565b80601f0160208091040260200160405190810160405280929190818152602001828054610c0190611eeb565b8015610c4e5780601f10610c2357610100808354040283529160200191610c4e565b820191906000526020600020905b815481529060010190602001808311610c3157829003601f168201915b50505050508152505081526020019060010190610ac9565b505050509050919050565b6060610c7c82610e0d565b610ce25760405162461bcd60e51b815260206004820152603160248201527f45524337323155524953746f726167653a2055524920717565727920666f72206044820152703737b732bc34b9ba32b73a103a37b5b2b760791b6064820152608401610409565b60008281526006602052604081208054610cfb90611eeb565b80601f0160208091040260200160405190810160405280929190818152602001828054610d2790611eeb565b8015610d745780601f10610d4957610100808354040283529160200191610d74565b820191906000526020600020905b815481529060010190602001808311610d5757829003601f168201915b505050505090506000610db760408051808201909152601d81527f68747470733a2f2f676174657761792e70696e6174612e636c6f75642f000000602082015290565b9050805160001415610dca575092915050565b815115610dfc578082604051602001610de4929190611b6a565b60405160208183030381529060405292505050919050565b610e05846111fa565b949350505050565b6000908152600260205260409020546001600160a01b0316151590565b600081815260046020526040902080546001600160a01b0319166001600160a01b0384169081179091558190610e5f82610556565b6001600160a01b03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b6000610ea382610e0d565b610f045760405162461bcd60e51b815260206004820152602c60248201527f4552433732313a206f70657261746f7220717565727920666f72206e6f6e657860448201526b34b9ba32b73a103a37b5b2b760a11b6064820152608401610409565b6000610f0f83610556565b9050806001600160a01b0316846001600160a01b03161480610f4a5750836001600160a01b0316610f3f846103a1565b6001600160a01b0316145b80610e0557506001600160a01b0380821660009081526005602090815260408083209388168352929052205460ff16610e05565b826001600160a01b0316610f9182610556565b6001600160a01b031614610ff95760405162461bcd60e51b815260206004820152602960248201527f4552433732313a207472616e73666572206f6620746f6b656e2074686174206960448201526839903737ba1037bbb760b91b6064820152608401610409565b6001600160a01b03821661105b5760405162461bcd60e51b8152602060048201526024808201527f4552433732313a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b6064820152608401610409565b611066600082610e2a565b6001600160a01b038316600090815260036020526040812080546001929061108f908490611ea8565b90915550506001600160a01b03821660009081526003602052604081208054600192906110bd908490611e7c565b909155505060008181526002602052604080822080546001600160a01b0319166001600160a01b0386811691821790925591518493918716917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b6111388282604051806020016040528060008152506112f7565b5050565b61114582610e0d565b6111a85760405162461bcd60e51b815260206004820152602e60248201527f45524337323155524953746f726167653a2055524920736574206f66206e6f6e60448201526d32bc34b9ba32b73a103a37b5b2b760911b6064820152608401610409565b600082815260066020908152604090912082516104b192840190611684565b6111d2848484610f7e565b6111de8484848461132a565b610a6d5760405162461bcd60e51b815260040161040990611cf8565b606061120582610e0d565b6112695760405162461bcd60e51b815260206004820152602f60248201527f4552433732314d657461646174613a2055524920717565727920666f72206e6f60448201526e3732bc34b9ba32b73a103a37b5b2b760891b6064820152608401610409565b60006112a560408051808201909152601d81527f68747470733a2f2f676174657761792e70696e6174612e636c6f75642f000000602082015290565b905060008151116112c557604051806020016040528060008152506112f0565b806112cf84611437565b6040516020016112e0929190611b6a565b6040516020818303038152906040525b9392505050565b6113018383611551565b61130e600084848461132a565b6104b15760405162461bcd60e51b815260040161040990611cf8565b60006001600160a01b0384163b1561142c57604051630a85bd0160e11b81526001600160a01b0385169063150b7a029061136e903390899088908890600401611b99565b602060405180830381600087803b15801561138857600080fd5b505af19250505080156113b8575060408051601f3d908101601f191682019092526113b591810190611948565b60015b611412573d8080156113e6576040519150601f19603f3d011682016040523d82523d6000602084013e6113eb565b606091505b50805161140a5760405162461bcd60e51b815260040161040990611cf8565b805181602001fd5b6001600160e01b031916630a85bd0160e11b149050610e05565b506001949350505050565b60608161145b5750506040805180820190915260018152600360fc1b602082015290565b8160005b8115611485578061146f81611f26565b915061147e9050600a83611e94565b915061145f565b60008167ffffffffffffffff8111156114ae57634e487b7160e01b600052604160045260246000fd5b6040519080825280601f01601f1916602001820160405280156114d8576020820181803683370190505b5090505b8415610e05576114ed600183611ea8565b91506114fa600a86611f41565b611505906030611e7c565b60f81b81838151811061152857634e487b7160e01b600052603260045260246000fd5b60200101906001600160f81b031916908160001a90535061154a600a86611e94565b94506114dc565b6001600160a01b0382166115a75760405162461bcd60e51b815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f20616464726573736044820152606401610409565b6115b081610e0d565b156115fd5760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e746564000000006044820152606401610409565b6001600160a01b0382166000908152600360205260408120805460019290611626908490611e7c565b909155505060008181526002602052604080822080546001600160a01b0319166001600160a01b03861690811790915590518392907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b82805461169090611eeb565b90600052602060002090601f0160209004810192826116b257600085556116f8565b82601f106116cb57805160ff19168380011785556116f8565b828001600101855582156116f8579182015b828111156116f85782518255916020019190600101906116dd565b50611704929150611708565b5090565b5b808211156117045760008155600101611709565b600067ffffffffffffffff8084111561173857611738611f81565b604051601f8501601f19908116603f0116810190828211818310171561176057611760611f81565b8160405280935085815286868601111561177957600080fd5b858560208301376000602087830101525050509392505050565b80356001600160a01b038116811461050557600080fd5b600082601f8301126117ba578081fd5b6112f08383356020850161171d565b6000602082840312156117da578081fd5b6112f082611793565b600080604083850312156117f5578081fd5b6117fe83611793565b915061180c60208401611793565b90509250929050565b600080600060608486031215611829578081fd5b61183284611793565b925061184060208501611793565b9150604084013590509250925092565b60008060008060808587031215611865578081fd5b61186e85611793565b935061187c60208601611793565b925060408501359150606085013567ffffffffffffffff81111561189e578182fd5b8501601f810187136118ae578182fd5b6118bd8782356020840161171d565b91505092959194509250565b600080604083850312156118db578182fd5b6118e483611793565b9150602083013580151581146118f8578182fd5b809150509250929050565b60008060408385031215611915578182fd5b61191e83611793565b946020939093013593505050565b60006020828403121561193d578081fd5b81356112f081611f97565b600060208284031215611959578081fd5b81516112f081611f97565b600060208284031215611975578081fd5b813567ffffffffffffffff81111561198b578182fd5b610e05848285016117aa565b6000602082840312156119a8578081fd5b813567ffffffffffffffff808211156119bf578283fd5b90830190608082860312156119d2578283fd5b6119da611e30565b6119e383611793565b815260208301356020820152604083013582811115611a00578485fd5b611a0c878286016117aa565b604083015250606083013582811115611a23578485fd5b611a2f878286016117aa565b60608301525095945050505050565b600060208284031215611a4f578081fd5b813567ffffffffffffffff80821115611a66578283fd5b9083019060e08286031215611a79578283fd5b611a81611e59565b82358152611a9160208401611793565b602082015260408301356040820152611aac60608401611793565b60608201526080830135608082015260a083013582811115611acc578485fd5b611ad8878286016117aa565b60a08301525060c083013582811115611aef578485fd5b611afb878286016117aa565b60c08301525095945050505050565b600060208284031215611b1b578081fd5b5035919050565b60008151808452611b3a816020860160208601611ebf565b601f01601f19169290920160200192915050565b60008251611b60818460208701611ebf565b9190910192915050565b60008351611b7c818460208801611ebf565b835190830190611b90818360208801611ebf565b01949350505050565b6001600160a01b0385811682528416602082015260408101839052608060608201819052600090611bcc90830184611b22565b9695505050505050565b60018060a01b0385168152836020820152608060408201526000611bfd6080830185611b22565b8281036060840152611c0f8185611b22565b979650505050505050565b60006020808301818452808551808352604092508286019150828160051b870101848801865b83811015611cd757888303603f19018552815180518452878101516001600160a01b03908116898601528782015188860152606080830151909116908501526080808201519085015260a08082015160e08287018190529190611ca583880182611b22565b9250505060c08083015192508582038187015250611cc38183611b22565b968901969450505090860190600101611c40565b509098975050505050505050565b6020815260006112f06020830184611b22565b60208082526032908201527f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560408201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b606082015260800190565b6020808252818101527f4f6e6c79206f776e65722063616e20646f2074686973206f7065726174696f6e604082015260600190565b60208082526031908201527f4552433732313a207472616e736665722063616c6c6572206973206e6f74206f6040820152701ddb995c881b9bdc88185c1c1c9bdd9959607a1b606082015260800190565b8781526001600160a01b03878116602083015260408201879052851660608201526080810184905260e060a08201819052600090611e1090830185611b22565b82810360c0840152611e228185611b22565b9a9950505050505050505050565b6040516080810167ffffffffffffffff81118282101715611e5357611e53611f81565b60405290565b60405160e0810167ffffffffffffffff81118282101715611e5357611e53611f81565b60008219821115611e8f57611e8f611f55565b500190565b600082611ea357611ea3611f6b565b500490565b600082821015611eba57611eba611f55565b500390565b60005b83811015611eda578181015183820152602001611ec2565b83811115610a6d5750506000910152565b600181811c90821680611eff57607f821691505b60208210811415611f2057634e487b7160e01b600052602260045260246000fd5b50919050565b6000600019821415611f3a57611f3a611f55565b5060010190565b600082611f5057611f50611f6b565b500690565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052601260045260246000fd5b634e487b7160e01b600052604160045260246000fd5b6001600160e01b031981168114611fad57600080fd5b5056fea2646970667358221220e9dc19026af63a7c1ec013d95717b0b4f7f7b7f3d97c913a965333b86af0d3a064736f6c63430008040033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_GETHISTORY = "getHistory";

    public static final String FUNC_GETTOKENID = "getTokenId";

    public static final String FUNC_ISAPPROVEDFORALL = "isApprovedForAll";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_safeTransferFrom = "safeTransferFrom";

    public static final String FUNC_SETAPPROVALFORALL = "setApprovalForAll";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOKENURI = "tokenURI";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event APPROVALFORALL_EVENT = new Event("ApprovalForAll", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bool>() {}));
    ;

    public static final Event MINTED_EVENT = new Event("Minted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TRANSFERRED_EVENT = new Event("Transferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected NFTLabStore(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFTLabStore(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFTLabStore(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NFTLabStore(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.approved = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<ApprovalForAllEventResponse> getApprovalForAllEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVALFORALL_EVENT, transactionReceipt);
        ArrayList<ApprovalForAllEventResponse> responses = new ArrayList<ApprovalForAllEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalForAllEventResponse>() {
            @Override
            public ApprovalForAllEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVALFORALL_EVENT, log);
                ApprovalForAllEventResponse typedResponse = new ApprovalForAllEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.operator = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.approved = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalForAllEventResponse> approvalForAllEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVALFORALL_EVENT));
        return approvalForAllEventFlowable(filter);
    }

    public List<MintedEventResponse> getMintedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MINTED_EVENT, transactionReceipt);
        ArrayList<MintedEventResponse> responses = new ArrayList<MintedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintedEventResponse typedResponse = new MintedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.artist = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.artistId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.hash = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.timestamp = (String) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MintedEventResponse>() {
            @Override
            public MintedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MINTED_EVENT, log);
                MintedEventResponse typedResponse = new MintedEventResponse();
                typedResponse.log = log;
                typedResponse.artist = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.artistId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.hash = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.timestamp = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<MintedEventResponse> mintedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTED_EVENT));
        return mintedEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokenId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<TransferredEventResponse> getTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERRED_EVENT, transactionReceipt);
        ArrayList<TransferredEventResponse> responses = new ArrayList<TransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferredEventResponse typedResponse = new TransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.seller = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.sellerId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.buyerId = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.price = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.timestamp = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferredEventResponse> transferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferredEventResponse>() {
            @Override
            public TransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERRED_EVENT, log);
                TransferredEventResponse typedResponse = new TransferredEventResponse();
                typedResponse.log = log;
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.seller = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.sellerId = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.buyerId = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.price = (String) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.timestamp = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferredEventResponse> transferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERRED_EVENT));
        return transferredEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getApproved(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAPPROVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<List> getHistory(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETHISTORY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<NFTTransaction>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getTokenId(String hash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOKENID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(hash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> isApprovedForAll(String owner, String operator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISAPPROVEDFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, operator)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(NFTLab nft) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(nft), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> ownerOf(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> safeTransferFrom(String from, String to, BigInteger tokenId, byte[] _data) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_safeTransferFrom, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId), 
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setApprovalForAll(String operator, Boolean approved) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAPPROVALFORALL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, operator), 
                new org.web3j.abi.datatypes.Bool(approved)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> tokenURI(BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOKENURI, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(NFTTransaction transaction) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(transaction), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger tokenId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(tokenId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static NFTLabStore load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTLabStore(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFTLabStore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFTLabStore(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFTLabStore load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFTLabStore(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NFTLabStore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFTLabStore(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NFTLabStore> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(NFTLabStore.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<NFTLabStore> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(NFTLabStore.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFTLabStore> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(NFTLabStore.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<NFTLabStore> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol)));
        return deployRemoteCall(NFTLabStore.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class NFTLab extends DynamicStruct {
        public String artist;

        public BigInteger artistId;

        public String hash;

        public String timestamp;

        public NFTLab(String artist, BigInteger artistId, String hash, String timestamp) {
            super(new org.web3j.abi.datatypes.Address(artist),new org.web3j.abi.datatypes.generated.Uint256(artistId),new org.web3j.abi.datatypes.Utf8String(hash),new org.web3j.abi.datatypes.Utf8String(timestamp));
            this.artist = artist;
            this.artistId = artistId;
            this.hash = hash;
            this.timestamp = timestamp;
        }

        public NFTLab(Address artist, Uint256 artistId, Utf8String hash, Utf8String timestamp) {
            super(artist,artistId,hash,timestamp);
            this.artist = artist.getValue();
            this.artistId = artistId.getValue();
            this.hash = hash.getValue();
            this.timestamp = timestamp.getValue();
        }
    }

    public static class NFTTransaction extends DynamicStruct {
        public BigInteger tokenId;

        public String seller;

        public BigInteger sellerId;

        public String buyer;

        public BigInteger buyerId;

        public String price;

        public String timestamp;

        public NFTTransaction(BigInteger tokenId, String seller, BigInteger sellerId, String buyer, BigInteger buyerId, String price, String timestamp) {
            super(new org.web3j.abi.datatypes.generated.Uint256(tokenId),new org.web3j.abi.datatypes.Address(seller),new org.web3j.abi.datatypes.generated.Uint256(sellerId),new org.web3j.abi.datatypes.Address(buyer),new org.web3j.abi.datatypes.generated.Uint256(buyerId),new org.web3j.abi.datatypes.Utf8String(price),new org.web3j.abi.datatypes.Utf8String(timestamp));
            this.tokenId = tokenId;
            this.seller = seller;
            this.sellerId = sellerId;
            this.buyer = buyer;
            this.buyerId = buyerId;
            this.price = price;
            this.timestamp = timestamp;
        }

        public NFTTransaction(Uint256 tokenId, Address seller, Uint256 sellerId, Address buyer, Uint256 buyerId, Utf8String price, Utf8String timestamp) {
            super(tokenId,seller,sellerId,buyer,buyerId,price,timestamp);
            this.tokenId = tokenId.getValue();
            this.seller = seller.getValue();
            this.sellerId = sellerId.getValue();
            this.buyer = buyer.getValue();
            this.buyerId = buyerId.getValue();
            this.price = price.getValue();
            this.timestamp = timestamp.getValue();
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String approved;

        public BigInteger tokenId;
    }

    public static class ApprovalForAllEventResponse extends BaseEventResponse {
        public String owner;

        public String operator;

        public Boolean approved;
    }

    public static class MintedEventResponse extends BaseEventResponse {
        public String artist;

        public BigInteger artistId;

        public String hash;

        public String timestamp;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger tokenId;
    }

    public static class TransferredEventResponse extends BaseEventResponse {
        public BigInteger tokenId;

        public String seller;

        public BigInteger sellerId;

        public String buyer;

        public BigInteger buyerId;

        public String price;

        public String timestamp;
    }
}
