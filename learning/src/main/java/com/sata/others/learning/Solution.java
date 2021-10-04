package com.sata.others.learning;

/*
 * Click `Run` to execute the snippet below!
 */

/*  Check if array has an triplet increase numbers
 * input {1, 5, 3, 2} return false
 * input {1, 5, 2, 3} return true;
 * input {5, 1, 4, 2, 3 } return true
 *
 */

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

/*
findNextGreaterElements of
[12, 11, 6, 5, 2, 1] is [-1, -1, -1, -1, -1, -1]
[12, 4, 6, 5, 2, 7] is [-1, 6, 7, 7, 7, -1]
[12,5,13] is [13.13,-1]
*/

class Solution {
    //    public static void main(String[] args) {
//
//        int[] nums = {12, 11, 6, 5, 2, 1};
//        int[] nums1 = {12, 4, 6, 5, 2, 7};
//        int[] nums2 = {12,5,13} ;
//
//        System.out.println(Arrays.toString(findNextGreaterElements2(nums)));
//        System.out.println(Arrays.toString(findNextGreaterElements2(nums1)));
//        System.out.println(Arrays.toString(findNextGreaterElements2(nums2)));
//    }
//
//    public static int[] findNextGreaterElements2(int[] nums){
//        int[] res = new int[nums.length];
//        Stack<Integer> stack = new Stack<>();
//        Arrays.fill(res,-1);
//        for(int i = 0;i<nums.length;i++){
//            if(stack.isEmpty()){
//                stack.push(i);
//            }else{
//                while( !stack.isEmpty() && nums[stack.peek()] < nums[i]){
//                    res[stack.pop()] = nums[i];
//                }
//                stack.push(i);
//            }
//        }
//        return res;
//    }
//
//    public static int[] findNextGreaterElements1(int[] nums){
//
//        int[] res = new int[nums.length];
//        Arrays.fill(res,-1);
//        for(int i = 0;i<nums.length;i++){
//            for(int j = i + 1; j<nums.length; j++){
//                if(nums[j] > nums[i]){
//                    res[i] = nums[j];
//                    break;
//                }
//            }
//        }
//        return res;
//    }
//
//
//    public static  boolean increaseTriplet(int[] nums){
//        int small = Integer.MAX_VALUE;
//        int mid = Integer.MAX_VALUE;
//        for(int i = 0; i< nums.length; i++){
//            if(nums[i] < small){
//                small = nums[i];
//            }else if (nums[i] < mid){
//                mid = nums[i];
//            }else{
//                return true;
//            }
//        }
//        return false;
//    }
    public static int findNthDigit(int n) {
        for (long len = 1, start = 1, total = 9; ; ) {
            if (n <= len * total) {
                long number = (n - 1) / len + start;
                char[] s = String.valueOf(number).toCharArray();
                return s[(int) ((n - 1) % len)] - '0';
            } else {
                n -= len * total;
                len++;
                start *= 10;
                total *= 10;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(3));
    }

}