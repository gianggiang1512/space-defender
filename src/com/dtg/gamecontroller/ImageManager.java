package com.dtg.gamecontroller;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageManager {
	
	private Image playerImg;
	private Image enemyNormalImg;
	private Image enemySuperImg;
	private Image enemyBulletSuperImg;
	private Image enemyBulletNormalImg;
	private Image heartImg;
	private Image skillHeartImg;
	private Image skillBoomImg;
	private Image skillBulletImg;
	private Image bulletNormalImg;
	private Image bulletSuperImg;
	private Image stoneBigImg;
	private Image stoneMediumImg;
	private Image stoneSmallImg;
	private Image explosion;
	
	public ImageManager(){
		ImageIcon icon = new ImageIcon(getClass().getResource("/IMAGES/spacePlayer.png"));
		this.playerImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/EnemyPlaneNormal.png"));
		this.enemyNormalImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/EnemyPlaneSuper.png"));
		this.enemySuperImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/EnemyBulletNormal.png"));
		this.enemyBulletNormalImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/EnemyBulletSuper.png"));
		this.enemyBulletSuperImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/Heart.png"));
		this.heartImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/SkillHeart.png"));
		this.skillHeartImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/SkillBoom.png"));
		this.skillBoomImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/SkillBullet.png"));
		this.skillBulletImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/dan1.png"));
		this.bulletNormalImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/dan2.png"));
		this.bulletSuperImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/Stone_Big.png"));
		this.stoneBigImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/Stone_Medium.png"));
		this.stoneMediumImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/Stone_Small.png"));
		this.stoneSmallImg = icon.getImage();
		icon = new ImageIcon(getClass().getResource("/IMAGES/explosion.png"));
		this.explosion = icon.getImage();
	}
	
	

	public Image getExplosion() {
		return explosion;
	}



	public Image getPlayerImg() {
		return playerImg;
	}

	public Image getEnemyNormalImg() {
		return enemyNormalImg;
	}

	public Image getEnemySuperImg() {
		return enemySuperImg;
	}

	public Image getEnemyBulletSuperImg() {
		return enemyBulletSuperImg;
	}

	public Image getEnemyBulletNormalImg() {
		return enemyBulletNormalImg;
	}

	public Image getHeartImg() {
		return heartImg;
	}

	public Image getSkillHeartImg() {
		return skillHeartImg;
	}

	public Image getSkillBoomImg() {
		return skillBoomImg;
	}

	public Image getSkillBulletImg() {
		return skillBulletImg;
	}

	public Image getBulletNormalImg() {
		return bulletNormalImg;
	}

	public Image getBulletSuperImg() {
		return bulletSuperImg;
	}

	public Image getStoneBigImg() {
		return stoneBigImg;
	}

	public Image getStoneMediumImg() {
		return stoneMediumImg;
	}

	public Image getStoneSmallImg() {
		return stoneSmallImg;
	}
}
