package com.nhom01.entities;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class Explosion extends BaseEntity {

	private static final int SIZE_EXPLOSION = 15;
	private static final int TIME_VISIBLE = 150;
	private boolean active;

	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
		this.active = true;
		this.size = SIZE_EXPLOSION;
		this.icon = new ImageIcon(getClass().getResource(
				"/IMAGES/explosion.png"));
		this.img = icon.getImage();
	}

	@Override
	public void move(int time) {
		if (time % TIME_VISIBLE == 0) {
			this.active = false;
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.img, this.x, this.y, null);
	}

	public void setIsActive() {
		this.active = false;
	}

	public boolean isActive() {
		return this.active;
	}

}
