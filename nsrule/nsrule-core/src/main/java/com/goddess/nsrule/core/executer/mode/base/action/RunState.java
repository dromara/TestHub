package com.goddess.nsrule.core.executer.mode.base.action;

import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.executer.mode.base.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2023/1/17 09:14
 */
public class RunState {
    //编码
    private String code;
    private List<Item> items = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Map<String, Item> getItemsMap() {
        return this.items.stream().collect(Collectors.toMap(o -> o.getCode(), o -> o));
    }

    public static class Item {
        private String code;
        private JSONObject params = new JSONObject();
        private JSONObject runParams = new JSONObject();
        private JSONObject systemParams = new JSONObject();
        private Result<Object> result;


        public JSONObject getParams() {
            return params;
        }

        public void setParams(JSONObject params) {
            this.params = params;
        }

        public void setResult(Result<Object> result) {
            this.result = result;
        }

        public Result<Object> getResult() {
            return result;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public JSONObject getRunParams() {
            return runParams;
        }

        public void addRunParams(String key, Object data) {
            if (this.runParams == null) {
                this.runParams = new JSONObject();
            }
            this.runParams.put(key, data);
        }

        public void addSystemParams(String key, Object data) {
            if (this.systemParams == null) {
                this.systemParams = new JSONObject();
            }
            this.systemParams.put(key, data);
        }

        public JSONObject getSystemParams() {
            return systemParams;
        }

        public void setRunParams(JSONObject runParams) {
            this.runParams = runParams;
        }
    }
}
