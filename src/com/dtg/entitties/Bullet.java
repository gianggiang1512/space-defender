package com.dtg.entitties;

import java.awt.Image;

public abstract class Bullet extends BaseEntity {

	protected int type;
	protected int speed;
	protected int damage;

	public Bullet(int x, int y, int size, Image img, int type,
			int speed) {
		super(x, y, size, img);
		this.type = type;
		this.speed = speed;
	}

	public int getType() {
		return type;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDamage() {
		return damage;
	}

}
