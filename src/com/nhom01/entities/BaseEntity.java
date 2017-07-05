package com.nhom01.entities;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class BaseEntity {

	protected int x, y;
	protected int size; // Kich thuoc anh goc
	protected Image img;
	protected ImageIcon icon;
	
	public BaseEntity() {
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public Image getImg() {
		return img;
	}

	public abstract void move(int time);
	public abstract void draw(Graphics2D g2d);
}
