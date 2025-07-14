package DataStructuresAndAlgorithms.算法;

import java.util.Arrays;

public class mianshi {
    public static void main(String[] args) {

        //splitNumber(12345);
        int thisIsAWord = findLongestWord("this is a word");
        System.out.println(thisIsAWord);
    }
    //将正整数的每一位拆分出来,不调用方法
    public static void splitNumber(int a) {
        int len = 0;
        int[] arr = new int[len];
        while(a > 0) {
            int digit = a % 10;
            a /= 10;
            arr[len]=digit;
            len++;
            System.out.println(digit);
        }
        System.out.println(Arrays.toString(arr));
    }
    //查找英文句子中最长单词的长度
    public static int findLongestWord(String sentence) {
        int maxLength = 0;
        int length = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == ' ') {
                if (length > maxLength) {
                    maxLength = length;
                }
                length = 0;
            } else {
                length++;
            }
        }
        if (length > maxLength) {
            maxLength = length;
        }
        return maxLength;
    }
    //现在有三张mysql表，用户表：用户ID、用户姓名。收入表：用户ID、收入金额。订单表：用户ID、订单金额。用子查询统计出用户姓名，总订单金额，总收入金额
    //select a.name,sum(b.money),sum(c.money)
    //from user a,
    //(select user_id,sum(money) as money from order group by user_id) b,
    //(select user_id,sum(money) as money from income group by user_id)c
    //where a.id=b.user_id and a.id=c.user_id group by a.id;

    /**
     * SELECT u.user_name AS 用户名, SUM(o.amount) AS 总订单金额, SUM(s.income) AS 总收入金额
     * FROM `user` u
     * LEFT JOIN `order` o ON u.id = o.user_id
     * LEFT JOIN `income` s ON u.id = s.user_id
     * GROUP BY u.id;
     */
}
