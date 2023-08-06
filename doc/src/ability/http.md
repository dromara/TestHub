---
title: 🍒 http 发送请求
order: 40
author: 失败女神
editLink: false
category:
  - 能力
---

## HTTP

在自动化测试中，必须具备发送 HTTP 请求等能力。这种能力允许测试程序与服务端进行通信，模拟用户行为，验证系统的各种接口和功能。通过发送 HTTP 请求，我们可以模拟 GET、POST、PUT 等操作，检查响应状态码、验证返回数据，并对系统进行全面的自动化测试。支持的返回值类型包括 XML、HTML、Text 和 JSON，同时也支持多种请求体的发送和处理。这种强大的功能使得测试人员能够对系统行为进行深入分析、灵活验证，并确保系统的稳定性和可靠性。

<div style="display: flex;">
  <div style="flex: 1;">
    
| 请求方式 | 支持 |
| -------- | ---- |
| POST     | ✅   |
| GET      | ✅   |
| PUT      | ✅   |
| HEAD     |      |
| DELETE   | ✅   |
| PATCH    |      |
| OPTIONS  |      |

  </div>
  <div style="flex: 1;">

| 请求体                | 支持 |
| --------------------- | ---- |
| row-json              | ✅   |
| row-xml               | ✅   |
| row-text              | ✅   |
| row-html              |      |
| none                  | ✅   |
| x-www-form-urlencoded | ✅   |
| form-data             |      |

  </div>
</div>

<div style="display: flex; justify-content: center; align-items: center;">

</div>

```xml copy
<?xml version="1.0" encoding="UTF-8"?>
<rule code="DEMO_HTTP" name="HTTP测试" model="flow">
    <actions>
        <action code="order" name="下单" type="HTTP" dataType="map">
            <httpModel url="http://192.168.0.4:12004/order" method="post">
                <headers>
                    <param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>
                </headers>
                <body type="row" language="json">
                    <bound>
                        {
                        "acctId": "960307",
                        "orderPrice": "1.8",
                        "orderQty": "100"
                        }
                    </bound>
                </body>
            </httpModel>
        </action>
        <action code="orderXml" name="下单" type="HTTP" dataType="map">
            <httpModel url="http://192.168.0.4:12004/getOrdersXml" method="post">
                <body type="row" language="xml">
                    <bound> <![CDATA[<acctId>960703</acctId>
                        <orderPrice>1.8</orderPrice>
                        <orderQty>100</orderQty>
                        ]]> </bound>
                </body>
            </httpModel>
        </action>
        <action code="orderText" name="下单" type="HTTP" dataType="map">
            <httpModel url="http://192.168.0.4:12004/getOrdersText" method="post">
                <body type="row" language="text">
                    <bound>
                       vinc
                    </bound>
                </body>
            </httpModel>
        </action>
        <action code="getOrders" name="订单列表" type="HTTP" dataType="map">
            <httpModel url="http://192.168.0.4:12004/getOrders" method="get">
                <headers>
                    <param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>
                    <param code="Accept" dataType="STRING" data="application/json"/>
                </headers>
            </httpModel>
        </action>
        <action code="getOrder" name="获取订单" type="HTTP" dataType="map">
            <httpModel url="http://192.168.0.4:12004/getOrder/{id}" method="get">
                <rests>
                    <param code="id" dataType="STRING" data="18"/>
                </rests>
                <params>
                    <param code="orderCode" dataType="STRING" data="28bcff20b0"/>
                </params>
            </httpModel>
        </action>
        <action code="orderForm" name="form创建订单" type="HTTP" dataType="map">
            <httpModel url="http://192.168.0.4:12004/orderForm" method="post">
                <body type="x-www-form-urlencoded">
                    <param code="acctId" dataType="STRING" data="960703"/>
                    <param code="orderPrice" dataType="NUMBER" data="1.8"/>
                    <param code="orderQty" dataType="NUMBER" data="100"/>
                </body>
            </httpModel>
        </action>
        <action code="reHtml" name="获取HTML" type="HTTP" dataType="map">
            <httpModel url="http://www.baidu.com" method="get"/>
        </action>
    </actions>
    <flows>
        <flow code="RU001G1">
              <execute code="stp10" name="请求 post row json" actionCode="order">
                <expression expressionType="relation" operationCode="eq" dataType="STRING" cover="${data.code}" threshold="200"/>
            </execute>
            <execute code="stp11" name="请求 post row xml" actionCode="orderXml"/>
            <execute code="stp12" name="请求 post row text" actionCode="orderText"/>
            <execute code="stp21" name="请求 post x-www-form-urlencoded" actionCode="orderForm"/>
            <!-- <execute code="stp22" name="请求 post form-data" actionCode="orderForm"/> -->
            <execute code="stp31" name="请求 get none" actionCode="getOrders"/>
            <execute code="stp32" name="请求 get rests params" actionCode="getOrder"/>
            <execute code="stp44" name="响应 HTML" actionCode="reHtml"/>
        </flow>
    </flows>
</rule>

```

<img class="heardImg" src="/demo/http.png">
