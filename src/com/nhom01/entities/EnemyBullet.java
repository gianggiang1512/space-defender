package com.nhom01.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.nhom01.values.CommonValues;

public class EnemyBullet extends Bullet {

	private static final int ENEMY_BULLET_SPEED = 2;

	public EnemyBullet(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.size = CommonValues.SIZE_ENEMY_BULLET;
		this.speed = ENEMY_BULLET_SPEED;
		this.type = type;
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

	public void setImage() {
		if (this.type == CommonValues.NORMAL_ENEMY) {
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/EnemyBulletNormal.png"));
		} else {
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/EnemyBulletSuper.png"));

		}
		this.img = icon.getImage();
	}

	public boolean isCollided(BaseEntity player) {
		Rectangle rectBullet = new Rectangle(this.x + 2, this.y + 2,
				this.size - 2, this.size - 2);
		Rectangle rectEntity = new Rectangle(player.getX() + 6,
				player.getY(), player.getSize() - 14, player.getSize() - 8);
		return rectBullet.intersects(rectEntity);
	}

}
