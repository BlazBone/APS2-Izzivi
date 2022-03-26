import java.util.Arrays;
import java.util.Scanner;

/**
 * Radix
 */
public class Radix {

    public static void main(String[] args) {
        int size;
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        int[] arr = new int[size];
        int[] arrBit = new int[size];
        int[] count = new int[33];
        int[] endarr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
            arrBit[i] = Integer.bitCount(arr[i]);
            count[arrBit[i]]++;
            // System.out.println(Integer.bitCount(arr[i]));
        }
        // System.out.println(Arrays.toString(count));
        for (int i = 0; i < count.length - 1; i++) {
            count[i + 1] += count[i];
            if (count[i] > 0) {
                count[i] -= 1;
            }
        }

        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(arrBit));
        // System.out.println(Arrays.toString(count));

        for (int i = endarr.length - 1; i >= 0; i--) {
            endarr[count[arrBit[i]]] = arr[i];
            System.out.printf("(%d,%d)\n", arr[i], count[arrBit[i]]);
            count[arrBit[i]] -= 1;
        }

        for (int i = 0; i < endarr.length; i++) {
            System.out.printf("%d", endarr[i]);
            System.out.printf(" ", endarr[i]);
        }
        // System.out.println(Arrays.toString(endarr));

    }
}