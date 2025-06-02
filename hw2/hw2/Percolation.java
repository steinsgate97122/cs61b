package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    /*
    isFull()需要constant time，如果遍历第一行的所有元素，时间复杂度会变成O(N)
    此外percolates()也需要constant time，所以也不能遍历最后一行的所有元素
    因此在disjoint set中额外维护2个元素，分别代表顶部和底部
    顶部索引规定为N*N，底部索引N*N+1
    只用1个disjointSet会有问题，底部的元素不应该传递full的关系
    如果只使用一个disjointSet，会在底部元素full之后，将最后一行open的元素都设置为full
    */
    private int size;
    private boolean[][] world;
    WeightedQuickUnionUF quickUnionUFAll;
    WeightedQuickUnionUF quickUnionUFTop;
    private int openCount;

    /* create N-by-N grid, with all sites initially blocked
    quickUnionUF在world内N*N的基础上+2，分别代表顶部和底部元素
    顶部元素的index认为是-1，底部index认为是N*N，分别与第一行和最后一行元素连接
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        size = N;
        world = new boolean[N][N];
        quickUnionUFTop = new WeightedQuickUnionUF(N * N + 1);
        quickUnionUFAll = new WeightedQuickUnionUF(N * N + 2);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (world[row][col]) {
            return;
        }
        world[row][col] = true;
        openCount += 1;
        int index = row * size + col;
        // 如果row为0或者size-1，那么还要额外与顶部或者底部元素连通
        if (row == 0) {
            quickUnionUFTop.union(index, size * size);
            quickUnionUFAll.union(index, size * size);
        }
        if (row == size - 1) {
            quickUnionUFAll.union(index, size * size + 1);
        }
        // (row, col) open之后，如果上下左右4个存在open，那么这两个就可以connect
        if ((col - 1 >= 0) && isOpen(row, col - 1)) {
            int leftI = index - 1;
            quickUnionUFAll.union(index, leftI);
            quickUnionUFTop.union(index, leftI);
        }
        if ((col + 1 < size) && isOpen(row, col + 1)) {
            int rightI = index + 1;
            quickUnionUFAll.union(index, rightI);
            quickUnionUFTop.union(index, rightI);
        }
        if ((row - 1 >= 0) && isOpen(row - 1, col)) {
            int upperI = (row - 1) * size + col;
            quickUnionUFAll.union(index, upperI);
            quickUnionUFTop.union(index, upperI);
        }
        if ((row + 1 < size) && isOpen(row + 1, col)) {
            int lowerI = (row + 1) * size + col;
            quickUnionUFAll.union(index, lowerI);
            quickUnionUFTop.union(index, lowerI);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return world[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = row * size + col;
        return quickUnionUFTop.connected(index, size * size);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickUnionUFAll.connected(size * size, size * size + 1);
    }
}
