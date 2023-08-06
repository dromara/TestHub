package com.goddess.nsrule.core.executer.mode.base.bound;


import com.alibaba.fastjson.JSONObject;
import com.goddess.nsrule.core.constant.RuleException;
import com.goddess.nsrule.core.executer.context.Context;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.StringWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class FreeMarker extends Bound{
    private String type;
    private String text;
    public static final ReferenceQueue<FreeMarker> referenceQueue = new ReferenceQueue<>();
    public static final Set<FreeMarkerReference> references = new HashSet<>();
    public static Configuration CFG = new Configuration(Configuration.VERSION_2_3_30);
    public static StringTemplateLoader STRING_LOADER = new StringTemplateLoader();

    static {
        try {
            CFG.setSetting(Configuration.BOOLEAN_FORMAT_KEY, "c");
            CFG.setNumberFormat("0.##########");
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
        CFG.setTemplateLoader(STRING_LOADER);
    }

    public String getText() {
        return text;
    }

    public synchronized static FreeMarker getInstance(String text) {
        FreeMarker freeMarker =  new FreeMarker();
        freeMarker.setText(text);
        STRING_LOADER.putTemplate(freeMarker.getObjId(),text);
        references.add(new FreeMarker.FreeMarkerReference(freeMarker, referenceQueue));
        // 检查引用队列，调用 cleanup 方法
        Reference<? extends FreeMarker> collectedRef;
        while ((collectedRef = referenceQueue.poll()) != null) {
            ((FreeMarkerReference) collectedRef).cleanup();
            references.remove((FreeMarkerReference) collectedRef);
        }

        return freeMarker;
    }

    private FreeMarker() {
        super();
        type = "FreeMarker";
        super.type =type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getObjId(){
        return System.identityHashCode(this)+"";
    }

    @Override
    public String build(Context context) {
        try {
            JSONObject param = context.getMergedParams();

            Template template = CFG.getTemplate(getObjId());
            StringWriter writer = new StringWriter();
            template.process(param, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuleException(e);
        }
    }


    private static class FreeMarkerReference extends WeakReference<FreeMarker> {
        private String objId;
        public FreeMarkerReference(FreeMarker referent, ReferenceQueue<? super FreeMarker> queue) {
            super(referent, queue);
            this.objId = referent.getObjId();
        }

        public void cleanup() {
            STRING_LOADER.removeTemplate(objId);
        }
    }


}
