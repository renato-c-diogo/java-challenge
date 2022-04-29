package com.rcd.test;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.*;

import java.util.stream.Collectors;

public class Program {

    /*
    Input:
        array: [7 , 6, 4, -1, 1 ,2]
        targetSum = 16

    Output:
        [[7, 6, 4, -1], [7, 6, 1, 2]]  // the quadruplets could be ordered differently
    */

    public static List<Integer[]> fourNumberSumV1(int[] array, int targetSum) {
        List<Integer> values = Arrays.stream(array).boxed().collect(Collectors.toList());
        List<Integer[]> results = new ArrayList();
        values.forEach(item1 -> {
            List<Integer> aux = new ArrayList();
            aux.add(item1);
            values.stream().filter(a1 -> !aux.contains(a1)).forEach(item2 -> {
                aux.add(item2);
                values.stream().filter(a2 -> !aux.contains(a2)).forEach(item3 -> {
                    aux.add(item3);
                    values.stream().filter(a3 -> !aux.contains(a3)).forEach(item4 -> {
                        if ((item1 + item2 + item3 + item4) == targetSum) {
                            Integer[] newer = new Integer[] {item1, item2, item3, item4};
                            Arrays.sort(newer);
                            if (results.stream().filter(a -> {
                                return Arrays.equals(a, newer);
                            }).count() == 0) {
                                results.add(newer);
                            }
                        }
                        aux.remove(item4);
                    });
                    aux.remove(item3);
                });
                aux.remove(item2);
            });
        });
        return results;
    }

    public static List<Integer[]> fourNumberSumV2(int[] array, int targetSum) {
        List<Integer> values = Arrays.stream(array).boxed().collect(Collectors.toList());
        Set<List<Integer>> results = new HashSet();
        values.parallelStream().forEach(it ->
                handleMiddleItem(values, new ArrayList(Arrays.asList(it)), targetSum, results)
        );
        return results.stream().map(it -> it.stream().toArray(Integer[]::new)).collect(Collectors.toList());
    }

    private static void handleMiddleItem(List<Integer> values, List<Integer> temp, Integer targetSum, Set<List<Integer>> results) {
        values.parallelStream()
                .filter(it -> !temp.contains(it))
                .forEach(it -> {
                    List<Integer> tempInternal = new ArrayList(temp);
                    tempInternal.add(it);
                    if (tempInternal.size() == 4) {
                        handleFinalItem(targetSum, results, tempInternal);
                    } else {
                        handleMiddleItem(values, tempInternal, targetSum, results);
                    }
                });
    }

    public static synchronized void addSynchronized(Set<List<Integer>> results, List<Integer> item) {
        results.add(item);
    }

    private static synchronized Boolean noneMatch(Set<List<Integer>> results, List<Integer> newer) {
        return results.stream().noneMatch(it -> it.equals(newer));
    }

    private static void handleFinalItem(Integer targetSum, Set<List<Integer>> results, List<Integer> newer) {
        if (Objects.equals(newer.parallelStream().reduce(0, Integer::sum), targetSum)) {
            Collections.sort(newer);
            if (noneMatch(results, newer)) {
                addSynchronized(results, newer);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {7, 6,  4, -1, 1 ,2};
        int targetSum = 16;
        List<Integer[]> result = Program.fourNumberSumV1(array, targetSum);
        System.out.print("[");
        for(Integer[] it : result) {
            System.out.print(Arrays.toString(it));
        }
        System.out.print("]");
    }
}