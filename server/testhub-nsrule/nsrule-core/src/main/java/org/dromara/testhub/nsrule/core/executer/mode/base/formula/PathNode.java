package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import com.google.common.base.Joiner;
import org.dromara.testhub.nsrule.core.constant.RuleConstant;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.DefContext;
import org.dromara.testhub.nsrule.core.executer.meta.MetaJavaType;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.PathItemLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.PathLog;
import org.dromara.testhub.nsrule.core.executer.operation.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vinc
 */
public class PathNode extends FormulaNode {
    public static String TYPE = "PATH";
    public List<PathItem> items;

    public PathNode(String text) {
        super(TYPE, text);
        this.text = text;
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        PathLog log = new PathLog(this);
        String path = getPath(context, log, isLog);
        log.setPath(path);
        Object data = context.getObject("$" + path);
        result.setContent(data);
        if(log!=null){
            log.setPath("$" + path);
            log.setData(data);
        }
        result.setLog(isLog ? log : null);

        return result;
    }

    public FormulaNode simplify() {
        if (canSimplify()) {
            String path = getPath(new DefContext(), new PathLog(this), false);
            path = path.substring(1);
            List<PathItem> items1 = new ArrayList<>();
            items1.add(new PathItem(path));
            setItems(items1);
        }
        return this;
    }

    public boolean canSimplify() {
        for (PathItem item : items) {
            if (!item.canSimplify()) {
                return false;
            }
        }
        return true;
    }

    private String getPath(Context context, PathLog log, boolean isLog) {
        StringBuilder path = new StringBuilder();
        List<PathItemLog> itemLogs = new ArrayList<>();
        log.setItemLogs(itemLogs);
        for (PathItem item : items) {
            PathItemLog itemLog = new PathItemLog();
            String str = item.getPath(context, itemLog, isLog);
            itemLog.setAttrName(str);
            path.append(str);
            itemLogs.add(itemLog);
        }
        return path.toString();
    }

    public static class PathItem {
        //*
        public static final int TYPE_ALL = -1;
        //属性
        public static final int TYPE_ATTR = 0;
        //索引
        public static final int TYPE_INDEX = 1;
        //范围
        public static final int TYPE_SECT = 2;
        //范围+步长
        public static final int TYPE_SECT_STEP = 4;
        //非空
        public static final int TYPE_NOT_NULL = 5;

        //计算
        public static final int TYPE_EXP = 6;


        public static final String IN = "in";
        public static final String NOT = "not";
        public static final String LIKE = "like";
        public static final String RLIKE = "rlike";

        public static final String EQ = "=";
        public static final String NEQ = "!=";
        public static final String GT = ">";
        public static final String LT = "<";
        public static final String GE = ">=";
        public static final String LE = "<=";


        public int type;
        public String op;
        public String attrName;

        public List<FormulaNode> nodes = new ArrayList<>();

        public PathItem(int type) {
            this.type = type;
        }

        public PathItem(String attrName) {
            this.type = TYPE_ATTR;
            this.attrName = attrName;
        }

        public PathItem(FormulaNode index) {
            this.type = TYPE_INDEX;
            nodes.add(index);
        }

        public PathItem(String attrName, String op, FormulaNode data) {
            this.attrName = attrName;
            this.type = TYPE_EXP;
            this.op = op.trim();
            nodes.add(data);
        }

        public PathItem(FormulaNode start, FormulaNode end) {
            this.type = TYPE_SECT;
            nodes.add(start);
            nodes.add(end);
        }

        public PathItem(FormulaNode start, FormulaNode end, FormulaNode step) {
            this.type = TYPE_SECT_STEP;
            nodes.add(start);
            nodes.add(end);
            nodes.add(step);
        }

        public PathItem(List<FormulaNode> indexes) {
            this.type = TYPE_INDEX;
            this.nodes = indexes;
        }

        public PathItem(String attrName, boolean flag) {
            this.type = TYPE_NOT_NULL;
            this.attrName = attrName;
        }

