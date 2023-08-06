---
title: 🏈 数据类型
order: 1
editLink: false
category:
  - 原理介绍
---

ThestHub 支持基础数据类型和复合数据类型 ,自定义对象可以采用 Meta 组件中的能力来扩展复杂的数据类型

## 基础数据类型

| 数据类型       | 标识        | 数据范围            |
| -------------- | ----------- | ------------------- |
| `数值`         | NUMBER      | 整数，小数          |
| `字符串`       | STRING      |                     |
| `布尔`         | BOLL        | true，false         |
| `年月日`       | TIME_YMD    | YYYY-MM-DD          |
| `时分秒`       | TIME_HMS    | hh:mm:ss            |
| `年月日时分秒` | TIME_YMDHMS | YYYY-MM-DD hh:mm:ss |

## 复合数据类型

编程语言中的数组、键值对、类、结构体均为复合数据类型 ,类我们通过<a href="#元对象">元对象</a>

复合数据类型提供了:`键值对`MAP、`列表`则通过 complex 标识 complex=N 标识变量为 N 维数组

例如 [1,2,3] complex 表示为 complex = 1

例如 [[1,2,3],[1,2,3]] complex 表示为 complex = 2

::: info complex 的默认值问题
complex 的默认值为 0
:::

<a name="元对象"></a>

## 元对象

ThestHub 提供了 Meta 组件 ── 元数据定义用于自定义数据类型

::: info Meta 组件是提供对元数据的管理
提示：参与执行过程的数据实体则可以抽象成 Meta 组件配置的内容  
 包括了 MetaClass 规则对象类、MetaProperty 对象属性项  
 例如在[如何测试下单接口](/ability/README.md#如何测试下单接口)可以将资金账号利用 meta 组件组件的配置进行表达
:::

```xml copy
<metaClasses>
    <metaClass code="account" name="资金账号">
        <properties>
            <property code="id" name="资金账号ID" dataType="NUMBER"/>
            <property code="usableAmt" name="可用金额" dataType="NUMBER"/>
        </properties>
    </metaClass>
</metaClasses>
```
