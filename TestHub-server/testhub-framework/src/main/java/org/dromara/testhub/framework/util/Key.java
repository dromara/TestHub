package org.dromara.testhub.framework.util;

import com.google.common.base.Joiner;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Key {
    private int size = 0;
    private List<Object> val;
    private int hash = 0;

    public Key(Object... keys) {
        size = keys.length;
        val = new ArrayList<>();
        for (Object key : keys) {
            if (key == null) {
                throw new RuntimeException("key不能为空");
            }
            val.add(key);
            hash += key.hashCode();
        }
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Key)) {
            return false;
        }
        Key old = (Key) obj;
        if (old.getHash() == this.getHash() && old.getSize() == this.getSize()) {
            boolean flag = true;
            for (int i = 0; i < size; i++) {
                if (!this.getVal().get(i).equals(old.getVal().get(i))) {
                    flag = false;
                    break;
                }
            }
            return flag;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Key{" +
                "size=" + size +
                ", val=" + Joiner.on(",").join(val) +
                ", hash=" + hash +
                '}';
    }
}
