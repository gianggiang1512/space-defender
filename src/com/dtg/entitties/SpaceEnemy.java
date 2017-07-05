package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.dtg.values.CommonValues;

public class SpaceEnemy extends BaseEntity {

	private static final int TIME_OUT = 10000;
	private static final int NORMAL_ENEMY = 0;
	private static final boolean ORIENT_LEFT = true;
	private int health;
	private int type;
	private boolean orient;
	private int speedMove;
	private int timeOut;
	private int moveX;
	private int moveY;
	private Random rand;
	private boolean haveSkill;

	public SpaceEnemy(int x, int y, int size, Image img, int type,
			int speedMove, int haveSkill) {
		super(x, y, size, img);
		this.type = type;
		this.speedMove = speedMove;
		this.rand = new Random();
		this.orient = rand.nextBoolean();
		if (this.type == NORMAL_ENEMY) {
			this.health = 10;
		} else {
			this.health = 20;
			if (this.orient) {
				this.moveX = speedMove;
				this.moveY = speedMove;
			} else {
				this.moveX = -speedMove;
				this.moveY = -speedMove;
			}
		}

		if (haveSkill % 50 == 0) {
			this.haveSkill = true;
		} else {
			this.haveSkill = false;
		}
	}

	@Override
	public void move(int time) {
		if (time % speedMove != 0) {
			return;
		}
		if (this.type == NORMAL_ENEMY) {
			moveNormalEnemy(orient);
		} else {
			moveSuperEnemy();
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.img, this.x, this.y, null);
	}

	private void moveNormalEnemy(boolean orient) {
		if (ORIENT_LEFT) {
//			x -= speedMove;
//			if(moveDown){
//			y-= speedMove;
//			count++;
//			if(count%50==0){
//				moveDown=false;
//			}
//			}else{
//				y+= speedMove;
//				count++;
//				if(count%50==0){
//					moveDown=true;
//				}
//			}
//		}
		} else {
			x += speedMove;
		}
		if (x <= 0 || x >= CommonValues.WIDTH_GUI - this.size - 3) {
			speedMove = (-1) * speedMove;
		}
	}

	private void moveSuperEnemy() {
		if (x <= 0 || x >= CommonValues.WIDTH_GUI - this.size) {
			moveX = -moveX;
		}
		if (y <= 0 || y >= CommonValues.HEIGHT_GUI - this.size) {
			moveY = -moveY;
		}
		x += moveX;
		y += moveY;
	}

	public void setTimeOut() {
		if (this.type == NORMAL_ENEMY) {
			this.timeOut = rand.nextInt(TIME_OUT) + 5000;
		} else {
			this.timeOut = rand.nextInt(2 * TIME_OUT) + 5000;
		}
	}

	public boolean isFire() {
		if (timeOut <= 0) {
			return true;
		}
		return false;
	}

	public void downTimeOut(int value) {
		this.timeOut -= value;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int damage) {
		this.health -= damage;
	}

	public int getType() {
		return type;
	}

	public boolean isHaveSkill() {
		return haveSkill;
	}

	public boolean isCollided(BaseEntity entity) {
		Rectangle rectEnemy = new Rectangle(this.x + 2, this.y + 2,
				this.size - 2, this.size - 2);
		Rectangle rectEntity = new Rectangle(entity.getX() + 2,
				entity.getY() + 2, entity.getSize(), entity.getSize());
		return rectEnemy.intersects(rectEntity);
	}
}
