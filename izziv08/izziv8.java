import java.util.Arrays;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

class Complex {
    double re;
    double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public String toString() {
        double tRe = (double) Math.round(re * 100000) / 100000;
        double tIm = (double) Math.round(im * 100000) / 100000;
        if (tIm == 0)
            return tRe + "";
        if (tRe == 0)
            return tIm + "i";
        if (tIm < 0)
            return tRe + "-" + (-tIm) + "i";
        return tRe + "+" + tIm + "i";
    }

    public Complex conj() {
        return new Complex(re, -im);
    }

    // sestevanje
    public Complex plus(Complex b) {
        Complex a = this;
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // odstevanje
    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // mnozenje z drugim kompleksnim stevilo
    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // mnozenje z realnim stevilom
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // reciprocna vrednost kompleksnega stevila
    public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    // deljenje
    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    // e^this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // potenca komplesnega stevila
    public Complex pow(int k) {

        Complex c = new Complex(1, 0);
        for (int i = 0; i < k; i++) {
            c = c.times(this);
        }
        return c;
    }

}

public class izziv8 {

    public static Complex[] FFT(Complex[] polinom) {
        // System.out.println(Arrays.toString(polinom));
        int n = polinom.length;
        if (n == 1) {
            return polinom;
        }

        Complex[] polinom1 = new Complex[n / 2];
        Complex[] polinom2 = new Complex[n / 2];

        for (int i = 0; i < n / 2; i++) {
            polinom1[i] = polinom[2 * i];
            polinom2[i] = polinom[2 * i + 1];
        }
        // recursive calls
        Complex[] fft1 = FFT(polinom1);
        for (int i = 0; i < fft1.length; i++) {
            if (fft1[i] == null) {
                fft1[i] = new Complex(0, 0);
            }
        }
        Complex[] fft2 = FFT(polinom2);
        for (int i = 0; i < fft2.length; i++) {
            if (fft1[i] == null) {
                fft1[i] = new Complex(0, 0);
            }
        }
        // Complex pwr = new Complex(2 * Math.PI / n, 2 * Math.PI / n);
        // Complex root = pwr.exp();
        Complex root = new Complex(Math.cos(2 * Math.PI / n), Math.sin(2 * Math.PI / n));
        // System.out.printf("koren za %d: %s\n", n, root);
        Complex fRoot = new Complex(1, 0);
        Complex[] fft = new Complex[n];
        for (int i = 0; i < fft.length; i++) {
            fft[i] = new Complex(0, 0);
        }
        for (int i = 0; i < n / 2; i++) {
            fft[i] = fft1[i].plus(fRoot.times(fft2[i]));
            fft[i + n / 2] = fft1[i].minus(fRoot.times(fft2[i]));
            fRoot = fRoot.times(root);
        }
        // System.out.println(Arrays.deepToString(polinom1) + " :\n");
        for (Complex complex : fft) {
            System.out.print(complex.toString());
            System.out.print(" ");
        }
        System.out.println();
        // System.out.println();
        // System.out.println(Arrays.deepToString(polinom2) + " :\n");
        // for (Complex complex : fft2) {
        // System.out.print(complex.toString() + " ");
        // }
        // System.out.println();
        return fft;
    }

    public static Complex[] FFTinv(Complex[] polinom) {
        // System.out.println(Arrays.toString(polinom));
        int n = polinom.length;
        if (n == 1) {
            return polinom;
        }

        Complex[] polinom1 = new Complex[n / 2];
        Complex[] polinom2 = new Complex[n / 2];

        for (int i = 0; i < n / 2; i++) {
            polinom1[i] = polinom[2 * i];
            polinom2[i] = polinom[2 * i + 1];
        }
        // recursive calls
        Complex[] fft1 = FFTinv(polinom1);
        for (int i = 0; i < fft1.length; i++) {
            if (fft1[i] == null) {
                fft1[i] = new Complex(0, 0);
            }
        }
        Complex[] fft2 = FFTinv(polinom2);
        for (int i = 0; i < fft2.length; i++) {
            if (fft1[i] == null) {
                fft1[i] = new Complex(0, 0);
            }
        }
        // Complex pwr = new Complex(2 * Math.PI / n, 2 * Math.PI / n);
        // Complex root = pwr.exp();
        Complex root = new Complex(Math.cos(2 * Math.PI / n), -1 * Math.sin(2 * Math.PI / n));
        // System.out.printf("\n\nkoren za %d: %s\n\n", n, root);
        Complex fRoot = new Complex(1, 0);
        Complex[] fft = new Complex[n];
        for (int i = 0; i < fft.length; i++) {
            fft[i] = new Complex(0, 0);
        }
        for (int i = 0; i < n / 2; i++) {
            fft[i] = fft1[i].plus(fRoot.times(fft2[i]));
            fft[i + n / 2] = fft1[i].minus(fRoot.times(fft2[i]));
            fRoot = fRoot.times(root);
        }
        // System.out.println(Arrays.deepToString(polinom1) + " :\n");
        for (Complex complex : fft) {
            System.out.print(complex.toString());
            System.out.print(" ");
        }
        System.out.println();
        // System.out.println();
        // System.out.println(Arrays.deepToString(polinom2) + " :\n");
        // for (Complex complex : fft2) {
        // System.out.print(complex.toString() + " ");
        // }
        // System.out.println();
        return fft;
    }

    public static int nextPowerOf2(int a) {
        int b = 1;
        while (b < a) {
            b = b << 1;
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int dolzinaPolinomov = sc.nextInt();
        int dolzinaPolinomov2 = (int) Math.pow(2, Math.ceil(Math.log(dolzinaPolinomov * 2) / Math.log(2)));
        Complex[] polinom1 = new Complex[dolzinaPolinomov2];
        Complex[] polinom2 = new Complex[dolzinaPolinomov2];
        for (int i = 0; i < dolzinaPolinomov; i++) {
            polinom1[i] = new Complex(sc.nextDouble(), 0);
        }
        for (int i = 0; i < dolzinaPolinomov; i++) {
            polinom2[i] = new Complex(sc.nextDouble(), 0);
        }

        for (int i = dolzinaPolinomov; i < dolzinaPolinomov2; i++) {
            polinom1[i] = new Complex(0, 0);
            polinom2[i] = new Complex(0, 0);
        }
        // double[] bb = { 1.0, 1.0 };
        // Complex[] nevem = new Complex[4];
        // nevem[0] = new Complex(1, 0);
        // nevem[1] = new Complex(1, 0);
        // nevem[2] = new Complex(0, 0);
        // nevem[3] = new Complex(0, 0);
        Complex[] a = FFT(polinom1);
        // for (Complex complex : a) {
        // System.out.print(complex.toString() });
        // }
        // System.out.println();
        Complex[] b = FFT(polinom2);

        Complex[] ab = new Complex[dolzinaPolinomov2];
        for (int i = 0; i < dolzinaPolinomov2; i++) {
            ab[i] = a[i].times(b[i]);
        }
        Complex[] abInv = FFTinv(ab);

        for (Complex complex : abInv) {
            System.out.print(complex.times(new Complex(1.0 / dolzinaPolinomov2, 0)) + " ");
        }
    }
}