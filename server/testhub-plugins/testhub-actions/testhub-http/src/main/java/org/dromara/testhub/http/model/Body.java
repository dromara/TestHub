package org.dromara.testhub.http.model;

import com.goddess.nsrule.core.executer.mode.base.action.Param;
import com.goddess.nsrule.core.executer.mode.base.bound.Bound;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body {
    public static String ROW="row";
    public static String NONE="none";
    public static String FROM_DATA="form-data";
    public static String X_WWW_FORM_URENCODED="x-www-form-urlencoded";
    public static List<String> TYPES = Arrays.asList(new String[]{ROW,NONE,FROM_DATA,X_WWW_FORM_URENCODED});

    public static String JSON="json";
    public static String XML="xml";
    public static String TEXT="text";

    private String type;
    private String language = JSON;
    private String content;
    private Bound bound;
    private List<Param> datas;
}
