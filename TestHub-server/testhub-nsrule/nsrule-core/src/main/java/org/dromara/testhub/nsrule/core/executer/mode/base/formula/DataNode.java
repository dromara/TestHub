package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.DefContext;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.DataLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author vinc
 */
public class DataNode extends FormulaNode {
    public static String TYPE = "DATA";

    public static int EXEC_DATA = 1;
    public static int EXEC_DATAS = 2;
    public static int EXEC_MAP = 3;
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
            processNodes(datas, dataLog, temps, context, isLog);
            result.setContent(temps);
        } else if (exec == EXEC_MAP) {
            Map<String, Object> temps = new HashMap<>();
            processMap(map, dataLog, temps, context, isLog);
            result.setContent(temps);
        }

        if (dataLog != null) {
            dataLog.setData(result.getContent());
        }
        return result;
    }

    public FormulaNode simplify() {
        if (exec == EXEC_DATA) {
            return this;
        }

        if (exec == EXEC_DATAS) {
            List<FormulaNode> nodes = new ArrayList<>();
            boolean flag = true;
            for (FormulaNode node : datas) {
                FormulaNode node1 = node.simplify();
                if(!node1.canSimplify()){
                    flag = false;
                }
                nodes.add(node1);
            }
            if(flag){
                List<Object> temps = new ArrayList<>();
                processNodes(datas, null, temps, new DefContext(), false);
                exec = EXEC_DATA;
                data = temps;
                datas = null;
            }else {
                datas = nodes;
            }
        } else if (exec == EXEC_MAP) {
            AtomicBoolean flag = new AtomicBoolean(true);
            Map<String, FormulaNode> tempMap = new HashMap<>();

            map.forEach((key, node) -> {
                FormulaNode node1 = node.simplify();
                if(!node1.canSimplify()){
                    flag.set(false);
                }
                tempMap.put(key, node1);
            });
            if(flag.get()){
                Map<String, Object> temps = new HashMap<>();
                processMap(map, null, temps, new DefContext(), false);
                exec = EXEC_DATA;
                data = temps;
                map = null;
            }else {
                map = tempMap;
            }


        }
        return this;
    }
    @Override
    public boolean canSimplify(){
        if(this.getExec() != EXEC_DATA){
            return false;
        }
        return true;
    }



    private void processNodes(List<FormulaNode> nodes, DataLog dataLog, List<Object> temps, Context context, boolean isLog) {
        for (FormulaNode node : nodes) {
            var temp = node.apply(context, isLog);
            if (dataLog != null) {
                dataLog.getNodes().add((FormulaLog) temp.getLog());
            }
            temps.add(temp.getContent());
        }
    }

    private void processMap(Map<String, FormulaNode> inputMap, DataLog dataLog, Map<String, Object> temps, Context context, boolean isLog) {
        inputMap.forEach((key, value) -> {
            var temp = value.apply(context, isLog);
            if (dataLog != null) {
                dataLog.getMapLog().put(key, (FormulaLog) temp.getLog());
            }
            temps.put(key, temp.getContent());
        });
    }


    public int getExec() {
        return exec;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }



}
