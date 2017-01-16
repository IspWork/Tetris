/**
 * The Block that constructs the whole board and the figures.
 * Has its own x,y position , color and block state(if it is filled or not).
 * 
 */
package tetris.models;

import java.awt.Color;

public final class Block {

	private int xPos = 0;

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	private int yPos = 0;

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	private Color blockColor;

	public Color getBlockColor() {
		return blockColor;
	}

	public void setBlockColor(Color blockColor) {
		this.blockColor = blockColor;
	}

	private boolean blockState = false;

	public boolean getBlockState() {
		return this.blockState;
	}

	public void setBlockState(boolean blockState) {
		this.blockState = blockState;
	}

}
