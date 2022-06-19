// https://leetcode.com/problems/longest-palindromic-subsequence
// Note: wanted to also calculate what is the sequence instead of just length, this could be optimized if only length is memoized instead of palindromes

class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();

        if (len <= 1) return len;

        String[][] memo = new String[len - 1][len];

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) memo[i][j] = s.substring(i, j + 1);
                else {
                    if (s.charAt(i) == s.charAt(j)) {
                        memo[i][j] = s.charAt(i) + ((i == j - 1) ? "" : memo[i + 1][j - 1]) + s.charAt(j);
                    } else {
                        memo[i][j] = i == len - 2 || (memo[i][j - 1].length() > memo[i + 1][j].length())
                                ? memo[i][j - 1] : memo[i + 1][j];
                    }
                }
            }
        }

        return memo[0][len-1].length();
    }
}