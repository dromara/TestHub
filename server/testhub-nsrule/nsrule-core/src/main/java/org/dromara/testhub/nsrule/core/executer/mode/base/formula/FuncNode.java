package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.dromara.testhub.nsrule.core.constant.ExceptionCode;
import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandler;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandlerFactory;
import org.dromara.testhub.nsrule.core.expand.FormulaBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/8 22:06
 */
public class FuncNode extends FormulaNode {
    //内置方法
    protected String func;
    //attr1:,
    protected List<Param> params;
    //.val
    protected String lastAttr;


    public FuncNode(String text, FormulaBuilder builder) {
        this.type = "FUNC";
        this.text = text;
        this.params = new ArrayList<>();
        CutObj cutObj = cut(text);
        if (text.endsWith("}")) {
            //%{xxxx()}
            this.lastAttr = "all";
        } else {
            //%{xxxx()}.val
            this.lastAttr = text.substring(text.lastIndexOf("}.") + 2);
        }

        int leftIndex = 0;
        //%{xxxx
        this.func = cutObj.paragraphs.get(leftIndex).substring(2);
        int startIndex = leftIndex + 1;
        do {
            String nowStr = cutObj.paragraphs.get(startIndex);
            if (nowStr.startsWith("},")) {
                nowStr = nowStr.substring(2);
            } else if (nowStr.startsWith("(")) {
                nowStr = nowStr.substring(1);
            }

            int endIndex = -1;
            if (nowStr.endsWith(":") && nowStr.indexOf(",") != -1) {
                //attr1:固定值,attr2:
                List<String> ps = Arrays.asList(nowStr.split(","));
                for (int i = 0; i < ps.size(); i++) {
                    FormulaNode node = null;
                    String key = getKey(ps.get(i));
                    String val = null;
                    if (i == ps.size() - 1) {
                        //attr1:
                        val = cutObj.getStr(startIndex + 1);
                        endIndex = cutObj.getEnd(startIndex + 1);
                        node = builder.getFormulaNode(val);
                    } else {
                        //attr1:固定值 或者 attr1:固定值
                        val = ps.get(i).substring(ps.get(i).indexOf(":") + 1);
                        node = builder.getFormulaNode(val);
                    }
                    params.add(new Param(key, node));
                }
            } else if (nowStr.endsWith(":")) {
                //attr1:
                String key = getKey(nowStr);
                String val = cutObj.getStr(startIndex + 1);
                endIndex = cutObj.getEnd(startIndex + 1);
                FormulaNode node = builder.getFormulaNode(val);
                params.add(new Param(key, node));
            } else if (!nowStr.endsWith(":") && !nowStr.trim().equals("")) {
                //attr1:固定值  或则者 attr1:固定值, attr2:XXXX
                do {
                    String key = getKey(nowStr);
                    String val = null;
                    if (nowStr.indexOf(",") == -1) {
                        val = nowStr.substring(nowStr.indexOf(":") + 1);
                        params.add(new Param(key, new DataNode(val)));
                    } else {
                        val = nowStr.substring(nowStr.indexOf(":") + 1, nowStr.indexOf(","));
                        params.add(new Param(key, new DataNode(val)));
                    }
                    if (nowStr.length() > key.length() + val.length() + 2) {
                        nowStr = nowStr.substring(key.length() + val.length() + 2);
                    } else {
                        nowStr = null;
                    }
                } while (nowStr != null && nowStr.length() > 0);
                endIndex = cutObj.getEnd(startIndex + 1);

            } else if (!nowStr.trim().equals("")) {
                throw new RuleException(ExceptionCode.EC_0005, text);
            } else {
//                throw new BlException(ExceptionCode.EC_0005,text);
            }
            if (endIndex != -1) {
                if (cutObj.paragraphs.get(endIndex).equals("}")) {
                    //}
                    break;
                } else if (cutObj.paragraphs.get(endIndex).startsWith("},")) {
                    //},
                    startIndex = endIndex;
                } else {
                    throw new RuleException(ExceptionCode.EC_0005, text);
                }
            } else {
                break;
            }


        } while (true);
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        FuncLog log = null;
        if (isLog) {
            log = new FuncLog(this);
        }

        FunctionHandlerFactory handlerFactory = context.getProject().getRuleConfig().getFunctionHandlerFactory();
        FunctionHandler functionHandler = handlerFactory.getFunctionHandler(func);
        JSONObject attrs = paramsHandler(context, log);
        Object reData = functionHandler.execute(context, attrs);

        result.setContent(reData);

        result.setLog(log);

        if (isLog) {
            log.setData(reData);
        }

        return result;
    }

    /**
     * 处理入参
     *
     * @param context
     * @return
     */
    protected JSONObject paramsHandler(Context context, FuncLog funcLog) {
        JSONObject attrs = new JSONObject();

        boolean isLog = funcLog == null ? false : true;

        if (isLog) {
            List<ParamLog> paramLogs = new ArrayList<>();
            funcLog.setParamLogs(paramLogs);
            for (Param param : params) {
                Result<Object> result = param.getNode().apply(context, true);
                attrs.put(param.getCode(), result.getContent());
                ParamLog paramLog = new ParamLog(param.getCode(), (FormulaLog) result.getLog());
                paramLogs.add(paramLog);
            }
        } else {
            for (Param param : params) {
                attrs.put(param.getCode(), param.getNode().apply(context, false).getContent());
            }
        }
        return attrs;
    }

    /**
     * 处理出参
     *
     * @param reData
     * @return
     */
    protected Object resultHandler(Object reData) {
        if (lastAttr.equals("all")) {
            return reData;
        } else {
            return JSONPath.eval(reData, "$." + lastAttr, false);
        }
    }


    public static class Param {
        private String code;
        private FormulaNode node;

        public Param(String code, FormulaNode node) {
            this.code = code;
            this.node = node;
        }

        public String getCode() {
            return code;
        }

        public FormulaNode getNode() {
            return node;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setNode(FormulaNode node) {
            this.node = node;
        }
    }

    public static class ParamLog {
        private String code;
        private FormulaLog log;

        public ParamLog(String code, FormulaLog log) {
            this.code = code;
            this.log = log;
        }

        public String getCode() {
            return code;
        }

        public FormulaLog getLog() {
            return log;
        }

        public void setLog(FormulaLog log) {
            this.log = log;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }


    public static class FuncLog extends FormulaLog {
        //内置方法
        protected String func;
        //attr1:,
        protected List<ParamLog> paramLogs;
        //.val
        protected String lastAttr;

        public FuncLog(FuncNode node) {
            super(node);
            this.func = node.func;
            this.lastAttr = node.lastAttr;
        }

        public List<ParamLog> getParamLogs() {
            return paramLogs;
        }

        public void setParamLogs(List<ParamLog> paramLogs) {
            this.paramLogs = paramLogs;
        }

        public String getFunc() {
            return func;
        }

        public void setFunc(String func) {
            this.func = func;
        }

        public String getLastAttr() {
            return lastAttr;
        }

        public void setLastAttr(String lastAttr) {
            this.lastAttr = lastAttr;
        }
    }
}
