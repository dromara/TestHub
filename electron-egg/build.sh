#!/bin/bash

# 读取用户输入
read -p "版本号: " DOCKER_IMAGE_TAG


# 拷贝jar包
cp ../server/testhub-server/target/testhub-server-$DOCKER_IMAGE_TAG-SNAPSHOT.jar ./build/extraResources/TestHub.jar

echo "请选择平台:"
echo "1. win64"
echo "2. mac 英特尔"

read -p "请输入数字 (1 或 2): " choice
rm -rf ./build/extraResources/jdk-17.0.6/*
rm -rf ./public/dist/*

# build前端
cd ../client/
yarn build:desktop

pwd

# 拷贝前端
cd ../electron-egg
pwd
cp -r ../client/dist/* ./public/dist

case $choice in
    1)
        cp -r ./build/jre/win_jre/* ./build/extraResources/jdk-17.0.6/

        yarn build-w-64
        ;;
    2)
        cp -r ./build/jre/mac_jre/* ./build/extraResources/jdk-17.0.6/
        # 执行 mac 英特尔 平台的命令
        yarn build-m
        ;;
    *)
        echo "无效的输入，只能输入 1 或 2"
        exit 1
        ;;
esac

rm -rf ./build/extraResources/jdk-17.0.6/*
rm ./build/extraResources/TestHub.jar
rm -rf ./public/dist/*
cd ../client/
rm -rf ./dist
