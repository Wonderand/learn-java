package DataStructuresAndAlgorithms.BinarySearch;

/**
 * @Classname BinarySearch
 * @Author: hzr
 * @Description 二分查找算法
 * @Version 1.0.0
 * @Date 2023/8/24 10:10
 * @Created by 22906
 */
public class BinarySearch {

    public static int binarySearchBasc(int[] a, int target) {

        int i = 0, j = a.length - 1;  //设置指针和初值
        while (i <= j) {  //范围内有东西
            int m = (i + j) / 2; //自动取整，去掉小数部分
            if (target < a[m]) { //目标在中间值的左边
                j = m - 1;
            }else if (target > a[m]){
                i = m + 1;
            }else {
                return m;
            }
        }
        return -1;
    }
}
