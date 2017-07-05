package com.nhom01.gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.nhom01.interfaces.GUIExit;

public class GUI extends JFrame implements GUIExit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyContainer myContainer;

	public GUI() {
		initGUI();
		initComponents();
		addComponents();
	}

	private void initGUI() {
		setTitle("Space Defender");
		setLayout(new CardLayout());
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmExit();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	private void initComponents() {
		myContainer = new MyContainer();
		myContainer.setGUIExit(this);
	}

	private void addComponents() {
		add(myContainer);
	}

	private void confirmExit() {
		int choice = JOptionPane.showConfirmDialog(GUI.this,
				"Bạn có muốn thoát không?", "Xác nhận",
				JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
			guiExit();
		}
	}

	@Override
	public void guiExit() {
		this.dispose();
		System.exit(0);
	}
}
