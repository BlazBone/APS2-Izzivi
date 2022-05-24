import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * izziv05
 */
public class izziv05 {

    public static int podzaporedje(ArrayList<Integer> arr, int suma) {
        if (arr.size() <= 1) {
            suma = arr.get(0);
            System.out.println(arr + ": " + suma);
            return arr.get(0);
        } else {
            int middle = (int) Math.ceil(arr.size() / 2.0);
            int desna = podzaporedje(new ArrayList<>(arr.subList(0, middle)), suma);
            int leva = podzaporedje(new ArrayList<>(arr.subList(middle, arr.size())), suma);
            int maxObeh = arr.get(middle - 1) + arr.get(middle);
            int currSum = arr.get(middle - 1) + arr.get(middle);
            int index = middle - 2;
            while (index >= 0) {
                currSum += arr.get(index);
                if (maxObeh < currSum) {
                    maxObeh = currSum;
                }
                index--;
            }
            currSum = maxObeh;

            index = middle + 1;
            while (index < arr.size()) {
                currSum += arr.get(index);
                if (maxObeh < currSum) {
                    maxObeh = currSum;
                }
                index++;
            }

            System.out.println(arr + ": " + Math.max(leva, Math.max(desna, maxObeh)));
            return Math.max(leva, Math.max(desna, maxObeh));
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<Integer>();

        while (sc.hasNextInt()) {
            arr.add(sc.nextInt());
        }
        podzaporedje(arr, 0);
    }
}