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
    public static final String BINARY = "60806040523480156200001157600080fd5b5060405162002486380380620024868339810160408190526200003491620001da565b8151829082906200004d90600090602085019062000081565b5080516200006390600190602084019062000081565b5050600780546001600160a01b031916331790555062000294915050565b8280546200008f9062000241565b90600052602060002090601f016020900481019282620000b35760008555620000fe565b82601f10620000ce57805160ff1916838001178555620000fe565b82800160010185558215620000fe579182015b82811115620000fe578251825591602001919060010190620000e1565b506200010c92915062000110565b5090565b5b808211156200010c576000815560010162000111565b600082601f83011262000138578081fd5b81516001600160401b03808211156200015557620001556200027e565b604051601f8301601f19908116603f011681019082821181831017156200018057620001806200027e565b816040528381526020925086838588010111156200019c578485fd5b8491505b83821015620001bf5785820183015181830184015290820190620001a0565b83821115620001d057848385830101525b9695505050505050565b60008060408385031215620001ed578182fd5b82516001600160401b038082111562000204578384fd5b620002128683870162000127565b9350602085015191508082111562000228578283fd5b50620002378582860162000127565b9150509250929050565b600181811c908216806200025657607f821691505b602082108114156200027857634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052604160045260246000fd5b6121e280620002a46000396000f3fe608060405234801561001057600080fd5b50600436106101005760003560e01c806370a0823111610097578063c8691b2a11610066578063c8691b2a1461021d578063c87b56dd1461023d578063e985e9c514610250578063ff6a1e141461028c57600080fd5b806370a08231146101dc57806395d89b41146101ef578063a22cb465146101f7578063b88d4fde1461020a57600080fd5b8063136c6d55116100d3578063136c6d551461018257806323b872dd146101a357806342842e0e146101b65780636352211e146101c957600080fd5b806301ffc9a71461010557806306fdde031461012d578063081812fc14610142578063095ea7b31461016d575b600080fd5b610118610113366004611ab9565b61029f565b60405190151581526020015b60405180910390f35b6101356102f1565b6040516101249190611ec8565b610155610150366004611caf565b610383565b6040516001600160a01b039091168152602001610124565b61018061017b366004611a90565b610410565b005b610195610190366004611af1565b610526565b604051908152602001610124565b6101806101b13660046119a2565b610704565b6101806101c43660046119a2565b610735565b6101556101d7366004611caf565b610750565b6101956101ea366004611956565b6107c7565b61013561084e565b610180610205366004611a56565b61085d565b6101806102183660046119dd565b610922565b61023061022b366004611caf565b61095a565b6040516101249190611dd0565b61013561024b366004611caf565b610c6c565b61011861025e366004611970565b6001600160a01b03918216600090815260056020908152604080832093909416825291909152205460ff1690565b61018061029a366004611bb1565b610e08565b60006001600160e01b031982166380ac58cd60e01b14806102d057506001600160e01b03198216635b5e139f60e01b145b806102eb57506301ffc9a760e01b6001600160e01b03198316145b92915050565b606060008054610300906120e7565b80601f016020809104026020016040519081016040528092919081815260200182805461032c906120e7565b80156103795780601f1061034e57610100808354040283529160200191610379565b820191906000526020600020905b81548152906001019060200180831161035c57829003601f168201915b5050505050905090565b600061038e82610f9a565b6103f45760405162461bcd60e51b815260206004820152602c60248201527f4552433732313a20617070726f76656420717565727920666f72206e6f6e657860448201526b34b9ba32b73a103a37b5b2b760a11b60648201526084015b60405180910390fd5b506000908152600460205260409020546001600160a01b031690565b600061041b82610750565b9050806001600160a01b0316836001600160a01b031614156104895760405162461bcd60e51b815260206004820152602160248201527f4552433732313a20617070726f76616c20746f2063757272656e74206f776e656044820152603960f91b60648201526084016103eb565b336001600160a01b03821614806104a557506104a5813361025e565b6105175760405162461bcd60e51b815260206004820152603860248201527f4552433732313a20617070726f76652063616c6c6572206973206e6f74206f7760448201527f6e6572206e6f7220617070726f76656420666f7220616c6c000000000000000060648201526084016103eb565b6105218383610fb7565b505050565b6007546000906001600160a01b031633146105535760405162461bcd60e51b81526004016103eb90611f2d565b600a82604001516040516105679190611cf3565b9081526040519081900360200190205460ff16156105ba5760405162461bcd60e51b815260206004820152601060248201526f151bdad95b88195e1a5cdd1cc81e595d60821b60448201526064016103eb565b6105c8600880546001019055565b60006105d360085490565b90506105e3836000015182611025565b6105f1818460400151611043565b6000818152600960209081526040909120845181546001600160a01b0319166001600160a01b039091161781558482015180518693610637926001850192910190611811565b5060408201518051610653916002840191602090910190611811565b506060820151805161066f916003840191602090910190611811565b509050506001600a84604001516040516106899190611cf3565b908152602001604051809103902060006101000a81548160ff021916908360ff1602179055507fc7513b939beca9cacdf3957eff8a462d2f637eab930ac9b0474dc6c1eff4a3ff83600001518460200151856040015186606001516040516106f49493929190611d7b565b60405180910390a190505b919050565b61070e33826110ce565b61072a5760405162461bcd60e51b81526004016103eb90611f62565b6105218383836111b4565b61052183838360405180602001604052806000815250610922565b6000818152600260205260408120546001600160a01b0316806102eb5760405162461bcd60e51b815260206004820152602960248201527f4552433732313a206f776e657220717565727920666f72206e6f6e657869737460448201526832b73a103a37b5b2b760b91b60648201526084016103eb565b60006001600160a01b0382166108325760405162461bcd60e51b815260206004820152602a60248201527f4552433732313a2062616c616e636520717565727920666f7220746865207a65604482015269726f206164647265737360b01b60648201526084016103eb565b506001600160a01b031660009081526003602052604090205490565b606060018054610300906120e7565b6001600160a01b0382163314156108b65760405162461bcd60e51b815260206004820152601960248201527f4552433732313a20617070726f766520746f2063616c6c65720000000000000060448201526064016103eb565b3360008181526005602090815260408083206001600160a01b03871680855290835292819020805460ff191686151590811790915590519081529192917f17307eab39ab6107e8899845ad3d59bd9653f200f220920489ca2b5937696c31910160405180910390a35050565b61092c33836110ce565b6109485760405162461bcd60e51b81526004016103eb90611f62565b61095484848484611354565b50505050565b6007546060906001600160a01b031633146109875760405162461bcd60e51b81526004016103eb90611f2d565b6000828152600b6020908152604080832080548251818502810185019093528083529193909284015b82821015610c615760008481526020908190206040805160e081018252600786029092018054835260018101546001600160a01b03169383019390935260028301805492939291840191610a03906120e7565b80601f0160208091040260200160405190810160405280929190818152602001828054610a2f906120e7565b8015610a7c5780601f10610a5157610100808354040283529160200191610a7c565b820191906000526020600020905b815481529060010190602001808311610a5f57829003601f168201915b505050918352505060038201546001600160a01b03166020820152600482018054604090920191610aac906120e7565b80601f0160208091040260200160405190810160405280929190818152602001828054610ad8906120e7565b8015610b255780601f10610afa57610100808354040283529160200191610b25565b820191906000526020600020905b815481529060010190602001808311610b0857829003601f168201915b50505050508152602001600582018054610b3e906120e7565b80601f0160208091040260200160405190810160405280929190818152602001828054610b6a906120e7565b8015610bb75780601f10610b8c57610100808354040283529160200191610bb7565b820191906000526020600020905b815481529060010190602001808311610b9a57829003601f168201915b50505050508152602001600682018054610bd0906120e7565b80601f0160208091040260200160405190810160405280929190818152602001828054610bfc906120e7565b8015610c495780601f10610c1e57610100808354040283529160200191610c49565b820191906000526020600020905b815481529060010190602001808311610c2c57829003601f168201915b505050505081525050815260200190600101906109b0565b505050509050919050565b6060610c7782610f9a565b610cdd5760405162461bcd60e51b815260206004820152603160248201527f45524337323155524953746f726167653a2055524920717565727920666f72206044820152703737b732bc34b9ba32b73a103a37b5b2b760791b60648201526084016103eb565b60008281526006602052604081208054610cf6906120e7565b80601f0160208091040260200160405190810160405280929190818152602001828054610d22906120e7565b8015610d6f5780601f10610d4457610100808354040283529160200191610d6f565b820191906000526020600020905b815481529060010190602001808311610d5257829003601f168201915b505050505090506000610db260408051808201909152601d81527f68747470733a2f2f676174657761792e70696e6174612e636c6f75642f000000602082015290565b9050805160001415610dc5575092915050565b815115610df7578082604051602001610ddf929190611d0f565b60405160208183030381529060405292505050919050565b610e0084611387565b949350505050565b6007546001600160a01b03163314610e325760405162461bcd60e51b81526004016103eb90611f2d565b8051602080830151606084015160408051938401905260008352610e57928490610922565b6000818152600b602090815260408083208054600180820183559185529383902086516007909502019384558583015190840180546001600160a01b0319166001600160a01b039092169190911790558401518051859392610ec0926002850192910190611811565b5060608201516003820180546001600160a01b0319166001600160a01b0390921691909117905560808201518051610f02916004840191602090910190611811565b5060a08201518051610f1e916005840191602090910190611811565b5060c08201518051610f3a916006840191602090910190611811565b5050825160208401516040808601516060870151608088015160a089015160c08a015194517fbca74d7b3026cf67de2a959bcc515edcd164a1e096a87b6db2e51e374046872c9850610f8e97969590611fb3565b60405180910390a15050565b6000908152600260205260409020546001600160a01b0316151590565b600081815260046020526040902080546001600160a01b0319166001600160a01b0384169081179091558190610fec82610750565b6001600160a01b03167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92560405160405180910390a45050565b61103f828260405180602001604052806000815250611484565b5050565b61104c82610f9a565b6110af5760405162461bcd60e51b815260206004820152602e60248201527f45524337323155524953746f726167653a2055524920736574206f66206e6f6e60448201526d32bc34b9ba32b73a103a37b5b2b760911b60648201526084016103eb565b6000828152600660209081526040909120825161052192840190611811565b60006110d982610f9a565b61113a5760405162461bcd60e51b815260206004820152602c60248201527f4552433732313a206f70657261746f7220717565727920666f72206e6f6e657860448201526b34b9ba32b73a103a37b5b2b760a11b60648201526084016103eb565b600061114583610750565b9050806001600160a01b0316846001600160a01b031614806111805750836001600160a01b031661117584610383565b6001600160a01b0316145b80610e0057506001600160a01b0380821660009081526005602090815260408083209388168352929052205460ff16610e00565b826001600160a01b03166111c782610750565b6001600160a01b03161461122f5760405162461bcd60e51b815260206004820152602960248201527f4552433732313a207472616e73666572206f6620746f6b656e2074686174206960448201526839903737ba1037bbb760b91b60648201526084016103eb565b6001600160a01b0382166112915760405162461bcd60e51b8152602060048201526024808201527f4552433732313a207472616e7366657220746f20746865207a65726f206164646044820152637265737360e01b60648201526084016103eb565b61129c600082610fb7565b6001600160a01b03831660009081526003602052604081208054600192906112c59084906120a4565b90915550506001600160a01b03821660009081526003602052604081208054600192906112f3908490612078565b909155505060008181526002602052604080822080546001600160a01b0319166001600160a01b0386811691821790925591518493918716917fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef91a4505050565b61135f8484846111b4565b61136b848484846114b7565b6109545760405162461bcd60e51b81526004016103eb90611edb565b606061139282610f9a565b6113f65760405162461bcd60e51b815260206004820152602f60248201527f4552433732314d657461646174613a2055524920717565727920666f72206e6f60448201526e3732bc34b9ba32b73a103a37b5b2b760891b60648201526084016103eb565b600061143260408051808201909152601d81527f68747470733a2f2f676174657761792e70696e6174612e636c6f75642f000000602082015290565b90506000815111611452576040518060200160405280600081525061147d565b8061145c846115c4565b60405160200161146d929190611d0f565b6040516020818303038152906040525b9392505050565b61148e83836116de565b61149b60008484846114b7565b6105215760405162461bcd60e51b81526004016103eb90611edb565b60006001600160a01b0384163b156115b957604051630a85bd0160e11b81526001600160a01b0385169063150b7a02906114fb903390899088908890600401611d3e565b602060405180830381600087803b15801561151557600080fd5b505af1925050508015611545575060408051601f3d908101601f1916820190925261154291810190611ad5565b60015b61159f573d808015611573576040519150601f19603f3d011682016040523d82523d6000602084013e611578565b606091505b5080516115975760405162461bcd60e51b81526004016103eb90611edb565b805181602001fd5b6001600160e01b031916630a85bd0160e11b149050610e00565b506001949350505050565b6060816115e85750506040805180820190915260018152600360fc1b602082015290565b8160005b811561161257806115fc81612122565b915061160b9050600a83612090565b91506115ec565b60008167ffffffffffffffff81111561163b57634e487b7160e01b600052604160045260246000fd5b6040519080825280601f01601f191660200182016040528015611665576020820181803683370190505b5090505b8415610e005761167a6001836120a4565b9150611687600a8661213d565b611692906030612078565b60f81b8183815181106116b557634e487b7160e01b600052603260045260246000fd5b60200101906001600160f81b031916908160001a9053506116d7600a86612090565b9450611669565b6001600160a01b0382166117345760405162461bcd60e51b815260206004820181905260248201527f4552433732313a206d696e7420746f20746865207a65726f206164647265737360448201526064016103eb565b61173d81610f9a565b1561178a5760405162461bcd60e51b815260206004820152601c60248201527f4552433732313a20746f6b656e20616c7265616479206d696e7465640000000060448201526064016103eb565b6001600160a01b03821660009081526003602052604081208054600192906117b3908490612078565b909155505060008181526002602052604080822080546001600160a01b0319166001600160a01b03861690811790915590518392907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef908290a45050565b82805461181d906120e7565b90600052602060002090601f01602090048101928261183f5760008555611885565b82601f1061185857805160ff1916838001178555611885565b82800160010185558215611885579182015b8281111561188557825182559160200191906001019061186a565b50611891929150611895565b5090565b5b808211156118915760008155600101611896565b600067ffffffffffffffff808411156118c5576118c561217d565b604051601f8501601f19908116603f011681019082821181831017156118ed576118ed61217d565b8160405280935085815286868601111561190657600080fd5b858560208301376000602087830101525050509392505050565b80356001600160a01b03811681146106ff57600080fd5b600082601f830112611947578081fd5b61147d838335602085016118aa565b600060208284031215611967578081fd5b61147d82611920565b60008060408385031215611982578081fd5b61198b83611920565b915061199960208401611920565b90509250929050565b6000806000606084860312156119b6578081fd5b6119bf84611920565b92506119cd60208501611920565b9150604084013590509250925092565b600080600080608085870312156119f2578081fd5b6119fb85611920565b9350611a0960208601611920565b925060408501359150606085013567ffffffffffffffff811115611a2b578182fd5b8501601f81018713611a3b578182fd5b611a4a878235602084016118aa565b91505092959194509250565b60008060408385031215611a68578182fd5b611a7183611920565b915060208301358015158114611a85578182fd5b809150509250929050565b60008060408385031215611aa2578182fd5b611aab83611920565b946020939093013593505050565b600060208284031215611aca578081fd5b813561147d81612193565b600060208284031215611ae6578081fd5b815161147d81612193565b600060208284031215611b02578081fd5b813567ffffffffffffffff80821115611b19578283fd5b9083019060808286031215611b2c578283fd5b611b3461202c565b611b3d83611920565b8152602083013582811115611b50578485fd5b611b5c87828601611937565b602083015250604083013582811115611b73578485fd5b611b7f87828601611937565b604083015250606083013582811115611b96578485fd5b611ba287828601611937565b60608301525095945050505050565b600060208284031215611bc2578081fd5b813567ffffffffffffffff80821115611bd9578283fd5b9083019060e08286031215611bec578283fd5b611bf4612055565b82358152611c0460208401611920565b6020820152604083013582811115611c1a578485fd5b611c2687828601611937565b604083015250611c3860608401611920565b6060820152608083013582811115611c4e578485fd5b611c5a87828601611937565b60808301525060a083013582811115611c71578485fd5b611c7d87828601611937565b60a08301525060c083013582811115611c94578485fd5b611ca087828601611937565b60c08301525095945050505050565b600060208284031215611cc0578081fd5b5035919050565b60008151808452611cdf8160208601602086016120bb565b601f01601f19169290920160200192915050565b60008251611d058184602087016120bb565b9190910192915050565b60008351611d218184602088016120bb565b835190830190611d358183602088016120bb565b01949350505050565b6001600160a01b0385811682528416602082015260408101839052608060608201819052600090611d7190830184611cc7565b9695505050505050565b6001600160a01b0385168152608060208201819052600090611d9f90830186611cc7565b8281036040840152611db18186611cc7565b90508281036060840152611dc58185611cc7565b979650505050505050565b60006020808301818452808551808352604092508286019150828160051b870101848801865b83811015611eba57888303603f19018552815180518452878101516001600160a01b0316888501528681015160e088860181905290611e3782870182611cc7565b915050606080830151611e54828801826001600160a01b03169052565b505060808083015186830382880152611e6d8382611cc7565b9250505060a08083015186830382880152611e888382611cc7565b9250505060c08083015192508582038187015250611ea68183611cc7565b968901969450505090860190600101611df6565b509098975050505050505050565b60208152600061147d6020830184611cc7565b60208082526032908201527f4552433732313a207472616e7366657220746f206e6f6e20455243373231526560408201527131b2b4bb32b91034b6b83632b6b2b73a32b960711b606082015260800190565b6020808252818101527f4f6e6c79206f776e65722063616e20646f2074686973206f7065726174696f6e604082015260600190565b60208082526031908201527f4552433732313a207472616e736665722063616c6c6572206973206e6f74206f6040820152701ddb995c881b9bdc88185c1c1c9bdd9959607a1b606082015260800190565b878152600060018060a01b03808916602084015260e06040840152611fdb60e0840189611cc7565b81881660608501528381036080850152611ff58188611cc7565b91505082810360a084015261200a8186611cc7565b905082810360c084015261201e8185611cc7565b9a9950505050505050505050565b6040516080810167ffffffffffffffff8111828210171561204f5761204f61217d565b60405290565b60405160e0810167ffffffffffffffff8111828210171561204f5761204f61217d565b6000821982111561208b5761208b612151565b500190565b60008261209f5761209f612167565b500490565b6000828210156120b6576120b6612151565b500390565b60005b838110156120d65781810151838201526020016120be565b838111156109545750506000910152565b600181811c908216806120fb57607f821691505b6020821081141561211c57634e487b7160e01b600052602260045260246000fd5b50919050565b600060001982141561213657612136612151565b5060010190565b60008261214c5761214c612167565b500690565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052601260045260246000fd5b634e487b7160e01b600052604160045260246000fd5b6001600160e01b0319811681146121a957600080fd5b5056fea26469706673582212207c6a46b0039696786ee9f8e8c53a03d8ecc9c0c52585f0ba2a92e1cfdd99243664736f6c63430008040033";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETAPPROVED = "getApproved";

    public static final String FUNC_GETHISTORY = "getHistory";

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
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    public static final Event TRANSFERRED_EVENT = new Event("Transferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
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
            typedResponse.artistId = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse.artistId = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
            typedResponse.sellerId = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.buyerId = (String) eventValues.getNonIndexedValues().get(4).getValue();
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
                typedResponse.sellerId = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.buyer = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.buyerId = (String) eventValues.getNonIndexedValues().get(4).getValue();
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

        public String artistId;

        public String hash;

        public String timestamp;

        public NFTLab(String artist, String artistId, String hash, String timestamp) {
            super(new org.web3j.abi.datatypes.Address(artist),new org.web3j.abi.datatypes.Utf8String(artistId),new org.web3j.abi.datatypes.Utf8String(hash),new org.web3j.abi.datatypes.Utf8String(timestamp));
            this.artist = artist;
            this.artistId = artistId;
            this.hash = hash;
            this.timestamp = timestamp;
        }

        public NFTLab(Address artist, Utf8String artistId, Utf8String hash, Utf8String timestamp) {
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

        public String sellerId;

        public String buyer;

        public String buyerId;

        public String price;

        public String timestamp;

        public NFTTransaction(BigInteger tokenId, String seller, String sellerId, String buyer, String buyerId, String price, String timestamp) {
            super(new org.web3j.abi.datatypes.generated.Uint256(tokenId),new org.web3j.abi.datatypes.Address(seller),new org.web3j.abi.datatypes.Utf8String(sellerId),new org.web3j.abi.datatypes.Address(buyer),new org.web3j.abi.datatypes.Utf8String(buyerId),new org.web3j.abi.datatypes.Utf8String(price),new org.web3j.abi.datatypes.Utf8String(timestamp));
            this.tokenId = tokenId;
            this.seller = seller;
            this.sellerId = sellerId;
            this.buyer = buyer;
            this.buyerId = buyerId;
            this.price = price;
            this.timestamp = timestamp;
        }

        public NFTTransaction(Uint256 tokenId, Address seller, Utf8String sellerId, Address buyer, Utf8String buyerId, Utf8String price, Utf8String timestamp) {
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

        public String artistId;

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

        public String sellerId;

        public String buyer;

        public String buyerId;

        public String price;

        public String timestamp;
    }
}
