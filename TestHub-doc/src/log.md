---
# 这是文章的标题
title: 💡更新日志
# 这是页面的图标
icon: <svg t="1679837826543" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1201" width="200" height="200"><path d="M763.136 958.72H262.656c-50.944 0-92.16-41.216-92.16-92.16V518.144H107.776c-25.6 0-48.128-15.36-57.6-39.168s-3.584-50.432 14.848-68.096c0.256-0.256 0.512-0.512 0.768-0.512l344.064-307.2c56.576-53.248 145.408-53.76 202.496-1.28l346.624 307.2 0.512 0.512c18.944 17.408 25.088 44.288 15.616 68.352-9.472 24.064-32 39.424-57.856 39.424h-61.696v348.928c-0.256 50.944-41.472 92.416-92.416 92.416zM107.52 456.704h93.696c16.896 0 30.72 13.824 30.72 30.72v379.136c0 16.896 13.824 30.72 30.72 30.72h500.48c16.896 0 30.72-13.824 30.72-30.72V486.656c0-16.896 13.824-30.72 30.72-30.72H917.504s0.256-0.512 0.256-0.768l-0.256-0.256-346.368-307.2-0.512-0.512c-33.536-30.976-86.016-30.72-119.04 0.768-0.256 0.256-0.512 0.512-0.768 0.512L107.264 455.68c0 0.256-0.256 0.256-0.256 0.256s0.256 0.512 0.512 0.768c-0.256 0 0 0 0 0z m0 0z" fill="#040000" p-id="1202"></path><path d="M644.608 897.024h-61.44v-218.112c0-16.64-13.824-29.952-30.72-29.952H471.04c-16.896 0-30.72 13.568-30.72 29.952v218.112h-61.44v-218.112c0-50.432 41.216-91.392 92.16-91.392h81.408c50.944 0 92.16 40.96 92.16 91.392v218.112z" fill="#D63123" p-id="1203"></path></svg>
# 这是侧边栏的顺序
order: 1
# 设置作者
# 此页面会在文章列表置顶
sticky: true
# 此页面会出现在文章收藏中
star: true
# copyright: 无版权
---

## 1.0.4 更新内容

**2024 年 04 月 15 日**
[1.0.4 升级使用说明 ](/logDesc/1_0_4.md)

- 🪲🪲 修复 BUG🪲🪲

  - 优化前端内存占用过大的问题

- 👍👍 新增功能 👍👍

  - 前端整体重构
  - 支持 Restful API 工具
  - 引入百宝箱
  - 支持多项目切换

## V 1.0.3

**2024 年 01 月 13 日**

- 🪲🪲 修复 BUG🪲🪲

  - 优化用例编辑器卡顿问题
  - 修复 HTTP 不支持异步接口能问题
  - 自动生成的 ID 和人工输入的 ID 可能会产生重复@magic [#I8AZW1 ](https://gitee.com/dromara/TestHub/issues/I8AZW1)

- 👍👍 新增功能 👍👍
  - 优化 Formula 表达式日志展示。[使用说明](/principle/operand.md)
  - 基于 antlr4 重构 Formula 表达式
  - 支持操作 cookie [#I8I89D ](https://gitee.com/dromara/TestHub/issues/I8I89D)
  - 参数支持复制键入 [#I8B27J ](https://gitee.com/dromara/TestHub/issues/I8B27J)
  - 优化环境变量的使用 [#I8CEPE ](https://gitee.com/dromara/TestHub/issues/I8CEPE)

## V 1.0.2

**2023 年 10 月 27 日**

[1.0.2 升级使用说明 ](/logDesc/1_0_2.md)

- 🪲🪲 修复 BUG🪲🪲

  - 解决用例类目树超出不显示滚动条的问题
  - 修复无法退出登陆的问题
  - 修复 HTTP 超时问题

- 👍👍 新增功能 👍👍

  - sql 能力支持同时执行多条 sql
  - 实现简单级权限控制
  - 后端部分插件化改造
  - 提供组件库的功能
  - 提供页面配置行为
  - 支持配置多环境

## V 1.0.1

**2023 年 8 月 27 日**

- 🪲🪲 修复 BUG🪲🪲

  - 解决没有配置校验项提示语导致展示结果不对的问题
  - 解决文本太长超出父组件宽度的问题
  - 卸载入法自动保存个人文件

- 👍👍 新增功能 👍👍

  - 集成<a href="https://sa-token.cc/">sa-token</a> 实现权限登陆
  - 支持 pgsql 作为做数据库
  - http 支持设置超时时间
  - 新增版本升级记录

## V 1.0.0

**2023 年 7 月 31 日**
TestHub 正式开源 1.0.0 版本发布

同日加入<a align="center" target="_blank"  href="https://dromara.org/zh/">dromara 社区</a>，跪谢 🙇 社区一众大佬。

感谢 🙇 <a align="center" target="_blank"  href="https://wind.kim/">SMS4J</a> 作者 <a align="center" target="_blank"  href="https://gitee.com/MR-wind">风如歌</a>大佬的鼎力支持

## 内测

**2023 年 2 月 7 日**
TestHub 发布<a align="center" target="_blank"  href="https://www.crootway.com/">根网科技</a>内测初版

TestHub 测试平台是从 2022 年 10 月开始做的一个项目，期间经历了各种改版，结合根网科技的私有协议需求，又进行了更多的功能开发和完善，在此向感谢一下在 TestHub 的成长过程中给予支持的各位领导和一起付出努力和提出建议的各位同事。

首先感谢 TestHub 起步时给予大力支持的`项目组大佬刘培琦`，是您的支持才有了 TestHub 的诞生。

然后感谢 `部门总监黎伟` 给予的大力支持，是您的支持，才有了 TestHub 的第二次改版， 从一个简陋的小工具，到功能逐步完善，是您的强力推进落地，才有了平台在公司落地为研发团队赋能， 在 CTS 让 TestHub 真正的完善，成为一个可使用的功能完善的产品。

感谢根网 CTS 团队的小伙伴们一起对 TestHub 进行完善。
感谢使用 TestHub 的各位同事在使用过程中的不断反馈，平台也是在大家的使用和反馈中不断完善的。

<!-- <Badge text="数据校验" color="#242378" /> -->