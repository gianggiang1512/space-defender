package com.nhom01.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import com.nhom01.values.CommonValues;

public class SpaceEnemy extends BaseEntity {

	private static final int MAX_ENEMY_SPEED = 3;
	private static final int RANDOM_RANGE_TYPE_ENEMY = 50;
	private static final int RANDOM_RANGE_HAVE_SKILL = 1000;
	private static final int RANDOM_RANGE_TIME_OUT = 10000;
	private static final int MIN_TIMEOUT = 5000;
	private static final int COUNT_TO_CHANGE_MOVE = 40;
	private static final boolean ORIENT_LEFT = true;

	private int health;
	private int type;
	private boolean orient;
	private int speedMove;
	private int timeOut;
	private int moveX;
	private int moveY;
	private boolean moveDown;
	private int count;// dem so lan di chuyen cua UFO
	private Random rand;
	private boolean haveSkill;
	private int randSkill;

	public SpaceEnemy(int level) {
		this.rand = new Random();
		this.speedMove = rand.nextInt(MAX_ENEMY_SPEED) + 1;
		initType(level);
		initLocation();
		initOrient();
		initHaveSkill();
	}

	private void initType(int level) {
		this.type = rand.nextInt(RANDOM_RANGE_TYPE_ENEMY) + 1;
		if (this.type % 7 == 0 && level > 2) {
			this.type = CommonValues.UFO_ENEMY;
			this.icon = new ImageIcon(getClass().getResource("/IMAGES/ufo.png"));
			this.img = icon.getImage();
			this.size = CommonValues.SIZE_UFO_ENEMY;
			this.health = 40;
			moveDown = rand.nextBoolean();
			count = 0;
		} else if (this.type % 5 == 0 && level > 1) {
			this.type = CommonValues.SUPER_ENEMY;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/EnemyPlaneSuper.png"));
			this.img = icon.getImage();
			this.size = CommonValues.SIZE_SUPER_ENEMY;
			this.health = 20;
		} else {
			this.type = CommonValues.NORMAL_ENEMY;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/EnemyPlaneNormal.png"));
			this.img = icon.getImage();
			this.size = CommonValues.SIZE_NORMAL_ENEMY;
			this.health = 10;
		}
	}

	private void initLocation() {
		this.x = rand.nextInt(CommonValues.WIDTH_GUI - this.size
				- MAX_ENEMY_SPEED - 1) + 1;
		this.y = rand.nextInt(CommonValues.HEIGHT_GUI / 2) + 50;
	}

	private void initOrient() {
		this.orient = rand.nextBoolean();
		if (this.orient == ORIENT_LEFT) {
			this.moveX = -speedMove;
			this.moveY = -speedMove;
		} else {
			this.moveX = speedMove;
			this.moveY = speedMove;
		}
	}

	private void initHaveSkill() {
		randSkill = rand.nextInt(RANDOM_RANGE_HAVE_SKILL);
		if (randSkill % 50 == 0) {
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
		if (this.type == CommonValues.NORMAL_ENEMY) {
			moveNormalEnemy();
		} else if (this.type == CommonValues.SUPER_ENEMY) {
			moveSuperEnemy();
		} else {
			moveUfoEnemy();
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.img, this.x, this.y, null);
	}

	private void moveNormalEnemy() {
		x += moveX;
		if (x <= 0 || x >= CommonValues.WIDTH_GUI - this.size - MAX_ENEMY_SPEED) {
			moveX = (-1) * moveX;
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

	private void moveUfoEnemy() {
		x += moveX;
		if (moveDown) {
			y += moveY;
			count++;
			if (count % COUNT_TO_CHANGE_MOVE == 0) {
				moveDown = false;
				moveY = -moveY;
			}
		} else {
			y += moveY;
			count++;
			if (count % COUNT_TO_CHANGE_MOVE == 0) {
				moveDown = true;
				moveY = -moveY;
			}
		}
		if (x <= 0 || x >= CommonValues.WIDTH_GUI - this.size - MAX_ENEMY_SPEED) {
			moveX = (-1) * moveX;
		}
	}

	public void setTimeOut() {
		if (this.type == CommonValues.NORMAL_ENEMY) {
			this.timeOut = rand.nextInt(RANDOM_RANGE_TIME_OUT) + MIN_TIMEOUT;
		} else {
			this.timeOut = rand.nextInt(2 * RANDOM_RANGE_TIME_OUT)
					+ MIN_TIMEOUT;
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

	public boolean isCollided(BaseEntity player) {
		Rectangle rectEnemy;
		if (this.type == CommonValues.UFO_ENEMY) {
			rectEnemy = new Rectangle(this.x, this.y + 4, this.size,
					this.size - 4);
		} else if (this.type == CommonValues.SUPER_ENEMY) {
			rectEnemy = new Rectangle(this.x, this.y, this.size, this.size - 10);
		} else {
			rectEnemy = new Rectangle(this.x + 2, this.y, this.size - 1,
					this.size - 2);
		}
		Rectangle rectEntity = new Rectangle(player.getX() + 6, player.getY(),
				player.getSize() - 14, player.getSize() - 8);
		return rectEnemy.intersects(rectEntity);
	}
}
