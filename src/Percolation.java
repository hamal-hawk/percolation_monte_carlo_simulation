import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private int n;
    private int numOfOpenSites;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF grid2;
    private boolean[] open;

    public Percolation(int n){
        if(n<=0){
            throw new IllegalArgumentException();
        }
        this.n = n;
        grid = new WeightedQuickUnionUF((n*n)+2);
        grid2 = new WeightedQuickUnionUF((n*n)+2);
        open = new boolean[(n*n)+2];
        numOfOpenSites = 0;
        for(int i = 1; i <= n; i++){
            grid.union(0, i);
            grid2.union(0,i);
        }
        for(int i = (n*n)-n + 1; i <= (n*n); i++){
            grid.union(i, (n*n)+1);
        }
    }

    public void open(int row, int col) {

        if(row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException();
        }
        if(isOpen(row, col)){
            return;
        }

        int index = (row - 1) * n + col;
        open[index] = true;
        numOfOpenSites++;
        if (index > n && index <= (n * n) - n) {
            if (index % n == 1) {
                if(open[index+1]) {grid.union(index, index + 1);grid2.union(index, index + 1);}//connect to right
                if(open[index-n]) {grid.union(index, index - n);grid2.union(index, index - n);} //connect to up
                if(open[index+n]) {grid.union(index, index + n);grid2.union(index, index + n);} //connect to bottom
            } else if (index % n == 0) {
                if(open[index-1]) {grid.union(index, index - 1);grid2.union(index, index - 1);} //connect to left
                if(open[index-n]) {grid.union(index, index - n);grid2.union(index, index - n);} //connect to up
                if(open[index+n]) {grid.union(index, index + n);grid2.union(index, index + n);} //connect to bottom
            } else {
                if(open[index-1]) {grid.union(index, index - 1);grid2.union(index, index - 1);} //connect to left
                if(open[index+1]) {grid.union(index, index + 1);grid2.union(index, index + 1);} //connect to right
                if(open[index-n]) {grid.union(index, index - n);grid2.union(index, index - n);} //connect to up
                if(open[index+n]) {grid.union(index, index + n);grid2.union(index, index + n);} //connect to bottom
            }
        }
        else if (index <= n) {
            if (index % n == 1) {
                if(open[index+1]) {grid.union(index, index + 1);grid2.union(index, index + 1);} //connect to right
                if(open[index+n]) {grid.union(index, index + n);grid2.union(index, index + n);} //connect to bottom
            } else if (index % n == 0) {
                if(open[index-1]) {grid.union(index, index - 1);grid2.union(index, index - 1);} //connect to left
                if(open[index+n]) { grid.union(index, index + n);grid2.union(index, index + n);} //connect to bottom
            } else {
                if(open[index-1]) {grid.union(index, index - 1);grid2.union(index, index - 1);} //connect to left
                if(open[index+1]) {grid.union(index, index + 1);grid2.union(index, index + 1);} //connect to right
                if(open[index+n]) {grid.union(index, index + n);grid2.union(index, index + n);} //connect to bottom
            }
                }
         else if (index > (n*n) -n) {
            if (index % n == 1) {
                if(open[index+1]) {grid.union(index, index + 1);grid2.union(index, index + 1);} //connect to right
                if(open[index-n]) {grid.union(index, index - n);grid2.union(index, index - n);} //connect to up
            } else if (index % n == 0) {
                if(open[index-1]) {grid.union(index, index - 1);grid2.union(index, index - 1);} //connect to left
                if(open[index-n]) { grid.union(index, index - n);grid2.union(index, index - n);} //connect to up
            } else {
                if(open[index-1]) {grid.union(index, index - 1);grid2.union(index, index - 1);} //connect to left
                if(open[index+1]) {grid.union(index, index + 1);grid2.union(index, index + 1);} //connect to right
                if(open[index-n]) {grid.union(index, index - n);grid2.union(index, index - n);} //connect to up
            }

        }
     }

    public boolean isOpen(int row, int col){
        if(row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException();
        }

        int index = (row - 1) * n + col;

        return open[index];
    }

//    public boolean isFull(int row, int col){
//        if(row <= 0 || row > n || col <= 0 || col > n){
//            throw new IllegalArgumentException();
//        }
//        int index = (row - 1) * n + col;
//        if(!isOpen(row, col)){
//            return false;
//        }
//        return grid.find(0) == grid.find(index);
//    }

    public boolean isFull(int row, int col){
        if(row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException();
        }
        int index = (row - 1) * n + col;
        if(!isOpen(row, col)){
            return false;
        }
        return grid2.find(0) == grid2.find(index);
    }

    public int numberOfOpenSites(){

        return numOfOpenSites;
    }

    public boolean percolates(){
        if(numberOfOpenSites() == 0 ){
            return false;
        }
        return grid.find(0) == grid.find(((n*n)+1));
    }





//test here
    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(3,2);
        p.open(4,2);

        System.out.println(p.percolates());

    }
}
