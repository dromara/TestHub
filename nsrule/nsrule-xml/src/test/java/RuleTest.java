import cn.hutool.core.thread.ExecutorBuilder;
import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.Rule;
import com.goddess.nsrule.flow.model.FlowContext;
import com.goddess.nsrule.flow.model.RuleFlow;
import com.goddess.nsrule.parserXml.XMLRuleConfigBuilder;
import org.junit.Test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 16:20
 */
public class RuleTest {
    @Test
    public void main() throws Exception {
        RuleConfig ruleConfig = new XMLRuleConfigBuilder().build("classpath:rule-config.xml");

        Rule rule = ruleConfig.getRule("DEMO_Check");

        FlowContext flowContext = new FlowContext(ruleConfig.getProject(rule.getProject()), (RuleFlow)rule, "", ExecutorBuilder.create().build());


        JSONObject data =new JSONObject();
        data.put("a",1);
        data.put("b",1);
        data.put("c",1);
        Object res = rule.decision(flowContext,data);
        System.out.println("--");

    }
}
