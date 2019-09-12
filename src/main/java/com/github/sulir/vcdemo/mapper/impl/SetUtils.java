package com.github.sulir.vcdemo.mapper.impl;

import java.util.Set;

public class SetUtils {
    private SetUtils() {}

    public static double jaccardIndex(Set<Term> set1, Set<Term> set2) {
        int set1size = set1.size();
        int set2size = set2.size();

        set1.retainAll(set2);
        int intersectionSize = set1.size();

        return ((double) intersectionSize) / (set1size + set2size - intersectionSize);
    }
}
