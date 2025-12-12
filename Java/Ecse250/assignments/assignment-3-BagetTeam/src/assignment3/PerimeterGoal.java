package assignment3;

import java.awt.Color;

public class PerimeterGoal extends Goal{

	public PerimeterGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		/*
		 * ADD YOUR CODE HERE
		 */
		Color[][] flattenBoard = board.flatten();
		int score = 0;

        for (int i = 0; i < flattenBoard.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (flattenBoard[i][j * (flattenBoard.length - 1)].equals(targetGoal)) {
					score++;
				}
            }
        }
		for (int j = 0; j < flattenBoard.length; j++) {
            for (int i = 0; i < 2; i++) {
                if (flattenBoard[i * (flattenBoard.length - 1)][j].equals(targetGoal)) {
					score++;
				}
            }
        }
		return score;
	}

	@Override
	public String description() {
		return "Place the highest number of " + GameColors.colorToString(targetGoal) 
		+ " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}

}
