// https://leetcode.com/problems/last-stone-weight-ii/

class Solution {
        
    public int lastStoneWeightII(int[] stones) {
        int totalSum = 0;
        for(int stone: stones) totalSum += stone;
        
        boolean[] canAchieveSubsetWithSum = new boolean[totalSum + 1];
        canAchieveSubsetWithSum[0] = true;
        
        int runningSum = 0;
        
        for (int stone : stones) {
            runningSum += stone;
            for (int i = runningSum; i >= stone; i--) {
                if (canAchieveSubsetWithSum[i - stone]) canAchieveSubsetWithSum[i] = true;
            }
        }
        
        
        for (int i = totalSum / 2; i >= 0; i--)
            if (canAchieveSubsetWithSum[i]) return totalSum - i - i;
        

        return 0;
    }
}