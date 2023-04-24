import java.util.Arrays;

public class mianshi {
    public static void main(String[] args) {

        //splitNumber(12345);

        int thisIsAWord = findLongestWord("this is a word");
//        String s = "this is a word";
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
}
