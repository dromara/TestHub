package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.DefContext;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.MinLog;

import java.util.ArrayList;
import java.util.List;


/**
 * @author vinc
 */
public class MixNode extends FormulaNode {
    public static String TYPE = "MIX";
    private List<FormulaNode> datas;

    public MixNode(List<FormulaNode> datas, String text) {
        super(TYPE, text);
        this.datas = datas;
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        MinLog log = isLog ? new MinLog(this) : null;

        List<FormulaLog> dayaLogs = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (FormulaNode tNode : datas) {
            Result<Object> temp = tNode.apply(context, isLog);
            dayaLogs.add((FormulaLog) temp.getLog());
            stringBuilder.append(temp.getContent().toString());

        }
        result.setContent(stringBuilder.toString());
        if (log != null) {
            log.setNodes(dayaLogs);
            log.setData(result.getContent());
            result.setLog(log);
        }

        return result;
    }

    public FormulaNode simplify() {
        if (!canSimplify()) {
            return this;
        }
        for (FormulaNode tNode : datas) {
            tNode.simplify();
        }

        return this;
    }

    public boolean canSimplify() {
        for (FormulaNode tNode : datas) {
            if (!tNode.canSimplify()) {
                return false;
            }
        }
        return true;
    }

}
