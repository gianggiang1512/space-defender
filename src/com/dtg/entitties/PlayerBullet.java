package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class PlayerBullet extends Bullet {

	private static final int NORMAL_BULLET = 0;
	private static final int NORMAL_BULLET_DAMAGE = 10;
	private static final int SUPER_BULLET_DAMAGE = 50;

	public PlayerBullet(int x, int y, int size, Image img, int type, int speed) {
		super(x, y, size, img, type, speed);
		if (this.type == NORMAL_BULLET) {
			this.damage = NORMAL_BULLET_DAMAGE;
		} else {
			this.damage = SUPER_BULLET_DAMAGE;
		}
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
		Rectangle rectBullet = new Rectangle(this.x, this.y,
				this.size, this.size + 5);
		Rectangle rectEntity = new Rectangle(entity.getX() + 2,
				entity.getY() + 2, entity.getSize() - 2, entity.getSize() - 2);
		return rectBullet.intersects(rectEntity);
	}
}
