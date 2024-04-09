#!/bin/bash

# 读取用户输入
read -p "版本号: " DOCKER_IMAGE_TAG


# 拷贝jar包
cp ../TestHub-server/testhub-server/target/testhub-server-$DOCKER_IMAGE_TAG-SNAPSHOT.jar ./build/extraResources/TestHub.jar
cp ../TestHub-docker/backend/application.yml ./build/extraResources/application.yml

echo "请选择平台:"
echo "1. win64"
echo "2. mac 英特尔"
echo "9. 全部"

read -p "请输入数字 (1 或 2 或 9): " choice
rm -rf ./build/extraResources/jdk-17.0.6/*
rm -rf ./public/dist/*
mkdir -p ./build/extraResources/jdk-17.0.6
mkdir -p ./public/dist

# build前端
cd ../TestHub-client/
echo "开始构建前端"
yarn build:desktop

pwd

# 拷贝前端
cd ../TestHub-electron
echo "开始构建electron"
yarn 
pwd
cp -r ../TestHub-client/dist/* ./public/dist

case $choice in
    1)
        cp -r ./build/jre/win_jre/* ./build/extraResources/jdk-17.0.6/
        echo "开始构建 Win"
        yarn build-w-64
        rm -rf ./build/extraResources/jdk-17.0.6/*
        ;;
    2)
        cp -r ./build/jre/mac_jre/* ./build/extraResources/jdk-17.0.6/
        # 执行 mac 英特尔 平台的命令
        echo "开始构建 Mac"
        yarn build-m
        rm -rf ./build/extraResources/jdk-17.0.6/*
        ;;
    9)
        cp -r ./build/jre/mac_jre/* ./build/extraResources/jdk-17.0.6/
        echo "开始构建 Mac"
        # 执行 mac 英特尔 平台的命令
        yarn build-m
        rm -rf ./build/extraResources/jdk-17.0.6/*

        cp -r ./build/jre/win_jre/* ./build/extraResources/jdk-17.0.6/
        echo "开始构建 Win"
        yarn build-w-64
        rm -rf ./build/extraResources/jdk-17.0.6/*
        ;;
    *)
        echo "无效的输入，只能输入 1 或 2 或 9"
        exit 1
        ;;
esac


rm ./build/extraResources/TestHub.jar
rm ./build/extraResources/application.yml
rm -rf ./public/dist/*
cd ../TestHub-client/
rm -rf ./dist
