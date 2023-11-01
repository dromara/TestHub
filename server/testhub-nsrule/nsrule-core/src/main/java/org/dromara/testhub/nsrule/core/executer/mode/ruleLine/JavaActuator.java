package org.dromara.testhub.nsrule.core.executer.mode.ruleLine;

import org.dromara.testhub.nsrule.core.constant.Constant;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;
import org.dromara.testhub.nsrule.core.executer.operation.OperationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vinc
 */

public class JavaActuator {

    public static boolean execute(Context context, Expression expression, Log log) {
        if (log != null) {
            log.setOperationCode(expression.getOperationCode());
            log.setOperationType(expression.getExpressionType());
        }
        if (Constant.ExpressionType.LOGIC.equals(expression.getExpressionType())) {
            //逻辑
            return execute(expression.getOperationCode(), expression.getSubExpression(), context, log);
        } else {

            //关系
            // 1>2  1被比较的阀值 >操作符 2阀值
            Operation operation = OperationFactory.getOperation(expression.getOperationCode());
            Result<Object> coverResult = expression.getCoverFormula().apply(context, log == null ? false : true);
            Object cover = coverResult.getContent();
            Object threshold = null;
            Result<Object> thresholdResult = null;
            if (!operation.isOneOp()) {
                thresholdResult = expression.getThresholdFormula().apply(context, log == null ? false : true);
                threshold = thresholdResult.getContent();
            }

            boolean execute = operation.execute(expression.getDataType().toUpperCase(), expression.getCoverComplex(), cover, expression.getThresholdComplex(), threshold);

            if (log != null) {
                log.setDataType(expression.getDataType());
                log.setCoverComplex(expression.getCoverComplex());
                log.setCover(expression.getCoverFormula().getText());
                log.setCoverData(cover);
                log.setCoverLog((FormulaLog) coverResult.getLog());


                if (!operation.isOneOp()) {
                    log.setThresholdComplex(expression.getThresholdComplex());
                    log.setThreshold(expression.getThresholdFormula().getText());
                    log.setThresholdData(threshold);
                    log.setThresholdLog((FormulaLog) thresholdResult.getLog());
                }

                log.setFlag(execute);
            }
            //关系
            return execute;
        }
    }


    public static boolean execute(Context context, Expression expression) {
        return execute(context, expression, null);
    }

    //逻辑
    private static boolean execute(String operationCode, List<Expression> subExpressions, Context context, Log log) {
        if ("or".equals(operationCode)) {
            for (Expression expression : subExpressions) {
                boolean flag;
                if (log != null) {
                    Log subLog = new Log();
                    log.addSubLog(subLog);
                    flag = JavaActuator.execute(context, expression, subLog);
                    log.setFlag(flag);
                } else {
                    flag = JavaActuator.execute(context, expression, null);
                }
                if (flag) {
                    return true;
                }
            }
            return false;
        } else {
            for (Expression expression : subExpressions) {
                boolean flag;
                if (log != null) {
                    Log subLog = new Log();
                    log.addSubLog(subLog);
                    flag = JavaActuator.execute(context, expression, subLog);
                    log.setFlag(flag);
                } else {
                    flag = JavaActuator.execute(context, expression, null);
                }
                if (!flag) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class Log {
        private String dataType;

        private int coverComplex;
        private String cover;
        private Object coverData;
        private FormulaLog coverLog;

        private String operationCode;
        private String operationType;

        private int thresholdComplex;
        private String threshold;
        private Object thresholdData;
        private FormulaLog thresholdLog;

        private boolean flag;

        private List<Log> subLog;


        public String getMsg(int l) {
            StringBuilder msg = new StringBuilder();
            for (int i = 0; i < l; i++) {
                msg.append("|\t");
            }
            if (Constant.ExpressionType.LOGIC.equalsIgnoreCase(operationType)) {
                msg.append("操作符[" + operationCode + "]==结果[" + flag + "]\n");
                for (Log log : subLog) {
                    msg.append(log.getMsg(l + 1));
                }
            } else {
                msg.append("数据类型[" + dataType + "]被比较数[" + coverData + "]操作符[" + operationCode + "]比较数[" + thresholdData + "]==结果[" + flag + "]\n");
            }
            return msg.toString();
        }

        public List<Log> getSubLog() {
            return subLog;
        }

        public void addSubLog(Log log) {
            if (subLog == null) {
                subLog = new ArrayList<>();
            }
            subLog.add(log);
        }

        public String getOperationType() {
            return operationType;
        }

        public void setOperationType(String operationType) {
            this.operationType = operationType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public void setCoverComplex(int coverComplex) {
            this.coverComplex = coverComplex;
        }

        public void setOperationCode(String operationCode) {
            this.operationCode = operationCode;
        }

        public void setThresholdComplex(int thresholdComplex) {
            this.thresholdComplex = thresholdComplex;
        }

        public FormulaLog getCoverLog() {
            return coverLog;
        }

        public void setCoverLog(FormulaLog coverLog) {
            this.coverLog = coverLog;
        }

        public FormulaLog getThresholdLog() {
            return thresholdLog;
        }

        public void setThresholdLog(FormulaLog thresholdLog) {
            this.thresholdLog = thresholdLog;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getDataType() {
            return dataType;
        }

        public int getCoverComplex() {
            return coverComplex;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public Object getCoverData() {
            return coverData;
        }

        public void setCoverData(Object coverData) {
            this.coverData = coverData;
        }

        public String getOperationCode() {
            return operationCode;
        }

        public int getThresholdComplex() {
            return thresholdComplex;
        }

        public String getThreshold() {
            return threshold;
        }

        public void setThreshold(String threshold) {
            this.threshold = threshold;
        }

        public Object getThresholdData() {
            return thresholdData;
        }

        public void setThresholdData(Object thresholdData) {
            this.thresholdData = thresholdData;
        }

        public boolean isFlag() {
            return flag;
        }
    }
}
