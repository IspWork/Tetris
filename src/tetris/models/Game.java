/**
 * The TETRIS game.
 * With following functions :
 * saving a game , loading a game and validation for :
 * -if the figure is out of the game board(left , right , top or bottom).
 * -available figure rotation.
 * -if the figure can move down without stepping on other figures.
 * -full rows.
 * 
 */
package tetris.models;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String fileName = "saveTetris.txt";
	private static final int DEFAULT_SPEED = 30;
	private int xPosGamePanel = 0;
	private int yPosGamePanel = 0;
	private Random randomKindOfFigure = new Random();
	private Random randomKindOfRotation = new Random();
	private int kindOfFigure;
	private int kindOfRotation;
	private int kindOfNextFigure = generateTypeOfFigure();
	private int kindOfNextRotation = generateTypeOfRotation();
	private boolean isActionDrop = false;
	private int speed = DEFAULT_SPEED;
	private boolean isGameInAction = false;
	private boolean isGamePaused = false;

	JPanel superPanel = new JPanel();
	GamePanel gamePanel = new GamePanel();
	InfoPanel infoPanel;
	Figure figure;

	public void setIsGameInAction(boolean isGameInAction) {
		this.isGameInAction = isGameInAction;
	}

	public Game() {
		superPanel.add(gamePanel);
		superPanel.setVisible(true);
		superPanel.setLayout(null);
		infoPanel = new InfoPanel(GamePanel.GAME_PANEL_WIDTH * GamePanel.BLOCK_WIDTH,
				GamePanel.GAME_PANEL_HEIGHT * GamePanel.BLOCK_HEIGHT);
		infoPanel.sayStartGame();
		superPanel.add(infoPanel);
		superPanel.setVerifyInputWhenFocusTarget(true);
		figure = new Figure(0, 0);

		superPanel.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent keyEvent) {

				switch (keyEvent.getKeyCode()) {

				case KeyEvent.VK_RIGHT:
					if (isGameInAction) {
						if (!checkOutOfBoundsRight()) {
							xPosGamePanel = xPosGamePanel + (1 * GamePanel.BLOCK_WIDTH);
						}
					}
					break;
				case KeyEvent.VK_LEFT:
					if (isGameInAction) {
						if (!checkOutOfBoundsLeft()) {
							xPosGamePanel = xPosGamePanel - (1 * GamePanel.BLOCK_WIDTH);
						}
					}
					break;
				case KeyEvent.VK_DOWN:
					if (isGameInAction) {
						if (!isActionDrop) {
							isActionDrop = true;
						}
					}
					break;
				case KeyEvent.VK_R:
					if (isGameInAction) {
						checkAvailableRotation(kindOfFigure, kindOfRotation);
					}
					break;
				case KeyEvent.VK_ENTER:

					if (isGameInAction == false) {
						isGameInAction = true;
						startNewLoop();
						infoPanel.removeAll();
						infoPanel.setScore(0);
						infoPanel.setLevel(1);
						speed = DEFAULT_SPEED;
						infoPanel.showScore();
						infoPanel.showLevel();
						infoPanel.help();
					}
					break;
				case KeyEvent.VK_SPACE:
					if (isGamePaused == false) {
						isGameInAction = false;
						isGamePaused = true;
					} else {
						isGameInAction = true;
						isGamePaused = false;
					}
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				}
			}
		});

	}

	/**
	 * Checks if the figure is out of the left side of the game board.
	 *
	 */

	private boolean checkOutOfBoundsLeft() {
		for (int counter = 0; counter < 4; counter++) {
			// Checks to see if the figure is going out of the left side of the
			// game board
			if ((xPosGamePanel + figure.getBlock(counter).getxPos() * GamePanel.BLOCK_WIDTH
					- GamePanel.BLOCK_WIDTH) < 0) {
				return true;
			}
			// checks to see if the figure is going to go into another figure.
			if (gamePanel.getGameArray(calculateXCol() - 1 + figure.getBlock(counter).getxPos(),
					calculateYRow() + figure.getBlock(counter).getyPos()).getBlockState()) {
				return true;

			}
		}
		return false;

	}

	/**
	 * Checks if the figure is out of the right side of the board.
	 * 
	 */

	private boolean checkOutOfBoundsRight() {
		for (int counter = 0; counter < 4; counter++) {
			// Checks to see if the figure is going out of the right side of the
			// game board
			if ((xPosGamePanel + figure.getBlock(counter).getxPos() * GamePanel.BLOCK_WIDTH
					+ GamePanel.BLOCK_WIDTH) > (GamePanel.GAME_PANEL_WIDTH * GamePanel.BLOCK_WIDTH
							- GamePanel.BLOCK_WIDTH)) {
				return true;
			}
			// checks to see if the figure is going to go into another figure.
			if (gamePanel.getGameArray(calculateXCol() + 1 + figure.getBlock(counter).getxPos(),
					calculateYRow() + figure.getBlock(counter).getyPos()).getBlockState()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Checks if the figure is out of the bottom side of the board.
	 * 
	 */

	private boolean checkOutOfBoundsBottom() {
		for (int counter = 0; counter < 4; counter++) {
			if (yPosGamePanel + figure.getBlock(counter).getyPos()
					* GamePanel.BLOCK_HEIGHT > GamePanel.GAME_PANEL_HEIGHT * GamePanel.BLOCK_WIDTH
							- GamePanel.BLOCK_WIDTH - 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the figure is out of the top side of the board.
	 */

	private boolean checkOutOBondsTop() {
		if (calculateYRow() == 0) {
			int oldYPos = yPosGamePanel;

			for (int counter = 0; counter < 4; counter++) {
				if (gamePanel.getGameArray(calculateXCol() + figure.getBlock(counter).getxPos(),
						calculateYRow() + figure.getBlock(counter).getyPos()).getBlockState()) {

					yPosGamePanel = oldYPos;
					return true;

				}
			}

			yPosGamePanel = oldYPos;

			return false;

		} else {
			return false;
		}
	}

	/**
	 * Checks if the figure can make a certain rotation without: -stepping on
	 * other figures. -getting out of the board.
	 */

	private void checkAvailableRotation(int typeFigure, int typeRotation) {
		int oldRotation = typeRotation;
		if (typeRotation + 1 >= 4) {
			typeRotation = 0;
		} else {
			typeRotation++;
		}

		figure.makeRotation(typeFigure, typeRotation);
		// Checking if figure can do available rotation without going out of the
		// board or going into another figure.
		for (int counter = 0; counter < 4; counter++) {
			if ((xPosGamePanel + figure.getBlock(counter).getxPos()
					* GamePanel.BLOCK_WIDTH) > (GamePanel.GAME_PANEL_WIDTH * GamePanel.BLOCK_WIDTH
							- GamePanel.BLOCK_WIDTH)) {
				figure.makeRotation(typeFigure, oldRotation);
				return;
			}
			if ((xPosGamePanel + figure.getBlock(counter).getxPos() * GamePanel.BLOCK_WIDTH) < 0) {
				figure.makeRotation(typeFigure, oldRotation);
				return;
			}
			if (gamePanel.getGameArray(calculateXCol() + figure.getBlock(counter).getxPos(),
					calculateYRow() + figure.getBlock(counter).getyPos()).getBlockState()) {
				figure.makeRotation(typeFigure, oldRotation);
				return;
			}
		}
		gamePanel.repaint();
		kindOfRotation = typeRotation;

	}

	private int calculateYRow() {
		return yPosGamePanel / GamePanel.BLOCK_HEIGHT;
	}

	private int calculateXCol() {
		return xPosGamePanel / GamePanel.BLOCK_WIDTH;
	}

	/**
	 * This method is used when the figure has reached bottom , or has reached
	 * another figure and cannot move down anymore. This method marks the state
	 * of the 'figure blocks' as true(taken) and sets their color.
	 */

	private void markFilledBlocks() {
		for (int counter = 0; counter < 4; counter++) {
			gamePanel
					.getGameArray(calculateXCol() + figure.getBlock(counter).getxPos(),
							calculateYRow() + figure.getBlock(counter).getyPos())
					.setBlockColor(figure.getBlock(counter).getBlockColor());
			gamePanel.getGameArray(calculateXCol() + figure.getBlock(counter).getxPos(),
					calculateYRow() + figure.getBlock(counter).getyPos()).setBlockState(true);
		}
	}

	/**
	 * Checks if the figure will step onto another figure when it moves down.
	 */

	private boolean checkAvailableFigureMove() {
		int oldYPos = yPosGamePanel;
		if (calculateYRow() < GamePanel.GAME_PANEL_HEIGHT - 1) {
			for (int counter = 0; counter < 4; counter++) {
				if (gamePanel.getGameArray(calculateXCol() + figure.getBlock(counter).getxPos(),
						calculateYRow() + 1 + figure.getBlock(counter).getyPos()).getBlockState()) {
					yPosGamePanel = oldYPos;
					return true;
				}
			}
		}

		yPosGamePanel = oldYPos;
		return false;
	}

	/**
	 * Checks to see if any game board rows are full. If so it 'removes' these
	 * rows and updates the score and level accordingly.
	 */

	private void checkForFullRows() {
		boolean isFull;
		int fullRows = 0;
		for (int j = 0; j < GamePanel.GAME_PANEL_HEIGHT; j++) {
			isFull = true;
			for (int i = 0; i < GamePanel.GAME_PANEL_WIDTH; i++) {
				if (gamePanel.getGameArray(i, j).getBlockState() == false) {
					isFull = false;
					break;
				}
			}
			if (isFull) {
				fullRows++;
				for (int r = j; r > 0; r--) {
					for (int l = 0; l < GamePanel.GAME_PANEL_WIDTH; l++) {
						gamePanel.getGameArray(l, r).setBlockState(gamePanel.getGameArray(l, r - 1).getBlockState());
						gamePanel.getGameArray(l, r).setBlockColor(gamePanel.getGameArray(l, r - 1).getBlockColor());
					}
				}

				for (int l = 0; l < GamePanel.GAME_PANEL_WIDTH; l++) {
					gamePanel.getGameArray(l, 0).setBlockState(false);
					gamePanel.getGameArray(l, 0).setBlockColor(GamePanel.DEFAULT_COLOR_GAME_ARRAY);

				}
			}

		}
		/*
		 * score
		 */
		if (fullRows > 0) {
			switch (fullRows) {
			case 1:
				infoPanel.setScore(infoPanel.getScore() + 10);
				break;
			case 2:
				infoPanel.setScore(infoPanel.getScore() + 20);
				break;
			case 3:
				infoPanel.setScore(infoPanel.getScore() + 40);
				break;
			case 4:
				infoPanel.setScore(infoPanel.getScore() + 70);
				break;

			}
			/*
			 * level
			 */
			if (infoPanel.getScore() <= 500) {
				infoPanel.setLevel(1);
				speed = 30;
			} else if (infoPanel.getScore() > 500 && infoPanel.getScore() <= 1000) {
				infoPanel.setLevel(2);
				speed = 15;
			} else if (infoPanel.getScore() > 1000 && infoPanel.getScore() <= 2000) {
				infoPanel.setLevel(3);
				speed = 10;
			} else {
				infoPanel.setLevel(4);
				speed = 5;
			}
			infoPanel.removeAll();
			infoPanel.showScore();
			infoPanel.showLevel();
			infoPanel.help();
		}
	}

	private int generateTypeOfFigure() {
		return randomKindOfFigure.nextInt(7);
	}

	private int generateTypeOfRotation() {
		return randomKindOfRotation.nextInt(4);
	}

	/**
	 * Method that generates random figures and random figure rotations on both
	 * the game board and info panel.
	 */

	private void startNewLoop() {
		kindOfFigure = kindOfNextFigure;
		kindOfRotation = kindOfNextRotation;
		figure.makeRotation(kindOfFigure, kindOfRotation);
		kindOfNextFigure = generateTypeOfFigure();
		kindOfNextRotation = generateTypeOfRotation();
		infoPanel.nextFigure.makeRotation(kindOfNextFigure, kindOfNextRotation);
		infoPanel.repaint();
		xPosGamePanel = (GamePanel.GAME_PANEL_WIDTH * GamePanel.BLOCK_WIDTH) / 2;
		yPosGamePanel = 0;
		isActionDrop = false;
	}

	public void beginGame() {
		startNewLoop();
		while (true) {
			gamePanel.repaint();
			if (isGameInAction) {
				try {
					if (isActionDrop) {
						Thread.sleep(0);
					} else {
						Thread.sleep(speed);
					}
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

				if (checkOutOBondsTop()) {
					setIsGameInAction(false);
					gamePanel.fillArrays();
					gamePanel.initFigureArray();
					infoPanel.removeAll();
					infoPanel.sayStartGame();
					infoPanel.gameOver();
					infoPanel.showScore();
					infoPanel.showLevel();
					gamePanel.repaint();

				} else if (!checkOutOfBoundsBottom()) {
					if (checkAvailableFigureMove()) {

						markFilledBlocks();
						checkForFullRows();
						gamePanel.repaint();
						startNewLoop();
					} else {
						gamePanel.drawColorFigure(xPosGamePanel, yPosGamePanel, figure);
						gamePanel.repaint();
						yPosGamePanel++;
					}

				} else {
					markFilledBlocks();
					checkForFullRows();
					gamePanel.repaint();
					startNewLoop();
				}
			} else {
				try {
					Thread.sleep(50);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	public void saveGame() {
		File fileOutput = new File(fileName);
		try {
			PrintWriter printer = new PrintWriter(fileOutput);
			for (int j = 0; j < GamePanel.GAME_PANEL_HEIGHT; j++) {
				for (int i = 0; i < GamePanel.GAME_PANEL_WIDTH; i++) {
					if (i == GamePanel.GAME_PANEL_WIDTH - 1) {
						printer.print(gamePanel.getGameArray(i, j).getBlockState());

					} else {
						printer.print(gamePanel.getGameArray(i, j).getBlockState() + ",");
					}
				}
				printer.println();
			}
			for (int j = 0; j < GamePanel.GAME_PANEL_HEIGHT; j++) {
				for (int i = 0; i < GamePanel.GAME_PANEL_WIDTH; i++) {
					if (i == GamePanel.GAME_PANEL_WIDTH - 1) {
						printer.print(gamePanel.getGameArray(i, j).getBlockColor().getRGB());

					} else {
						printer.print(gamePanel.getGameArray(i, j).getBlockColor().getRGB() + ",");
					}
				}
				printer.println();

			}
			for (int counter = 0; counter < 4; counter++) {
				if (counter == 3) {
					printer.println(figure.getBlock(counter).getxPos());
				} else {
					printer.print(figure.getBlock(counter).getxPos() + ",");
				}
			}
			for (int counter = 0; counter < 4; counter++) {
				if (counter == 3) {
					printer.println(figure.getBlock(counter).getyPos());
				} else {
					printer.print(figure.getBlock(counter).getyPos() + ",");
				}
			}
			for (int counter = 0; counter < 4; counter++) {
				if (counter == 3) {
					printer.println(figure.getBlock(counter).getBlockState());
				} else {
					printer.print(figure.getBlock(counter).getBlockState() + ",");
				}
			}
			for (int counter = 0; counter < 4; counter++) {
				if (counter == 3) {
					printer.println(figure.getBlock(counter).getBlockColor().getRGB());
				} else {
					printer.print(figure.getBlock(counter).getBlockColor().getRGB() + ",");
				}
			}
			printer.println(infoPanel.getScore());
			printer.println(infoPanel.getLevel());
			printer.println(kindOfFigure);
			printer.println(kindOfRotation);

			printer.flush();
			printer.close();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			System.out.println("Error !!!");
		}

	}

	public void loadGame() {
		setIsGameInAction(true);
		startNewLoop();
		infoPanel.removeAll();
		speed = DEFAULT_SPEED;
		infoPanel.help();
		java.util.Scanner fileInput = null;
		try {
			fileInput = new java.util.Scanner(new File(fileName), "windows-1251");

			String line;
			while (fileInput.hasNextLine()) {
				for (int j = 0; j < GamePanel.GAME_PANEL_HEIGHT; j++) {
					line = fileInput.nextLine().trim();
					if (!line.isEmpty()) {
						String[] parts = line.split(",");
						for (int p = 0; p < parts.length; p++) {
							gamePanel.getGameArray(p, j).setBlockState(Boolean.valueOf(parts[p]));
						}
					}
				}
				for (int j = 0; j < GamePanel.GAME_PANEL_HEIGHT; j++) {
					line = fileInput.nextLine().trim();

					if (!line.isEmpty()) {
						String[] parts = line.split(",");
						for (int p = 0; p < parts.length; p++) {
							Color color = new Color(Integer.valueOf(parts[p]));
							gamePanel.getGameArray(p, j).setBlockColor(color);
						}
					}
				}
				line = fileInput.nextLine().trim();

				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					for (int p = 0; p < parts.length; p++) {
						figure.getBlock(p).setxPos(Integer.valueOf(parts[p]));
					}
				}
				line = fileInput.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					for (int p = 0; p < parts.length; p++) {
						figure.getBlock(p).setyPos(Integer.valueOf(parts[p]));
					}
				}
				line = fileInput.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					for (int p = 0; p < parts.length; p++) {
						figure.getBlock(p).setBlockState(Boolean.valueOf(parts[p]));
					}
				}
				line = fileInput.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					for (int p = 0; p < parts.length; p++) {
						Color color = new Color(Integer.valueOf(parts[p]));
						figure.getBlock(p).setBlockColor(color);
					}
				}
				line = fileInput.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					infoPanel.setScore(Integer.valueOf(parts[0]));
					infoPanel.showScore();

				}
				line = fileInput.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					infoPanel.setLevel(Integer.valueOf(parts[0]));
					infoPanel.showLevel();

				}
				line = fileInput.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					kindOfFigure = Integer.valueOf(parts[0]);

				}
				line = fileInput.nextLine().trim();
				if (!line.isEmpty()) {
					String[] parts = line.split(",");
					kindOfRotation = Integer.valueOf(parts[0]);

				}

			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			System.out.println("File isn't found");

		} finally {
			if (null != fileInput) {
				fileInput.close();
			}
		}
		gamePanel.repaint();
		infoPanel.repaint();

	}

}
