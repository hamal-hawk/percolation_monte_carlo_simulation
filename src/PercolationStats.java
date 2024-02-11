import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private int trials;
    private double[] results;
    //private Percolation percolation;

    public PercolationStats(int n, int trials){
        if(n <=0 || trials <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        results = new double[trials];
        Percolation percolation;

        for(int i = 0; i < trials; i++){
            percolation = new Percolation(n);
            while(!percolation.percolates()){
                int x = StdRandom.uniformInt(1, n+1);
                int y = StdRandom.uniformInt(1, n+1);
                if(!percolation.isOpen(x,y)) {
                    percolation.open(x, y);
                }
                //StdOut.println(percolation.numberOfOpenSites());
            }
            results[i] = (double)percolation.numberOfOpenSites()/(double)(n*n);
            //StdOut.println(percolation.numberOfOpenSites());
        }
    }

    public double mean(){
        return StdStats.mean(results);
    }

    public double stddev(){
        return StdStats.stddev(results);
    }

    public double confidenceLo(){
        return mean() - ((1.96 * stddev())/Math.sqrt(trials));
    }

    public double confidenceHi(){
        return mean() + ((1.96 * stddev())/Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, T);
        StdOut.println("mean\t"+"= "+percolationStats.mean());
        StdOut.println("stddev\t"+"= "+percolationStats.stddev());
        StdOut.println("95% confidence interval\t"+"= "+"["+percolationStats.confidenceLo()+", "+percolationStats.confidenceHi()+"]");

    }
}
