package com.dtg.entitties;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

public class Skill extends BaseEntity {
	
	private int speed;
	private int typeSkill;

	public Skill(int x, int y, int size, int typeSkill, Image img, int speed) {
		super(x, y, size, img);
		this.speed = speed;
		this.typeSkill = typeSkill;
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
	
	public int getTypeSkill(){
		return this.typeSkill;
	}

	public boolean isCollied(BaseEntity entity){
		Rectangle rectSkill = new Rectangle(this.x, this.y, this.size, this.size);
		Rectangle rectEntity = new Rectangle(entity.getX() + 2, entity.getY() + 2,
				entity.getSize(), entity.getSize());
		return rectSkill.intersects(rectEntity);
	}
}
