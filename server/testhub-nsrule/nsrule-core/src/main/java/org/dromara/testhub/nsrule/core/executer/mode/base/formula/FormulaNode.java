package org.dromara.testhub.nsrule.core.executer.mode.base.formula;


import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;

import java.util.*;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:03
 */
public abstract class FormulaNode {
    public String dataType;
    public String type;
    public String text;

    public FormulaNode(String type,String text){
        this.type = type;
        this.text = text;
    }

    public Result<Object> apply(Context context) {
        return apply(context, false);
    }

    public Result<Object> apply(Context context, boolean isLog) {

        Result<Object> result = null;
        try {
            result = doApply(context, isLog);
        } catch (RuleException e) {
            throw e;
        } catch (Exception e) {
            throw new RuleException(text + "计算失败", e);
        }
//        if(!result.isFlag()){
//            try {
//                result.setContent();
//            } catch (Exception e) {
//                throw new RuleException(result.toString() + "类型转换为"+dataType+"失败", e);
//            }
//        }
        return result;
    }

    public abstract Result<Object> doApply(Context context, boolean isLog);

    public FormulaNode simplify(){
        return this;
    }
    public boolean canSimplify(){
        return false;
    }

    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }


}
