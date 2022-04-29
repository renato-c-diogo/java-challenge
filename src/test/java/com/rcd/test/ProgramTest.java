package com.rcd.test;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {

    @Nested
    public class testCase1 {
        int[] array = new int[] {7, 6,  4, -1, 1 ,2};
        int targetSum = 16;
        int[][] expected = new int[][] {
                {7, 6, 4, -1},
                {7, 6, 1, 2}
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    @Nested
    public class testCase2 {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7};
        int targetSum = 10;
        int[][] expected = new int[][] {
                {1, 2, 3, 4}
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    @Nested
    public class testCase3 {
        int[] array = new int[] {5, -5, -2, 2, 3, -3};
        int targetSum = 0;
        int[][] expected = new int[][] {
                {5, -5, -2, 2},
                {5, -5, 3, -3},
                {-2, 2, 3, -3}
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    @Nested
    public class testCase4 {
        int[] array = new int[] {-2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int targetSum = 4;
        int[][] expected = new int[][] {
                {-2, -1, 1, 6},
                {-2, 1, 2, 3},
                {-2, -1, 2, 5},
                {-2, -1, 3, 4}
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    private void compare(int[][] expected, List<Integer[]> result) {
        compare(convertToList(expected), convertToList(result));
    }

    private void compare(List<List<Integer>> listExpected, List<List<Integer>> listResult) {
//        System.out.println("listExpected: " + listExpected);
//        System.out.println("listResult: " + listResult);
        listExpected.forEach(itemExpected -> {
            Collections.sort(itemExpected);
            Optional<List<Integer>> itemResult = listResult
                    .stream()
                    .filter(it -> it.equals(itemExpected))
                    .findAny();
            assertNotNull(itemResult.orElse(null), itemExpected + ": not found");
            Collections.sort(itemResult.get());
            assertEquals(itemExpected, itemResult.get());
        });
        assertEquals(listExpected.size(), listResult.size());
    }

    @Nested
    public class testCase5 {
        int[] array = new int[] {-1, 22, 18, 4, 7, 11, 2, -5, -3};
        int targetSum = 30;
        int[][] expected = new int[][] {
                {-1, 22, 7, 2},
                {22, 4, 7, -3},
                {-1, 18, 11, 2},
                {18, 4, 11, -3},
                {22, 11, 2, -5}
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    @Nested
    public class testCase6 {
        int[] array = new int[] {-10, -3, -5, 2, 15, -7, 28, -6, 12, 8, 11, 5};
        int targetSum = 20;
        int[][] expected = new int[][] {
            {-5, 2, 15, 8},
            {-3, 2, -7, 28},
            {-10, -3, 28, 5},
            {-10, 28, -6, 8},
            {-7, 28, -6, 5},
            {-5, 2, 12, 11},
            {-5, 12, 8, 5}
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    @Nested
    public class testCase7 {
        int[] array = new int[] {1, 2, 3, 4, 5};
        int targetSum = 100;
        int[][] expected = new int[][] {
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    @Nested
    public class testCase8 {
        int[] array = new int[] {1, 2, 3, 4, 5, -5, 6, -6};
        int targetSum = 5;
        int[][] expected = new int[][] {
                {2, 3, 5, -5},
                {1, 4, 5, -5},
                {2, 4, 5, -6},
                {1, 3, -5, 6},
                {2, 3, 6, -6},
                {1, 4, 6, -6}
        };

        @Test
        void fourNumberSumV1() {
            compare(expected, Program.fourNumberSumV1(array, targetSum));
        }

        @Test
        void fourNumberSumV2() {
            compare(expected, Program.fourNumberSumV2(array, targetSum));
        }
    }

    private List<List<Integer>> convertToList(int[][] array2d) {
        return Arrays
                .stream(array2d)
                .map(internalArray -> Arrays
                        .stream(internalArray)
                        .boxed()
                        .sorted()
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList())
        ;
    }

    private List<List<Integer>> convertToList(List<Integer[]> listArray2d) {
        return listArray2d
                .stream()
                .map(internalArray -> Arrays
                        .stream(internalArray)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }

}
