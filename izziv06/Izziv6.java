import java.util.Arrays;
import java.util.Scanner;

class Matrix {

    private int[][] m;

    public int n; // only square matrices

    public Matrix(int n) {

        this.n = n;

        m = new int[n][n];

    }

    public int sumAllElems() {
        int suma = 0;
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                suma += this.v(i, j);
            }
        }
        return suma;
    }

    // set value at i,j
    public void setV(int i, int j, int val) {

        m[i][j] = val;

    }

    // get value at index i,j
    public int v(int i, int j) {

        return m[i][j];

    }

    // return a square submatrix from this
    public Matrix getSubmatrix(int startRow, int startCol, int dim) {

        Matrix subM = new Matrix(dim);

        for (int i = 0; i < dim; i++)

            for (int j = 0; j < dim; j++)

                subM.setV(i, j, m[startRow + i][startCol + j]);

        return subM;

    }

    // write this matrix as a submatrix from b (useful for the result of
    // multiplication)
    public void putSubmatrix(int startRow, int startCol, Matrix b) {

        for (int i = 0; i < b.n; i++)

            for (int j = 0; j < b.n; j++)

                setV(startRow + i, startCol + j, b.v(i, j));

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(m[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // matrix addition
    public Matrix sum(Matrix b) {

        Matrix c = new Matrix(n);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                c.setV(i, j, m[i][j] + b.v(i, j));

            }

        }

        return c;

    }

    // matrix subtraction
    public Matrix sub(Matrix b) {

        Matrix c = new Matrix(n);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                c.setV(i, j, m[i][j] - b.v(i, j));

            }

        }

        return c;

    }

    // simple multiplication
    public Matrix mult(Matrix b) {
        // TODO
        Matrix res = new Matrix(b.n);

        for (int i = 0; i < b.n; i++) {
            for (int j = 0; j < b.n; j++) {
                for (int k = 0; k < b.n; k++) {
                    res.setV(i, j, res.v(i, j) + m[i][k] * b.v(k, j));
                }
            }
        }
        return res;
    }

    // Strassen multiplication
    public Matrix multStrassen(Matrix b, int cutoff) {
        Matrix res = new Matrix(b.n);
        if (cutoff >= b.n) {
            return this.mult(b);
        } else {
            Matrix a11 = this.getSubmatrix(0, 0, b.n / 2);
            Matrix a12 = this.getSubmatrix(0, b.n / 2, b.n / 2);
            Matrix a21 = this.getSubmatrix(b.n / 2, 0, b.n / 2);
            Matrix a22 = this.getSubmatrix(b.n / 2, b.n / 2, b.n / 2);

            Matrix b11 = b.getSubmatrix(0, 0, b.n / 2);
            Matrix b12 = b.getSubmatrix(0, b.n / 2, b.n / 2);
            Matrix b21 = b.getSubmatrix(b.n / 2, 0, b.n / 2);
            Matrix b22 = b.getSubmatrix(b.n / 2, b.n / 2, b.n / 2);

            Matrix p1 = (a11.sum(a22)).multStrassen(b11.sum(b22), cutoff);
            Matrix p2 = (a21.sum(a22)).multStrassen(b11, cutoff);
            Matrix p3 = a11.multStrassen(b12.sub(b22), cutoff);
            Matrix p4 = a22.multStrassen(b21.sub(b11), cutoff);
            Matrix p5 = (a11.sum(a12)).multStrassen(b22, cutoff);
            Matrix p6 = (a21.sub(a11)).multStrassen(b11.sum(b12), cutoff);
            Matrix p7 = (a12.sub(a22)).multStrassen(b22.sum(b21), cutoff);
            System.out.println("m1: " + p1.sumAllElems());
            System.out.println("m2: " + p2.sumAllElems());
            System.out.println("m3: " + p3.sumAllElems());
            System.out.println("m4: " + p4.sumAllElems());
            System.out.println("m5: " + p5.sumAllElems());
            System.out.println("m6: " + p6.sumAllElems());
            System.out.println("m7: " + p7.sumAllElems());
            Matrix c11 = p1.sum(p4).sub(p5).sum(p7);
            Matrix c12 = p3.sum(p5);
            Matrix c21 = p2.sum(p4);
            Matrix c22 = p1.sub(p2).sum(p3).sum(p6);

            res.putSubmatrix(0, 0, c11);
            res.putSubmatrix(0, b.n / 2, c12);
            res.putSubmatrix(b.n / 2, 0, c21);
            res.putSubmatrix(b.n / 2, b.n / 2, c22);
            // System.out.print(res.toString());
        }
        return res;
    }

}

public class Izziv6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int rekStop = sc.nextInt();
        Matrix a = new Matrix(n);
        Matrix b = new Matrix(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a.setV(i, j, sc.nextInt());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b.setV(i, j, sc.nextInt());
            }
        }

        Matrix c = a.multStrassen(b, rekStop);

        System.out.print(c.toString());
    }

}
