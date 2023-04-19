package io.buffer;

import java.util.Arrays;

import static java.util.Arrays.binarySearch;

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

        // 二分查找
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int index = binarySearch(arr1, 5);
        System.out.println(index);
        // 根据id查询学生信息和学生的班级信息sql语句
        // select * from student where id = 1;
        // select * from class where id = 1;
        // 一对一查询
        // select * from student s,class c where s.id = 1 and s.class_id = c.id;
        // 一对多查询
        //以及该班级参加的所有课程的sql语句
        //select * from student s,class c where s.id = 1 and s.class_id = c.id;

        //根据id查询学生信息和学生的班级信息,以及该班级参加的所有课程的sql语句
        //select * from student s,class c where s.id = 1 and s.class_id = c.id;
        //select * from student s,class c,course co where s.id = 1 and s.class_id = c.id and c.id = co.class_id;
        //多对多查询
        //select * from student s,class c,course co where s.id = 1 and s.class_id = c.id and c.id = co.class_id;
        //select * from student s,class c,course co where s.id = 1 and s.class_id = c.id and c.id = co.class_id;
        //select * from student s,class c,course co where s.id = 1 and s.class_id = c.id and c.id = co.class_id

    }
}
