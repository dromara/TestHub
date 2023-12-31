---
title: 🐶 扩展组件
order: 20
editLink: false
category:
  - 用例编写
---

## convert 转换

convert 的作用配置你的数据转换规则，目前仅用于 [convert ](convert.md)数据转换处理中

| 参数 | 名称         | 唯一 | 必填       | 可选值                                  | 说明                                                   |
| ---- | ------------ | ---- | ---------- | --------------------------------------- | ------------------------------------------------------ |
| code | 操作的 key   |      |            |                                         | 填写规则参考[特殊说明](../ability/convert.md#特殊说明) |
| type | 命令         |      | 是         | DEL 删除、PUT 设置                      |                                                        |
| data | 被操作的数据 |      | PUT 的必填 | [formula 表达式](/principle/operand.md) |                                                        |

<a name="转换组"></a>

<h3>转换组</h3>

转换组就是这样的一个结构

```xml copy
<converts>
    <convert></convert >
</converts>
```

<a name="checkItem检查项"></a>

## checkItem 检查项

| 参数   | 名称     | 唯一 | 必填 | 可选值                                  | 说明                   |
| ------ | -------- | ---- | ---- | --------------------------------------- | ---------------------- |
| code   | 断言编码 | 是   | 是   |                                         | ==组件内必须唯一==     |
| name   | 断言名称 |      |      |                                         | 执行步骤组件内建议唯一 |
| msg    | 错误提示 | 是   |      |                                         |                        |
| repeat | 循环次数 | 否   |      | [formula 表达式](/principle/operand.md) |                        |

<h3>子组件</h3>

| 组件                                                                               | 名称     | 必选 |
| ---------------------------------------------------------------------------------- | -------- | ---- |
| <a href="#expression运算表达式"></a>[操作符](/custom/base.md#expression运算表达式) | 检查规则 | 是   |

<a name="checkObj检查对象"></a>

## checkObj 检查对象

| 参数      | 名称     | 唯一 | 必填                                    | 可选值 | 说明                   |
| --------- | -------- | ---- | --------------------------------------- | ------ | ---------------------- |
| code      | 断言编码 | 是   | 是                                      |        | ==组件内必须唯一==     |
| name      | 断言名称 |      |                                         |        | 执行步骤组件内建议唯一 |
| msg       | 错误提示 | 是   |                                         |        |                        |
| cover     | 被阈值   | 是   | [formula 表达式](/principle/operand.md) |        |                        |
| threshold | 阈值     | 是   | [formula 表达式](/principle/operand.md) |        |                        |

<!--
## command 命令

command 的作用配置你的 redis 命令，目前仅用于<a href="/10如何使用-可以干什么#REDIS">REDIS</a> 操作 redis 中

| 参数    | 名称         | 唯一     | 必填                    | 可选值                                    | 说明 |
| ------- | ------------ | -------- | ----------------------- | ----------------------------------------- | ---- |
| code    | 操作编码     | 建议唯一 | 不填写则默认为 key 的值 |                                           |      |
| op      | 命令         |          | 参考 redis 命令         | 目前支持的命令列表                        |      |
| key     | 键           |          | 参考 redis 命令         |                                           |      |
| field   | field 参数   |          | 参考 redis 命令         |                                           |      |
| index   | 索引         |          |                         | 参考 redis 命令                           |      |
| data    | 被操作的数据 |          | 参考 redis 命令         | <a href="#formula基础">formula 表达式</a> |      |
| convert | 后置处理     |          |                         |                                           |      |

<a name="命令组"></a>

<h3>命令组</h3>

命令组就是这样的一个结构

```xml copy
<commands>
    <command></command >
</commands>
``` -->
