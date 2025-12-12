package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal{

	public BlobGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		/*
		 * ADD YOUR CODE HERE
		 */
		Color[][] flattenBoard = board.flatten();
		int score = 0;

		int n = flattenBoard.length;

		boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = false;
            }
        }

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					int blobSize = undiscoveredBlobSize(i, j, flattenBoard, visited);
					if (blobSize > score) {
						score = blobSize;
					}
				}
			}
		}

		return score;
	}

	@Override
	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(targetGoal) 
		+ " blocks, anywhere within the block";
	}


	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		int[][] positions = new int[(int) Math.pow(unitCells.length, 2)][2];
		int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		
		System.out.println(i + " " + unitCells.length);
		if (i < 0 || i >= unitCells.length || j < 0 || j >= unitCells.length || !unitCells[i][j].equals(targetGoal)) {
			return 0;
		}

		positions[0] = new int[]{i, j};
		int idx = 0;
		visited[i][j] = true;

		int blobSize = 1;

		while (idx >= 0) {
			i = positions[idx][0];
			j = positions[idx][1];
			for (int[] direction : directions) {
				int ni = i + direction[0];
				int nj = j + direction[1];
				if (ni < 0 || ni >= unitCells.length || nj < 0 || nj >= unitCells.length) {
					continue;
				}
				if (visited[ni][nj]) {
					continue;
				}
				if (unitCells[ni][nj].equals(targetGoal)) {
					visited[ni][nj] = true;
					positions[idx] = new int[]{ni, nj};
					blobSize++;
					idx++;
				}
			}
			idx--;
		}

		return blobSize;

	}

}
