import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;


/**
 * Izziv11
 */
public class Izziv11 {

    /**
     * Pair of integers
     */
    class Pair {
        int weight;
        int value;
        Pair(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
        public String toString() {
            return "(" + weight + ", " + value + ")";
        }
        
    }
    // 0 1 backpack algorithm
    public static int[][] backpack(int[] weights, int[] values, int capacity) {
        int[][] dp = new int[weights.length + 1][capacity + 1];
        for (int i = 1; i <= weights.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp;
    } 
    public static void printList(ArrayList<Pair> list, int i) {
        System.out.printf("%d: ", i);
        for (Pair p : list) {
            System.out.printf("%s ", p);
        }
        System.out.println();
    }
    public static void backpackMona(int[] weights, int[] values, int W) {
        Izziv11 a = new Izziv11();
        ArrayList<Pair> list = new ArrayList<>();
        // for (int i = 0; i < weights.length; i++) {
        //     list.add(a.new Pair(0, 0));
        // }
        list.add(a.new Pair(0, 0));
        // Collectios.sort(list, (a, b) -> b.weight - a.weight);
        // Database.arrayList.sort((o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
        Collections.sort(list, (f, b) -> f.weight - b.weight);
        // System.out.println(list);
        printList(list, 0);
        for (int i = 0; i < values.length; i++) {
           
            //add ith item to evertything
            int n = list.size();
            for (int j = 0; j < n; j++) {
                Pair temp = list.get(j);
                if (temp.weight + weights[i] <= W) {
                    // System.out.println(temp);
                    // System.out.println("adding " + weights[i] + " to " + temp.weight);
                    // System.out.println("adding " + values[i] + " to " + temp.value);
                    list.add(a.new Pair(temp.weight + weights[i], temp.value + values[i]));
                }
            }
            // remove duplicates
            Set<Pair> set2 = new HashSet<>();
            set2.addAll(list);
            list.clear();
            list.addAll(set2);
            Collections.sort(list, (f, b) -> f.weight - b.weight);
            // // remove bad items

            Set<Pair> toRemove = new HashSet<>();

            int size = list.size();
            for (int j = 0; j < size; j++) {
                Pair temp1 = list.get(j);
                for(int k = j + 1; k < size; k++) {
                    Pair temp2 = list.get(k);
                    // System.out.println(temp1 + " " + temp2);
                    if (temp1.weight <= temp2.weight && temp1.value >= temp2.value ) {
                        toRemove.add(temp2);    // add to remove list
                    }else if (temp1.weight >= temp2.weight && temp1.value <= temp2.value) {
                        toRemove.add(temp1); // add to remove list
                    }
                }
                
            }
            list.removeAll(toRemove);
            
            // list.clear();
            // list.addAll(set);
            Collections.sort(list, (f, b) -> f.weight - b.weight);
            // System.out.println(list);
            printList(list,i+1);
            //dont add ith item to everything
        }
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int volume = sc.nextInt();
        int numOfItems = sc.nextInt();
        int[] weights = new int[numOfItems];
        int[] values = new int[numOfItems];
        for (int i = 0; i < numOfItems; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }
        sc.close();
        int[][] a = backpack(weights, values, volume);
        backpackMona(weights, values, volume);
        // System.out.println(Arrays.deepToString(a));
    }
}