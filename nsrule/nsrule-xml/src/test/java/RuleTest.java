import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.parserXml.XMLRuleConfigBuilder;
import org.junit.Test;


/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/4 16:20
 */
public class RuleTest {
    @Test
    public void main() throws Exception {
        RuleConfig ruleConfig = new XMLRuleConfigBuilder().build("classpath:rule-config.xml");


        System.out.println("--");

    }
}
