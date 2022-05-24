import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * HeapSort
 */
public class HeapSort {

    public static void pogrezni(int a[], int i, int dolzKopice) {
        int change = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < dolzKopice && a[change] < a[left]) {
            change = left;
        }

        if (right < dolzKopice && a[change] < a[right]) {
            change = right;
        }

        if (i != change) {
            // swap
            int temp = a[i];
            a[i] = a[change];
            a[change] = temp;

            pogrezni(a, change, dolzKopice);
        }
    }

    public static boolean powerOfTwo(int a) {
        // check if number we get when useing log is whole
        int lower = (int) Math.floor((Math.log(a) / Math.log(2)));
        int upper = (int) Math.ceil((Math.log(a) / Math.log(2)));
        return upper == lower;
    }

    public static void izpisi(int a[], int dolzina) {

        for (int i = 0; i <= dolzina; i++) {
            if (i > 0 && powerOfTwo(i + 1)) {
                System.out.print("| ");
            }
            System.out.print(a[i]);
            if (i != dolzina) {
                System.out.print(" ");
            }
        }

        System.out.println();

    }

    public static void buildHeap(int a[]) {
        for (int i = (a.length / 2) - 1; i >= 0; i--) {
            pogrezni(a, i, a.length);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int numbers[] = new int[len];
        for (int i = 0; i < len; i++) {
            numbers[i] = sc.nextInt();
        }
        buildHeap(numbers);
        int temp;
        for (int i = numbers.length - 1; i >= 0; i--) {
            // System.out.println(Arrays.toString(numbers));
            izpisi(numbers, i);
            temp = numbers[0];
            numbers[0] = numbers[i];
            numbers[i] = temp;
            pogrezni(numbers, 0, i);

        }
    }
}