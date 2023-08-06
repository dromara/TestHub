package com.goddess.nsrule.core.parser;

import com.goddess.nsrule.core.executer.mode.base.bound.FreeMarker;

public class BoundParserFreeMarker implements BoundParser<String, FreeMarker>{

    @Override
    public synchronized FreeMarker parser(String dataObj) {
        FreeMarker freeMarker = FreeMarker.getInstance(dataObj);
        return freeMarker;
    }

    public static void main(String[] args)throws Exception {
        FreeMarker freeMarker1 = FreeMarker.getInstance("1");



        freeMarker1 = null;
        System.gc();
        Thread.sleep(100);
        System.gc();
        Thread.sleep(100);
        FreeMarker freeMarker2 = FreeMarker.getInstance("2");
        FreeMarker freeMarker3 = FreeMarker.getInstance("3");


        freeMarker3 = null;
        System.gc();
        Thread.sleep(100);
        System.gc();
        Thread.sleep(100);

        FreeMarker.getInstance("4");

    }
}