        public String getPath(Context context, PathItemLog itemLog, boolean isLog) {
            switch (type) {
                case TYPE_ALL:
                    return "[*]";
                case TYPE_ATTR:
                    return "." + attrName;
                case TYPE_INDEX:
                    return "[" + getPathIndex(context, itemLog, isLog) + "]";
                case TYPE_SECT:
                    return "[" + getPathSect(context, itemLog, isLog) + "]";
                case TYPE_SECT_STEP:
                    return "[" + getPathSectStep(context, itemLog, isLog) + "]";
                case TYPE_NOT_NULL:
                    return "[" + getPathNotNull(context, isLog) + "]";
                case TYPE_EXP:
                    return "[" + getPathExp(context, isLog) + "]";
            }
            return "";
        }

        private String getPathExp(Context context, boolean isLog) {
            String path = attrName + " " + op + " ";
            String val;
            if (op.contains(IN)) {
                List<String> paths = new ArrayList<>();
                for (FormulaNode node : nodes) {
                    Object content = node.apply(context, isLog).getContent();
                    if (MetaJavaType.checkDataType(content, RuleConstant.DataType.NUMBER)) {
                        paths.add(content.toString());
                    } else {
                        String v = content.toString();
                        if (v.startsWith("'") && v.endsWith("'")) {
                            paths.add(v);
                        } else {
                            paths.add("'" + v + "'");
                        }
                    }
                }
                val = "( " + Joiner.on(",").join(paths) + " )";
            } else {
                Object content = nodes.get(0).apply(context, isLog).getContent();
                if (MetaJavaType.checkDataType(content, RuleConstant.DataType.STRING)) {
                    String v = content.toString();
                    if (v.startsWith("'") && v.endsWith("'")) {
                        val = v;
                    } else {
                        val = "'" + v + "'";
                    }
                } else {
                    val = content.toString();
                }
            }
            return path + val;
        }

        private String getPathNotNull(Context context, boolean isLog) {
            return "?(" + attrName + ")";
        }

        private String getPathSect(Context context, PathItemLog itemLog, boolean isLog) {
            List<FormulaLog> nodeLogs = new ArrayList<>();
            itemLog.setNodes(nodeLogs);
            Result<Object> result0 = nodes.get(0).apply(context, isLog);
            nodeLogs.add((FormulaLog) result0.getLog());
            Result<Object> result1 = nodes.get(1).apply(context, isLog);
            nodeLogs.add((FormulaLog) result1.getLog());

            return result0.getContent().toString() + ":" + result1.getContent().toString();
        }

        private String getPathSectStep(Context context, PathItemLog itemLog, boolean isLog) {
            List<FormulaLog> nodeLogs = new ArrayList<>();
            itemLog.setNodes(nodeLogs);
            Result<Object> result0 = nodes.get(0).apply(context, isLog);
            nodeLogs.add((FormulaLog) result0.getLog());
            Result<Object> result1 = nodes.get(1).apply(context, isLog);
            nodeLogs.add((FormulaLog) result1.getLog());
            Result<Object> result2 = nodes.get(2).apply(context, isLog);
            nodeLogs.add((FormulaLog) result2.getLog());
            return result0.getContent().toString() + ":" + result1.getContent().toString() + ":" + result2.getContent().toString();
        }

        private String getPathIndex(Context context, PathItemLog itemLog, boolean isLog) {
            List<String> paths = new ArrayList<>();
            List<FormulaLog> nodeLogs = new ArrayList<>();
            itemLog.setNodes(nodeLogs);
            for (FormulaNode node : nodes) {
                Result<Object> result = node.apply(context, isLog);
                nodeLogs.add((FormulaLog) result.getLog());
                paths.add(result.getContent().toString());
            }
            return Joiner.on(",").join(paths);
        }

        //=======================================================================================================简化相关
        public boolean canSimplify() {
            switch (type) {
                case TYPE_ALL:
                case TYPE_ATTR:
                case TYPE_NOT_NULL:
                    return true;
                case TYPE_INDEX:
                case TYPE_SECT:
                case TYPE_SECT_STEP:
                case TYPE_EXP:
                    return nodesCanSimplify();
            }
            return false;
        }

        public boolean nodesCanSimplify() {
            for (FormulaNode node : nodes) {
                //只有固定值的才行
                if (!(node instanceof DataNode)) {
                    return false;
                }
            }
            return true;
        }
    }

    public List<PathItem> getItems() {
        return items;
    }

    public void setItems(List<PathItem> items) {
        this.items = items;
    }


}
