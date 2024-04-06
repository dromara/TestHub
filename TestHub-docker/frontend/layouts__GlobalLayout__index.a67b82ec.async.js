"use strict";(self.webpackChunkTestHub=self.webpackChunkTestHub||[]).push([[577],{1513:function(Ke,U,a){a.r(U),a.d(U,{default:function(){return Ve}});var W=a(97857),p=a.n(W),$=a(5574),j=a.n($),f=a(67294),b=a(59680),X=a(28459),P=a(14726),_=a(18253),q=a(82925),w=a(40075),ee=a(39825),ne=a(9783),v=a.n(ne),M=a(9361),c=a(54377),Q={fontSize:14,wireframe:!0,borderRadius:4,borderRadiusLG:8},te=v()(v()(v()(v()(v()(v()(v()(v()({},c.gC.Polar_Green,{colorPrimary:"#3c8618"}),c.gC.Golden_Purple,{colorPrimary:"#8276c9"}),c.gC.Polar_Blue,{colorPrimary:"#1677ff"}),c.gC.Silver,{colorPrimary:"#c3b7a4"}),c.gC.Red,{colorPrimary:"#fd6874"}),c.gC.Orange,{colorPrimary:"#ffa940"}),c.gC.Blue2,{colorPrimary:"#009cc7"}),c.gC.Gold,{colorPrimary:"#b59a6d"}),ae={algorithm:[M.Z.darkAlgorithm,M.Z.compactAlgorithm],customName:"dark",antdPrimaryColor:te,token:p()(p()({},Q),{},{colorTextBase:"#f1f1f4",colorBgBase:"#1c2128",colorHoverBg:"hsla(0, 0%, 100%, 0.03)",colorBgContainer:"#1c2128",colorBgSubtle:"#27282e",colorBgSecondary:"#232429",colorBgElevated:"#1c2128",colorBorder:"#373e4766",colorBorderSecondary:"#373e4766",controlItemBgActive:"#f1f1f414",colorBgSpotlight:"#536176",caseResultSuccessBg:"#2D5D36",caseResultSuccessBorder:"#2D5D36",caseResultErrorBg:"#802D2D",caseResultErrorBorder:"#802D2D"})},oe=ae,le=v()(v()(v()(v()(v()(v()(v()(v()({},c.gC.Polar_Green,{colorPrimary:"#039e74"}),c.gC.Golden_Purple,{colorPrimary:"#9373ee"}),c.gC.Polar_Blue,{colorPrimary:"#587df1"}),c.gC.Silver,{colorPrimary:"#8e8374"}),c.gC.Red,{colorPrimary:"#fd6874"}),c.gC.Orange,{colorPrimary:"#fa8c16"}),c.gC.Blue2,{colorPrimary:"#00c3ee"}),c.gC.Gold,{colorPrimary:"#9a7d56"}),ie={algorithm:[M.Z.defaultAlgorithm,M.Z.compactAlgorithm],customName:"light",antdPrimaryColor:le,token:p()(p()({},Q),{},{colorTextBase:"#232429",colorBgBase:"#ffffff",colorHoverBg:"rgba(0, 0, 0, 0.03)",colorBgContainer:"#ffffff",colorBgSubtle:"#f3f5f6",colorBgSecondary:"#fafafa",colorBgElevated:"#ffffff",colorBorder:"rgba(211, 211, 212, 0.4)",colorBorderSecondary:"rgba(211, 211, 212, 0.4)",colorBgSpotlight:"rgba(181, 181, 181, 1)",caseResultSuccessBg:"#f6ffed",caseResultSuccessBorder:"#b7eb8f",caseResultErrorBg:"#fff2f0",caseResultErrorBorder:"#f1bdb4"})},re=ie,se=a(96486),ue=a.n(se),ce=v()(v()({},c.f8.Dark,oe),c.f8.Light,re);function de(s){var i=ue().cloneDeep(ce[s.backgroundColor]);i.token=p()(p()({},i.token),i.antdPrimaryColor[s.primaryColor]||{});var u=M.Z.getDesignToken(i);return ve(u,s.backgroundColor,s.primaryColor),i}function ve(s,i,u){var l="";Object.keys(s).map(function(r){var o=me(r),e=s[r],y=["fontSize","borderRadius","borderRadiusLG"];y.includes(r)&&(e=e+"px"),l=l+"--".concat(o,": ").concat(e,`;
`)});var m="html[theme='".concat(i,"'],html[primary-color='").concat(u,`']{
    `).concat(l,`
  }`),t=document.createElement("style");t.type="text/css",t.appendChild(document.createTextNode(m)),document.head.appendChild(t),window._AppThemePack=s}function me(s){return s.replace(/([A-Z])/g,"-$1").toLowerCase()}var k=a(19515),F=a(2453),fe=function(){F.ZP.config({maxCount:1,duration:3})},J=a(16568),Te=function(){J.ZP.config({placement:"BottomRight",maxCount:2,duration:null})},Y=a(46773),pe=a(64599),je=a.n(pe),E=a(2302),G=[{label:"rule",insertText:['<rule code="" name=""  model="flow">',"	","</rule>"].join(`
`),detail:"\u7528\u4F8B"},{label:"params",insertText:["<params>",'	<param code="" name="" dataType="STRING"/>',"</params>"].join(`
`),detail:"\u53C2\u6570\u7EC4"},{label:"paramsMySql",insertText:["<params>",'	<param code="url" name="\u6570\u636E\u5E93\u5730\u5740" dataType="STRING" data="jdbc:mysql://"/>','	<param code="driver" name="\u9A71\u52A8" dataType="STRING" data="org.postgresql.Driver"/>','	<param code="username" name="\u7528\u6237" dataType="STRING" data=""/>','	<param code="password" name="\u5BC6\u7801" dataType="STRING" data=""/>','	<param code="timeout" name="\u8D85\u65F6\u65F6\u95F4" dataType="NUMBER" data="1000"/>',"</params>"].join(`
`),detail:"\u53C2\u6570\u7EC4"},{label:"paramsPgSql",insertText:["<params>",'	<param code="url" name="\u6570\u636E\u5E93\u5730\u5740" dataType="STRING" data="jdbc:postgresql://"/>','	<param code="driver" name="\u9A71\u52A8" dataType="STRING" data="org.postgresql.Driver"/>','	<param code="username" name="\u7528\u6237" dataType="STRING" data=""/>','	<param code="password" name="\u5BC6\u7801" dataType="STRING" data=""/>','	<param code="timeout" name="\u8D85\u65F6\u65F6\u95F4" dataType="NUMBER" data="1000"/>',"</params>"].join(`
`),detail:"\u53C2\u6570\u7EC4"},{label:"paramsOrecle",insertText:["<params>",'	<param code="url" name="\u6570\u636E\u5E93\u5730\u5740" dataType="STRING" data="jdbc:oracle:thin:@"/>','	<param code="driver" name="\u9A71\u52A8" dataType="STRING" data="oracle.jdbc.driver.OracleDriver"/>','	<param code="username" name="\u7528\u6237" dataType="STRING" data=""/>','	<param code="password" name="\u5BC6\u7801" dataType="STRING" data=""/>','	<param code="timeout" name="\u8D85\u65F6\u65F6\u95F4" dataType="NUMBER" data="1000"/>',"</params>"].join(`
`),detail:"\u53C2\u6570\u7EC4"},{label:"paramsRedis",insertText:["<params>",'	<param code="redisIp" name="\u670D\u52A1\u5668" dataType="STRING" data=""/>','	<param code="redisPort" name="\u7AEF\u53E3" dataType="NUMBER" data="6379"/>','	<param code="redisPassword" name="\u7AEF\u53E3" dataType="STRING" data=""/>',"</params>"].join(`
`),detail:"\u53C2\u6570\u7EC4"},{label:"param",insertText:['<param code="" name="" dataType="STRING"/>'].join(`
`),detail:"\u53C2\u6570"},{label:"actions",insertText:["<actions>","	","</actions>"].join(`
`),detail:"\u884C\u4E3A\u7EC4"},{label:"action",insertText:['<action code="" name="" type="" dataType="MAP">',"	","</action>"].join(`
`),detail:"\u884C\u4E3A"},{label:"actionSQL",insertText:['<action code="" name="" type="SQL" dataType="MAP">',"	<bound>","		","	</bound>","</action>"].join(`
`),detail:"\u884C\u4E3A"},{label:"actionSQL_CALL",insertText:['<action code="" name="" type="SQL_CALL" dataType="MAP">',"	<bound>","		","	</bound>","</action>"].join(`
`),detail:"\u884C\u4E3A"},{label:"actionJS",insertText:['<action code="" name="" type="JS" dataType="MAP">',"	<script>",`		var resultData = new ResultData();
		resultData.runParams = new Object();
		resultData.datas = new Array();
		
		return resultData;`,"	<\/script>","</action>"].join(`
`),detail:"\u884C\u4E3A"},{label:"actionRedis",insertText:['<action code="" name="" type="REDIS" dataType="MAP">',"	<commands>",'		<command code="" op="" key=""/>',"	</commands>","</action>"].join(`
`),detail:"\u884C\u4E3A"},{label:"actionCONST",insertText:['<action code="" name="" type="CONST" dataType="MAP">',"	<bound>","		","	</bound>","</action>"].join(`
`),detail:"\u884C\u4E3A"},{label:"actionCONVERT",insertText:['<action code="" name="" type="CONVERT" dataType="MAP">',"	<params>",'		<param code="source" name="\u6E90\u6570\u636E" dataType="MAP" />',"	</params>","	<converts>",'		<convert code=""  type="">',"	</converts>","</action>"].join(`
`),detail:"\u884C\u4E3A"},{label:"flow",insertText:['<flow code="" name="">','	<execute code="" name="" actionCode="" />',"</flow>"].join(`
`),detail:"\u6D41\u7A0B"},{label:"flows",insertText:["<flows>",'	<flow code="" name="">','		<execute code="" name="" actionCode="" />',"	</flow>","</flows>"].join(`
`),detail:"\u6D41\u7A0B\u7EC4"},{label:"execute",insertText:['<execute code="" name="" actionCode="" />'].join(`
`),detail:"\u6B65\u9AA4"},{label:"executeInject",insertText:['<execute code="" name="" actionCode="" >',"	<injects>",'		<inject code="" data=""/>',"	</injects>","</execute>"].join(`
`),detail:"\u6B65\u9AA4-\u4F20\u9012"},{label:"executeBEGIN",insertText:['<execute code="" name="" actionCode="begin" conKey=""/>'].join(`
`),detail:"\u6B65\u9AA4-\u5F00\u542F\u4E8B\u7269"},{label:"executeCOMMIT",insertText:['<execute code="" name="" actionCode="commit" conKey="" />'].join(`
`),detail:"\u6B65\u9AA4-\u63D0\u4EA4\u4E8B\u7269"},{label:"executeSLEEP",insertText:['<execute code="" name="" actionCode="sleep" sleepTime="1000" />'].join(`
`),detail:"\u6B65\u9AA4-\u505C\u987F"},{label:"executeCHECK",insertText:['<execute code="" name="" actionCode="check" >','	<checkItem code="check1" name=""  msg="">','		<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',"	</checkItem>","</execute>"].join(`
`),detail:"\u6B65\u9AA4-\u68C0\u67E5"},{label:"executeCHECKObj",insertText:['<execute code="" name="" actionCode="checkObj" >','	<checkObj code="check1" name=""  msg="" cover="" threshold=""/>',"</execute>"].join(`
`),detail:"\u6B65\u9AA4-\u5BF9\u8C61\u6BD4\u8F83"},{label:"injects",insertText:["<injects>",'	<inject code="" data=""/>',"</injects>"].join(`
`),detail:"\u53C2\u6570\u4F20\u9012"},{label:"checkItem",insertText:['<checkItem code="" name=""  msg="">','	<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',"</checkItem>"].join(`
`),detail:"\u68C0\u67E5\u9879"},{label:"checkObj",insertText:['<checkObj code="" name=""  msg="" cover="" threshold=""/>'].join(`
`),detail:"\u5BF9\u8C61\u68C0\u67E5\u9879"},{label:"expressionlogic",insertText:['<expression expressionType ="logic" operationCode="and">',"	<subExpressions>",'		<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />','		<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />',"	</subExpressions>","</expression>"].join(`
`),detail:"\u903B\u8F91\u8FD0\u7B97\u8868\u8FBE\u5F0F"},{label:"expressionrelation",insertText:['<expression expressionType ="relation" operationCode="eq" dataType="STRING" cover="" threshold="" />'].join(`
`),detail:"\u5173\u7CFB\u8FD0\u7B97\u8868\u8FBE\u5F0F"},{label:"commands",insertText:["<commands>",'	<command code="" op="" key=""/>',"</commands>"].join(`
`),detail:"\u547D\u4EE4\u7EC4"},{label:"command",insertText:['<command code="" op="" key=""/>'].join(`
`),detail:"\u547D\u4EE4"},{label:"httpModelPOST",insertText:['<httpModel url=""  method="post">',"	<headers>",'		<param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>',"	</headers>",'	<body type="raw" language="json">',"		<content>","		</content>","	</body>","</httpModel>"].join(`
`),detail:"\u8BF7\u6C42\u8BBE\u7F6EPOST"},{label:"httpModelGET",insertText:['<httpModel url=""  method="post">',"	<headers>",'		<param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>',"	</headers>","	<rests>",'		<param code="" dataType="STRING" data=""/>',"	</rests>","</httpModel>"].join(`
`),detail:"\u8BF7\u6C42\u8BBE\u7F6EGET"},{label:"httpModel",insertText:['<httpModel url=""  method="post">',"	<headers>",'		<param code="Content-Type" dataType="STRING" data="application/json;charset=utf-8"/>',"	</headers>","	<rests>",'		<param code="" dataType="STRING" data=""/>',"	</rests>",'	<body type="row" language="json">',"		<content>","		</content>","	</body>","</httpModel>"].join(`
`),detail:"\u8BF7\u6C42\u8BBE\u7F6E"}],x=function(i,u){var l=0,m=je()(i),t;try{for(m.s();!(t=m.n()).done;){var r=t.value;r==u&&l++}}catch(o){m.e(o)}finally{m.f()}return l},xe=function(){E.Fd.languages.register({id:"casexml"}),E.Fd.languages.registerCompletionItemProvider("casexml",{provideCompletionItems:function(u,l){var m=u.getValueInRange({startLineNumber:l.lineNumber,startColumn:1,endLineNumber:l.lineNumber,endColumn:l.column}),t=m.trim(),r=G;if(t.length>0){var o=t.substring(t.lastIndexOf(" ")+1,t.lastIndexOf("=")),e=t.substring(1,t.indexOf(" "));if(e!="<"){if(e=="rule")return x(t.substring(t.lastIndexOf("=")+1),'"')==1?{suggestions:[{label:"",insertText:"",detail:""}]}:{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u7F16\u7801"},{label:"name",insertText:['name=""'].join(`
`),detail:"\u540D\u79F0"},{label:"model",insertText:['model="flow"'].join(`
`),detail:"\u6A21\u5F0F"}]};if(e=="param")return x(t.substring(t.lastIndexOf("=")+1),'"')==1&&o=="dataType"?{suggestions:[{label:"NUMBER",insertText:["NUMBER"].join(`
`),detail:"\u6570\u503C"},{label:"STRING",insertText:["STRING"].join(`
`),detail:"\u5B57\u7B26\u4E32"},{label:"BOLL",insertText:["BOLL"].join(`
`),detail:"\u5E03\u5C14"},{label:"MAP",insertText:["MAP"].join(`
`),detail:"\u952E\u503C\u5BF9"},{label:"TIME_YMD",insertText:["TIME_YMD"].join(`
`),detail:"\u5E74\u6708\u65E5"},{label:"TIME_HMS",insertText:["TIME_HMS"].join(`
`),detail:"\u65F6\u5206\u79D2"},{label:"TIME_YMDHMS",insertText:["TIME_YMDHMS"].join(`
`),detail:"\u5E74\u6708\u65E5\u65F6\u5206\u79D2"}]}:{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u7F16\u7801"},{label:"name",insertText:['name=""'].join(`
`),detail:"\u540D\u79F0"},{label:"dataType",insertText:['dataType=""'].join(`
`),detail:"\u6570\u636E\u7C7B\u578B"},{label:"data",insertText:['data=""'].join(`
`),detail:"\u9ED8\u8BA4\u503C"},{label:"complex",insertText:['complex="1"'].join(`
`),detail:"\u7EF4\u5EA6"},{label:"necessary",insertText:['necessary="true"'].join(`
`),detail:"\u662F\u5426\u5FC5\u586B"}]};if(e=="action"){if(x(t.substring(t.lastIndexOf("=")+1),'"')==1){if(o=="type")return{suggestions:[{label:"CONST",insertText:["CONST"].join(`
`),detail:"\u5E38\u91CF"},{label:"CONVERT",insertText:["CONVERT"].join(`
`),detail:"\u6570\u636E\u5904\u7406"},{label:"MAP",insertText:["MAP"].join(`
`),detail:"\u952E\u503C\u5BF9"},{label:"JS",insertText:["JS"].join(`
`),detail:"Js\u811A\u672C"},{label:"SQL",insertText:["SQL"].join(`
`),detail:"SQL\u8BED\u53E5"},{label:"SQL_CALL",insertText:["SQL_CALL"].join(`
`),detail:"SQL\u5B58\u50A8\u8FC7\u7A0B"}]};if(o=="dataType")return{suggestions:[{label:"NUMBER",insertText:["NUMBER"].join(`
`),detail:"\u6570\u503C"},{label:"STRING",insertText:["STRING"].join(`
`),detail:"\u5B57\u7B26\u4E32"},{label:"BOLL",insertText:["BOLL"].join(`
`),detail:"\u5E03\u5C14"},{label:"MAP",insertText:["MAP"].join(`
`),detail:"\u952E\u503C\u5BF9"},{label:"TIME_YMD",insertText:["TIME_YMD"].join(`
`),detail:"\u5E74\u6708\u65E5"},{label:"TIME_HMS",insertText:["TIME_HMS"].join(`
`),detail:"\u65F6\u5206\u79D2"},{label:"TIME_YMDHMS",insertText:["TIME_YMDHMS"].join(`
`),detail:"\u5E74\u6708\u65E5\u65F6\u5206\u79D2"}]}}return{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u7F16\u7801"},{label:"name",insertText:['name=""'].join(`
`),detail:"\u540D\u79F0"},{label:"dataType",insertText:['dataType=""'].join(`
`),detail:"\u8FD4\u56DE\u503C\u7C7B\u578B"},{label:"complex",insertText:['complex="1"'].join(`
`),detail:"\u8FD4\u56DE\u503C\u7EF4\u5EA6"}]}}else{if(e=="flow")return x(t.substring(t.lastIndexOf("=")+1),'"')==1?{suggestions:[{label:"",insertText:"",detail:""}]}:{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u7F16\u7801"},{label:"name",insertText:['name=""'].join(`
`),detail:"\u540D\u79F0"}]};if(e=="execute")return x(t.substring(t.lastIndexOf("=")+1),'"')!=2?{suggestions:[{label:"",insertText:"",detail:""}]}:{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u7F16\u7801"},{label:"name",insertText:['name=""'].join(`
`),detail:"\u540D\u79F0"},{label:"actionCode",insertText:['actionCode=""'].join(`
`),detail:"\u884C\u4E3A\u7F16\u7801"},{label:"block",insertText:['block="true"'].join(`
`),detail:"\u662F\u5426\u5F02\u5E38\u963B\u65AD"},{label:"conKey",insertText:['conKey=""'].join(`
`),detail:"\u4E8B\u52A1ID"},{label:"sleepTime",insertText:['sleepTime="1000"'].join(`
`),detail:"\u505C\u987F\u65F6\u95F4"},{label:"init",insertText:['init="false"'].join(`
`),detail:"\u662F\u5426\u63A5\u53D7\u8FD4\u56DE\u503C"}]};if(e=="convert")return x(t.substring(t.lastIndexOf("=")+1),'"')==1&&o=="type"?{suggestions:[{label:"DEL",insertText:["DEL"].join(`
`),detail:"\u5220\u9664"},{label:"PUT",insertText:["PUT"].join(`
`),detail:"\u8BBE\u7F6E"}]}:{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u5C5E\u6027"},{label:"name",insertText:['name=""'].join(`
`),detail:"\u540D\u79F0"},{label:"type",insertText:['type=""'].join(`
`),detail:"\u7C7B\u578B"},{label:"data",insertText:['data=""'].join(`
`),detail:"\u6570\u636E"}]};if(e=="command")return x(t.substring(t.lastIndexOf("=")+1),'"')==1&&o=="op"?{suggestions:[{label:"del",insertText:["del"].join(`
`),detail:"\u8BE5\u547D\u4EE4\u7528\u4E8E\u5728 key \u5B58\u5728\u65F6\u5220\u9664 key"},{label:"get",insertText:["get"].join(`
`),detail:"\u83B7\u53D6\u6307\u5B9A key \u7684\u503C"},{label:"hget",insertText:["hget"].join(`
`),detail:"\u83B7\u53D6\u5B58\u50A8\u5728\u54C8\u5E0C\u8868\u4E2D\u6307\u5B9A\u5B57\u6BB5\u7684\u503C"},{label:"hgetall",insertText:["hgetall"].join(`
`),detail:"\u83B7\u53D6\u5728\u54C8\u5E0C\u8868\u4E2D\u6307\u5B9A key \u7684\u6240\u6709\u5B57\u6BB5\u548C\u503C"},{label:"llen",insertText:["llen"].join(`
`),detail:"\u83B7\u53D6\u5217\u8868\u957F\u5EA6"},{label:"lindex",insertText:["lindex"].join(`
`),detail:"\u901A\u8FC7\u7D22\u5F15\u83B7\u53D6\u5217\u8868\u4E2D\u7684\u5143\u7D20"},{label:"strlen",insertText:["strlen"].join(`
`),detail:"\u8FD4\u56DE key \u6240\u50A8\u5B58\u7684\u5B57\u7B26\u4E32\u503C\u7684\u957F\u5EA6"},{label:"exists",insertText:["exists"].join(`
`),detail:"\u68C0\u67E5\u7ED9\u5B9A key \u662F\u5426\u5B58\u5728"},{label:"hmset",insertText:["hmset"].join(`
`),detail:"\u540C\u65F6\u5C06\u591A\u4E2A field-value (\u57DF-\u503C)\u5BF9\u8BBE\u7F6E\u5230\u54C8\u5E0C\u8868 key \u4E2D"},{label:"set",insertText:["set"].join(`
`),detail:"\u8BBE\u7F6E\u6307\u5B9A key \u7684\u503C"},{label:"rpush",insertText:["rpush"].join(`
`),detail:"\u5728\u5217\u8868\u4E2D\u6DFB\u52A0\u4E00\u4E2A\u6216\u591A\u4E2A\u503C\u5230\u5217\u8868\u5C3E\u90E8"},{label:"mset",insertText:["mset"].join(`
`),detail:"\u540C\u65F6\u8BBE\u7F6E\u4E00\u4E2A\u6216\u591A\u4E2A key-value \u5BF9"}]}:{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u7F16\u7801"},{label:"op",insertText:['op=""'].join(`
`),detail:"\u547D\u4EE4\u540D\u79F0"},{label:"key",insertText:['key=""'].join(`
`),detail:"\u952E"},{label:"index",insertText:['index="0"'].join(`
`),detail:"\u7D22\u5F15"},{label:"convert",insertText:['convert=""'].join(`
`),detail:"\u540E\u7F6E\u8F6C\u6362"},{label:"field",insertText:['field=""'].join(`
`),detail:"hash\u4E2D\u7684key"},{label:"data",insertText:['data=""'].join(`
`),detail:"\u6570\u636E"}]};if(e=="body"){if(x(t.substring(t.lastIndexOf("=")+1),'"')==1){if(o=="type")return{suggestions:[{label:"raw",insertText:["raw"].join(`
`),detail:"raw"},{label:"x-www-form-urlencoded",insertText:["x-www-form-urlencoded"].join(`
`),detail:"x-www-form-urlencoded"},{label:"none",insertText:["none"].join(`
`),detail:"none"}]};if(o=="language")return{suggestions:[{label:"xml",insertText:["xml"].join(`
`),detail:"xml"},{label:"json",insertText:["json"].join(`
`),detail:"json"},{label:"text",insertText:["text"].join(`
`),detail:"text"}]}}return{suggestions:[{label:"language",insertText:['language="json"'].join(`
`),detail:"\u8BED\u8A00"},{label:"type",insertText:['type="json"'].join(`
`),detail:"\u7C7B\u578B"}]}}else if(e=="httpModel"){if(x(t.substring(t.lastIndexOf("=")+1),'"')==1){if(o=="method")return{suggestions:[{label:"POST",insertText:["POST"].join(`
`),detail:"POST"},{label:"GET",insertText:["GET"].join(`
`),detail:"GET"},{label:"DELETE",insertText:["DELETE"].join(`
`),detail:"DELETE"},{label:"PUT",insertText:["PUT"].join(`
`),detail:"PUT"}]};if(o=="language")return{suggestions:[{label:"xml",insertText:["xml"].join(`
`),detail:"xml"},{label:"json",insertText:["json"].join(`
`),detail:"json"},{label:"text",insertText:["text"].join(`
`),detail:"text"}]}}return{suggestions:[{label:"code",insertText:['code=""'].join(`
`),detail:"\u5C5E\u6027"},{label:"name",insertText:['name=""'].join(`
`),detail:"\u540D\u79F0"},{label:"type",insertText:['type=""'].join(`
`),detail:"\u7C7B\u578B"},{label:"data",insertText:['data=""'].join(`
`),detail:"\u6570\u636E"}]}}}if(e=="bound"||e=="script"||e=="flows"||e=="params"||e=="converts")return{suggestions:[{label:"",insertText:"",detail:""}]}}else r=G}else r=G;return{suggestions:JSON.parse(JSON.stringify(r))}}}),E.Fd.languages.registerHoverProvider("casexml",{provideHover:function(u,l,m){var t=u.getValueInRange({startLineNumber:l.lineNumber,startColumn:1,endLineNumber:l.lineNumber,endColumn:l.column}),r=t.trim(),o=r.substring(1,r.indexOf(" "));if(u.getWordAtPosition(l)!=null){var e=u.getWordAtPosition(l).word;if(o.trim().length>1&&o.trim()!=""){if(o=="rule"){if(e=="code")return{contents:[{value:"**\u7F16\u7801**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)}]};if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"},{value:["\u5EFA\u8BAE\u552F\u4E00"].join(`

`)}]};if(e=="model")return{contents:[{value:"**\u6A21\u5F0F**:\u76EE\u524D\u4EC5\u652F\u6301folw"},{value:"**\u53EF\u9009\u503C**"},{value:["flow"].join(`

`)}]}}else if(o=="flow"){if(e=="code")return{contents:[{value:"**\u7F16\u7801**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)}]};if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"},{value:["\u5EFA\u8BAE\u552F\u4E00"].join(`

`)}]}}else if(o=="param"){if(e=="code")return{contents:[{value:"**\u7F16\u7801**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)}]};if(e=="data")return{contents:[{value:"**\u9ED8\u8BA4\u503C**:"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]};if(e=="necessary")return{contents:[{value:"**\u662F\u5426\u5FC5\u586B**"},{value:["\u5982\u679C\u5FC5\u586B\u88AB\u8C03\u7528\u65F6\u6CA1\u6709\u63A5\u6536\u5230\u4F1A\u62A5\u9519"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:["\u9ED8\u8BA4false,true"].join(`

`)}]};if(e=="complex")return{contents:[{value:"**\u7EF4\u5EA6**:"},{value:["\u4EE3\u8868\u6570\u636E\u7684\u7EF4\u5EA6"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:"\u9ED8\u8BA4\u4E3A0,\u53EF\u9009\u4EFB\u610F\u6570\u5B57"}]};if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"},{value:["\u5EFA\u8BAE\u552F\u4E00"].join(`

`)}]};if(e=="dataType")return{contents:[{value:"**\u6570\u636E\u7C7B\u578B**"},{value:["\u5FC5\u586B"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:["NUMBER\u6570\u503C","STRING\u5B57\u7B26\u4E32","BOLL\u5E03\u5C14","MAP\u952E\u503C\u5BF9","TIME_YMD\u5E74\u6708\u65E5","TIME_HMS\u65F6\u5206\u79D2","TIME_YMDHMS\u5E74\u6708\u65E5\u65F6\u5206\u79D2"].join(`

`)}]}}else if(o=="action"){if(e=="code")return{contents:[{value:"**\u7F16\u7801**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)}]};if(e=="complex")return{contents:[{value:"**\u7EF4\u5EA6**:"},{value:["\u4EE3\u8868\u8FD4\u56DE\u503C\u7684\u7EF4\u5EA6"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:"\u9ED8\u8BA4\u4E3A0,\u53EF\u9009\u4EFB\u610F\u6570\u5B57"}]};if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"},{value:["\u5EFA\u8BAE\u552F\u4E00"].join(`

`)}]};if(e=="dataType")return{contents:[{value:"**\u6570\u636E\u7C7B\u578B**"},{value:["\u5FC5\u586B\uFF0C\u8FD4\u56DE\u503C\u7684\u6570\u636E\u7C7B\u578B"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:["NUMBER\u6570\u503C","STRING\u5B57\u7B26\u4E32","BOLL\u5E03\u5C14","MAP\u952E\u503C\u5BF9","TIME_YMD\u5E74\u6708\u65E5","TIME_HMS\u65F6\u5206\u79D2","TIME_YMDHMS\u5E74\u6708\u65E5\u65F6\u5206\u79D2"].join(`

`)}]};if(e=="type")return{contents:[{value:"**\u80FD\u529B\u7C7B\u578B**"},{value:["\u5FC5\u586B\uFF0C\u8FD4\u56DE\u503C\u7684\u6570\u636E\u7C7B\u578B"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:["SQL-\u6267\u884Csql","SQL_CALL-\u5B58\u50A8\u8FC7\u7A0B","HTTP-HTTP\u8BF7\u6C42","JS-\u8FD0\u884CJS\u811A\u672C","CONST-\u5B9A\u4E49\u5E38\u91CF","CONVERT-\u6570\u636E\u5904\u7406"].join(`

`)}]}}else if(o=="execute"){if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"},{value:["\u5EFA\u8BAE\u552F\u4E00"].join(`

`)}]};if(e=="code")return{contents:[{value:"**\u7F16\u7801**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)}]};if(e=="actionCode")return{contents:[{value:"**\u884C\u4E3A\u7F16\u7801**:"},{value:["\u53EF\u9009\u4F60\u5B9A\u4E49\u7684\u884C\u4E3Acode\u548C\u5168\u5C40\u884C\u4E3A\u7F16\u7801"].join(`

`)}]};if(e=="conKey")return{contents:[{value:"**\u4E8B\u52A1ID**"},{value:["conKey\u4E8B\u52A1ID(SQL,SQL_BEGIN,SQL_COMMIT\u53EF\u9009)"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:["\u9ED8\u8BA4\u4E0E\u6B65\u9AA4\u7684code\u76F8\u540C"].join(`

`)}]};if(e=="sleepTime")return{contents:[{value:"**\u505C\u987F\u65F6\u95F4**"},{value:["\u505C\u987F\u65F6\u95F4(SLEEP\u5FC5\u987B\u9009),\u5355\u4F4D\u4E3A\u79D2"].join(`

`)}]};if(e=="init")return{contents:[{value:"**\u662F\u5426\u63A5\u53D7\u8FD4\u56DE\u503C**:"},{value:"true\u9ED8\u8BA4\u63A5\u6536,false\u4E0D\u63A5\u6536"}]};if(e=="block")return{contents:[{value:"**\u662F\u5426\u5F02\u5E38\u963B\u65AD**:"},{value:"true\u963B\u65AD,false\u9ED8\u8BA4\u4E0D\u963B\u65AD"}]};if(e=="dataType")return{contents:[{value:"**\u6570\u636E\u7C7B\u578B**"},{value:["\u5FC5\u586B"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:["NUMBER\u6570\u503C","STRING\u5B57\u7B26\u4E32","BOLL\u5E03\u5C14","MAP\u952E\u503C\u5BF9","TIME_YMD\u5E74\u6708\u65E5","TIME_HMS\u65F6\u5206\u79D2","TIME_YMDHMS\u5E74\u6708\u65E5\u65F6\u5206\u79D2"].join(`

`)}]}}else if(o=="inject"){if(e=="code")return{contents:[{value:"**\u53C2\u6570**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)},{value:"**\u53EF\u9009\u503C**:"},{value:["\u53EF\u9009\u503C\u8303\u56F4\u4E3A\u88AB\u8C03\u7528\u7684\u884C\u4E3A\u58F0\u660E\u7684\u53C2\u6570"].join(`

`)}]};if(e=="data")return{contents:[{value:"**\u6570\u636E\u503C**"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]}}else if(o=="checkItem"){if(e=="code")return{contents:[{value:"**\u7F16\u7801**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)}]};if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"},{value:["\u5EFA\u8BAE\u552F\u4E00"].join(`

`)}]};if(e=="msg")return{contents:[{value:"**\u9519\u8BEF\u63D0\u793A**"}]}}else if(o=="checkObj"){if(e=="code")return{contents:[{value:"**\u7F16\u7801**:"},{value:["\u5FC5\u586B\u4E14\u552F\u4E00"].join(`

`)}]};if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"},{value:["\u5EFA\u8BAE\u552F\u4E00"].join(`

`)}]};if(e=="msg")return{contents:[{value:"**\u9519\u8BEF\u63D0\u793A**"}]};if(e=="cover")return{contents:[{value:"**\u88AB\u6BD4\u8F83\u5BF9\u8C61**"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]};if(e=="threshold")return{contents:[{value:"**\u6BD4\u8F83\u5BF9\u8C61**"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]}}else if(o=="expression"){if(e=="operationCode")return{contents:[{value:"**\u64CD\u4F5C\u7B26**:"},{value:["\u903B\u8F91\u6216\u5173\u7CFB\u8FD0\u7B97\u652F\u6301\u7684\u64CD\u4F5C\u7B26\u4E0D\u540C"].join(`

`)},{value:"**logic\u903B\u8F91\u8FD0\u7B97**"},{value:["or\u6216","and\u4E14"].join(`

`)},{value:"**elation\u5173\u7CFB\u8FD0\u7B97**"},{value:["eq\u7B49\u4E8E	neq\u4E0D\u7B49\u4E8E","gt\u5927\u4E8E	le\u5C0F\u4E8E\u7B49\u4E8E","lt\u5C0F\u4E8E	ge\u5927\u4E8E\u7B49\u4E8E"].join(`

`)}]};if(e=="expressionType")return{contents:[{value:"**\u7C7B\u578B**:"},{value:"**\u53EF\u9009\u503C**"},{value:["logic\u903B\u8F91\u8FD0\u7B97;relation\u5173\u7CFB\u8FD0\u7B97"].join(`

`)}]};if(e=="dataType")return{contents:[{value:"**\u6570\u636E\u7C7B\u578B**"},{value:["\u5FC5\u586B"].join(`

`)},{value:"**\u53EF\u9009\u503C**"},{value:["NUMBER\u6570\u503C","STRING\u5B57\u7B26\u4E32","BOLL\u5E03\u5C14","MAP\u952E\u503C\u5BF9","TIME_YMD\u5E74\u6708\u65E5","TIME_HMS\u65F6\u5206\u79D2","TIME_YMDHMS\u5E74\u6708\u65E5\u65F6\u5206\u79D2"].join(`

`)}]};if(e=="cover")return{contents:[{value:"**\u88AB\u9608\u503C**"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]};if(e=="threshold")return{contents:[{value:"**\u9608\u503C**"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]}}else if(o=="convert"){if(e=="code")return{contents:[{value:"**code\u53C2\u6570**:"}]};if(e=="name")return{contents:[{value:"**\u540D\u79F0**:"}]};if(e=="type")return{contents:[{value:"**\u7C7B\u578B**:"},{value:"**\u53EF\u9009\u503C**"},{value:["DEL\u5220\u9664;PUT\u8BBE\u7F6E"].join(`

`)}]};if(e=="data")return{contents:[{value:"**\u6570\u636E**"},{value:"**\u53EF\u9009\u503C**"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]}}else if(o=="command"){if(e=="code")return{contents:[{value:"**code\u53C2\u6570**"}]};if(e=="op")return{contents:[{value:"**\u547D\u4EE4\u540D\u79F0**"},{value:"**\u53EF\u9009\u503C**"},{value:["del:\u5220\u9664key","exists:\u68C0\u67E5\u7ED9\u5B9Akey\u662F\u5426\u5B58\u5728","get:\u83B7\u53D6key\u7684\u503C","hget:\u83B7\u53D6\u54C8\u5E0C\u8868\u4E2D\u6307\u5B9A\u5B57\u6BB5\u7684\u503C","hgetall:\u83B7\u53D6\u54C8\u5E0C\u8868\u5B8C\u6574\u7684kv","llen:\u83B7\u53D6\u5217\u8868\u957F\u5EA6","lindex:\u901A\u8FC7\u7D22\u5F15\u83B7\u53D6\u5217\u8868\u4E2D\u7684\u5143\u7D20","strlen:\u8FD4\u56DEkey\u6240\u50A8\u5B58\u7684\u5B57\u7B26\u4E32\u503C\u7684\u957F\u5EA6","mset:\u540C\u65F6\u8BBE\u7F6E\u4E00\u4E2A\u6216\u591A\u4E2Akey-value\u5BF9","rpush:\u5728\u5217\u8868\u4E2D\u6DFB\u52A0\u4E00\u4E2A\u6216\u591A\u4E2A\u503C\u5230\u5217\u8868\u5C3E\u90E8","set:\u8BBE\u7F6E\u6307\u5B9Akey\u7684\u503C","hmset:\u540C\u65F6\u5C06\u591A\u4E2A(\u57DF-\u503C)\u5BF9\u8BBE\u7F6E\u5230\u54C8\u5E0C\u8868"].join(`

`)}]};if(e=="key")return{contents:[{value:"**\u88AB\u64CD\u4F5C\u7684\u952E**"}]};if(e=="index")return{contents:[{value:"**\u7D22\u5F15**:"},{value:"**\u53EF\u9009\u503C**"},{value:["\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]};if(e=="field")return{contents:[{value:"**field**:"},{value:["hash\u6570\u636E\u4E2D\u7684key"].join(`

`)}]};if(e=="convert")return{contents:[{value:"**\u540E\u7F6E\u8F6C\u6362**"},{value:"**\u53EF\u9009\u503C**"},{value:["\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]};if(e=="data")return{contents:[{value:"**\u6570\u636E**"},{value:"**\u53EF\u9009\u503C**"},{value:["\u53EF\u8F93\u5165\u4EFB\u610Fformula\u8868\u8FBE\u5F0F"].join(`

`)}]}}else if(o=="httpModel"){if(e=="url")return{contents:[{value:"**\u8BF7\u6C42\u8DEF\u5F84**"}]};if(e=="method")return{contents:[{value:"**\u8BF7\u6C42\u65B9\u5F0F**"},{value:"**\u53EF\u9009\u503C**"},{value:["POST","GET","PUT","DELETE"].join(`

`)}]}}else if(o=="body"){if(e=="type")return{contents:[{value:"**\u8BF7\u6C42\u4F53\u7C7B\u578B**"},{value:"**\u53EF\u9009\u503C**"},{value:["none","form-data","x-www-form-urlencoded","raw"].join(`

`)}]};if(e=="language")return{contents:[{value:"**\u8BF7\u6C42\u6570\u636E\u7C7B\u578B**"},{value:"\u4EC5\u4EC5\u5728type\u4E3Araw\u65F6\u751F\u6548"},{value:"**\u53EF\u9009\u503C**"},{value:["json","xml","text"].join(`

`)}]}}return{}}else{if(e=="rule")return{contents:[{value:"**\u7528\u4F8B**:\u6BCF\u4E00\u4E2A\u6D4B\u8BD5\u7528\u4F8B\u7684\u6839\u6807\u7B7E\u90FD\u662Frule"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u7F16\u7801;name\u540D\u79F0;model\u6A21\u5F0F[flow]"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["params\u53C2\u6570\u7EC4;actions\u884C\u4E3A\u7EC4;folws\u6D41\u7A0B\u7EC4"].join(`

`)}]};if(e=="params")return{contents:[{value:"**\u53C2\u6570\u7EC4**"},{value:["\u7528\u4E8E\u5B9A\u4E49\u7528\u4F8B\u6216\u884C\u4E3A\u6240\u9700\u63A5\u6536\u7684\u53C2\u6570"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["param\u53C2\u6570\u7533\u660E"].join(`

`)}]};if(e=="param")return{contents:[{value:"**\u53C2\u6570\u7533\u660E**"},{value:["\u7528\u4E8E\u58F0\u660E\u53C2\u6570"].join(`

`)},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u7F16\u7801;dataType\u6570\u636E\u7C7B\u578B"].join(`

`)},{value:"**\u53EF\u9009\u53C2\u6570**"},{value:["name\u540D\u79F0;data\u9ED8\u8BA4\u503C;complex\u7EF4\u5EA6[0];necessary\u662F\u5426\u5FC5\u586B[false],true"].join(`

`)}]};if(e=="actions")return{contents:[{value:"**\u884C\u4E3A\u7EC4**"},{value:["\u7528\u4E8E\u5B9A\u4E49\u7528\u4F8B\u53EF\u4EE5\u6267\u884C\u7684\u52A8\u4F5C"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["action\u884C\u4E3A\u5B9A\u4E49"].join(`

`)}]};if(e=="action")return{contents:[{value:"**\u884C\u4E3A\u5B9A\u4E49**:\u7528\u4E8E\u5B9A\u4E49\u884C\u4E3A\u7684\u7EC6\u8282"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u7F16\u7801;type\u80FD\u529B\u7C7B\u578B;dataType\u8FD4\u56DE\u503C\u7C7B\u578B"].join(`

`)},{value:"**\u53EF\u9009\u53C2\u6570**"},{value:["name\u540D\u79F0;complex\u8FD4\u56DE\u503C\u7EF4\u5EA6[0]"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:[`params\u53C2\u6570\u7533\u660E;script\u811A\u672C(JS\u5FC5\u9009);

bound\u52A8\u6001\u5B57\u7B26\u4E32(SQL,SQL_CALL,HTTP,CONST\u5FC5\u9009)`].join(`

`)}]};if(e=="flows")return{contents:[{value:"**\u6D41\u7A0B\u7EC4**"},{value:["\u7528\u4E8E\u5B9A\u4E49\u7528\u4F8B\u53EF\u4EE5\u6267\u884C\u7684\u6D41\u7A0B\u5217\u8868"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["flow\u6D41\u7A0B"].join(`

`)}]};if(e=="flow")return{contents:[{value:"**\u6D41\u7A0B**"},{value:["\u7528\u4E8E\u5B9A\u4E49\u6267\u884C\u7684\u6D41\u7A0B"].join(`

`)},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u7F16\u7801"].join(`

`)},{value:"**\u53EF\u9009\u53C2\u6570**"},{value:["name\u540D\u79F0"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["execute\u6B65\u9AA4"].join(`

`)}]};if(e=="execute")return{contents:[{value:"**\u6B65\u9AA4**:\u7528\u4E8E\u5B9A\u4E49\u6D41\u7A0B\u4E2D\u7684\u6B65\u9AA4"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u7F16\u7801;name\u540D\u79F0;actionCode\u884C\u4E3A\u7F16\u7801"].join(`

`)},{value:"**\u53EF\u9009\u53C2\u6570**"},{value:["block\u5F02\u5E38\u963B\u65AD[false],true","init\u63A5\u53D7\u8FD4\u56DE\u503C[false],true","sleepTime\u505C\u987F\u65F6\u95F4(SLEEP\u5FC5\u987B\u9009)","conKey\u4E8B\u52A1ID(SQL,SQL_BEGIN,SQL_COMMIT\u53EF\u9009)"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["injects\u53C2\u6570\u4F20\u9012\u7EC4(\u53EF\u9009)","checkItem\u68C0\u67E5\u9879(CHECK\u5FC5\u9009)","checkObj\u5BF9\u8C61\u6BD4\u8F83(CHECK_OBJ\u5FC5\u9009)"].join(`

`)}]};if(e=="injects")return{contents:[{value:"**\u53C2\u6570\u4F20\u9012\u7EC4**:\u7528\u4E8E\u5411\u88AB\u8C03\u7528\u7684\u884C\u4E3A\u4F20\u9012\u53C2\u6570"},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["inject\u53C2\u6570\u4F20\u9012"].join(`

`)}]};if(e=="inject")return{contents:[{value:"**\u53C2\u6570\u4F20\u9012**:\u5B9A\u4E49\u4F20\u9012\u89C4\u5219"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u53C2\u6570;data\u6570\u636E\u503C"].join(`

`)}]};if(e=="checkItem")return{contents:[{value:"**\u68C0\u67E5\u9879**:\u5B9A\u4E49\u68C0\u67E5\u9879"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u53C2\u6570;name\u68C0\u67E5\u540D\u79F0;msg\u5F02\u5E38\u63D0\u793A"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["expression\u8FD0\u7B97\u8868\u8FBE\u5F0F"].join(`

`)}]};if(e=="expression")return{contents:[{value:"**\u8FD0\u7B97\u8868\u8FBE\u5F0F**:\u903B\u8F91\u8FD0\u7B97\u3001\u7B97\u6570\u8FD0\u7B97"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["expressionType\u7C7B\u578Brelation,logic;operationCode\u8FD0\u7B97\u7B26"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["subExpressions\u5B50\u8868\u8FBE\u5F0F(logic\u5FC5\u9009)"].join(`

`)}]};if(e=="subExpressions")return{contents:[{value:"**\u5B50\u8868\u8FBE\u5F0F**:"},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["expression\u8FD0\u7B97\u8868\u8FBE\u5F0F"].join(`

`)}]};if(e=="actions")return{contents:[{value:"**\u884C\u4E3A\u7EC4**"},{value:["\u7528\u4E8E\u5B9A\u4E49\u7528\u4F8B\u53EF\u4EE5\u6267\u884C\u7684\u52A8\u4F5C"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["action\u884C\u4E3A\u5B9A\u4E49"].join(`

`)}]};if(e=="checkObj")return{contents:[{value:"**\u5BF9\u8C61\u6BD4\u8F83**:\u5B9A\u4E49\u5BF9\u8C61\u6BD4\u8F83"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u53C2\u6570;name\u68C0\u67E5\u540D\u79F0;msg\u5F02\u5E38\u63D0\u793A","cover\u88AB\u6BD4\u8F83\u5BF9\u8C61;threshold\u6BD4\u8F83\u5BF9\u8C61"].join(`

`)}]};if(e=="bound")return{contents:[{value:"**bound**:\u52A8\u6001\u5B57\u7B26\u4E32"}]};if(e=="script")return{contents:[{value:"**script**:\u811A\u672C"}]};if(e=="converts")return{contents:[{value:"**\u8F6C\u6362\u7EC4**:\u7528\u4E8E\u5B9A\u4E49\u8F6C\u6362\u89C4\u5219\u8F6C\u6362\u7684\u5217\u8868"},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["convert\u8F6C\u6362\u89C4\u5219"].join(`

`)}]};if(e=="convert")return{contents:[{value:"**\u8F6C\u6362**:\u7528\u4E8E\u5B9A\u4E49\u8F6C\u6362\u89C4\u5219"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["code\u53C2\u6570;name\u540D\u79F0"].join(`

`)},{value:"**\u53EF\u9009\u53C2\u6570**"},{value:["data\u6570\u636E(PUT\u5FC5\u9009)"].join(`

`)}]};if(e=="commands")return{contents:[{value:"**\u547D\u4EE4\u7EC4**"},{value:["\u7528\u4E8E\u5B9A\u4E49\u64CD\u4F5Credis\u7684\u547D\u4EE4"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["command\u547D\u4EE4"].join(`

`)}]};if(e=="command")return{contents:[{value:"**redis\u7684\u547D\u4EE4**"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["op\u547D\u4EE4"].join(`

`)},{value:"**\u53EF\u9009\u53C2\u6570**"},{value:["code\u7F16\u7801(mset\u5FC5\u586B)","key\u64CD\u4F5C\u952E(\u975Emset\u5FC5\u586B)","data\u6570\u636E(\u8BBE\u7F6E\u7C7B\u64CD\u4F5C\u5FC5\u586B)","index\u6570\u636E(\u542B\u7D22\u5F15\u64CD\u4F5C\u5FC5\u586B)","field\u6570\u636E\u4E2D\u7684key","convert\u540E\u7F6E\u64CD\u4F5C"].join(`

`)}]};if(e=="httpModel")return{contents:[{value:"**Http\u8BF7\u6C42\u8BBE\u7F6E**"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["url\u8BF7\u6C42\u5730\u5740","method\u8BF7\u6C42\u65B9\u5F0F"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["headers\u8BF7\u6C42\u5934","rests\u8DEF\u5F84\u53C2\u6570","params\u53C2\u6570","body\u8BF7\u6C42\u4F53"].join(`

`)}]};if(e=="rests")return{contents:[{value:"**rests\u8DEF\u5F84\u53C2\u6570**"},{value:["\u7528\u4E8E\u914D\u7F6Erest\u8DEF\u5F84\u53C2\u6570"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["param\u53C2\u6570"].join(`

`)}]};if(e=="params")return{contents:[{value:"**params\u53C2\u6570**"},{value:["\u7528\u4E8E\u914D\u7F6E\u62FC\u63A5\u5230url\u4E2D\u7684\u53C2\u6570"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["param\u53C2\u6570"].join(`

`)}]};if(e=="headers")return{contents:[{value:"**headers\u53C2\u6570**"},{value:["\u7528\u4E8E\u914D\u7F6E\u8BF7\u6C42\u5934\u7684\u53C2\u6570"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["param\u53C2\u6570"].join(`

`)}]};if(e=="body")return{contents:[{value:"**\u8BF7\u6C42\u4F53\u8BBE\u7F6E**"},{value:"**\u5FC5\u9009\u53C2\u6570**"},{value:["type\u7C7B\u578B"].join(`

`)},{value:"**\u53EF\u9009\u53C2\u6570**"},{value:["language\u8BED\u8A00"].join(`

`)},{value:"**\u5B50\u7EC4\u4EF6**"},{value:["content\u6587\u672C\u5185\u5BB9","param\u53C2\u6570"].join(`

`)}]};if(e=="content")return{contents:[{value:"**\u6587\u672C\u5185\u5BB9**"},{value:["\u652F\u6301\u914D\u7F6Efreemarker\u811A\u672C"].join(`

`)}]}}}}}),E.Fd.languages.setLanguageConfiguration("casexml",{comments:{blockComment:["<!--","-->"]},brackets:[["{","}"],["[","]"]],autoClosingPairs:[{open:"{",close:"}"},{open:"[",close:"]"},{open:"<",close:">",notIn:["string"]},{open:'"',close:'"'}],surroundingPairs:[{open:"(",close:")"},{open:"[",close:"]"},{open:"`",close:"`"}],folding:{markers:{start:new RegExp("^\\\\s*<!--\\\\s*#?region\\\\b.*-->"),end:new RegExp("^\\\\s*<!--\\\\s*#?endregion\\\\b.*-->")}}}),E.Fd.languages.setMonarchTokensProvider("casexml",{ignoreCase:!0,tokenizer:{root:[[/<\?xml/,"metatag","@doctype"],[/<!--/,"comment","@comment"],[/(<)((?:[\w\-]+:)?[\w\-]+)(\s*)(\/>)/,["delimiter","tag","","delimiter"]],[/(<)((?:[\w\-]+:)?[\w\-]+)/,["delimiter",{token:"tag",next:"@otherTag"}]],[/(<\/)((?:[\w\-]+:)?[\w\-]+)/,["delimiter",{token:"tag",next:"@otherTag"}]],[/</,"delimiter"],[/[^<]+/]],doctype:[[/[^>]+/,"metatag.content"],[/>/,"metatag","@pop"]],comment:[[/-->/,"comment","@pop"],[/[^-]+/,"comment.content"],[/./,"comment.content"]],otherTag:[[/\/?>/,"delimiter","@pop"],[/"([^"]*)"/,"attribute.value"],[/'([^']*)'/,"attribute.value"],[/[\w\-]+/,"attribute.name"],[/=/,"delimiter"],[/[ \t\r\n]+/]]}})},ge=xe,be=function(){(0,k.y1)(),ye(),fe(),Te(),ge()},ye=function(){var i=(0,Y.VQ)();if(!i){(0,Y.Wg)(c.IO.EN_US),document.documentElement.setAttribute("lang",c.IO.EN_US);var u=new Date("2030-12-30 12:30:00").toUTCString();document.cookie="CHAT2DB.LOCALE=".concat(i,";Expires=").concat(u)}},he=be,Se=a(78957),z=a(17788),V=a(34232),g={"f-icon-button":"f-icon-button___j21r3","f-fill-absolute":"f-fill-absolute___WcBHE","f-button":"f-button___b7wzt","f-doc-en-break":"f-doc-en-break___Rjuy1",message:"message___v0Ozo",modal:"modal___qnSp0",modalTitle:"modalTitle___s2X4A",modalFooter:"modalFooter___RSbeP",copyErrorTips:"copyErrorTips___DYVmf",description:"description___HaHWO",notification:"notification___NSjY1",errorDetail:"errorDetail___TUUB2","loading-animation":"loading-animation___QCmUx"},n=a(85893);function Ce(){var s=J.ZP.useNotification({maxCount:2}),i=j()(s,2),u=i[0],l=i[1],m=(0,f.useState)(!1),t=j()(m,2),r=t[0],o=t[1],e=(0,f.useState)(),y=j()(e,2),d=y[0],h=y[1];window._notificationApi=(0,f.useCallback)(function(T){var N=T.traceId,O=T.errorCode,H=T.errorMessage,B=T.solutionLink,S=T.errorDetail;h(T);var C=(0,n.jsxs)(Se.Z,{children:[S&&(0,n.jsx)(P.ZP,{type:"link",size:"small",onClick:function(){o(!0)},children:(0,b.ZP)("common.notification.detail")}),B&&(0,n.jsx)(P.ZP,{type:"link",size:"small",target:"_blank",href:B,children:(0,b.ZP)("common.notification.solution")})]}),D=function(){return(0,n.jsxs)("div",{className:g.description,children:[O," ",H]})},Z=function(){return(0,n.jsxs)("div",{className:g.message,children:[(0,n.jsx)(V.Z,{code:"\uE60C"}),"Error"]})};u.open({className:g.notification,message:Z(),description:D(),placement:"bottomRight",btn:C})},[]);function L(){var T=[d==null?void 0:d.errorCode,d==null?void 0:d.errorMessage];return(0,n.jsx)("div",{className:g.modalTitle,children:T.filter(function(N){return N}).join(":")})}function A(){var T=p()({getApplicationMessage:(0,k.rT)()},d);(0,k.JG)(JSON.stringify(T)),F.ZP.success((0,b.ZP)("common.button.copySuccessfully"))}function R(){return d!=null&&d.requestParams?(0,n.jsxs)("div",{className:g.modalFooter,onClick:A,children:[(0,n.jsx)(V.Z,{code:"\uEB4E"}),(0,b.ZP)("common.button.copyError"),(0,n.jsx)("span",{className:g.copyErrorTips,children:(0,b.ZP)("common.button.copyErrorTips")})]}):!1}return(0,n.jsxs)(n.Fragment,{children:[l,(0,n.jsxs)(z.Z,{className:g.modal,title:L(),open:r,width:"70vw",footer:R(),onCancel:function(){o(!1)},zIndex:99999,children:[(0,n.jsxs)("div",{className:g.errorDetail,children:["traceId:",d==null?void 0:d.traceId]}),(0,n.jsx)("div",{className:g.errorDetail,children:d==null?void 0:d.errorDetail})]})]})}var Me=Ce,Ee=a(52464),Ie=a(782),Ne=a(76248),Pe={focusedContent:null},We=function(i){return useCommonStore.setState({focusedContent:i})},Le=(0,Ee.F)((0,Ie.mW)(function(){return p()(p()({},Pe),Re)}),Ne.X),Re={openModal:null},Oe=function(i){return Le.setState({openModal:i})},$e=function(i){var u,l;return(u=(l=useCommonStore.getState()).openModal)===null||u===void 0?void 0:u.call(l,i)},Be=(0,f.memo)(function(){var s=(0,f.useState)(!1),i=j()(s,2),u=i[0],l=i[1],m=(0,f.useState)(null),t=j()(m,2),r=t[0],o=t[1],e=function(h){h===!1?l(!1):(l(!0),o(h))};(0,f.useEffect)(function(){Oe(e)},[]);var y=(0,f.useMemo)(function(){return r&&r.footer?{footer:r.footer,onOk:r.onOk}:{footer:!1}},[r]);return!!r&&(0,n.jsx)(z.Z,p()(p()({title:r.title,open:u,width:r.width,onCancel:function(){l(!1)},destroyOnClose:!0},y),{},{children:r.content}))}),De=Be,ke=function(){return(0,n.jsxs)(n.Fragment,{children:[(0,n.jsx)(Me,{}),(0,n.jsx)(De,{})]})},Ge=ke,I={"f-icon-button":"f-icon-button___Qucux","f-fill-absolute":"f-fill-absolute___yem0b","f-button":"f-button___lf6a8","f-doc-en-break":"f-doc-en-break___qwRpP",app:"app___NIftm",serverLoading:"serverLoading___qYw0b",hint:"hint___qa7J3",loadingBox:"loadingBox___0sMDn","loading-animation":"loading-animation___Rf2z4"},Ae=function(){return(0,n.jsx)("div",{className:"loading_main",children:(0,n.jsx)("div",{className:"loaders",children:(0,n.jsx)("div",{className:"loader",children:(0,n.jsxs)("div",{className:"loader-inner ball-scale-multiple",children:[(0,n.jsx)("div",{}),(0,n.jsx)("div",{}),(0,n.jsx)("div",{})]})})})})},He=Ae,Ze=a(46659),Ue=a(4816),Qe=a(86111),Xe={javaServerStatus:"controller.framework.runStatus",startJavaServer:"controller.framework.startJavaServer",closeJavaServer:"controller.framework.closeJavaServer"},Fe=window.require&&window.require("electron")||window.electron||{},_e=Fe.ipcRenderer||void 0,qe=null;console.log("AppContainer=======__APP_VERSION__1.0.4"),console.log("AppContainer=======__UMI_ENV__web"),console.log("AppContainer=======__BUILD_TIME__"+1712371389719),console.log("AppContainer=======__APP_PORT__12003");var Je=10,Ye=1e3,ze=function(){var i=(0,ee.F)(),u=j()(i,2),l=u[0],m=u[1],t=(0,f.useState)({}),r=j()(t,2),o=r[0],e=r[1],y=(0,f.useState)(0),d=j()(y,2),h=d[0],L=d[1],A=(0,f.useState)(!1),R=j()(A,2),T=R[0],N=R[1];(0,f.useLayoutEffect)(function(){H(),O()},[]),(0,f.useLayoutEffect)(function(){he(),B()},[]);function O(){N(!1);var S=0,C=setInterval(function(){w.Z.testService().then(function(){clearInterval(C),L(2),S++}).catch(function(D){L(1),S++}),S>Je&&(N(!0),clearInterval(C))},Ye)}function H(){if(!1)var S,C}(0,f.useLayoutEffect)(function(){e(de(l))},[l]);var B=function(){function C(Z){l.backgroundColor===c.f8.FollowOs&&m(p()(p()({},l),{},{backgroundColor:Z.matches?c.f8.Dark:c.f8.Light}))}var D=window.matchMedia("(prefers-color-scheme: dark)");D.onchange=C};return(0,n.jsxs)(X.ZP,{locale:b.a1?_.Z:q.Z,theme:o,children:[(0,n.jsxs)("div",{className:I.app,children:[h!=2&&(0,n.jsx)(n.Fragment,{children:(0,n.jsxs)("div",{className:I.serverLoading,children:[!T&&(0,n.jsx)(He,{}),(0,n.jsx)("div",{className:I.hint,children:(0,n.jsx)(Ze.Z,{noLogin:!0,className:I.setIcon,render:(0,n.jsx)(P.ZP,{type:"link",style:{fontSize:14},children:(0,b.ZP)("setting.title.setting")})})}),T&&(0,n.jsxs)("div",{className:I.loadingBox,children:[(0,n.jsx)(P.ZP,{type:"link",onClick:O,style:{fontSize:14},children:(0,b.ZP)("common.text.tryToRestart")}),(0,n.jsx)("div",{style:{marginTop:8},children:(0,n.jsx)(Ue.Z,{})})]})]})}),h===2&&(0,n.jsx)(Qe.Outlet,{})]}),(0,n.jsx)(Ge,{})]})},Ve=ze}}]);
