package com.nhom01.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import com.nhom01.values.CommonValues;

public class Skill extends BaseEntity {

	private static final int SKILL_SPEED = 3;
	private static final int RANDOM_SKILL = 3;
	private static final int SIZE_SKILL_BOOM = 20;
	private static final int SIZE_SKILL_HEART = 32;
	private static final int SIZE_SKILL_BULLET = 32;
	private Random rand;
	private int speed;
	private int typeSkill;

	public Skill(int x, int y) {
		this.x = x;
		this.y = y;
		this.speed = SKILL_SPEED;
		rand = new Random();
		this.typeSkill = rand.nextInt(RANDOM_SKILL);
		if (this.typeSkill == CommonValues.SKILL_BOOM) {
			this.size = SIZE_SKILL_BOOM;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/SkillBoom.png"));
			this.img = icon.getImage();
		} else if (this.typeSkill == CommonValues.SKILL_HEART) {
			this.size = SIZE_SKILL_HEART;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/SkillHeart.png"));
			this.img = icon.getImage();
		} else {
			this.size = SIZE_SKILL_BULLET;
			this.icon = new ImageIcon(getClass().getResource(
					"/IMAGES/SkillBullet.png"));
			this.img = icon.getImage();
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

	public int getTypeSkill() {
		return this.typeSkill;
	}

	public boolean isCollied(BaseEntity player) {
		Rectangle rectSkill;
		if (this.typeSkill == CommonValues.SKILL_BOOM) {
			rectSkill = new Rectangle(this.x, this.y, this.size, this.size + 10);
		} else if (this.typeSkill == CommonValues.SKILL_HEART) {
			rectSkill = new Rectangle(this.x, this.y + 6, this.size,
					this.size - 12);
		} else {
			rectSkill = new Rectangle(this.x, this.y, this.size, this.size);
		}
		Rectangle rectEntity = new Rectangle(player.getX() + 6,
				player.getY(), player.getSize() - 14, player.getSize() - 8);
		return rectSkill.intersects(rectEntity);
	}
}
