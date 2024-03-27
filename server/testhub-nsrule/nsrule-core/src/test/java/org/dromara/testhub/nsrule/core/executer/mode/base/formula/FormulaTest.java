package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.dromara.testhub.nsrule.core.executer.context.DefContext;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.expand.FormulaBuilder;
import org.dromara.testhub.nsrule.core.expand.impl.DefaultFormulaBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class FormulaTest {
    @Test
    public void test() {
        String dataStr = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":2}";
        System.out.println(dataStr);
        JSONObject data = JSONObject.parseObject(dataStr,JSONObject.class);
        DefContext context = new DefContext(data);

        FormulaBuilder formulaBuilder = new DefaultFormulaBuilder();

//        FormulaNode node1 = formulaBuilder.getFormulaNode("${expensive}");
//        Result<Object>  result =  node1.apply(context);
//        Assert.assertEquals(2, result.getContent());
//
//        FormulaNode node2 = formulaBuilder.getFormulaNode("${store.book}");
//        Result<Object>  result2 =  node2.apply(context);
//        Assert.assertEquals(data.getJSONObject("store").getJSONArray("book"), result2.getContent());
//
//
//        FormulaNode node3 = formulaBuilder.getFormulaNode("${user.books.id}");
//        FormulaNode node4 = formulaBuilder.getFormulaNode("${store.book[-1].author}");
//        Result<Object>  result4 =  node4.apply(context);
//        Assert.assertEquals("J. R. R. Tolkien", result4.getContent());
//
//        FormulaNode node5 = formulaBuilder.getFormulaNode("${store.book[${expensive}].author}");
//        Result<Object>  result5 =  node5.apply(context);
//        Assert.assertEquals("Herman Melville", result5.getContent());
//
//        FormulaNode node6 = formulaBuilder.getFormulaNode("${store.book[%{add(attr1:1,attr2:2)}].author}");
//        Result<Object>  result6 =  node6.apply(context);
//        Assert.assertEquals("J. R. R. Tolkien", result6.getContent());
//
//        FormulaNode node7 = formulaBuilder.getFormulaNode("${store.book[*].author}");
//        Result<Object>  result7 =  node7.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[*].author"), result7.getContent());
//
//        FormulaNode node8 = formulaBuilder.getFormulaNode("${store.book[0:2].author}");
//        Result<Object>  result8 =  node8.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[0:2].author"), result8.getContent());
//
//        FormulaNode node9 = formulaBuilder.getFormulaNode("${store.book[${expensive}:%{add(attr1:1,attr2:2)}].author}");
//        Result<Object>  result9 =  node9.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[2:3].author"), result9.getContent());
//
//
//        FormulaNode node10 = formulaBuilder.getFormulaNode("${store.book[0:3:2].author}");
//        Result<Object>  result10 =  node10.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[0:3:2].author"), result10.getContent());
//
//        FormulaNode node11 = formulaBuilder.getFormulaNode("${store.book[?(isbn)]}");
//        Result<Object>  result11 =  node11.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[?(isbn)]"), result11.getContent());
//
//        FormulaNode node12 = formulaBuilder.getFormulaNode("${store.book[author='Nigel Rees']}");
//        Result<Object>  result12 =  node12.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[author='Nigel Rees']"), result12.getContent());
//
//
//        FormulaNode node13 = formulaBuilder.getFormulaNode("${store.book[author like 'Nigel%']}");
//        Result<Object>  result13 =  node13.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[author like 'Nigel%']"), result13.getContent());
//
//        FormulaNode node14 = formulaBuilder.getFormulaNode("${books[id not rlike '123%']}");
//        FormulaNode node15 = formulaBuilder.getFormulaNode("${store.book[price > ${expensive}]}");
//        Result<Object>  result15 =  node15.apply(context);
//        Assert.assertEquals(JSONPath.eval(data,"$.store.book[price > 2]"), result15.getContent());
//
//        FormulaNode node20 = formulaBuilder.getFormulaNode("%{now()}");
//        FormulaNode node21 = formulaBuilder.getFormulaNode("%{add(attr1:1,attr2:2)}");
//        Result<Object>  result21 =  node21.apply(context);
//        Assert.assertEquals(new BigDecimal("3"), result21.getContent());
//
//        FormulaNode node22 = formulaBuilder.getFormulaNode("%{add(attr1:${expensive})}");
//        Result<Object>  result22 =  node22.apply(context);
//        Assert.assertEquals(new BigDecimal("2"), result22.getContent());
//
//
//        FormulaNode node24 = formulaBuilder.getFormulaNode("%{add(1)}");
//        Result<Object>  result24 =  node24.apply(context);
//        Assert.assertEquals(new BigDecimal("1"), result24.getContent());
//
//        FormulaNode node25 = formulaBuilder.getFormulaNode("%{add(1,2)}");
//        Result<Object>  result25 =  node21.apply(context);
//        Assert.assertEquals(new BigDecimal("3"), result25.getContent());
//
//        FormulaNode node26 = formulaBuilder.getFormulaNode("%{add(${index},${index})}");
//        FormulaNode node27 = formulaBuilder.getFormulaNode("%{add(attr1:${index},attr2:${index})}");
//        FormulaNode node28 = formulaBuilder.getFormulaNode("%{obj(id:1,name:vinc,age:18,data:{a:[1,2],b:2}).name}");
////        Result<Object>  result28 =  node28.apply(context);
//
//        FormulaNode node40 = formulaBuilder.getFormulaNode("asd");
//        Result<Object> result40 =  node40.apply(context);
//
//
//        FormulaNode node41 = formulaBuilder.getFormulaNode("1");
//        FormulaNode node42 = formulaBuilder.getFormulaNode("-1");
//        FormulaNode node43 = formulaBuilder.getFormulaNode("1.0086");
//        FormulaNode node44 = formulaBuilder.getFormulaNode("-1.0086");
//        Result<Object> result44 =  node44.apply(context,true);
//        FormulaNode node45 = formulaBuilder.getFormulaNode("{attr:{a:1},attr1:1}");
//        Result<Object> result45 =  node45.apply(context);
//        FormulaNode node46 = formulaBuilder.getFormulaNode("{attr:2,attr1:1}");
//        Result<Object> result46 =  node46.apply(context);
//        FormulaNode node47 = formulaBuilder.getFormulaNode("[1,2]");
//        Result<Object> result47 =  node47.apply(context);
//        FormulaNode node48 = formulaBuilder.getFormulaNode("[${expensive},2]");
//        FormulaNode node49 = formulaBuilder.getFormulaNode("http:172.20.101.65:14001/api/bas/swagger-ui/index.html#/%E6%9F%9C%E5%91%98%E4%BF%A1%E6%81%AF/loginPwd");
//
//        Assert.assertEquals(node49.apply(context).getContent(),"http:172.20.101.65:14001/api/bas/swagger-ui/index.html#/%E6%9F%9C%E5%91%98%E4%BF%A1%E6%81%AF/loginPwd");
//
//
//        FormulaNode node50 = formulaBuilder.getFormulaNode("1+2+3+%{add(1,2)}+(1-2)*3/${expensive}");
//        Result<Object>  result50 =  node50.apply(context,true);
//        Assert.assertEquals(new BigDecimal("7.5"), result50.getContent());
//
//        FormulaNode node51 = formulaBuilder.getFormulaNode("1+2+3*4/5%3");
//        Result<Object>  result51 =  node51.apply(context,true);
//        Assert.assertEquals(new BigDecimal("5.4"), result51.getContent());
//        FormulaNode node52 = formulaBuilder.getFormulaNode("as&/sdasd");
//
//
//        FormulaNode node60 = formulaBuilder.getFormulaNode("asd${store.book[${expensive}].author}123");
//        Result<Object>  result60 =  node60.apply(context,true);
//        Assert.assertEquals("asdHerman Melville123", result60.getContent());
//
//        FormulaNode node61 = formulaBuilder.getFormulaNode("(time)'2023-09-12'");
//        Assert.assertEquals("asdHerman Melville123", result60.getContent());
//        FormulaNode node62 = formulaBuilder.getFormulaNode("1+2:asdasad${stp1.asd[1]}:asd");
//        FormulaNode node63 = formulaBuilder.getFormulaNode("* 0/1 * * * ?");
//        FormulaNode node64 = formulaBuilder.getFormulaNode("http:172.20.101.65:14001");
//        FormulaNode node65 = formulaBuilder.getFormulaNode("application/json;charset=utf-8");
//        FormulaNode node66 = formulaBuilder.getFormulaNode("000000");
//        FormulaNode node67 = formulaBuilder.getFormulaNode("'http:'${expensive}':10010/server/workerHeartbeat'");
//        Assert.assertEquals("http:2:10010/server/workerHeartbeat", node67.apply(context).getContent());
//
//        FormulaNode node68 = formulaBuilder.getFormulaNode("http:${expensive}:10010/server/workerHeartbeat");
//        Assert.assertEquals("http:2:10010/server/workerHeartbeat", node68.apply(context).getContent());



//        FormulaNode node69 = formulaBuilder.getFormulaNode("[8.95,12.99,8.99,22.99]");
        FormulaNode node70 = formulaBuilder.getFormulaNode("http:172.20.101.65:10010/server/workerHeartbeat?id=1&a=1");
        FormulaNode node71 = formulaBuilder.getFormulaNode("0123");

        System.out.println("---");
    }
}
