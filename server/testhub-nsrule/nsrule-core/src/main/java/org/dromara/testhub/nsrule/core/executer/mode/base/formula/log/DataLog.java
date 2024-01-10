package org.dromara.testhub.nsrule.core.executer.mode.base.formula.log;

import org.dromara.testhub.nsrule.core.executer.mode.base.formula.FormulaNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataLog extends FormulaLog {

    private List<FormulaLog> dataLog = new ArrayList<>();
    private Map<String, FormulaLog> mapLog = new HashMap<>();

    public DataLog(FormulaNode node) {
        super(node);
    }

    public List<FormulaLog> getDataLog() {
        return dataLog;
    }

    public void setDataLog(List<FormulaLog> dataLog) {
        this.dataLog = dataLog;
    }

    public void setMapLog(Map<String, FormulaLog> mapLog) {
        this.mapLog = mapLog;
    }

    public Map<String, FormulaLog> getMapLog() {
        return mapLog;
    }
}
