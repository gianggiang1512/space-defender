package com.nhom01.entities;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import com.nhom01.values.CommonValues;

public class SpacePlayer extends BaseEntity {

	private static final int PLAYER_SPEED = 1;
	private static final int TIME_OUT = 600;
	private static final int QUANTITY_SUPER_BULLET_PLUS = 30;
	private int orient;
	private int lives;
	private int speedMove;
	private int timeOut;
	private boolean canFire;
	private boolean haveSuperBullet;
	private int quantityOfSuperBullet;

	public SpacePlayer() {
		this.x = CommonValues.WIDTH_GUI / 2 - 15;
		this.y = CommonValues.HEIGHT_GUI - 50;
		this.icon = new ImageIcon(getClass().getResource(
				"/IMAGES/spacePlayer.png"));
		this.img = icon.getImage();
		this.size = CommonValues.SIZE_PLAYER;
		this.lives = CommonValues.PLAYER_LIVES;
		this.speedMove = PLAYER_SPEED;
		this.orient = CommonValues.ORIENT_UP;
		this.haveSuperBullet = false;
		this.quantityOfSuperBullet = 0;
		this.canFire = true;
	}

	@Override
	public void move(int time) {
		switch (orient) {
		case CommonValues.ORIENT_UP:
			y -= speedMove;
			if (y <= 5) {
				y = 5;
			}
			break;
		case CommonValues.ORIENT_DOWN:
			y += speedMove;
			if (y >= CommonValues.HEIGHT_GUI - CommonValues.SIZE_PLAYER - 2) {
				y = CommonValues.HEIGHT_GUI - CommonValues.SIZE_PLAYER - 2;
			}
			break;
		case CommonValues.ORIENT_LEFT:
			x -= speedMove;
			if (x <= 3) {
				x = 3;
			}
			break;
		case CommonValues.ORIENT_RIGHT:
			x += speedMove;
			if (x >= CommonValues.WIDTH_GUI - CommonValues.SIZE_PLAYER - 8) {
				x = CommonValues.WIDTH_GUI - CommonValues.SIZE_PLAYER - 8;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.img, this.x, this.y, null);
	}

	public void setTimeOut() {
		this.timeOut = TIME_OUT;
	}

	public boolean isFire() {
		if (timeOut <= 0 && canFire) {
			return true;
		}
		return false;
	}

	public void downTimeOut(int value) {
		this.timeOut -= value;
	}

	public int getLives() {
		return lives;
	}

	public void changeOrient(int orient) {
		this.orient = orient;
	}

	public void setLocation(int xPlayer, int yPlayer) {
		this.x = xPlayer;
		this.y = yPlayer;
	}

	public void setSuperBullet() {
		this.haveSuperBullet = true;
		this.quantityOfSuperBullet += QUANTITY_SUPER_BULLET_PLUS;
	}

	public boolean isHaveSuperBullet() {
		return haveSuperBullet && this.quantityOfSuperBullet > 0;
	}

	public int getQuantityOfSuperBullet() {
		return quantityOfSuperBullet;
	}

	public void downQuantityOfSuperBullet() {
		this.quantityOfSuperBullet--;
	}
	
	public void setCanFire(boolean fire){
		this.canFire = fire;
	}
}
