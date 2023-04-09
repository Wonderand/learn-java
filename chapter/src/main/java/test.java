import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        int num = 123456;
        int[] ints = splitNumber(num);
        for (int anInt : ints) {
            System.out.println(anInt);
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
}
