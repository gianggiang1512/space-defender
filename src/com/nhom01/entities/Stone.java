package com.nhom01.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import com.nhom01.values.CommonValues;

public class Stone extends BaseEntity {

	private static final int RANDOM_RANGE_TYPE_STONE = 3;
	private static final int STONE_SPEED = 1;
	private Random rand;
	private int type;
	private int speed;

	public Stone() {
		rand = new Random();
		initType();
		initLocation();
	}

	private void initType() {
		this.type = rand.nextInt(RANDOM_RANGE_TYPE_STONE) + 1;
		if (this.type == CommonValues.SMALL_STONE_TYPE) {
			this.speed = STONE_SPEED;
			this.size = CommonValues.SIZE_SMALL_STONE;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/Stone_Small.png"));
			this.img = icon.getImage();
		} else if (this.type == CommonValues.NORMAL_STONE_TYPE) {
			this.speed = 2 * STONE_SPEED;
			this.size = CommonValues.SIZE_NORMAL_STONE;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/Stone_Medium.png"));
			this.img = icon.getImage();
		} else {
			this.speed = 2 * STONE_SPEED;
			this.size = CommonValues.SIZE_BIG_STONE;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/Stone_Big.png"));
			this.img = icon.getImage();
		}
	}

	private void initLocation() {
		this.x = rand.nextInt(CommonValues.WIDTH_GUI - 10) + 5;
		this.y = (-1) * (rand.nextInt(100) + 500);
	}

	@Override
	public void move(int time) {
		if (time % speed != 0) {
			return;
		}
		y += speed;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.img, this.x, this.y, null);
	}

	public boolean isCollided(BaseEntity player) {
		Rectangle rectStone;
		if (this.type == CommonValues.NORMAL_STONE_TYPE) {
			rectStone = new Rectangle(this.x + 3, this.y + 4, this.size - 7,
					this.size - 10);
		} else if (this.type == CommonValues.BIG_STONE_TYPE) {
			rectStone = new Rectangle(this.x + 3, this.y + 4, this.size - 7,
					this.size - 10);
		} else {
			rectStone = new Rectangle(this.x + 2, this.y + 2, this.size - 4,
					this.size - 6);
		}
		Rectangle rectEntity = new Rectangle(player.getX() + 6, player.getY(),
				player.getSize() - 14, player.getSize() - 8);
		return rectStone.intersects(rectEntity);
	}
}
