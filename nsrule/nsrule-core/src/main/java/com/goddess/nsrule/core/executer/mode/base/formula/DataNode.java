package com.goddess.nsrule.core.executer.mode.base.formula;

import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.Result;
import com.goddess.nsrule.core.executer.mode.base.formula.log.FormulaLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:04
 */
public class DataNode extends FormulaNode {
    private List<String> datas;

    public DataNode(String text) {
        this.type = "DATA";
        //this.datas = Arrays.asList(text.split(","));
        this.datas = new ArrayList<>();
        datas.add(text);
        this.text = text;
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        DataLog dataLog = null;
        if (isLog) {
            dataLog = new DataLog(this);
        }

        Object data = null;
        if (this.datas.size() > 1) {
            data = this.datas;
        } else {
            data = this.datas.get(0);
        }
        result.setContent(data);
        result.setLog(dataLog);
        if (isLog) {
            dataLog.setData(data);
        }
        return result;
    }

    public static class DataLog extends FormulaLog {
        public DataLog(FormulaNode node) {
            super(node);
        }
    }
}
