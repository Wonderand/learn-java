package io.buffer;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {

        /* 打印hello world */

        System.out.println("hello world");

        // 冒泡排序

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        for (int a = 0; a < arr.length; a++) {
            for (int b = 0; b < arr.length - 1 - a; b++) {
                if (arr[b] > arr[b + 1]) {
                    int temp = arr[b];
                    arr[b] = arr[b + 1];
                    arr[b + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

    }
}
