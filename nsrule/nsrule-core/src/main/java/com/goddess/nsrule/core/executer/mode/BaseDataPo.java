package com.goddess.nsrule.core.executer.mode;

import com.goddess.nsrule.core.executer.context.RuleConfig;
import com.goddess.nsrule.core.executer.mode.base.formula.FormulaNode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/9/4 19:33
 */

public class BaseDataPo extends BasePo {
    //数据类型
    private String dataType;
    //data
    private String data;
    private FormulaNode dataFormulaNode;
    private int complex = 0;
    //规则对象编码
    private String metaClassCode;


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getData() {
        return data;
    }

    public FormulaNode getDataFormulaNode() {
        return dataFormulaNode;
    }

    public void setData(String data) {
        RuleConfig ruleConfig = RuleConfig.getInstance();
        if (StringUtils.isEmpty(data)) {
            this.dataFormulaNode = null;
            this.data = null;
        } else {
            this.dataFormulaNode = ruleConfig.getFormulaBuilder().getFormulaNode(data);
            this.data = data;
        }

    }

    public void setDataFormulaNode(FormulaNode dataFormulaNode) {
        this.dataFormulaNode = dataFormulaNode;
    }

    public int getComplex() {
        return complex;
    }

    public void setComplex(int complex) {
        this.complex = complex;
    }

    public String getMetaClassCode() {
        return metaClassCode;
    }

    public void setMetaClassCode(String metaClassCode) {
        this.metaClassCode = metaClassCode;
    }
}
