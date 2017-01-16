/**
 * The Game Board(panel) in which the game is played.
 */

package tetris.models;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int GAME_PANEL_WIDTH = 10;
	public static final int GAME_PANEL_HEIGHT = 16;
	public static final int BLOCK_WIDTH = 30;
	public static final int BLOCK_HEIGHT = 30;
	public static final Color DEFAULT_COLOR_GAME_ARRAY = Color.BLACK;

	private final Block[][] gameArray = new Block[GAME_PANEL_WIDTH][GAME_PANEL_HEIGHT];
	private final Block[] currentFigure = new Block[4];

	public Block getGameArray(int x, int y) {
		return gameArray[x][y];
	}

	public void setGameArray(int x, int y, Block block) {
		this.gameArray[x][y] = block;
	}

	public GamePanel() {
		this.setLayout(null);
		this.setBounds(0, 0, GAME_PANEL_WIDTH * BLOCK_WIDTH, GAME_PANEL_HEIGHT * BLOCK_HEIGHT);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

		for (int i = 0; i < GAME_PANEL_WIDTH; i++) {
			for (int j = 0; j < GAME_PANEL_HEIGHT; j++) {
				gameArray[i][j] = new Block();
			}
		}

		fillArrays();
		initFigureArray();
		this.setBorder(border);

	}

	public void initFigureArray() {
		for (int i = 0; i < 4; i++) {
			currentFigure[i] = new Block();
			currentFigure[i].setBlockColor(DEFAULT_COLOR_GAME_ARRAY);
			currentFigure[i].setxPos(0);
			currentFigure[i].setyPos(0);
		}
	}

	/**
	 * Gives position and color to the blocks of the current figure array.
	 */
	public void drawColorFigure(int x, int y, Figure figure) {
		for (int i = 0; i < 4; i++) {
			currentFigure[i].setBlockColor(figure.getBlock(i).getBlockColor());
			currentFigure[i].setxPos(x + figure.getBlock(i).getxPos() * BLOCK_WIDTH);
			currentFigure[i].setyPos(y + figure.getBlock(i).getyPos() * BLOCK_HEIGHT);
		}
	}

	/**
	 * Clears the game board. Sets the color of all blocks to black and resets
	 * their state to false.
	 */
	public void fillArrays() {
		for (int i = 0; i < GAME_PANEL_WIDTH; i++) {
			for (int j = 0; j < GAME_PANEL_HEIGHT; j++) {
				gameArray[i][j].setBlockColor(GamePanel.DEFAULT_COLOR_GAME_ARRAY);
				gameArray[i][j].setBlockState(false);
				gameArray[i][j].setxPos(BLOCK_WIDTH * i);
				gameArray[i][j].setyPos(BLOCK_HEIGHT * j);
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < GAME_PANEL_WIDTH; i++) {
			for (int j = 0; j < GAME_PANEL_HEIGHT; j++) {
				g.setColor(gameArray[i][j].getBlockColor());
				g.fillRect(gameArray[i][j].getxPos(), gameArray[i][j].getyPos(), BLOCK_WIDTH, BLOCK_HEIGHT);
			}
		}
		for (int i = 0; i < 4; i++) {
			g.setColor(currentFigure[i].getBlockColor());
			g.fillRect(currentFigure[i].getxPos(), currentFigure[i].getyPos(), BLOCK_WIDTH, BLOCK_HEIGHT);
		}
	}
}
