package com.dtg.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.dtg.interfaces.ActionPanel;
import com.dtg.values.CommonValues;

public class MenuGamePanel extends BaseContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActionPanel actionPanel;
	private Image bgImage;
	private Image exitButtonImage;
	private Image huongDanButtonImage;
	private Image playButtonImage;

	public MenuGamePanel() {
		super();
	}

	@Override
	protected void initContainer() {
		setLayout(null);
		setBackground(Color.WHITE);
		setFocusable(true);
		setRequestFocusEnabled(true);
	}

	@Override
	protected void initComponents() {
		try {
			BufferedImage tempImg = ImageIO.read(getClass().getResource(
					"/IMAGES/menuBgImage.jpg"));
			this.bgImage = tempImg.getScaledInstance(CommonValues.WIDTH_GUI,
					CommonValues.HEIGHT_GUI, Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/IMAGES/PLAY.png"));
		this.playButtonImage = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/HUONGDAN.png"));
		this.huongDanButtonImage = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/EXIT.png"));
		this.exitButtonImage = icon.getImage();
	}

	@Override
	protected void addComponents() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickMouse(e);
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				escPressed(e);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.drawImage(bgImage, 0, 0, null);
		g2d.setColor(Color.WHITE.brighter());

		g2d.drawImage(playButtonImage, CommonValues.WIDTH_GUI / 2
				- CommonValues.WIDTH_BUTTON / 2, CommonValues.HEIGHT_GUI / 2,
				null);
		g2d.drawImage(huongDanButtonImage, CommonValues.WIDTH_GUI / 2
				- CommonValues.WIDTH_BUTTON / 2, CommonValues.HEIGHT_GUI / 2
				+ CommonValues.HEIGHT_BUTTON + 20, null);
		g2d.drawImage(exitButtonImage, CommonValues.WIDTH_GUI / 2
				- CommonValues.WIDTH_BUTTON / 2, CommonValues.HEIGHT_GUI / 2
				+ 2 * (CommonValues.HEIGHT_BUTTON + 20), null);
	}

	private void clickMouse(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (mouseX <= CommonValues.WIDTH_GUI / 2 + CommonValues.WIDTH_BUTTON
				/ 2
				&& mouseX >= CommonValues.WIDTH_GUI / 2
						- CommonValues.WIDTH_BUTTON / 2) {

			// Play
			if (mouseY >= CommonValues.HEIGHT_GUI / 2
					&& mouseY <= CommonValues.HEIGHT_GUI / 2
							+ CommonValues.HEIGHT_BUTTON) {
				this.actionPanel.showPlayPanel();
			}

			// Huong dan
			if (mouseY >= CommonValues.HEIGHT_GUI / 2
					+ CommonValues.HEIGHT_BUTTON + 20
					&& mouseY <= CommonValues.HEIGHT_GUI / 2 + 2
							* CommonValues.HEIGHT_BUTTON + 20) {
				this.actionPanel.showHuongDanPanel();
			}

			// Exit
			if (mouseY >= CommonValues.HEIGHT_GUI / 2 + 2
					* (CommonValues.HEIGHT_BUTTON + 20)
					&& mouseY <= CommonValues.HEIGHT_GUI / 2 + 2
							* (CommonValues.HEIGHT_BUTTON + 20)
							+ CommonValues.HEIGHT_BUTTON) {
				int result = JOptionPane.showConfirmDialog(MenuGamePanel.this,
						"Ban co muon thoat khong?", "Xac nhan",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					this.actionPanel.exitGUI();
				}
			}
		}
	}

	private void escPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			int result = JOptionPane.showConfirmDialog(MenuGamePanel.this,
					"Ban co muon thoat khong?", "Xac nhan",
					JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				this.actionPanel.exitGUI();
			}
		}
	}

	public void setActionPanel(ActionPanel actionPanel) {
		this.actionPanel = actionPanel;
	}
}
