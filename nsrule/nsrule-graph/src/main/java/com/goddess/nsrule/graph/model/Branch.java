package com.goddess.nsrule.graph.model;

import cn.hutool.core.collection.ListUtil;
import com.goddess.nsrule.core.constant.ExceptionCode;
import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.mode.BasePo;
import com.goddess.nsrule.core.executer.mode.base.Result;
import com.goddess.nsrule.core.executer.mode.base.action.Execute;
import com.goddess.nsrule.core.executer.mode.base.action.RunState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 22:10
 */
public class Branch extends BasePo {

    //连接
    private List<Link> links;
    //执行动作
    private List<Execute> execute;

    private Map<String, Link> linkExecuteMap;
    private Map<String, Integer> codeIndexMap;

    public Link decision(GraphContext context, int start) {
        for (Execute execute : execute) {
            try {
                RunState.Item stateItem = execute.execute(context);
                Result<Object> reData = stateItem.getResult();
                if (execute.isInit()) {
                    context.putRunData(execute.getCode(), reData);
                }
            } catch (RuleException e) {
                if (e.getCode().equals(ExceptionCode.EC_0301)) {
                    throw e;
                }
                if (execute.isBlock()) {
                    throw new RuleException(ExceptionCode.EC_0301, execute.getCode());
                }
            } catch (Exception e) {
                if (execute.isBlock()) {
                    throw new RuleException(ExceptionCode.EC_0301, execute.getCode());
                }
            }
        }
        for (int i = 0; i < links.size(); i++) {
            if (start <= i) {
                Link link = links.get(i);
                boolean flag = link.decision(context);
                context.execLink(link, flag);
                if (flag == true) {
                    return link;
                }
            }
        }
        //没找到符合条件的链接了需要回溯
        return null;
    }

    //获取指定链接编码的下一个链接
    public int getNextLinkExecuteIndex(String linkCode) {
        //找不到取最大的
        int index = codeIndexMap.getOrDefault(linkCode, links.size());
        //当前链接的优先级排序索引+1 小于链接个数 才有效
        if (index + 1 < links.size()) {
            return index + 1;
        } else {
            return -1;
        }
    }

    public List<Link> getLinks() {
        return links;
    }


    public void setLinks(List<Link> links) {
        this.linkExecuteMap = links.stream().collect(Collectors.toMap(Link::getCode, o -> o));
        this.links = ListUtil.sortByProperty(links, "priority");
        this.codeIndexMap = new HashMap<>(links.size());
        int index = 0;
        for (Link link : this.links) {
            this.codeIndexMap.put(link.getCode(), index);
            index++;
        }
    }

    public List<Execute> getExecute() {
        return execute;
    }

    public void setExecute(List<Execute> execute) {
        this.execute = execute;
    }
}
