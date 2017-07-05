package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Stone extends BaseEntity {

	// private static final int BIG_TYPE_SPEED = 1;
	// private static final int NORMAL_TYPE_SPEED = 2;
	private static final int SMALL_TYPE_SPEED = 3;
	private static final int STONE_SPEED = 1;
	private int type;
	private int speed;

	public Stone(int x, int y, int size, Image img, int type) {
		super(x, y, size, img);
		this.type = type;
		if (this.type == SMALL_TYPE_SPEED) {
			this.speed = STONE_SPEED;
		} else {
			this.speed = 2 * STONE_SPEED;
		}
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
		Rectangle rectStone = new Rectangle(this.x + 2, this.y + 2,
				this.size - 2, this.size - 2);
		Rectangle rectEntity = new Rectangle(entity.getX() + 2, entity.getY() + 2,
				entity.getSize(), entity.getSize());
		return rectStone.intersects(rectEntity);
	}
}
