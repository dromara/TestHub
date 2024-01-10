package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.DefContext;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.DataLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author vinc
 */
public class DataNode extends FormulaNode {
    public static String TYPE = "DATA";

    private static int EXEC_DATA = 1;
    private static int EXEC_DATAS = 2;
    private static int EXEC_MAP = 3;
    private Object data;
    private int exec;
    private List<FormulaNode> datas;
    private Map<String, FormulaNode> map;

    public DataNode(String text) {
        super(TYPE, text);
        this.data = text;
        this.exec = EXEC_DATA;
    }


    public DataNode(String text, Object data) {
        super(TYPE, text);
        this.exec = EXEC_DATA;
        this.data = data;
    }

    public DataNode(String text, List<FormulaNode> datas) {
        super(TYPE, text);
        this.exec = EXEC_DATAS;
        this.datas = datas;
    }

    public DataNode(String text, Map<String, FormulaNode> map) {
        super(TYPE, text);
        this.map = map;
        this.exec = EXEC_MAP;
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();

        DataLog dataLog = isLog ? new DataLog(this) : null;
        result.setLog(dataLog);

        if (exec == EXEC_DATA) {
            result.setContent(data);
        } else if (exec == EXEC_DATAS) {
            List<Object> temps = new ArrayList<>();
            for (FormulaNode tNode : datas) {
                Result<Object> temp = tNode.apply(context, isLog);
                if (dataLog != null) {
                    dataLog.getDataLog().add((FormulaLog) temp.getLog());
                }
                temps.add(temp.getContent());
            }
            result.setContent(temps);

        } else if (exec == EXEC_MAP) {
            Map<String, Object> temps = new HashMap<>();
            map.forEach((key, value) -> {
                Result<Object> temp = value.apply(context, isLog);
                if (dataLog != null) {
                    dataLog.getMapLog().put(key, (FormulaLog) temp.getLog());
                }
                temps.put(key, temp.getContent());
            });
            result.setContent(temps);
        }

        if (dataLog != null) {
            dataLog.setData(result.getContent());
        }
        return result;
    }

    public FormulaNode simplify() {
        if (!canSimplify() || exec == EXEC_DATA) {
            return this;
        }
        data = apply(new DefContext()).getContent();
        if (exec == EXEC_DATAS) {
            datas = null;
        } else if (exec == EXEC_MAP) {
            map = null;
        }
        exec = EXEC_DATA;
        return this;
    }

    public boolean canSimplify() {
        if (exec == EXEC_DATAS) {
            for (FormulaNode tNode : datas) {
                if (!tNode.canSimplify()) {
                    return false;
                }
            }
        } else if (exec == EXEC_MAP) {
            for (FormulaNode tNode : map.values()) {
                if (!tNode.canSimplify()) {
                    return false;
                }
            }
        }
        return true;
    }

}
