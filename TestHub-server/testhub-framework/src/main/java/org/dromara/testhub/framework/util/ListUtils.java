package org.dromara.testhub.framework.util;

import java.util.ArrayList;

public class ListUtils<T> extends ArrayList<T> {
    public ListUtils<T> add2(T value) {
        super.add( value);
        return this;
    }
}
