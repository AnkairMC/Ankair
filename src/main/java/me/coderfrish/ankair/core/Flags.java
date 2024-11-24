package me.coderfrish.ankair.core;

import java.util.EnumSet;
import java.util.Set;

@SuppressWarnings("unused")
public enum Flags {
    X(0),
    Y(1),
    Z(2),
    Y_ROT(3),
    X_ROT(4);

    private final int bit;

    Flags(int n2) {
        this.bit = n2;
    }

    private int getMask() {
        return 1 << this.bit;
    }

    private boolean isSet(int n) {
        return (n & this.getMask()) == this.getMask();
    }

    public static Set<Flags> unpack(int n) {
        EnumSet<Flags> enumSet = EnumSet.noneOf(Flags.class);
        for (Flags relativeArgument : Flags.values()) {
            if (!relativeArgument.isSet(n)) continue;
            enumSet.add(relativeArgument);
        }
        return enumSet;
    }

    public static int pack(Set<Flags> set) {
        int n = 0;
        for (Flags relativeArgument : set) {
            n |= relativeArgument.getMask();
        }
        return n;
    }
}
