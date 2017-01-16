/**
 * In this class we give the initial coordinates for every figure with every possible rotation.
 * 
 */
package tetris.models;

import java.awt.Color;

public class Figure {

	private Block[] currentFigure = new Block[4];

	public Block getBlock(int index) {
		return currentFigure[index];
	}

	public void setBlock(Block block, int index) {
		this.currentFigure[index] = block;
	}

	public Figure(int figureType, int rotationPos) {
		for (int counter = 0; counter < 4; counter++) {
			currentFigure[counter] = new Block();
		}
		switch (figureType) {
		case 0:
			makeO();
			break;
		case 1:
			makeI(rotationPos);
			break;
		case 2:
			makeL(rotationPos);
			break;
		case 3:
			makeJ(rotationPos);
			break;
		case 4:
			makeS(rotationPos);
			break;
		case 5:
			makeZ(rotationPos);
			break;
		case 6:
			makeT(rotationPos);
			break;
		}

	}

	/**
	 * Gives the coords. of a given figure with a given rotation.
	 * 
	 * @param figureType
	 *            - the type of the figure
	 * @param rotationPos
	 *            - the rotation of the figure
	 */

	public void makeRotation(int figureType, int rotationPos) {
		switch (figureType) {
		case 0:
			makeO();
			break;
		case 1:
			makeI(rotationPos);
			break;
		case 2:
			makeL(rotationPos);
			break;
		case 3:
			makeJ(rotationPos);
			break;
		case 4:
			makeS(rotationPos);
			break;
		case 5:
			makeZ(rotationPos);
			break;
		case 6:
			makeT(rotationPos);
			break;
		}
	}
	/*
	 * Square - has 0 rotations
	 */

	public void makeO() {
		currentFigure[0].setxPos(0);
		currentFigure[0].setyPos(0);
		currentFigure[1].setxPos(1);
		currentFigure[1].setyPos(0);
		currentFigure[2].setxPos(0);
		currentFigure[2].setyPos(1);
		currentFigure[3].setxPos(1);
		currentFigure[3].setyPos(1);
		currentFigure[0].setBlockColor(Color.YELLOW);
		currentFigure[1].setBlockColor(Color.YELLOW);
		currentFigure[2].setBlockColor(Color.YELLOW);
		currentFigure[3].setBlockColor(Color.YELLOW);
	}
	/*
	 * I-Shape - 4 possible rotations.
	 */

	public void makeI(int rotationPos) {
		currentFigure[0].setBlockColor(Color.CYAN);
		currentFigure[1].setBlockColor(Color.CYAN);
		currentFigure[2].setBlockColor(Color.CYAN);
		currentFigure[3].setBlockColor(Color.CYAN);
		switch (rotationPos) {

		case 0:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(0);
			currentFigure[2].setyPos(2);
			currentFigure[3].setxPos(0);
			currentFigure[3].setyPos(3);
			break;
		case 1:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(2);
			currentFigure[2].setyPos(0);
			currentFigure[3].setxPos(3);
			currentFigure[3].setyPos(0);
			break;
		case 2:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(0);
			currentFigure[2].setyPos(2);
			currentFigure[3].setxPos(0);
			currentFigure[3].setyPos(3);
			break;
		case 3:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(2);
			currentFigure[2].setyPos(0);
			currentFigure[3].setxPos(3);
			currentFigure[3].setyPos(0);
			break;
		}

	}

	/*
	 * L-shape - 4 possible rotations.
	 */
	private void makeL(int rotationPos) {
		currentFigure[0].setBlockColor(Color.ORANGE);
		currentFigure[1].setBlockColor(Color.ORANGE);
		currentFigure[2].setBlockColor(Color.ORANGE);
		currentFigure[3].setBlockColor(Color.ORANGE);
		switch (rotationPos) {

		case 0:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(1);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(2);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(0);
			break;
		case 1:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(0);
			currentFigure[2].setyPos(2);
			currentFigure[3].setxPos(1);
			currentFigure[3].setyPos(2);
			break;
		case 2:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(0);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(0);
			break;
		case 3:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(1);
			currentFigure[3].setyPos(2);
			break;
		}
	}

	/*
	 * J-Shape - 4 possible rotations.
	 */
	private void makeJ(int rotationPos) {
		currentFigure[0].setBlockColor(Color.BLUE);
		currentFigure[1].setBlockColor(Color.BLUE);
		currentFigure[2].setBlockColor(Color.BLUE);
		currentFigure[3].setBlockColor(Color.BLUE);
		switch (rotationPos) {

		case 0:
			currentFigure[0].setxPos(1);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(2);
			currentFigure[3].setxPos(0);
			currentFigure[3].setyPos(2);
			break;
		case 1:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(1);
			break;
		case 2:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(0);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(0);
			currentFigure[3].setyPos(2);
			break;
		case 3:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(2);
			currentFigure[2].setyPos(0);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(1);
			break;
		}
	}

	/*
	 * S-Shape - 4 possible rotations.
	 */
	private void makeS(int rotationPos) {
		currentFigure[0].setBlockColor(Color.GREEN);
		currentFigure[1].setBlockColor(Color.GREEN);
		currentFigure[2].setBlockColor(Color.GREEN);
		currentFigure[3].setBlockColor(Color.GREEN);
		switch (rotationPos) {

		case 0:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(1);
			currentFigure[3].setyPos(2);
			break;
		case 1:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(1);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(0);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(0);
			break;
		case 2:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(1);
			currentFigure[3].setyPos(2);
			break;
		case 3:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(1);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(0);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(0);
			break;
		}
	}

	/*
	 * Z-Shape - 4 possible rotations.
	 */
	private void makeZ(int rotationPos) {
		currentFigure[0].setBlockColor(Color.RED);
		currentFigure[1].setBlockColor(Color.RED);
		currentFigure[2].setBlockColor(Color.RED);
		currentFigure[3].setBlockColor(Color.RED);
		switch (rotationPos) {

		case 0:
			currentFigure[0].setxPos(1);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(0);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(0);
			currentFigure[3].setyPos(2);
			break;
		case 1:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(1);
			break;
		case 2:
			currentFigure[0].setxPos(1);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(0);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(0);
			currentFigure[3].setyPos(2);
			break;
		case 3:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(1);
			break;
		}
	}

	/*
	 * Triangle-Shape - 4 possible rotations.
	 */
	private void makeT(int rotationPos) {
		currentFigure[0].setBlockColor(Color.MAGENTA);
		currentFigure[1].setBlockColor(Color.MAGENTA);
		currentFigure[2].setBlockColor(Color.MAGENTA);
		currentFigure[3].setBlockColor(Color.MAGENTA);
		switch (rotationPos) {

		case 0:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(1);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(0);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(1);
			break;
		case 1:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(0);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(0);
			currentFigure[3].setyPos(2);
			break;
		case 2:
			currentFigure[0].setxPos(0);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(0);
			currentFigure[2].setxPos(1);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(2);
			currentFigure[3].setyPos(0);
			break;
		case 3:
			currentFigure[0].setxPos(1);
			currentFigure[0].setyPos(0);
			currentFigure[1].setxPos(1);
			currentFigure[1].setyPos(1);
			currentFigure[2].setxPos(0);
			currentFigure[2].setyPos(1);
			currentFigure[3].setxPos(1);
			currentFigure[3].setyPos(2);
			break;
		}
	}

}
