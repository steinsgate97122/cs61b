package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int N;
    private int T;
    private PercolationFactory pf;
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        double[] fractionArr = new double[T];
        for (int i = 0; i < T; i++) {
            fractionArr[i] = experiment();
        }
        this.mean = StdStats.mean(fractionArr);
        this.stddev = StdStats.stddev(fractionArr);
        this.confidenceLow = this.mean - 1.96 * stddev / Math.sqrt(T);
        this.confidenceHigh = this.mean + 1.96 * stddev / Math.sqrt(T);
    }

    private double experiment() {
        Percolation percolation = pf.make(N);
        int[] indexArr = new int[N * N];
        for (int i = 0; i < indexArr.length; i++) {
            indexArr[i] = i;
        }
        StdRandom.shuffle(indexArr);
        for (int i : indexArr) {
            int row = i / N;
            int col = i % N;
            percolation.open(row, col);
            if (percolation.percolates()) {
                break;
            }
        }
        return ((double) percolation.numberOfOpenSites()) / (N * N);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return confidenceLow;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHigh;
    }
}
