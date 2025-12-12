import assignment3.GameColors;
import assignment3.PerimeterGoal;

import java.awt.Color;
import java.util.Random;

import assignment3.BlobGoal;
import assignment3.Block;

public class Main {
    public static void main(String[] args) {
        // Block blockDepth2 = new Block(0,2);
        // blockDepth2.printBlock();

        // blockDepth2.updateSizeAndPosition(16, 0, 0);
        // blockDepth2.printBlock();

        // blockDepth2.reflect(0);
        // blockDepth2.printBlock();

        // blockDepth2.reflect(0);
        // blockDepth2.printBlock();

        // blockDepth2.rotate(1);
        // blockDepth2.printBlock();

        // blockDepth2.rotate(0);
        // blockDepth2.printBlock();

        // Block blockDepth3 = new Block(0,3);
        // blockDepth3.updateSizeAndPosition(16, 0, 0);
        // blockDepth3.printBlock();
        // Block b1 = blockDepth3.getSelectedBlock(2, 15, 1);
        // b1.printBlock();

        // Block b2 = blockDepth3.getSelectedBlock(3, 5, 1);
        // b2.printBlock();

        // b2.smash();
        // b2.printBlock();

        // Block blockDeapth3_2 = new Block(0, 4);
        // blockDeapth3_2.updateSizeAndPosition(16, 0, 0);
        // blockDeapth3_2.printBlock();
        
        // // Block b2 = blockDeapth3_2.getSelectedBlock(0, 1, 2);
        // // b2.printBlock();
        // // b2.smash();
        // // b2.printBlock();

        // Color[][] flattenBlock = blockDeapth3_2.flatten();

        // for (int i = 0; i < flattenBlock.length; i++) {
        //     for (int j = 0; j < flattenBlock[0].length; j++) {
        //         System.out.print(GameColors.colorToString(flattenBlock[i][j]).charAt(0));
        //     }
        //     System.out.println("");
        // }
        // PerimeterGoal goal = new PerimeterGoal(GameColors.RED);
        // int score = goal.score(blockDeapth3_2);
        // System.out.println(score);

        // int n = flattenBlock.length;
        // boolean[][] visited = new boolean[n][n];
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         visited[i][j] = false;
        //     }
        // }

        // BlobGoal blobGoal = new BlobGoal(GameColors.RED);
        // // int scoreBlob = blobGoal.undiscoveredBlobSize(n-1, n-1, flattenBlock, visited);
        // // System.out.println(scoreBlob);
        // int scoreBlob = blobGoal.score(blockDeapth3_2);
        // System.out.println(scoreBlob);


        Block.gen = new Random(99998888);
        Block b = new Block(4, 9);
        b.updateSizeAndPosition(128, 10, 20);

        Color[][] unitCells = b.flatten();

        boolean[][] visited = new boolean[unitCells.length][unitCells[0].length];

        BlobGoal g = new BlobGoal(GameColors.RED);
        int[] size = {-1};

        size[0] = g.undiscoveredBlobSize(750, 1000, unitCells, visited);

        System.out.println(size[0]);
    }
}
