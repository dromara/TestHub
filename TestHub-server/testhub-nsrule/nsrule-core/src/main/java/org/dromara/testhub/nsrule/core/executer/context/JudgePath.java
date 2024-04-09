package org.dromara.testhub.nsrule.core.executer.context;

/**
 * 执行路径节点
 *
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/6/3 23:02
 */
public class JudgePath {
    private String branchCode;
    private String linkCode;
    private boolean flag;
    private String graphCode;

    public JudgePath() {
    }

    public JudgePath(String graphCode, String branchCode, String linkCode, boolean flag) {
        this.graphCode = graphCode;
        this.branchCode = branchCode;
        this.linkCode = linkCode;
        this.flag = flag;
    }

    public String getGraphCode() {
        return graphCode;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getBranchCode() {
        return branchCode;
    }


    public String getLinkCode() {
        return linkCode;
    }

}
