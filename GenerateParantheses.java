// https://leetcode.com/problems/generate-parentheses/

class Solution {
    public List<String> generateParenthesis(int n) {
        Set<String>[] memo = new Set[n];
        memo[0] = new HashSet<>();
        memo[0].add("()");
        for (int i = 1; i < n; i++) {
            memo[i] = new HashSet<>();
            for (String parens : memo[i - 1]) {
                memo[i].add("("+parens+")");
            }

            for (int j = 0; j < i; j++) {
                for (String leftParens: memo[j]) {
                    for (String rightParens: memo[i - j - 1]) {
                        memo[i].add(leftParens + rightParens);
                    }
                }
            }
        }

        return memo[n-1].stream().toList();
    }
}