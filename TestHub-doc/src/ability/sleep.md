---
title: 🍉 sleep 暂停休息
order: 90
editLink: false
category:
  - 能力
---

## SLEEP

在自动化测试中，有些接口需要进行异步落库操作。为了确保测试程序在进行后续步骤之前等待足够的时间，我们需要添加一个延时机制。这样，测试程序将在适当的时间间隔后继续执行，以便正确处理异步落库操作。通过这个延时机制，我们可以确保测试的准确性和可靠性。

::: tip 特殊提示

sleep 已经是全局的配置，不需要单独定义 action

:::

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_Sleep" name="sleep休息X秒" model="flow">
    <flows>
        <flow code="RU001G1" name="sleep休息1秒">
            <execute code="stp6" name="休息1秒" actionCode="sleep" sleepTime="1000"/>
        </flow>
    </flows>
</rule>

```

<img class="heardImg" src="/demo/sleep.png">
