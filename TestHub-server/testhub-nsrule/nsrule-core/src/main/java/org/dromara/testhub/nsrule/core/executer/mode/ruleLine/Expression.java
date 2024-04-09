package org.dromara.testhub.nsrule.core.executer.mode.ruleLine;

import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.RuleConfig;
import org.dromara.testhub.nsrule.core.executer.mode.BasePo;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;
import org.dromara.testhub.nsrule.core.executer.operation.OperationFactory;

import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/8/30 15:53
 */
public class Expression extends BasePo {


    //操作符类型
    //逻辑logic  关系relation
    private String expressionType;
    //操作符编码
    private String operationCode;


    //被比较的阀值维度
    private Integer coverComplex;
    //被比较的阀值
    private String cover;
    private FormulaNode coverFormula;

    //数据类型
    private String dataType;

    //阀值维度
    private Integer thresholdComplex;
    //阀值
    private String threshold;
    private FormulaNode thresholdFormula;


    private List<Expression> subExpression;


    public boolean check() {
        if (expressionType == null) {
            throw new RuleException("操作符类型");
        }
        if (operationCode == null) {
            throw new RuleException("操作符不能为空");
        }

        if (RuleConstant.ExpressionType.LOGIC.equals(expressionType)) {
            if ((!RuleConstant.OperationType.OR.equals(operationCode)) && (!RuleConstant.OperationType.AND.equals(operationCode))) {
                for (Expression t : subExpression) {
                    t.check();
                }
            }
            return true;
        }
        if (RuleConstant.ExpressionType.RELATION.equals(expressionType)) {
            if (cover == null) {
                throw new RuleException("操作数字段不能为空");
            }
            OperationFactory.getOperation(operationCode);
            return true;
        }
        throw new RuleException("操作符类型 必须为 logic 或者 relation");
    }
    //public boolean check(MetaContext metaContext){
    //    if(operationCode==null){
    //        throw new RuleException("操作符不能为空");
    //    }
    //    if ("logic".equals(operationType)){
    //        if((!"or".equals(operationCode))&&(!"and".equals(operationCode))){
    //            for (RuleExpression t:subExpression) {
    //                t.check(metaContext);
    //            }
    //        }
    //        return true;
    //    }
    //    if ("relation".equals(operationType)){
    //        if(propertyPath==null){
    //            throw new RuleException("操作数字段不能为空");
    //        }
    //        OperationFactory.getOperation(operationCode);
    //        if(!metaContext.getPropertyMap().containsKey(metaObjectCode+"."+propertyPath)){
    //            throw new RuleException("操作数字段不存在");
    //        }
    //        return true;
    //    }
    //    throw new RuleException("操作符类型 必须为 logic 或者 relation");
    //}

    public Integer getCoverComplex() {
        return coverComplex;
    }

    public void setCoverComplex(Integer coverComplex) {
        this.coverComplex = coverComplex;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        if (cover == null) {
            this.cover = null;
            this.coverFormula = null;
        } else {
            RuleConfig ruleConfig = RuleConfig.getInstance();
            this.coverFormula = ruleConfig.getFormulaBuilder().getFormulaNode(cover.toString());
            this.cover = cover.toString();
        }
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getThresholdComplex() {
        return thresholdComplex;
    }

    public void setThresholdComplex(Integer thresholdComplex) {
        this.thresholdComplex = thresholdComplex;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        RuleConfig ruleConfig = RuleConfig.getInstance();
        this.thresholdFormula = ruleConfig.getFormulaBuilder().getFormulaNode(threshold);
        this.threshold = threshold;
    }


    public String getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(String expressionType) {
        this.expressionType = expressionType;
    }

    public FormulaNode getCoverFormula() {
        return coverFormula;
    }

    public FormulaNode getThresholdFormula() {
        return thresholdFormula;
    }

    public List<Expression> getSubExpression() {
        return subExpression;
    }

    public void setSubExpression(List<Expression> subExpression) {
        this.subExpression = subExpression;
    }

}
