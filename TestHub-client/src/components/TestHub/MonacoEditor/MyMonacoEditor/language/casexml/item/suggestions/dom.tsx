export const dom_suggestions = [
  {
    label: 'rule',
    insertText: ['<rule code="" name=""  model="flow">', '\t', '</rule>'].join('\n'),
    detail: '用例',
  },
  {
    label: 'params',
    insertText: ['<params>', '\t<param code="" name="" dataType="STRING"/>', '</params>'].join(
      '\n',
    ),
    detail: '参数组',
  },
  {
    label: 'paramsMySql',
    insertText: [
      '<params>',
      '\t<param code="url" name="数据库地址" dataType="STRING" data="jdbc:mysql://"/>',
      '\t<param code="driver" name="驱动" dataType="STRING" data="org.postgresql.Driver"/>',
      '\t<param code="username" name="用户" dataType="STRING" data=""/>',
      '\t<param code="password" name="密码" dataType="STRING" data=""/>',
      '\t<param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>',
      '</params>',
    ].join('\n'),
    detail: '参数组',
  },
  {
    label: 'paramsPgSql',
    insertText: [
      '<params>',
      '\t<param code="url" name="数据库地址" dataType="STRING" data="jdbc:postgresql://"/>',
      '\t<param code="driver" name="驱动" dataType="STRING" data="org.postgresql.Driver"/>',
      '\t<param code="username" name="用户" dataType="STRING" data=""/>',
      '\t<param code="password" name="密码" dataType="STRING" data=""/>',
      '\t<param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>',
      '</params>',
    ].join('\n'),
    detail: '参数组',
  },
  {
    label: 'paramsOrecle',
    insertText: [
      '<params>',
      '\t<param code="url" name="数据库地址" dataType="STRING" data="jdbc:oracle:thin:@"/>',
      '\t<param code="driver" name="驱动" dataType="STRING" data="oracle.jdbc.driver.OracleDriver"/>',
      '\t<param code="username" name="用户" dataType="STRING" data=""/>',
      '\t<param code="password" name="密码" dataType="STRING" data=""/>',
      '\t<param code="timeout" name="超时时间" dataType="NUMBER" data="1000"/>',
      '</params>',
    ].join('\n'),
    detail: '参数组',
  },

  {
    label: 'paramsRedis',
    insertText: [
      '<params>',
      '\t<param code="redisIp" name="服务器" dataType="STRING" data=""/>',
      '\t<param code="redisPort" name="端口" dataType="NUMBER" data="6379"/>',
      '\t<param code="redisPassword" name="端口" dataType="STRING" data=""/>',
      '</params>',
    ].join('\n'),
    detail: '参数组',
  },
  {
    label: 'param',
    insertText: ['<param code="" name="" dataType="STRING"/>'].join('\n'),
    detail: '参数',
  },
  {
    label: 'actions',
    insertText: ['<actions>', '\t', '</actions>'].join('\n'),
    detail: '行为组',
  },
  {
    label: 'action',
    insertText: ['<action code="" name="" type="" dataType="MAP">', '\t', '</action>'].join('\n'),
    detail: '行为',
  },

  {
    label: 'actionSQL',
    insertText: [
      '<action code="" name="" type="SQL" dataType="MAP">',
      '\t<bound>',
      '\t\t',
      '\t</bound>',
      '</action>',
    ].join('\n'),
    detail: '行为',
  },
  {
    label: 'actionSQL_CALL',
    insertText: [
      '<action code="" name="" type="SQL_CALL" dataType="MAP">',
      '\t<bound>',
      '\t\t',
      '\t</bound>',
      '</action>',
    ].join('\n'),
    detail: '行为',
  },
  {
    label: 'actionJS',
    insertText: [
      '<action code="" name="" type="JS" dataType="MAP">',
      '\t<script>',
      `\t\tvar resultData = new ResultData();
\t\tresultData.runParams = new Object();
\t\tresultData.datas = new Array();
\t\t
\t\treturn resultData;`,
      '\t</script>',
      '</action>',
    ].join('\n'),
    detail: '行为',
  },
  {
    label: 'actionRedis',
    insertText: [
      '<action code="" name="" type="REDIS" dataType="MAP">',
      '\t<commands>',
      '\t\t<command code="" op="" key=""/>',
      '\t</commands>',
      '</action>',
    ].join('\n'),
    detail: '行为',
  },
  {
    label: 'actionCONST',
    insertText: [
      '<action code="" name="" type="CONST" dataType="MAP">',
      '\t<bound>',
      '\t\t',
      '\t</bound>',
      '</action>',
    ].join('\n'),
    detail: '行为',
  },
  {
    label: 'actionCONVERT',
    insertText: [
      '<action code="" name="" type="CONVERT" dataType="MAP">',
      '\t<params>',
      '\t\t<param code="source" name="源数据" dataType="MAP" />',
      '\t</params>',
      '\t<converts>',
      '\t\t<convert code=""  type="">',
      '\t</converts>',
      '</action>',
    ].join('\n'),
    detail: '行为',
  },
  {
    label: 'flow',
    insertText: [
      '<flow code="" name="">',
      '\t<execute code="" name="" actionCode="" />',
      '</flow>',
    ].join('\n'),
    detail: '流程',
  },
  {
    label: 'flows',
    insertText: [
      '<flows>',
      '\t<flow code="" name="">',
      '\t\t<execute code="" name="" actionCode="" />',
      '\t</flow>',
      '</flows>',
    ].join('\n'),
    detail: '流程组',
  },
  {
    label: 'execute',
    insertText: ['<execute code="" name="" actionCode="" />'].join('\n'),
    detail: '步骤',
  },
  {
    label: 'executeInject',
    insertText: [
      '<execute code="" name="" actionCode="" >',
      '\t<injects>',
      '\t\t<inject code="" data=""/>',
      '\t</injects>',
      '</execute>',
    ].join('\n'),
    detail: '步骤-传递',
  },
  {
    label: 'executeBEGIN',
    insertText: ['<execute code="" name="" actionCode="begin" conKey=""/>'].join('\n'),
    detail: '步骤-开启事物',
  },
  {
    label: 'executeCOMMIT',
    insertText: ['<execute code="" name="" actionCode="commit" conKey="" />'].join('\n'),
    detail: '步骤-提交事物',
  },
  {
    label: 'executeSLEEP',
    insertText: ['<execute code="" name="" actionCode="sleep" sleepTime="1000" />'].join('\n'),
    detail: '步骤-停顿',
  },

  {
    label: 'executeCHECK',
    insertText: [
      '<execute code="" name="" actionCode="check" >',
      '\t<checkItem code="check1" name=""  msg="">',
      '\t\t<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',
      '\t</checkItem>',
      '</execute>',
    ].join('\n'),
    detail: '步骤-检查',
  },
  {
    label: 'executeCHECKObj',
    insertText: [
      '<execute code="" name="" actionCode="checkObj" >',
      '\t<checkObj code="check1" name=""  msg="" cover="" threshold=""/>',
      '</execute>',
    ].join('\n'),
    detail: '步骤-对象比较',
  },
  {
    label: 'injects',
    insertText: ['<injects>', '\t<inject code="" data=""/>', '</injects>'].join('\n'),
    detail: '参数传递',
  },
  {
    label: 'checkItem',
    insertText: [
      '<checkItem code="" name=""  msg="">',
      '\t<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',
      '</checkItem>',
    ].join('\n'),
    detail: '检查项',
  },
  {
    label: 'checkObj',
    insertText: ['<checkObj code="" name=""  msg="" cover="" threshold=""/>'].join('\n'),
    detail: '对象检查项',
  },
  {
    label: 'expressionlogic',
    insertText: [
      '<expression expressionType ="logic" operationCode="and">',
      '\t<subExpressions>',
      '\t\t<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',
      '\t\t<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',
      '\t</subExpressions>',
      '</expression>',
    ].join('\n'),
    detail: '逻辑运算表达式',
  },
  {
    label: 'expressionrelation',
    insertText: [
      '<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',
    ].join('\n'),
    detail: '关系运算表达式',
  },
  {
    label: 'commands',
    insertText: ['<commands>', '\t<command code="" op="" key=""/>', '</commands>'].join('\n'),
    detail: '命令组',
  },
  {
    label: 'command',
    insertText: ['<command code="" op="" key=""/>'].join('\n'),
    detail: '命令',
  },
  {
    label: 'httpModelPOST',
    insertText: [
      '<httpModel url=""  method="post">',
      '\t<headers>',
      '\t\t<param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>',
      '\t</headers>',
      '\t<body type="raw" language="json">',
      '\t\t<content>',
      '\t\t</content>',
      '\t</body>',
      '</httpModel>',
    ].join('\n'),
    detail: '请求设置POST',
  },
  {
    label: 'httpModelGET',
    insertText: [
      '<httpModel url=""  method="post">',
      '\t<headers>',
      '\t\t<param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>',
      '\t</headers>',
      '\t<rests>',
      '\t\t<param code="" dataType="STRING" data=""/>',
      '\t</rests>',
      '</httpModel>',
    ].join('\n'),
    detail: '请求设置GET',
  },
  {
    label: 'httpModel',
    insertText: [
      '<httpModel url=""  method="post">',
      '\t<headers>',
      '\t\t<param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>',
      '\t</headers>',
      '\t<rests>',
      '\t\t<param code="" dataType="STRING" data=""/>',
      '\t</rests>',
      '\t<body type="row" language="json">',
      '\t\t<content>',
      '\t\t</content>',
      '\t</body>',
      '</httpModel>',
    ].join('\n'),
    detail: '请求设置',
  },
];
