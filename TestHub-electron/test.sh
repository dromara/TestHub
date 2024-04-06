#!/bin/bash

read -p "版本号: " DOCKER_IMAGE_TAG

# # build前端
cd ../client/
yarn build:desktop

# pwd

# # 拷贝前端
cd ../electron-egg
# pwd
cp -r ../client/dist/* ./public/dist

cp ../server/testhub-server/target/testhub-server-$DOCKER_IMAGE_TAG-SNAPSHOT.jar ./build/extraResources/TestHub.jar

cp -r ./build/jre/mac_jre/* ./build/extraResources/jdk-17.0.6/


