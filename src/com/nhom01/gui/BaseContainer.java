package com.nhom01.gui;

import javax.swing.JPanel;

public abstract class BaseContainer extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseContainer(){
		initContainer();
		initComponents();
		addComponents();
	}

	protected abstract void initContainer();

	protected abstract void initComponents();

	protected abstract void addComponents();
}
