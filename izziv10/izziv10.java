import java.util.Scanner;

public class izziv10 {

    public static void printArr(int[][] arr) {
        System.out.printf("    ");
        for (int i = 1; i < arr[0].length; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%4d", arr[i][j]);
            }
            System.out.println();
        }
    }

    // function for egg dropping problem n number of eggs and k floors
    public static int eggDrop(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
            dp[i][1] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 0;
            dp[1][j] = 1;
            // dp[1][j] = 1;
        }
        // printArr(dp);
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int x = 1; x < i; x++) {
                    // printf("%d %d %d\n", i, j, x);
                    // System.out.printf("i: %d j: %d x: %d\n",i,j,x);
                    dp[i][j] = Math.min(dp[i][j], 1 + Math.max(dp[i-x][j],dp[x - 1][j-1]));
                }
            }
        }
        // printArr(dp);
        System.out.println(dp[n][k]);
        return dp[n][k];
    }
    
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /*
     * Function to get minimum number of trials needed in worst
     * case with n eggs and k floors
     */
    // static int eggDrop(int n, int k) {
    //     /*
    //      * A 2D table where entry eggFloor[i][j] will represent minimum
    //      * number of trials needed for i eggs and j floors.
    //      */
    //     int eggFloor[][] = new int[n + 1][k + 1];
    //     int res;
    //     int i, j, x;

    //     // We need one trial for one floor and0 trials for 0 floors
    //     for (i = 1; i <= n; i++) {
    //         eggFloor[i][1] = 1;
    //         eggFloor[i][0] = 0;
    //     }

    //     // We always need j trials for one egg and j floors.
    //     for (j = 1; j <= k; j++)
    //         eggFloor[1][j] = j;

    //     // Fill rest of the entries in table using optimal substructure
    //     // property
    //     for (i = 2; i <= n; i++) {
    //         for (j = 2; j <= k; j++) {
    //             eggFloor[i][j] = Integer.MAX_VALUE;
    //             for (x = 1; x <= j; x++) {
    //                 res = 1 + max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
    //                 if (res < eggFloor[i][j])
    //                     eggFloor[i][j] = res;
    //             }
    //         }
    //     }
    //     printArr(eggFloor);
    //     // eggFloor[n][k] holds the result
    //     return eggFloor[n][k];
    // }

    public static void main(String args[]) {
       Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int k = sc.nextInt();
         sc.close();
         eggDrop(n, k);
        
        
    }
    // This code is contributed by Ryuga.
}
