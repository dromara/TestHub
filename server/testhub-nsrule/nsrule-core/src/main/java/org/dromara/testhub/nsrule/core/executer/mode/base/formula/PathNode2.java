package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:13
 */
public class PathNode2 extends FormulaNode {
    public String path;
    public String val;
    public FormulaNode startFormulaNode;
    public FormulaNode endFormulaNode;
    public FormulaNode indexFormulaNode;
    public String suffix;

    public PathNode2() {

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

        public PathLog(PathNode2 node) {
            super(node);
            this.suffix = node.suffix;
            this.path = node.path;
            this.val = node.val;
        }

    }

}
