/**
 * The Information Panel on the right side of the game board showing:
 * -next figure.
 * -user.
 * -score.
 * -level.
 * -controls
 */
package tetris.models;

import static tetris.models.GamePanel.BLOCK_HEIGHT;
import static tetris.models.GamePanel.BLOCK_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int INFO_PANEL_WIDTH = 250;
	private int score = 0;
	private int level = 1;
	Figure nextFigure = new Figure(0, 0);
	int figureWidth = 0;
	int figureHeigth = 0;

	public InfoPanel(int widthGamePanel, int heightGamePanel) {
		this.setLayout(null);
		this.setBounds(widthGamePanel, 0, INFO_PANEL_WIDTH, heightGamePanel);
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.setBorder(border);
		this.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		initFigureArray();
	}

	public void sayStartGame() {
		JLabel startG = new JLabel("To Start The Game");
		JLabel startG2 = new JLabel("Press Enter.");
		startG.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		startG.setBounds(50, 350, 200, 30);
		startG2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		startG2.setBounds(80, 380, 200, 30);
		this.add(startG);
		this.add(startG2);
		this.repaint();
	}

	/**
	 * Takes the values of the controls and shows them on the InfoPanel.
	 */
	public void help() {
		JLabel sayNextFigureLabel = new JLabel("Next Piece :");
		JLabel moveRight = new JLabel("Right arrow - Move right");
		JLabel moveLeft = new JLabel("Left arrow - Move left");
		JLabel moveDown = new JLabel("Down arrow - Move down");
		JLabel pause = new JLabel("Space - Pause/Unpause");
		JLabel rotate = new JLabel("R - Figure rotation");
		JLabel escape = new JLabel("Escape - Close application");
		sayNextFigureLabel.setBounds(40, 200, 200, 30);
		moveRight.setBounds(50, 330, 200, 30);
		moveLeft.setBounds(50, 350, 200, 30);
		moveDown.setBounds(50, 370, 200, 30);
		rotate.setBounds(50, 390, 200, 30);
		pause.setBounds(50, 410, 200, 30);
		escape.setBounds(50, 430, 200, 30);
		this.add(sayNextFigureLabel);
		this.add(moveRight);
		this.add(moveLeft);
		this.add(moveDown);
		this.add(pause);
		this.add(rotate);
		this.add(escape);
		this.repaint();
	}

	public void showScore() {
		JLabel scoreLabel = new JLabel("Score : " + score);
		scoreLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		scoreLabel.setBounds(50, 270, 200, 30);
		this.add(scoreLabel);
		this.repaint();
	}

	public void showLevel() {
		JLabel levelLabel = new JLabel("Level : " + level);
		levelLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		levelLabel.setBounds(50, 300, 200, 30);
		this.add(levelLabel);
		this.repaint();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void gameOver() {
		JLabel gameOverLabel = new JLabel("Game Over!!!");
		gameOverLabel.setForeground(Color.RED);
		gameOverLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
		gameOverLabel.setBounds(55, 90, 200, 30);
		initFigureArray();
		this.add(gameOverLabel);
		this.repaint();
	}

	public void initFigureArray() {
		for (int i = 0; i < 4; i++) {
			nextFigure.getBlock(i).setBlockColor(GamePanel.DEFAULT_COLOR_GAME_ARRAY);
			nextFigure.getBlock(i).getxPos();
			nextFigure.getBlock(i).getyPos();
		}
	}

	public void findCentreOfFigure() {
		figureWidth = 0;
		figureHeigth = 0;
		int xMax = 0;
		int yMax = 0;
		for (int counter = 0; counter < 4; counter++) {
			if (nextFigure.getBlock(counter).getxPos() > xMax) {
				xMax = nextFigure.getBlock(counter).getxPos();
			}
			if (nextFigure.getBlock(counter).getyPos() > yMax) {
				yMax = nextFigure.getBlock(counter).getyPos();
			}
		}

		figureWidth = (xMax + 1) * GamePanel.BLOCK_WIDTH;
		figureHeigth = (yMax + 1) * GamePanel.BLOCK_HEIGHT;
		figureWidth = figureWidth / 2;
		figureHeigth = figureHeigth / 2;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		g.setColor(Color.BLACK);
		g.fillRect(40, 20, 180, 180);
		findCentreOfFigure();
		for (int i = 0; i < 4; i++) {
			g.setColor(nextFigure.getBlock(i).getBlockColor());
			g.fillRect(nextFigure.getBlock(i).getxPos() * BLOCK_WIDTH + (130 - figureWidth),
					nextFigure.getBlock(i).getyPos() * BLOCK_HEIGHT + (110 - figureHeigth), BLOCK_WIDTH, BLOCK_HEIGHT);
		}
	}

}
