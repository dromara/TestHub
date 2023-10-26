<h1 align="center" style="margin: 30px 0 30px; font-weight: bold;">TestHub</h1>
<h4 align="center" style="margin: 30px 0 30px; font-weight: bold;">流程编排，插件驱动，测试无限可能</h4>
<p align="center">
<a href="https://gitee.com/dromara/TestHub/stargazers"><img src="https://gitee.com/dromara/TestHub/badge/star.svg?theme=gvp"></a>
<a href="https://gitee.com/dromara/TestHub/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-Apache--2.0-green"></a>
<a href="https://gitee.com/dromara/TestHub"><img src="https://img.shields.io/badge/version-v1.0.0-blue"></a>
</p>

## 介绍

在软件开发旅程中，测试流程的管理和执行常常是复杂且耗时的挑战。传统测试工具主要面接口级自动化向无法满足多样化需求，无法扩展多样性需求。TestHub 诞生解决此问题。

我们引入独特的流程编排功能，轻松定义、管理和执行测试流程。TestHub 插件式架构扩展能力无限，适用于自动化测试、流程调度及其他自动化任务。

TestHub 提供直观易用界面，直观设计测试流程。简单拖放和连接步骤构建完整测试流程，包括执行测试用例、准备数据、配置环境等。在统一平台上高效一致完成。

选择 TestHub 自动化测试工具，流程编排驱动测试流程。优化测试流程，释放更多时间和资源创新品质。加入 TestHub，引领自动化测试未来！

[gitee](https://gitee.com/dromara/TestHub) [github](https://github.com/dromara/TestHub)

## 工程目录
<div align="center">

| 目录   | 说明                           |
| ------ | ------------------------------ |
| nsrule | NsRule 规则引擎源码            |
| server | TestHub 后端源码               |
| client | TestHub 前端源码               |
| doc    | TestHub 使用手册源码           |
| docker | TestHub 的 docker 镜像构建脚本 |
| static | markdown 文件资源              |
| demo   | TestHub 的演示 Demo            |

</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/概览.jpg" alt="Image" width="100%" >
</div>


## 演示视频

https://www.bilibili.com/video/BV1X94y1v7ak/?spm_id_from=333.337.search-card.all.click&vd_source=adbd50ab0dcce0aafbb00e7a8acb9211

## 演示地址

目前演示项目

地址： http://nsrule.com:11018

<span style="color:red;"><b>需要测试的话将原有的测试用例 xml 复制后，新增一个你自己的</b></span>

演示环境有问题 请微信：

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/contact.jpeg" alt="Image" width="300" >
</div>

## 部署

### 安装包下载

https://url84.ctfile.com/d/49656084-58580094-6ad8ce?p=3738 (访问密码: 3738)

### docker 部署 演示 demo

获取项目源码

```
git clone https://gitee.com/dromara/TestHub.git
```

切换到 演示 demo 的 docker compose 配置中

```
cd TestHub/demo/TestHubDemo/
```

启动 TestHubDemo

```
docker-compose up -d
```

执行成功后会启动以下服务

<div align="center">

| 服务名   | 服务说明          | 宿主机端口             | 容器 IP     | 容器端口               | 备注                    |
| -------- | ----------------- | ---------------------- | ----------- | ---------------------- | ----------------------- |
| testhub  | TestHub 项目      | 11018 前端；12003 后端 | 192.168.0.3 | 11018 前端；12003 后端 |                         |
| server   | 被测试的 web 项目 | 12004                  | 192.168.0.4 | 12004 http 接口        |                         |
| postgres | 被测试的数据库    | 12005                  | 192.168.0.5 | 5432                   | 用户名密码均为 postgres |

</div>



前端接口地址 http://127.0.0.1:11018/

后端接口文档 http://127.0.0.1:12003/swagger-ui/index.html#/

### docker 部署 TestHub

获取 TestHub 镜像

```
docker pull vinc02131/testhub:1.0.2
```

运行

```
docker run -d -p 12003:12003 -p 11018:11018 vinc02131/testhub:1.0.2
```

前端接口地址 http://127.0.0.1:11018/

后端接口文档 http://127.0.0.1:12003/swagger-ui/index.html#/

### 源码部署 TestHub

获取项目源码

```
git clone https://gitee.com/dromara/TestHub.git
```

**启动后台服务**

依赖 jdk17

1. 安装 nsrule 依赖。
   nsrule 项目 暂未发布中央仓库，需要打开 nsrule 的工程执行 mvn install 安装到本地仓库
2. 打开 server 中的 TestHub 项目，执行 TestHubApplication
3. 后端接口文档 : http://127.0.0.1:12003

**启动前端服务**

依赖 node v19.5.0

1. 命令行切换到 front 目录中。
2. 导入项目依赖

```
   yarn install
```

3. 启动项目

```
   yarn start
```

4. 前端地址 : http://localhost:8000

## 迭代方向


<div align="center">

| 功能           | 支持 |
| -------------- | ---- |
| 执行计划管理   |      |
|                |      |
| 前端拖拉拽配置 |      |

</div>

## 软件界面
<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/环境级参数.jpg" alt="Image" width="100%" >
</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/全局行为.jpg" alt="Image" width="100%" >
</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/check_loop.png" alt="Image" width="100%" >
</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/check_obj.png" alt="Image" width="100%" >
</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/check.png" alt="Image" width="100%" >
</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/convert.png" alt="Image" width="100%" >
</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/http.png" alt="Image" width="100%" >
</div>

<div align="center">
    <img src="https://gitee.com/dromara/TestHub/raw/main/static/img/sql.png" alt="Image" width="100%" >
</div>

