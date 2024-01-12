package org.dromara.testhub.nsrule.core.executer.mode.base.formula.log;

import org.dromara.testhub.nsrule.core.executer.mode.base.formula.DataNode;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLog extends FormulaLog {
    private int exec;
    private List<FormulaLog> nodes = new ArrayList<>();
    private Map<String, FormulaLog> mapLog = new HashMap<>();

    public DataLog(DataNode node) {
        super(node);
        exec = node.getExec();
    }

    public List<FormulaLog> getNodes() {
        return nodes;
    }

    public void setNodes(List<FormulaLog> nodes) {
        this.nodes = nodes;
    }

    public void setMapLog(Map<String, FormulaLog> mapLog) {
        this.mapLog = mapLog;
    }

    public Map<String, FormulaLog> getMapLog() {
        return mapLog;
    }

    public int getExec() {
        return exec;
    }

    public void setExec(int exec) {
        this.exec = exec;
    }
}
