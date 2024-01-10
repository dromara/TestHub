package org.dromara.testhub.nsrule.core.executer.mode.base.formula;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.mode.base.Result;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.FormulaLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.formula.log.PathLog;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandler;
import org.dromara.testhub.nsrule.core.executer.mode.base.function.FunctionHandlerFactory;

import java.util.ArrayList;
import java.util.List;

public class FuncNode extends FormulaNode {
    public static String TYPE = "FUNC";
    //内置方法
    public String func;
    //attr1:,
    public List<Param> params;
    //.val
    public PathNode lastAttr;


    public FuncNode(String text) {
        super(TYPE, text);
        this.type = "FUNC";
        this.text = text;
        this.params = new ArrayList<>();
    }

    @Override
    public Result<Object> doApply(Context context, boolean isLog) {
        Result<Object> result = new Result<>();
        FuncLog log = null;
        if (isLog) {
            log = new FuncLog(this);
        }

        FunctionHandlerFactory handlerFactory = context.getFunctionHandlerFactory();
        FunctionHandler functionHandler = handlerFactory.getFunctionHandler(func);
        JSONObject attrs = paramsHandler(context, log);
        Object reData = functionHandler.execute(context, attrs);
        reData = resultHandler(context, log, reData);
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

        boolean isLog = funcLog != null;

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
     * @return
     */
    protected Object resultHandler(Context context, FuncLog log, Object reData) {
        if (lastAttr == null) {
            return reData;
        }
        try {
            //todo 后边要优化
            context.pushData(JSONObject.parseObject(JSONObject.toJSONString(reData)));
            Result<Object> result = lastAttr.apply(context, log != null);
            if (log != null) {
                log.setLastAttrLog((PathLog) result.getLog());
            }
            return result.getContent();
        } catch (Exception e) {
            context.removeData();
        }
        return reData;
    }

    public static class Param {
        private String code;
        private FormulaNode node;

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
        protected PathLog lastAttrLog;

        public FuncLog(FuncNode node) {
            super(node);
            this.func = node.func;
//            this.lastAttr = node.lastAttr;
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

        public PathLog getLastAttrLog() {
            return lastAttrLog;
        }

        public void setLastAttrLog(PathLog lastAttrLog) {
            this.lastAttrLog = lastAttrLog;
        }
    }
}
