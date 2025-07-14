package DataStructuresAndAlgorithms.算法;

import java.util.Arrays;

public class test {

    public static void main(String[] args) {
//        int num = 123456;
//        int[] ints = splitNumber(num);
//        for (int anInt : ints) {
//            System.out.println(anInt);
//        }
//        int[] ints = twoSum(new int[]{2, 7, 11, 15}, 9);
//        System.out.println(Arrays.toString(ints));
        try {
            System.out.println("try");
            System.out.println(1 / 0);
        } catch (Exception e) {
            System.out.println("catch");
            System.exit(0);
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }

    public static int[] splitNumber(int n) {
        int length = (int) Math.log10(n) + 1;
        int[] result = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            result[i] = n % 10;
            n /= 10;
        }
        return result;
    }

    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        int[] a = new int[2];
        for (int i = 0; i < n; ++i) {
            for(int j = nums.length-1;j >=0 ;--j){
                if((nums[j]+nums[i])== target){
                    a[0]=i;
                    a[1]=j;
                    return a;
                }
            }
        }
        return new int[0];
    }
}