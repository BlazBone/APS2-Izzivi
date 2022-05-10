import java.util.Arrays;
import java.util.Scanner;

/**
 * izziv10
 */
public class izziv10 {

    private static boolean[][] valid;
    private static int[][] egg;

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%4d", arr[i][j]);
            }
            System.out.println();
        }
    }

    static int eggDrop2(int n, int k) {
        // If there are no floors, then
        // no trials needed. OR if there
        // is one floor, one trial needed.
        if (k == 1 || k == 0)
            return k;

        // We need k trials for one egg
        // and k floors
        if (n == 1)
            return k;

        int min = Integer.MAX_VALUE;
        int x, res;

        // Consider all droppings from
        // 1st floor to kth floor and
        // return the minimum of these
        // values plus 1.
        for (x = 1; x <= k; x++) {
            res = Math.max(eggDrop(n - 1, x - 1),
                    eggDrop(n, k - x));
            if (res < min)
                min = res;
        }

        return min + 1;
    }

    public static int eggDrop(int n, int k) {
        
        if (valid[n][k]) {
            return egg[n][k];
        }
        if (k == 1) {
            return n;
        }
        if (n <= 1) {
            return 0;
        }   
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, Math.max(eggDrop(i - 1, k-1), eggDrop(n-i, k)));
        }
        valid[n][k]=true;
        return min + 1;

        // return 1 + Math.min(eggDrop(n - 1, k - 1), eggDrop(n - 1, k));
        
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.close();
        valid = new boolean[n+1][k+1];
        egg = new int[n+1][k+1];
        int[][] egg2 = new int[n+1][k+1];
        //egg dropping problem
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if(j==0){
                    egg[i][j]=i;
                }else if(i==0){
                    egg[i][j]=0;
                }else{
                    egg[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        //fill the array egg
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                egg2[i][j] = eggDrop2(i, j);
                // egg[i][j] = eggDrop(i, j);
            }
        }
        printArr(egg);
        printArr(egg2);
        
        // egg[i][j] = eggDrop(i, j);
        // System.out.println(Arrays.deepToString(egg));
    }
}