package com.nhom01.gui;

import java.awt.CardLayout;
import java.awt.Color;

import com.nhom01.interfaces.ActionPanel;
import com.nhom01.interfaces.GUIExit;
import com.nhom01.values.CommonValues;

public class MyContainer extends BaseContainer implements ActionPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GUIExit guiExit;
	private CardLayout mCard;
	private MenuGamePanel menuPanel;
	private PlayGamePanel playPanel;
	private HuongDanPanel huongDanPanel;

	public MyContainer() {
		super();
	}

	@Override
	protected void initContainer() {
		setBackground(Color.WHITE);
		mCard = new CardLayout();
		setLayout(mCard);
	}

	@Override
	protected void initComponents() {
		menuPanel = new MenuGamePanel();
		playPanel = new PlayGamePanel();
		huongDanPanel = new HuongDanPanel();

		menuPanel.setActionPanel(this);
		playPanel.setActionPanel(this);
		huongDanPanel.setActionPanel(this);
	}

	@Override
	protected void addComponents() {
		add(menuPanel, CommonValues.MENU_ID);
		add(playPanel, CommonValues.PLAY_ID);
		add(huongDanPanel, CommonValues.HUONG_DAN_ID);
	}

	@Override
	public void showPlayPanel() {
		playPanel.play();
		mCard.show(MyContainer.this, CommonValues.PLAY_ID);
		playPanel.requestFocus();
	}

	@Override
	public void showMenuPanel() {
		playPanel.stopRunning();
		mCard.show(MyContainer.this, CommonValues.MENU_ID);
		menuPanel.requestFocus();
	}

	@Override
	public void showHuongDanPanel() {
		mCard.show(MyContainer.this, CommonValues.HUONG_DAN_ID);
		huongDanPanel.requestFocus();
	}

	@Override
	public void exitGUI() {
		this.guiExit.guiExit();
	}

	public void setGUIExit(GUIExit guiExit) {
		this.guiExit = guiExit;
	}

}
