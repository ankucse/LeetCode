package org.example;

//Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
//
//The overall run time complexity should be O(log (m+n)).
//
//
//
//Example 1:
//
//Input: nums1 = [1,3], nums2 = [2]
//Output: 2.00000
//Explanation: merged array = [1,2,3] and median is 2.
//Example 2:
//
//Input: nums1 = [1,2], nums2 = [3,4]
//Output: 2.50000
//Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
//
//
//Constraints:
//
//nums1.length == m
//nums2.length == n
//0 <= m <= 1000
//        0 <= n <= 1000
//        1 <= m + n <= 2000
//        -106 <= nums1[i], nums2[i] <= 106
//


public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLeft = (m + n + 1) / 2;  // Number of elements in the left part

        int left = 0, right = m;

        while (left <= right) {
            // Partition nums1
            int i = left + (right - left) / 2;
            // Partition nums2
            int j = totalLeft - i;

            // Edge handling
            int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Check if we have a valid partition
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // If total length is odd, return max of the left side
                if ((m + n) % 2 == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                }
                // If total length is even, return the average of max left and min right
                return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
            } else if (nums1LeftMax > nums2RightMin) {
                // Too many elements in nums1's left part, move left
                right = i - 1;
            } else {
                // Too few elements in nums1's left part, move right
                left = i + 1;
            }
        }

        // If we reach here, there is an error in the input
        throw new IllegalArgumentException("Input arrays are not valid.");
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();

        // Test case 1
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Median of [1, 3] and [2]: " + medianOfTwoSortedArrays.findMedianSortedArrays(nums1, nums2));  // Expected: 2.0

        // Test case 2
        int[] nums1_2 = {1, 2};
        int[] nums2_2 = {3, 4};
        System.out.println("Median of [1, 2] and [3, 4]: " + medianOfTwoSortedArrays.findMedianSortedArrays(nums1_2, nums2_2));  // Expected: 2.5
    }
}
