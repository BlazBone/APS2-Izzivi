
/**
 * Izziv01Bone
 * 
 * RAzmisleki:
 * Zakaj so casi pri vas drugacni kot v zgornji tabeli?
 *          Imamo drugacne implementacije (nekateri linearni so lahko hitrejsi kot drugi) ter drugacne specifikacije racunalnika, predvsem to.
 * 
 * Kateri algoritem je hitrejsi?
 *      Za iskanje je hitrjsi dvojiski algoritem ta ima zahtevnost log(n) linearni pa ima n
 * 
 * Kdaj bi bil lahko pocasnejsi algoritem hitrejsi?
 *      predvsem, ko je stevilka ki jo iscemo v intervalu od 0-log(n)
 * 
 * Kako je cas odvisem od velikosti naloge?
 *      binarno iskanje - log(n)
 *      linerno iskanje - n
 * 
 * Je casovna odvisnot dvojiskega iskanja blizje linearni ali konstanti?
 *      blizje je konstanti. (lahko pogledamo podatke, ali pa samo pogledamo funckijo logoritma, oz njen graf ki zelo pocasi narasca)
 * 
 * 
 */

import java.util.Random;

public class Izziv01Bone {

    public static long timeLinear(int n) {
        int[] table = generateTable(n);
        long start = System.nanoTime();
        Random gen = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomInteger = gen.nextInt(n - 1) + 1;
            findLinear(table, randomInteger);
        }

        long stop = System.nanoTime();
        return (stop - start) / 1000;

    }

    public static long timeBinary(int n) {
        int[] table = generateTable(n);
        Random gen = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            int randomInteger = gen.nextInt(n - 1) + 1;
            findBinary(table, randomInteger, 0, n);

        }

        long stop = System.nanoTime();
        return (stop - start) / 1000;
    }

    public static int findLinear(int[] table, int value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int findBinary(int[] table, int value, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            if (table[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            return findBinary(table, value, left, right);

        } else {
            return left;
        }

    }

    public static int[] generateTable(int n) {
        int[] gen = new int[n];
        for (int i = 0; i < n; i++) {

            gen[i] = i + 1;
        }
        return gen;

    }

    public static void printLines(long[] table1, long[] table2) {

        int n = 0;
        System.out.println();
        System.out.println();
        System.out.printf("%7s | %8s | %8s |\n", "n", "linearno", "dvojisko");
        System.out.printf("--------------------------------\n");
        for (int i = 0; i < table2.length; i++) {
            n += 1000;
            System.out.printf("%7d | %8d | %8d\n", n, table1[i], table2[i]);
        }

    }

    public static void drawLines(long[] table1, long[] table2) {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(0, 110);
        StdDraw.setYscale(0, 15000);
        StdDraw.setPenRadius(0.009);
        StdDraw.textLeft(5, 7000, "Cas");
        StdDraw.textRight(55, 100, "n");
        StdDraw.textRight(50, 10000, "Modre so oznake za linearno, rdece za dvojisko iskanje");
        for (int i = 0; i < table2.length; i++) {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(i + 3, table1[i] + 300);
            StdDraw.setPenColor(StdDraw.MAGENTA);
            StdDraw.point(i + 3, table2[i] + 300);
            // StdDraw.line(0.2, 0.2, 0.8, 0.2);
        }
    }

    public static void main(String[] args) {

        long[] timesBin = new long[100];
        long[] timesLin = new long[100];
        int n = 0;
        timeBinary(5000); // da se malo ogreje
        timeLinear(5000);

        for (int i = 0; i < 100; i++) {
            n += 1000;
            timesBin[i] = timeBinary(n);
            timesLin[i] = timeLinear(n);
        }
        printLines(timesLin, timesBin);
        drawLines(timesLin, timesBin);
        // StdDraw.line(1, 1, 1, 1);

    }

}