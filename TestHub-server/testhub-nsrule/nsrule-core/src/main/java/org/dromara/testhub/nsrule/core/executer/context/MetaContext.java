package org.dromara.testhub.nsrule.core.executer.context;

import cn.hutool.core.collection.CollectionUtil;
import org.dromara.testhub.nsrule.core.executer.meta.MetaClass;
import org.dromara.testhub.nsrule.core.executer.meta.MetaProperty;
import org.dromara.testhub.nsrule.core.executer.mode.base.action.Param;
import org.dromara.testhub.nsrule.core.executer.mode.Rule;
import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: 失败女神-vinc
 * @email: 18733123202@163.com
 * @date: 2022/8/30 21:41
 */
public class MetaContext {
    private Map<String, MetaProperty> propertyMap;
    private Map<MetaProperty, String> propertyKeyMap;
    private Map<String, MetaClass> classMap;
    private List<MetaClass> metaClasses;

    public MetaContext(List<MetaClass> metaClasses, Map<String, MetaClass> metaClassMap) {
        this.metaClasses = metaClasses;
        this.propertyKeyMap = new HashMap<>();
        this.propertyMap = new HashMap<>();
        this.classMap = new HashMap<>();
        List<String> paths = new ArrayList<>();
        for (MetaClass metaClass : metaClasses) {
            classMap.put(metaClass.getCode(), metaClass);
            paths.add(metaClass.getCode());
            init(metaClass, paths, metaClassMap);
            paths.remove(paths.size() - 1);
        }
    }

    public MetaContext(MetaContext baseMetaContext, Map<String, MetaClass> metaClassMap, Rule rule, RuleProject ruleProject) {
        this.metaClasses = new ArrayList<>(baseMetaContext.getMetaClasses());
        this.propertyMap = new HashMap<>(baseMetaContext.getPropertyMap());
        this.propertyKeyMap = new HashMap<>(baseMetaContext.getPropertyKeyMap());
        List<MetaClass> classes = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(metaClassMap)) {
            for (MetaClass metaClass : metaClassMap.values()) {
                int index = index(this.metaClasses, metaClass);
                if (index != -1) {
                    MetaClass remove = this.metaClasses.remove(index);
                    for (MetaProperty property : remove.getProperties()) {
                        String key = propertyKeyMap.remove(property);
                        propertyMap.remove(key);
                    }
                }
                this.metaClasses.add(metaClass);
                classes.add(metaClass);
            }
        }
        Map<String, MetaClass> allMetaClassMap = this.metaClasses.stream().collect(Collectors.toMap(o -> o.getCode(), o -> o));


        this.classMap = new HashMap<>(baseMetaContext.getClassMap());
        if (CollectionUtil.isNotEmpty(metaClassMap)) {
            List<String> paths = new ArrayList<>();
            for (MetaClass metaClass : classes) {
                classMap.put(metaClass.getCode(), metaClass);
                paths.add(metaClass.getCode());
                init(metaClass, paths, allMetaClassMap);
                paths.remove(paths.size() - 1);
            }
        }
        //入参名称
        List<Param> params = rule.getParams();
        for (Param param : params) {
            MetaProperty metaProperty = new MetaProperty();
            metaProperty.setCode(param.getCode());
            metaProperty.setName(param.getName());
            metaProperty.setDataType(param.getDataType());
            metaProperty.setComplex(param.getComplex());
            metaProperty.setMetaClassCode(param.getMetaClassCode());
            propertyMap.put(param.getCode(), metaProperty);
        }

        Map<String, MetaProperty> propertyMapRule = rule.getPropertyMap(ruleProject);
        if (CollectionUtil.isNotEmpty(propertyMapRule)) {
            propertyMap.putAll(propertyMapRule);
        }

    }

    private int index(List<MetaClass> classes, MetaClass metaClass) {
        int i = -1;
        for (MetaClass tmp : classes) {
            i++;
            if (tmp.getCode().equals(metaClass.getCode())) {
                return i;
            }
        }
        return -1;
    }

    private void init(MetaClass metaClass, List<String> paths, Map<String, MetaClass> metaClassMap) {
        for (MetaProperty property : metaClass.getProperties()) {
            MetaProperty pElement = propertyMap.get(Joiner.on(".").join(paths));
            if (pElement != null) {
                property.setComplex(property.getComplex() + pElement.getComplex());
            }
            paths.add(property.getCode());
            propertyMap.put(Joiner.on(".").join(paths), property);
            propertyKeyMap.put(property, Joiner.on(".").join(paths));
            if (property.getMetaClassCode() != null) {
                //init(metaClassMap.get(property.getMetaClassCode()),paths,metaClassMap);
            }
            paths.remove(paths.size() - 1);
        }
    }

    public Map<MetaProperty, String> getPropertyKeyMap() {
        return propertyKeyMap;
    }

    public Map<String, MetaProperty> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, MetaProperty> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Map<String, MetaClass> getClassMap() {
        return classMap;
    }

    public void setClassMap(Map<String, MetaClass> classMap) {
        this.classMap = classMap;
    }

    public List<MetaClass> getMetaClasses() {
        return metaClasses;
    }

    public void setMetaClasses(List<MetaClass> metaClasses) {
        this.metaClasses = metaClasses;
    }
}
