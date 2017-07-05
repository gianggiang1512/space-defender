package com.nhom01.entities;


public abstract class Bullet extends BaseEntity {

	protected int type;
	protected int speed;
	protected int damage;

	public Bullet() {
		
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
