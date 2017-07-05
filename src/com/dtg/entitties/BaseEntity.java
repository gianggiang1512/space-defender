package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class BaseEntity {

	protected int x, y;
	protected int size;
	protected Image img;
	
	public BaseEntity(int x, int y, int size, Image img) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.img = img;
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
