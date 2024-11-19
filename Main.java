import java.util.HashMap;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        // Use a HashMap to store the frequency of elements in the current window
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        long maxSum = 0, currentSum = 0;
        int start = 0;

        // Traverse the array with a sliding window of size k
        for (int end = 0; end < nums.length; end++) {
            int num = nums[end];
            // Add the current number to the frequency map and update the current sum
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            currentSum += num;

            // If the window size exceeds k, slide the window by removing the start element
            if (end - start + 1 > k) {
                int startNum = nums[start];
                currentSum -= startNum;
                frequencyMap.put(startNum, frequencyMap.get(startNum) - 1);
                if (frequencyMap.get(startNum) == 0) {
                    frequencyMap.remove(startNum);
                }
                start++;
            }

            // Check if the current window has all distinct elements and size k
            if (end - start + 1 == k && frequencyMap.size() == k) {
                maxSum = Math.max(maxSum, currentSum);
            }
        }

        return maxSum;
    }
}

