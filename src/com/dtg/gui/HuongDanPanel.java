package com.dtg.gui;

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

import com.dtg.interfaces.ActionPanel;
import com.dtg.values.CommonValues;

public class HuongDanPanel extends BaseContainer {

	/** */
	private static final long serialVersionUID = 1L;
	private static final int SIZE_BUTTON = 48;
	private ActionPanel actionPanel;
	private Image huongDanImage;
	private Image okImage;

	public HuongDanPanel() {
		super();
	}

	@Override
	protected void initContainer() {
		setLayout(null);
		setFocusable(true);
		setRequestFocusEnabled(true);
	}

	@Override
	protected void initComponents() {
		try {
			BufferedImage tempImg = ImageIO.read(getClass().getResource(
					"/IMAGES/huongdanImg.png"));
			this.huongDanImage = tempImg.getScaledInstance(CommonValues.WIDTH_GUI,
					CommonValues.HEIGHT_GUI, Image.SCALE_DEFAULT);
			tempImg = ImageIO.read(getClass().getResource("/IMAGES/okImg.png"));
			this.okImage = tempImg.getScaledInstance(SIZE_BUTTON, SIZE_BUTTON,
					Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				escapePressed(e);
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
		g2d.drawImage(huongDanImage, 0, 0, null);
		g2d.drawImage(okImage, CommonValues.WIDTH_GUI / 2 - SIZE_BUTTON / 2,
				CommonValues.HEIGHT_GUI - SIZE_BUTTON - 30, null);
	}

	private void clickMouse(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (mouseX <= CommonValues.WIDTH_GUI / 2 + SIZE_BUTTON / 2
				&& mouseX >= CommonValues.WIDTH_GUI / 2 - SIZE_BUTTON / 2) {
			if (mouseY >= CommonValues.HEIGHT_GUI - SIZE_BUTTON - 30
					&& mouseY <= CommonValues.HEIGHT_GUI - 30) {
				this.actionPanel.showMenuPanel();
			}
		}
	}

	private void escapePressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.actionPanel.showMenuPanel();
		}
	}

	public void setActionPanel(ActionPanel actionPanel) {
		this.actionPanel = actionPanel;
	}
}
