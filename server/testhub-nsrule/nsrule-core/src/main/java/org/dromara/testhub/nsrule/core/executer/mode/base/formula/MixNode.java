package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.DefContext;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.MinLog;

import java.util.ArrayList;
import java.util.List;


/**
 * @author vinc
 */
public class MixNode extends FormulaNode {
    public static String TYPE = "MIX";
    private List<FormulaNode> datas;

    public MixNode(List<FormulaNode> datas, String text) {
        super(TYPE, text);
        this.datas = datas;
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        MinLog log = isLog ? new MinLog(this) : null;

        List<FormulaLog> dayaLogs = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (FormulaNode tNode : datas) {
            Result<Object> temp = tNode.apply(context, isLog);
            dayaLogs.add((FormulaLog) temp.getLog());
            if(temp.getContent()!=null){
                stringBuilder.append(temp.getContent().toString());
            }
        }
        result.setContent(stringBuilder.toString());
        if (log != null) {
            log.setNodes(dayaLogs);
            log.setData(result.getContent());
            result.setLog(log);
        }

        return result;
    }

    public FormulaNode simplify() {
        List<FormulaNode> nodes = new ArrayList<>();
        DataNode per = null;
        //相连的两个dataNode可以合并
        for (int i = 0; i < datas.size(); i++) {
            FormulaNode thisNode = datas.get(i);
            thisNode.simplify();
            if (per == null) {
                if(thisNode instanceof MixNode mixNode){
                    nodes.addAll(mixNode.datas);
                }else {
                    nodes.add(thisNode);
                }
                if (nodes.get(nodes.size()-1).canSimplify()) {
                    per = (DataNode)nodes.get(nodes.size()-1);
                }
            }else  if (thisNode.canSimplify()) {
                per.text = per.text + thisNode.text;
                per.setData(per.text);
            }else {
                if(thisNode instanceof MixNode mixNode){
                    nodes.addAll(mixNode.datas);
                }else {
                    nodes.add(thisNode);
                }
                per = null;
            }
        }
        this.datas = nodes;
        if(canSimplify()){
            return new DataNode(text);
        }
        return this;
    }
    @Override
    public boolean canSimplify(){
        for (FormulaNode node : datas) {
            if(!node.canSimplify()){
                return false;
            }
        }
        return true;
    }
}
