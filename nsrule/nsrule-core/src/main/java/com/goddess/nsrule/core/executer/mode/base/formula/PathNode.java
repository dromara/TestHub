package com.goddess.nsrule.core.executer.mode.base.formula;

import com.goddess.nsrule.core.executer.context.Context;
import com.goddess.nsrule.core.executer.mode.base.Result;
import com.goddess.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import com.goddess.nsrule.core.expand.FormulaBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:13
 */
public class PathNode extends FormulaNode {
    public String path;
    public String val;
    public FormulaNode indexFormulaNode;
    public String suffix;

    public PathNode(String text, FormulaBuilder builder) {
        this.type = "PATH";
        String nowStr = text.substring(2, text.length() - 1);

        if (nowStr.indexOf("[") > 0 && nowStr.endsWith("]")) {
            //attr[XXXX]
            this.val = nowStr.substring(0, nowStr.indexOf("["));
            nowStr = nowStr.substring(this.val.length());
            indexFormulaNode = builder.getFormulaNode(nowStr.substring(1, nowStr.length() - 1));

        } else if (nowStr.indexOf("[") > 0 && !nowStr.endsWith("]")) {
            //attr[XXXX].固定值
            this.val = nowStr.substring(0, nowStr.indexOf("["));
            nowStr = nowStr.substring(this.val.length());
            indexFormulaNode = builder.getFormulaNode(nowStr.substring(1, nowStr.lastIndexOf("]")));
            this.suffix = nowStr.substring(nowStr.lastIndexOf("]") + 2);
        } else {

            this.val = nowStr;
        }

        this.path = "$." + val;
        this.text = text;
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        PathLog log = null;
        if (isLog) {
            log = new PathLog(this);
        }

        Object data = null;
        if (indexFormulaNode == null) {
            data = context.getObject(path);
        } else {
            Result<Object> indexResult = indexFormulaNode.apply(context, true);
            if (isLog) {
                log.indexLog = (FormulaLog) indexResult.getLog();
            }
            int index = 0;
            if (!indexResult.isBizError() && !indexResult.isFlag()) {
                if (indexResult.getContent() != null && StringUtils.isNotBlank(indexResult.getContent().toString())) {
                    try {
                        index = Integer.parseInt(indexResult.getContent().toString());
                    } catch (Exception e) {

                    }
                }
            }
            if (isLog) {
                log.index = index;
            }
            if (suffix == null) {
                data = context.getObject(path + "[" + index + "]");
            } else {
                data = context.getObject(path + "[" + index + "]." + suffix);
            }

        }
        result.setContent(data);
        result.setLog(log);
        if (isLog) {
            log.setData(data);
        }
        return result;
    }

    public static class PathLog extends FormulaLog {
        public String path;
        public String suffix;
        public String val;
        public Integer index;
        public FormulaLog indexLog;

        public PathLog(PathNode node) {
            super(node);
            this.suffix = node.suffix;
            this.path = node.path;
            this.val = node.val;
        }

    }

}
