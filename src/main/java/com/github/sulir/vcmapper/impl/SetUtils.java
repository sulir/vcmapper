package com.github.sulir.vcmapper.impl;

import java.util.HashSet;
import java.util.Set;

public class SetUtils {
    private SetUtils() {}

    public static double jaccardIndex(Set<?> set1, Set<?> set2) {
        int set1size = set1.size();
        int set2size = set2.size();

        set1 = new HashSet<>(set1);
        //noinspection SuspiciousMethodCalls
        set1.retainAll(set2);
        int intersectionSize = set1.size();

        return ((double) intersectionSize) / (set1size + set2size - intersectionSize);
    }
}
