package com.example.demo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountPairTest {

    public int countPairs(List<Integer> list, int target) {
        int countPair = 0;
        Set<Integer> listValueSet = new HashSet<>();
        Set<Integer> listValueSetWithTargetSum = new HashSet<>();
        for (Integer i:  list) {
            listValueSet.add(i);
            listValueSetWithTargetSum.add(i+target);
        }
        for (Integer j :listValueSetWithTargetSum) {
            if (listValueSet.contains(j)) {
                countPair++;
            }
        }
        return countPair;
    }
}
