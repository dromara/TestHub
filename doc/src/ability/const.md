---
title: 🍌 const 定义常量
order: 20
author: 失败女神
editLink: false
category:
  - 能力
---

## CONST

在自动化测试中，某些测试用例需要使用固定的数据。为了实现这一需求，我们需要引入定义常量的能力。通过定义常量，我们可以将固定的数据值赋予特定的常量，并在测试过程中使用这些常量来进行数据对比。这种方法使得测试用例的数据验证更加灵活、可维护性更高，确保了测试的一致性和准确性。

**常量返回数据说明**

<img class="heardImg" src="/demo/const.png">

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_CONST" name="定义常量" model="flow">
    <actions>
        <action code="getMap" name="常量MAP" type="CONST" dataType="MAP">
            <bound>
                {"id":2,"orderCode":"123","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc"},{"code":"0002","name":"崔胜利"}]}
            </bound>
        </action>
        <action code="getString" name="常量字符串" type="CONST" dataType="STRING">
            <bound>
                {"id":2,"orderCode":"123","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc"},{"code":"0002","name":"崔胜利"}]}
            </bound>
        </action>
        <action code="getNumber" name="常量数组" type="CONST" dataType="NUMBER">
            <bound>
                123
            </bound>
        </action>
        <action code="getList" name="常量列表" type="CONST" complex="1" dataType="MAP">
            <bound>
                [{"id":1,"orderCode":"123","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc"},{"code":"0002","name":"崔胜利"}]},{"id":2,"orderCode":"123","orderQur":123,"stkId":"123","users":[{"code":"0001","name":"vinc"},{"code":"0002","name":"崔胜利"}]}]
            </bound>
        </action>
    </actions>
    <flows>
        <flow code="RU001G1">
            <execute code="stp1" name="获取常量对象" actionCode="getMap"/>
            <execute code="stp2" name="获取常量列表" actionCode="getList"/>
            <execute code="stp5" name="对比两个对象" actionCode="checkObj">
                <checkObj code="check1" name="判断对象" msg="对象不一样" cover="${stp1}" threshold="${stp2[1]}"/>
            </execute>
            <execute code="stp6" name="获取常量字符串" actionCode="getString"/>
            <execute code="stp7" name="对比两个对象" actionCode="checkObj">
                <checkObj code="check1" name="判断对象" msg="对象不一样" cover="${stp6}" threshold="${stp6}"/>
            </execute>
            <execute code="stp8" name="获取常量数字" actionCode="getNumber"/>
        </flow>
    </flows>
</rule>
```
