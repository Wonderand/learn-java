package DataStructuresAndAlgorithms.BinarySearch;

import jogamp.opengl.glu.nurbs.Bin;

import java.net.BindException;

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
        int[] a = {21,45,68,90,123,135,679};
        BinarySearch binarySearch = new BinarySearch();
        int i = binarySearch.binarySearchBasc(a, 679);
        System.out.println(i);
    }
}
