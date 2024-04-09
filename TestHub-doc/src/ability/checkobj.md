---
title: 🍊 checkobj 对象对比
order: 2
author: 失败女神
editLink: false
category:
  - 能力
---

## CHECK_OBJ

对象对比的功能在自动化测试中支持多种数据格式，包括 XML、文本（Text）、HTML 和 JSON。这意味着您可以使用 checkObj 来比较和验证这些不同格式的数据对象。它提供了灵活的方式来检查和核实数据对象在不同格式中的一致性，以确保测试结果的准确性和可靠性。无论您处理的是 XML 文档、纯文本、HTML 页面还是 JSON checkObj 都可以帮助您轻松地进行对象值的比较和验证。

::: tip 特殊提示

checkObj 已经是全局的配置，不需要单独定义 action

:::

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_CheckObj" name="对象比较" model="flow">
    <actions>
        <action code="getMap" name="常量MAP" type="CONST" dataType="MAP">
            <bound>
                {"id":2,"orderCode":"123","orderQur":123,"stkId":"123","users":{"code":"0001","name":"vinc"}}
            </bound>
        </action>
        <action code="getMap2" name="常量MAP" type="CONST" dataType="MAP">
            <bound>
                {"id":2,"orderCode":"123","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc"},{"code":"0002","name":"崔胜利"}]}
            </bound>
        </action>
    </actions>
    <flows>
        <flow code="RU001G1">
            <execute code="stp1" name="常量MAP" actionCode="getMap"/>
            <execute code="stp2" name="常量MAP2" actionCode="getMap2"/>
            <execute code="stp3" name="对比两个对象" actionCode="checkObj">
                <checkObj code="check1" name="判断对象" msg="对象不一样" cover="${stp1}" threshold="${stp2}"/>
            </execute>
            <execute code="stp4" name="对比两个对象" actionCode="checkObj">
                <checkObj code="check1" name="判断对象" msg="对象不一样" cover="${stp1}" threshold="${stp1}"/>
            </execute>
        </flow>
    </flows>
</rule>
```

<img class="heardImg" src="/demo/check_obj.png">
