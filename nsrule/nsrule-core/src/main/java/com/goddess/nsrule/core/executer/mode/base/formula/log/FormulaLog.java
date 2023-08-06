package com.goddess.nsrule.core.executer.mode.base.formula.log;

import com.goddess.nsrule.core.executer.mode.base.BaseLog;
import com.goddess.nsrule.core.executer.mode.base.formula.FormulaNode;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2023/1/18 09:39
 */
public class FormulaLog extends BaseLog {
    public String type;
    public String text;
    public Object data;

    public FormulaLog(FormulaNode node) {
        this.type = node.type;
        this.text = node.text;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getText() {
        return text;
    }


    public String getType() {
        return type;
    }

}
