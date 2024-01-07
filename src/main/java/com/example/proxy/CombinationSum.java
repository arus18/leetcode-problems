package com.example.proxy;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        int k = 4;
        int n = 10;
        findCombinations(k, n);
    }

    public static void findCombinations(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinationsHelper(k, n, 1, new ArrayList<>(), result);

        // Print the generated combinations
        for (List<Integer> combination : result) {
            System.out.println(combination);
        }
    }

    private static void findCombinationsHelper(int k, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (k == 0 && target == 0) {
            // Found a valid combination
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= target; i++) {

            // Include the current number in the combination
            current.add(i);

            // Recursively find combinations for the remaining numbers
            findCombinationsHelper(k - 1, target - i, i + 1, current, result);

            // Backtrack by removing the last element to explore other possibilities
            current.remove(current.size() - 1);
        }
    }
}

