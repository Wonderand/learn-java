package DataStructuresAndAlgorithms.BinarySearch;


/**
 * @Classname test
 * @Author: hzr
 * @Description 测试用例
 * @Version 1.0.0
 * @Date 2023/8/24 10:23
 * @Created by 22906
 */
public class test {

    public static void main(String[] args) {
//        int[] a = {21,45,68,90,123,135,679};
//        BinarySearch binarySearch = new BinarySearch();
//        int i = binarySearch.binarySearchBasc(a, 679);
//        System.out.println(i);
        int i = 0;
        int j = Integer.MAX_VALUE - 1;

        int m = (i + j) / 2;
        System.out.println(m);
        i = m + 1;
        m = (i + j) / 2;
        System.out.println(m);
        System.out.println(Integer.MAX_VALUE - 1);
    }
}
