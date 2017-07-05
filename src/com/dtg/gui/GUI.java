package com.dtg.gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.dtg.interfaces.GUIExit;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

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
		setTitle("Space Game");
		setLayout(new CardLayout());
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		try {
			UIManager.setLookAndFeel(WindowsLookAndFeel.class.getName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
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
				"Ban co muon thoat khong?", "Xac nhan",
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
