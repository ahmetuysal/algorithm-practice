// https://leetcode.com/problems/longest-valid-parentheses/

class Solution {
     public int longestValidParentheses(String s) {
        int[] memo = new int[s.length() + 1]; // last index is 0
        int max = 0;

        for (int i = s.length() -2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                if (s.charAt(i+1) == ')') {
                    memo[i] = memo[i + 2] + 2;
                }
                else {
                    int prev = memo[i+1];
                    if (prev != 0 && i+prev+1 < s.length() && s.charAt(i+prev+1) == ')') {
                        memo[i] = prev + 2 + memo[i+prev+2];
                    }
                }

                if (max < memo[i]) max = memo[i];
            }
        }

        return max;
    }
}