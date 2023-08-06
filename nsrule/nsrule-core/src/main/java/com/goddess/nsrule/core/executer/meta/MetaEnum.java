package com.goddess.nsrule.core.executer.meta;

import com.goddess.nsrule.core.executer.mode.BasePo;

import java.util.List;

/**
 * 规则对象属相性项枚举
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 21:12
 */
public class MetaEnum extends BasePo {

    /**
     * 类型：固定动态Constant.EnumType
     */
    public String type;

    public String classPath;
    /**
     * 明细项列表
     */
    public List<Item> items;


    /**
     * 明细项
     */
    public static class Item {
        /**
         * 编码
         */
        private String code;
        /**
         * 父编码
         */
        private String pCode;
        /**
         * 名称
         */
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public String getpCode() {
            return pCode;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setpCode(String pCode) {
            this.pCode = pCode;
        }
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
