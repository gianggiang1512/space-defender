package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;

import com.dtg.values.CommonValues;

public class SpacePlayer extends BaseEntity {

	private static final int TIME_OUT = 500;
	private int orient;
	private int lives;
	private int speedMove;
	private int timeOut;
	private boolean haveSuperBullet;
	private int quantityOfSuperBullet;

	public SpacePlayer(int x, int y, int size, int orient, Image img,
			int lives, int speedMove) {
		super(x, y, size, img);
		this.lives = lives;
		this.speedMove = speedMove;
		this.orient = orient;
		this.haveSuperBullet = false;
		this.quantityOfSuperBullet = 0;
	}

	@Override
	public void move(int time) {
		if (time % speedMove != 0) {
			return;
		}
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
	
	public void setTimeOut(){
		this.timeOut = TIME_OUT;
	}
	
	public boolean isFire(){
		if(timeOut <= 0){
			return true;
		}
		return false;
	}
	
	public void downTimeOut(int value){
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

	public void setSuperBullet(){
		this.haveSuperBullet = true;
		this.quantityOfSuperBullet += 25;
	}

	public boolean isHaveSuperBullet() {
		return haveSuperBullet && this.quantityOfSuperBullet > 0;
	}

	public int getQuantityOfSuperBullet() {
		return quantityOfSuperBullet;
	}
	
	public void downQuantityOfSuperBullet(){
		this.quantityOfSuperBullet--;
	}
}
