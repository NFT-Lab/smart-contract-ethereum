const NFTLabStore = artifacts.require("NFTLabStore");

module.exports = async function (deployer) {
  await deployer.deploy(NFTLabStore, "NFTLab", "NFTL");
};
