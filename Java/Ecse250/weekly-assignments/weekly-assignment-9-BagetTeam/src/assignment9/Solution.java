package assignment9;
public class Solution {
    private int[][] grid;
    private int n;
    private int m;
    private int[][] directions = {{0, 1}, {1, 0}}; //right and down
    private int count = 0;
    public int getNumPaths(int[][] grid) {
        // TODO: complete implementation
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        if (grid[0][0] == 1) {
            return 0;
        }
        findPath(0, 0);
        return count;

    }
    private void findPath(int i, int j) {
        if (i == n-1 && j == m-1 && grid[i][j] == 0) {
            count++;
            return;
        }
        for (int[] direction : directions) {
            int ni = i + direction[0];
            int nj = j + direction[1];
            if (ni == n || nj == m) {
                continue;
            }
            if (grid[ni][nj] == 1) {
                continue;
            }
            findPath(ni, nj);
        }
    }
}
