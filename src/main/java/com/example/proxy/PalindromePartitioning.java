package com.example.proxy;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static List<List<String>> partitionPalindrome(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int start, List<String> currentPartition, List<List<String>> result) {
        if (start == s.length()) {
            // Base case: entire string processed, add current partition to the result
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                // If substring is a palindrome, continue with the next recursion level
                currentPartition.add(s.substring(start, end + 1));
                backtrack(s, end + 1, currentPartition, result);
                // Backtrack by removing the last added substring
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        // Check if the substring s[start:end+1] is a palindrome
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        String input = "aab";
        List<List<String>> result = partitionPalindrome(input);

        // Print the result
        for (List<String> partition : result) {
            System.out.println(partition);
        }
    }
}
