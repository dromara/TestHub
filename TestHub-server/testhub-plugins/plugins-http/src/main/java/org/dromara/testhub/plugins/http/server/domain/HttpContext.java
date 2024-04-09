package org.dromara.testhub.plugins.http.server.domain;

import org.dromara.testhub.nsrule.core.executer.context.Context;
import org.dromara.testhub.nsrule.core.executer.context.RuleProject;

import java.util.Map;

public class HttpContext extends Context<Map<Object, Object>> {

    public HttpContext(RuleProject project, String envCode) {
        super(project, envCode);
    }

    @Override
    public String getItemCode() {
        return "";
    }
}
