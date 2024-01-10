package org.dromara.testhub.nsrule.core.executer.mode.base.formula.log;

import org.dromara.testhub.nsrule.core.executer.mode.base.formula.PathNode;

import java.util.List;

public  class PathLog extends FormulaLog {
    public String path;
    public List<PathItemLog> itemLogs;

    public PathLog(PathNode node) {
        super(node);
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<PathItemLog> getItemLogs() {
        return itemLogs;
    }

    public void setItemLogs(List<PathItemLog> itemLogs) {
        this.itemLogs = itemLogs;
    }
}