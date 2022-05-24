import java.util.ArrayList;
import java.util.Scanner;

/**
 * izziv7
 */
public class izziv7 {

    public static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int findOpposite(int n, int base) {
        int i = n;
        while (i <= base) {
            if ((i * n) % base == 1) {
                // System.out.println(i);
                return i;
            }

            i++;
        }
        return i;
    }

    public static void vandermontMatrix(int zero, int size, int base) {
        // System.out.println(base);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                long novaBaza = (long) Math.pow(zero, i) % base;
                long a = (long) (Math.pow(novaBaza, j) % base);
                System.out.printf("%2d ", a);
            }
            System.out.println();
        }
    }

    public static ArrayList<Integer> findNthZero(int power, int base) {
        ArrayList<Integer> zeros = new ArrayList<Integer>();
        int i = 2;

        while (i <= base) {
            if (Math.pow(i, power) % base == 1) {
                // System.out.println(i);
                zeros.add(i);
                for (int j = 1; j < power; j++) {
                    if (Math.pow(i, j) % base == 1) {
                        zeros.remove(zeros.size() - 1);
                        break;
                    }
                }

            }
            i++;
        }
        if (zeros.size() == 0) {
            return null;
        }

        return zeros;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int power = sc.nextInt();

        for (int i = power; true; i++) {
            if (isPrime(i)) {
                if (findNthZero(power, i) != null) {
                    ArrayList<Integer> zeros = findNthZero(power, i);
                    // int opposite = findOpposite(zero, i);
                    System.out.print(i + ": ");
                    for (Integer integer : zeros) {
                        System.out.printf("%d ", integer);
                    }
                    System.out.println();
                    vandermontMatrix(zeros.get(0), power, i);
                    break;
                }

                // System.out.println("base: " + i);
                // break;
            }
        }
    }
}