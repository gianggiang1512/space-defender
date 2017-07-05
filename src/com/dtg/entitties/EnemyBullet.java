package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class EnemyBullet extends Bullet {

	public EnemyBullet(int x, int y, int size, Image img, int type, int speed) {
		super(x, y, size, img, type, speed);
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

	public boolean isCollided(BaseEntity entity) {
		Rectangle rectBullet = new Rectangle(this.x + 2, this.y + 2,
				this.size - 2, this.size - 2);
		Rectangle rectEntity = new Rectangle(entity.getX() + 2,
				entity.getY() + 2, entity.getSize(), entity.getSize());
		return rectBullet.intersects(rectEntity);
	}

}
