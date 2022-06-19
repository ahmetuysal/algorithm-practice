// https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference

class Solution {
        
    public int minimumDifference(int[] nums) {
        HashSet<Integer>[] possibleSumsUsingKElementsOfFirstHalf = new HashSet[nums.length / 2 + 1];
        possibleSumsUsingKElementsOfFirstHalf[0] = new HashSet<>();
        possibleSumsUsingKElementsOfFirstHalf[0].add(0);
        for (int i = 0; i < nums.length / 2; i++) {
            possibleSumsUsingKElementsOfFirstHalf[i+1] = new HashSet<>();

            for (int j = i; j >= 0; j--) {
                for (int num : possibleSumsUsingKElementsOfFirstHalf[j]) {
                    possibleSumsUsingKElementsOfFirstHalf[j + 1].add(num + nums[i]);
                }
            }

            possibleSumsUsingKElementsOfFirstHalf[1].add(nums[i]);
        }

        TreeSet<Integer>[] possibleSumsUsingKElementsOfSecondHalf = new TreeSet[nums.length / 2 + 1];
        possibleSumsUsingKElementsOfSecondHalf[0] = new TreeSet<>();
        possibleSumsUsingKElementsOfSecondHalf[0].add(0);
        for (int i = 0; i < nums.length / 2; i++) {
            possibleSumsUsingKElementsOfSecondHalf[i + 1] = new TreeSet<>();

            for (int j = i; j >= 0; j--) {
                for (int num : possibleSumsUsingKElementsOfSecondHalf[j]) {
                    possibleSumsUsingKElementsOfSecondHalf[j + 1].add(num + nums[i + nums.length / 2]);
                }
            }

            possibleSumsUsingKElementsOfSecondHalf[1].add(nums[i + nums.length / 2]);
        }

        int total = 0;
        for (int i : nums) {
            total += i;
        }

        int target = total / 2;

        int closestToTarget = Integer.MAX_VALUE;

        for (int elementsUsedFromFirstHalf = 0; elementsUsedFromFirstHalf < nums.length / 2; elementsUsedFromFirstHalf++) {
            for (int possibleSumOfFirstHalf : possibleSumsUsingKElementsOfFirstHalf[elementsUsedFromFirstHalf]) {
                int correspondingHalfTarget = target - possibleSumOfFirstHalf;
                TreeSet<Integer> correspondingSumsOfSecondHalf = possibleSumsUsingKElementsOfSecondHalf[nums.length / 2 - elementsUsedFromFirstHalf];

                Integer ceiling = correspondingSumsOfSecondHalf.ceiling(correspondingHalfTarget);
                if (ceiling != null && Math.abs(correspondingHalfTarget - ceiling) < Math.abs(closestToTarget - target)) closestToTarget = ceiling + possibleSumOfFirstHalf;

                Integer floor = correspondingSumsOfSecondHalf.floor(correspondingHalfTarget);
                if (floor != null && Math.abs(correspondingHalfTarget - floor) < Math.abs(closestToTarget - target)) closestToTarget = floor + possibleSumOfFirstHalf;
            }
        }

        return Math.abs(total - 2 * closestToTarget);
    }
}