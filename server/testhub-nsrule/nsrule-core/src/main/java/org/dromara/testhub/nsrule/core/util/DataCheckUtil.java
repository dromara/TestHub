package org.dromara.testhub.nsrule.core.util;

import org.dromara.testhub.nsrule.core.constant.RuleException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2023/1/27 19:38
 */
public class DataCheckUtil {
    public static void notBlank(String[] datas, String[] msgs) {
        int i = 0;
        for (String data : datas) {
            if (StringUtils.isBlank(data)) {
                throw new RuleException(msgs[i] + "不能为空");
            }
            i++;
        }

    }
}
