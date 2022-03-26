import java.util.ArrayList;
import java.util.Scanner;

/**
 * MergeSort
 */
public class MergeSort {

    public static void zlij(int[] one, int[] two) {
        int lenMax = Math.max(one.length, two.length);
        ArrayList<Character> crke = new ArrayList<Character>();
        ArrayList<Integer> stevilke = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        while (i < one.length && j < two.length) {
            if (one[i] <= two[j]) {
                stevilke.add(one[i]);
                crke.add('a');
                i++;
            } else {
                stevilke.add(two[j]);
                crke.add('b');
                j++;
            }
        }

        for (; i < one.length; i++) {
            stevilke.add(one[i]);
            crke.add('a');
        }
        for (; j < two.length; j++) {
            stevilke.add(two[j]);
            crke.add('b');
        }

        for (Character c : crke) {
            System.out.print(c);
        }
        System.out.println();

        for (Integer integer : stevilke) {
            System.out.print(integer);
            System.out.print(" ");
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lenFisrt = sc.nextInt();
        int lenSec = sc.nextInt();

        int[] first = new int[lenFisrt];
        int[] sec = new int[lenSec];

        for (int i = 0; i < lenFisrt; i++) {
            first[i] = sc.nextInt();
        }
        for (int i = 0; i < lenSec; i++) {
            sec[i] = sc.nextInt();
        }

        zlij(first, sec);
    }
}