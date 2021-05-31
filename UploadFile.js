const pinataSDK = require('@pinata/sdk');
const fs = require('fs');

const pinataAuth = require("./pinata-keys.json");
const pinata = pinataSDK(pinataAuth.API_Key, pinataAuth.API_Secret);

const readableStreamForFile = fs.createReadStream(process.argv[2]);

pinata.pinFileToIPFS(readableStreamForFile).then((result) => {
    //handle results here
    console.log(result);

    const filters = {
      hashContains: 'QmTPE2KCvHUvnYHeKcpBMpTqC2fLnrowUbETbBB6qNCLxe'
    };
    pinata.pinList(filters).then((result) => {
      //handle results here
      console.log(result);
    }).catch((err) => {
      //handle error here
      console.log(err);
    });
}).catch((err) => {
    //handle error here
    console.log(err);
});
