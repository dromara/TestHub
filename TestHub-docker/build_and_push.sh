#!/bin/bash

# 读取用户输入

read -p "版本号: " DOCKER_IMAGE_TAG
read -s -p "Docker Hub 密码: " DOCKER_PASSWORD


# 设置变量
DOCKER_IMAGE_NAME="testhub"
DOCKERHUB_REPO="vinc02131"
DOCKER_USERNAME="vinc02131"


rm -rf ./frontend/*


# 拷贝jar包
cp ../TestHub-server/testhub-server/target/testhub-server-$DOCKER_IMAGE_TAG-SNAPSHOT.jar ./backend/TestHub.jar

# build前端
cd ../TestHub-client/
yarn build:web

# 拷贝前端
cd ../TestHub-docker
cp -r ../TestHub-client/dist/* ./frontend/

# 构建镜像
docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG .

# 添加标签
docker tag $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG $DOCKERHUB_REPO/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

# 登录到 Docker Hub
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# 推送镜像到 Docker Hub
# docker push $DOCKERHUB_REPO/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

#注销 Docker Hub
docker logout
rm ./backend/TestHub.jar
