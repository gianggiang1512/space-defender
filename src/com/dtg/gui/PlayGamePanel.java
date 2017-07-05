package com.dtg.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.BitSet;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.dtg.gamecontroller.Manager;
import com.dtg.interfaces.ActionPanel;
import com.dtg.values.CommonValues;

public class PlayGamePanel extends BaseContainer implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActionPanel actionPanel;
	private Image bgPlay;
	private Manager manager;
	private BitSet mKeysValue;
	private int time;
	private boolean isRunning;

	public PlayGamePanel() {
		super();
	}

	@Override
	protected void initContainer() {
		setLayout(null);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				funtionalKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				funtionalKeyReleased(e);
			}
		});
		setFocusable(true);
		setRequestFocusEnabled(true);
	}

	@Override
	protected void initComponents() {
		try {
			BufferedImage tempImg = ImageIO.read(getClass().getResource(
					"/IMAGES/backGroundPlay.jpg"));
			this.bgPlay = tempImg.getScaledInstance(CommonValues.WIDTH_GUI,
					CommonValues.HEIGHT_GUI, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mKeysValue = new BitSet(256);
		manager = new Manager();
		manager.initObjects();
		time = 0;
	}

	@Override
	protected void addComponents() {
		// TODO Auto-generated method stub
	}

	public void play() {
		Thread thread = new Thread(this);
		thread.start();
		isRunning = true;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(this.bgPlay, 0, 0, null);
		manager.drawObjects(g2d);
	}

	@Override
	public void run() {
		while (isRunning) {
			moveOtherObjects(time);
			excuteTaskByKeyPressed();
			try {
				Thread.sleep(5);
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			manager.downTimeOut(10);
			manager.enemyFire();
			this.checkCondition();
			time++;
			if (time > 10000000) {
				time = 0;
			}
		}
	}

	private void checkCondition() {
		if (manager.nextLevel()) {
			JOptionPane.showMessageDialog(PlayGamePanel.this, "Next level : "
					+ manager.getLevel() + "\nLives: " + manager.getLives()
					+ "\nScore: " + manager.getScore(), "Next Level",
					JOptionPane.INFORMATION_MESSAGE);
			mKeysValue.clear();
			manager.initEnemy();
			manager.initPlayer();
			manager.initStone();
		} else if (manager.gameOver()) {
			int choice = JOptionPane.showConfirmDialog(PlayGamePanel.this,
					"Ban da chet!" + "\nDiem: " + manager.getScore()
							+ "\nCo muon choi lai khong?", "Game Over",
					JOptionPane.YES_NO_OPTION);
			mKeysValue.clear();
			if (choice == JOptionPane.YES_OPTION) {
				manager.resetAllObjects();
			} else {
				manager.resetAllObjects();
				actionPanel.showMenuPanel();
			}
		} else if (manager.checkVictory()) {
			JOptionPane.showMessageDialog(
					PlayGamePanel.this,
					"You're a great defender!" + "\nScore: "
							+ manager.getScore(), "Victory",
					JOptionPane.INFORMATION_MESSAGE);
			manager.resetAllObjects();
			actionPanel.showMenuPanel();
		}
	}

	private void moveOtherObjects(int time) {
		manager.movePlayerBullet(time);
		manager.moveSpaceEnemy(time);
		manager.moveEnemyBullet(time);
		manager.moveExplosion(time);
		manager.moveStone(time);
		if (time % 3000 == 0 && manager.getArrStoneSize() == 0) {
			manager.initStone();
		}
		manager.moveSkill(time);
	}

	private void excuteTaskByKeyPressed() {
		if (mKeysValue.get(KeyEvent.VK_UP)) {
			manager.changeOrientSpacePlayer(CommonValues.ORIENT_UP);
			manager.moveSpacePlayer(time);
		}
		if (mKeysValue.get(KeyEvent.VK_DOWN)) {
			manager.changeOrientSpacePlayer(CommonValues.ORIENT_DOWN);
			manager.moveSpacePlayer(time);
		}
		if (mKeysValue.get(KeyEvent.VK_LEFT)) {
			manager.changeOrientSpacePlayer(CommonValues.ORIENT_LEFT);
			manager.moveSpacePlayer(time);
		}
		if (mKeysValue.get(KeyEvent.VK_RIGHT)) {
			manager.changeOrientSpacePlayer(CommonValues.ORIENT_RIGHT);
			manager.moveSpacePlayer(time);
		}
		if (mKeysValue.get(KeyEvent.VK_SPACE)) {
			manager.playerFire();
		}
		if (mKeysValue.get(KeyEvent.VK_ESCAPE)) {
			int choice = JOptionPane.showConfirmDialog(PlayGamePanel.this,
					"Quay tro lai Menu Game?", "Xac nhan",
					JOptionPane.YES_NO_OPTION);
			mKeysValue.clear();
			if (choice == JOptionPane.YES_OPTION) {
				this.time = 0;
				manager.resetAllObjects();
				actionPanel.showMenuPanel();
			}
		}
	}

	private void funtionalKeyPressed(KeyEvent e) {
		mKeysValue.set(e.getKeyCode());
	}

	private void funtionalKeyReleased(KeyEvent e) {
		mKeysValue.clear(e.getKeyCode());
	}

	public void setActionPanel(ActionPanel actionPanel) {
		this.actionPanel = actionPanel;
	}

	public void stopRunning() {
		this.isRunning = false;
	}

}
