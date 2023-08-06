package com.goddess.nsrule.core.executer.mode.base.bound;

import com.goddess.nsrule.core.executer.context.Context;

import java.util.ArrayList;
import java.util.List;

public class Compose extends Bound {
    public List<Bound> items;

    public Compose() {
        items = new ArrayList<>();
        type = "Compose";
    }

    @Override
    public String build(Context context) {
        StringBuilder sb = new StringBuilder();
        for (Bound bound : items) {
            sb.append(bound.build(context));
        }
        return sb.toString();
    }

    public void addItem(Bound item) {
        items.add(item);
    }
}
