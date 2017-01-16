/**
 *  The whole TETRIS window with the game board, information panel and menus.
 */
package tetris.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Tetris extends JFrame {

	private static final long serialVersionUID = 1L;

	public Game game = new Game();

	public Tetris() {
		setTitle("TETRIS");
		createMenuBar();
		add(game.superPanel);
		game.superPanel.setFocusable(true);
		setSize(((GamePanel.GAME_PANEL_WIDTH * GamePanel.BLOCK_WIDTH) + (InfoPanel.INFO_PANEL_WIDTH)) + 8,
				(GamePanel.GAME_PANEL_HEIGHT * GamePanel.BLOCK_HEIGHT) + 60);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_F);
		JMenuItem menuItemLoad = new JMenuItem("Load Game");

		menu.add(menuItemLoad);
		menuItemLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				game.loadGame();

			}
		});
		menu.add(menuItemLoad);
		JMenuItem menuItemSave = new JMenuItem("Save Game");
		menu.add(menuItemSave);
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				game.saveGame();
			}
		});
		menu.add(menuItemSave);

		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		menu.add(menuItemExit);
		menuBar.add(menu);
		setJMenuBar(menuBar);

	}
}
