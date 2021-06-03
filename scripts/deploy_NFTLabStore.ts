import { ethers } from 'hardhat';

async function main() {
  // We get the contract to deploy
  const NFTLabStore = await ethers.getContractFactory('NFTLabStore');
  const deployed = await NFTLabStore.deploy('NFTLab', 'NFTL');

  console.log('NFTLabStore deployed to:', deployed.address);
}

main()
  .then(() => process.exit(0))
  .catch((error) => {
    console.error(error);
    process.exit(1);
  });
