
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
public class Percolation {

    private boolean[] Cells;
    private WeightedQuickUnionUF bt1, nt2;
    private int TOP;
    private int BOTTOM;
    private int Grid_Size;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("Invalid arg");
        }
        bt1 = new WeightedQuickUnionUF(N * N + 2);
        nt2 = new WeightedQuickUnionUF(N * N + 1);
        Cells = new boolean[N*N];
        for (int i = 0; i < (N*N); i++) {
                Cells[i] = true;
        }
        TOP = 0;
        BOTTOM = N * N + 1;
		Grid_Size=N;
    }
	 private void Verify(int i,int j) {
        if (i <= 0 || j<=0 || i > Grid_Size || j> Grid_Size) {
            throw new java.lang.IllegalArgumentException("Invalid argument");
        }
        return;
    }
	 public void open(int i,int j) {
        Verify(i,j);
		int a= (i-1)*Grid_Size + j;
        if (!Cells[a - 1]) {
            return;
        }
        if (a<=Grid_Size){
            this.bt1.union(TOP, a);
            this.nt2.union(TOP, a);
        }
        if (a > (Grid_Size*(Grid_Size-1))){
            this.bt1.union(a, BOTTOM);
        }
		int[] x = {-1, -Grid_Size, 1, Grid_Size};
		if(a%Grid_Size==1)
		{
			x[0]=0;
		}
		if(a%Grid_Size==0)
		{
			x[2]=0;
		}
        for (int k = 0; k < 4; ++k) {
            int a2 = a + x[k];
			if(a2!=a){
            if (a2 <= 0 || a2 > (Grid_Size*Grid_Size) || Cells[a2 - 1]) {
                continue;
            }
            this.bt1.union(a, a2);
            this.nt2.union(a, a2);
			}
        }
        Cells[a - 1] = false;
        return;
    }
	public boolean isOpen(int i,int j) {
        Verify(i,j);
        int b= (i-1)*Grid_Size + j;
        return !Cells[b - 1];
    }
	public boolean percolates() {
        return this.bt1.connected(TOP, BOTTOM);
    }
	 public boolean isFull(int i,int j) {
        Verify(i,j);
        return (!Cells[(i-1)*Grid_Size + j - 1]) && nt2.connected(TOP, (i-1)*Grid_Size + j);
    }
	public int numberOfOpenSites()
	{
		int cnt=0;
		for (int i = 0; i < Grid_Size*Grid_Size; ++i) {
			if(Cells[i] == false)
				{
					cnt ++;
				}
        }
		return cnt;
	}
	public static void main(String[] args) {
	//testing
    }
}
