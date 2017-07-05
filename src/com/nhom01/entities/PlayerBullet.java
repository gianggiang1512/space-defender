package com.nhom01.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.nhom01.values.CommonValues;

public class PlayerBullet extends Bullet {

	private static final int PLAYER_BULLET_SPEED = 3;
	private static final int NORMAL_BULLET_DAMAGE = 10;
	private static final int SUPER_BULLET_DAMAGE = 50;

	public PlayerBullet(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.speed = PLAYER_BULLET_SPEED;
		this.size = CommonValues.SIZE_PLAYER_BULLET;
		this.type = type;
		if (this.type == CommonValues.NORMAL_BULLET) {
			this.icon = new ImageIcon(getClass()
					.getResource("/IMAGES/dan1.png"));
			this.damage = NORMAL_BULLET_DAMAGE;
		} else {
			this.icon = new ImageIcon(getClass()
					.getResource("/IMAGES/dan2.png"));
			this.damage = SUPER_BULLET_DAMAGE;
		}
		this.img = icon.getImage();
	}

	@Override
	public void move(int time) {
		if (time % speed == 0) {
			y -= speed;
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.img, this.x, this.y, null);
	}

	public boolean isCollided(BaseEntity entity) {
		Rectangle rectBullet = new Rectangle(this.x, this.y, this.size,
				this.size + 5);
		Rectangle rectEntity;
		rectEntity = new Rectangle(entity.getX() + 2,
				entity.getY() + 2, entity.getSize() - 2, entity.getSize() - 2);
		return rectBullet.intersects(rectEntity);
	}
}
