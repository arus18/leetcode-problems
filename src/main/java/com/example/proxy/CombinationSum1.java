package com.example.proxy;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum1 {

    public static void main(String[] args) {
        int k = 3;
        int n = 9;
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
        System.out.println("Method Call: findCombinationsHelper(k=" + k + ", target=" + target + ", start=" + start + ", current=" + current + ")");
        if (k == 0 && target == 0) {
            // Found a valid combination
            result.add(new ArrayList<>(current));
            System.out.println("Valid Combination Found: " + current);
            return;
        }

        for (int i = start; i <= target; i++) {
            System.out.println("For Loop Iteration: i=" + i);

            // Include the current number in the combination
            current.add(i);
            System.out.println("Adding " + i + " to the combination: " + current);

            // Recursively find combinations for the remaining numbers
            findCombinationsHelper(k - 1, target - i, i + 1, current, result);

            // Backtrack by removing the last element to explore other possibilities
            current.remove(current.size() - 1);
            System.out.println("Backtracking: Removing " + i + ", Current Combination: " + current);
        }
    }
}
