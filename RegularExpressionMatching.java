// https://leetcode.com/problems/regular-expression-matching/

class Solution {
    // * ların kaç tane sayıldığından gitsek
    
    public boolean isMatch(String s, String p) {
         boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
        // base case
        memo[0][0] = true;

        for (int i = 1; i < p.length(); i+=2) {
            if (p.charAt(i) == '*') {
                memo[0][i+1] = true;
            } else break;
        }
        
        for (int sI = 1; sI <= s.length(); sI++) {
            for (int pI = 1; pI <= p.length(); pI++) {
                if (p.charAt(pI - 1) == '*') {
                    boolean result = memo[sI][pI - 2] // dont use the letter
                            || memo[sI][pI - 1];// use the letter only once

                    int i = sI;
                    while (!result && i > 0 && (p.charAt(pI - 2) == '.' || p.charAt(pI - 2)
                            == s.charAt(i - 1))) {
                        result = memo[i][pI];
                        i--;
                    }

                    memo[sI][pI] = result;
                } else {
                    memo[sI][pI] = memo[sI - 1][pI - 1] && (p.charAt(pI - 1) == '.' || p.charAt(pI - 1) == s.charAt(sI - 1));
                }
            }
        }

        return memo[s.length()][p.length()];
    }
}