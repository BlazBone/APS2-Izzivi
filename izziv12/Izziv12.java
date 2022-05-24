import java.util.*;

/**
 * Izziv12
 */
public class Izziv12 {
    public static void printPaths(Integer[] paths, int h) {
        System.out.print("h"+h+": ");
        for (int i = 0; i < paths.length; i++) {
            if (paths[i] == Integer.MAX_VALUE) {
                
                System.out.print("Inf ");
            }else {

                System.out.print(paths[i] + " ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfVertices = sc.nextInt();
        Integer[] paths1 = new Integer[numOfVertices];
        Integer[] paths2 = new Integer[numOfVertices];
        for (int i = 1; i < paths1.length; i++) {
            paths1[i] = Integer.MAX_VALUE;
            paths2[i] = Integer.MAX_VALUE;
            // System.out.println('h');
        }
        paths1[0]=0;
        paths2[0]=0;
        ArrayList<Edge> edges = new ArrayList<>();
        while (sc.hasNext()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();
            Edge ed = new Edge(from, to, weight);
            edges.add(ed);
        }

        //belman-Ford
        // System.out.println(Arrays.toString(paths));

        for (int i = 0; i < numOfVertices; i++) {
            printPaths(paths2, i);
            for (int j = 0; j < paths2.length; j++) {
                paths1[j] = paths2[j];
            }
            for (Edge ed : edges) {
               
                if (paths1[ed.from] != Integer.MAX_VALUE && paths1[ed.from] + ed.weight < paths1[ed.to]) {
                    int rez = paths1[ed.from] + ed.weight;
                    if (rez < paths2[ed.to]) {
                        paths2[ed.to] = rez;
                    }
                }
            }
        
        }
    }   
}

/**
 * Edge
 */
class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}