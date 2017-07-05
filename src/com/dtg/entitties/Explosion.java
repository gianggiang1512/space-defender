package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;

public class Explosion extends BaseEntity {

	private boolean active;

	public Explosion(int x, int y, int size, Image img) {
		super(x, y, size, img);
		this.active = true;
	}

	@Override
	public void move(int time) {
		if (time % 150 == 0) {
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
