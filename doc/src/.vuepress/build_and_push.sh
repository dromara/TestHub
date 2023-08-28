#!/bin/bash

# 读取用户输入
read -s -p "Docker Hub 密码: " DOCKER_PASSWORD
echo
read -p "TAG 标签号: " DOCKER_IMAGE_TAG

# 设置变量
DOCKER_IMAGE_NAME="testhub_doc"
DOCKER_USERNAME="vinc02131"
DOCKERHUB_REPO="vinc02131"

# 构建镜像
docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG .

# 添加标签
docker tag $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG $DOCKERHUB_REPO/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

# 登录到 Docker Hub
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

# 推送镜像到 Docker Hub
docker push $DOCKERHUB_REPO/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

# 注销 Docker Hub
docker logout
