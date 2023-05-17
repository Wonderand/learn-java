package str.api;


import java.util.Arrays;

/**
 * 字符串工具类
 */
public class utils {

    public static void main(String[] args) {
        String a = "HelloWorld.java";
        //判断是否以.java结尾
        boolean b = a.endsWith(".java");
        System.out.println(b);
        //判断是否以Hello开头
        boolean b1 = a.startsWith("Hello");
        System.out.println(b1);
        //获取字符数组
        char[] charArray = a.toCharArray();
        System.out.println(Arrays.toString(charArray));
        //判断wor是否包含在字符串中
        boolean b2 = a.contains("wor");
        System.out.println(b2);
        //截取字符串
        String substring = a.substring(0, 5);
        System.out.println(substring);
        //替换字符串
        String str = "馒头一文一个";
        String replaceStr = str.replace("一","壹");
        System.out.println(str);
        //分割字符串
        String str1 = "aaa,bbb,ccc";
        String[] split = str1.split(",");
        System.out.println(Arrays.toString(split));
        //去除字符串两端空格
        String str2 = "   aaa   ";
        String trim = str2.trim();
        System.out.println(trim);
        //字符串转换为大写
        String str3 = "aaa";
        String upperCase = str3.toUpperCase();
        System.out.println(upperCase);
        //字符串转换为小写
        String str4 = "AAA";
        String lowerCase = str4.toLowerCase();
        System.out.println(lowerCase);
        //忽略大小写比较字符串
        boolean equalsIgnoreCase = str3.equalsIgnoreCase(str4);
        System.out.println(equalsIgnoreCase);

        String c = "Hello";
        String d = new String("Java");
        String cd = c+d;
        String cd1 = "Hello"+"Java";
        String cd2 = "HelloJava";
        System.out.println(cd == cd1); //false
        System.out.println(cd == cd2); //false
        System.out.println(cd1 == cd2); //true
        System.out.println(cd.equals(cd1)); //true
        System.out.println(cd.equals(cd2)); //true
        System.out.println(cd1.equals(cd2)); //true

    }

    /**
     * 末尾追加字符串
     */
    public static String append(String str, String appendStr) {
        str.concat(appendStr);
        return str;
    }

}
