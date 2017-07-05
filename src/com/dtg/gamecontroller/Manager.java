package com.dtg.gamecontroller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dtg.entitties.EnemyBullet;
import com.dtg.entitties.Explosion;
import com.dtg.entitties.PlayerBullet;
import com.dtg.entitties.Skill;
import com.dtg.entitties.SpaceEnemy;
import com.dtg.entitties.SpacePlayer;
import com.dtg.entitties.Stone;
import com.dtg.values.CommonValues;

public class Manager {

	private static final int PLAYER_LIVES = 5;
	private static final int PLAYER_SPEED = 1;
	private static final int SKILL_SPEED = 3;
	private static final int SIZE_PLAYER_BULLET = 10;
	private static final int SIZE_ENEMY_BULLET = 9;
	private static final int NORMAL_ENEMY = 0;
	private static final int SUPER_ENEMY = 1;
	private static final int MAX_ENEMY_SPEED = 3;
	private static final int RANDOM_RANGE = 50;
	private static final int BULLET_SPEED = 5;
	private static final int BIG_STONE_TYPE = 1;
	private static final int NORMAL_STONE_TYPE = 2;
	private static final int SKILL_BOOM = 0;
	private static final int SKILL_HEART = 1;
	private static final int NORMAL_BULLET = 0;
	private static final int SUPER_BULLET = 1;
	private static final int RANGE_STONE = 6;
	private static final int X_LABEL = 10;
	private static final int Y_SCORE_LABEL = 20;
	private static final int Y_LIVES_LABEL = 80;
	private static final int Y_LEVEL_LABEL = 50;
	private static final int ENEMY_BULLET_SPEED = 3;
	private static final int SIZE_EXPLOSION = 15;
	private int level;
	private int score;
	private int lives;
	private Random rand;
	private ImageManager imageMgr;
	private AudioManager audioMgr;
	private SpacePlayer player;
	private List<PlayerBullet> arrPlayerBullet;
	private List<EnemyBullet> arrEnemyBullet;
	private List<SpaceEnemy> arrEnemy;
	private List<Stone> arrStone;
	private List<Skill> arrSkill;
	private List<Explosion> arrExplosion;

	public Manager() {
		imageMgr = new ImageManager();
		audioMgr = new AudioManager();
		rand = new Random();
		level = 1;
		score = 0;
		lives = PLAYER_LIVES;
	}

	public void initObjects() {
		arrPlayerBullet = new ArrayList<PlayerBullet>();
		arrEnemyBullet = new ArrayList<EnemyBullet>();
		arrEnemy = new ArrayList<SpaceEnemy>();
		arrStone = new ArrayList<Stone>();
		arrSkill = new ArrayList<Skill>();
		arrExplosion = new ArrayList<Explosion>();
		initPlayer();
		initEnemy();
		initStone();
		audioMgr.getSoundBg().play();
	}

	// Khoi tao cac doi tuong
	public void initPlayer() {
		player = new SpacePlayer(CommonValues.WIDTH_GUI / 2 - 15,
				CommonValues.HEIGHT_GUI - 50, CommonValues.SIZE_PLAYER,
				CommonValues.ORIENT_UP, imageMgr.getPlayerImg(), PLAYER_LIVES,
				PLAYER_SPEED);
	}

	public void initEnemy() {
		arrSkill.clear();
		arrEnemyBullet.clear();
		arrEnemy.clear();
		int typeEnemy;
		SpaceEnemy enemy;
		for (int i = 0; i < 10 * level; i++) {
			typeEnemy = rand.nextInt(RANDOM_RANGE) + 1;
			if (typeEnemy % 5 == 0 && this.level > 1) {
				typeEnemy = SUPER_ENEMY;
				enemy = new SpaceEnemy(rand.nextInt(CommonValues.WIDTH_GUI
						- CommonValues.SIZE_SUPER_ENEMY) + 1,
						rand.nextInt(CommonValues.HEIGHT_GUI / 2) + 50,
						CommonValues.SIZE_SUPER_ENEMY,
						imageMgr.getEnemySuperImg(), typeEnemy,
						rand.nextInt(MAX_ENEMY_SPEED) + 1,
						rand.nextInt(1000) + 1);
			} else {
				typeEnemy = NORMAL_ENEMY;
				enemy = new SpaceEnemy(rand.nextInt(CommonValues.WIDTH_GUI
						- CommonValues.SIZE_NORMAL_ENEMY) + 1,
						rand.nextInt(CommonValues.HEIGHT_GUI / 2 + 100),
						CommonValues.SIZE_PLAYER, imageMgr.getEnemyNormalImg(),
						typeEnemy, rand.nextInt(MAX_ENEMY_SPEED) + 1,
						rand.nextInt(1000) + 1);
			}
			enemy.setTimeOut();
			arrEnemy.add(enemy);
		}
	}

	public void initStone() {
		arrStone.clear();
		int typeStone;
		Stone stone;
		int numberOfStone = rand.nextInt(RANGE_STONE) + level;
		if (this.level > 1) {
			for (int i = 0; i < numberOfStone; i++) {
				typeStone = rand.nextInt(3) + 1;
				if (typeStone == BIG_STONE_TYPE) {
					stone = new Stone(
							rand.nextInt(CommonValues.WIDTH_GUI - 10) + 5, (-1)
									* (rand.nextInt(100) + 500),
							CommonValues.SIZE_BIG_STONE,
							imageMgr.getStoneBigImg(), typeStone);
				} else if (typeStone == NORMAL_STONE_TYPE) {
					stone = new Stone(
							rand.nextInt(CommonValues.WIDTH_GUI - 10) + 5, (-1)
									* (rand.nextInt(100) + 500),
							CommonValues.SIZE_NORMAL_STONE,
							imageMgr.getStoneMediumImg(), typeStone);
				} else {
					stone = new Stone(
							rand.nextInt(CommonValues.WIDTH_GUI - 10) + 5, (-1)
									* (rand.nextInt(100) + 500),
							CommonValues.SIZE_SMALL_STONE,
							imageMgr.getStoneSmallImg(), typeStone);
				}
				arrStone.add(stone);
			}
		}
	}

	// Ve cac doi tuong
	public void drawObjects(Graphics2D g2d) {
		player.draw(g2d);
		drawOtherObjects(g2d);
		g2d.setColor(Color.WHITE);
		g2d.setFont(CommonValues.NORMAL_FONT);
		g2d.drawString("Score: " + this.score, X_LABEL, Y_SCORE_LABEL);
		g2d.drawString("Level: " + this.level, X_LABEL, Y_LEVEL_LABEL);
		g2d.drawString("Lives: " + this.lives, X_LABEL, Y_LIVES_LABEL);
	}

	private void drawOtherObjects(Graphics2D g2d) {
		if (arrPlayerBullet.size() > 0) {
			for (int i = arrPlayerBullet.size() - 1; i >= 0; i--) {
				arrPlayerBullet.get(i).draw(g2d);
			}
		}
		if (arrStone.size() > 0) {
			for (int i = arrStone.size() - 1; i >= 0; i--) {
				arrStone.get(i).draw(g2d);
			}
		}
		if (arrSkill.size() > 0) {
			for (int i = arrSkill.size() - 1; i >= 0; i--) {
				arrSkill.get(i).draw(g2d);
			}
		}
		if (arrEnemy.size() > 0) {
			for (int i = arrEnemy.size() - 1; i >= 0; i--) {
				arrEnemy.get(i).draw(g2d);
			}
		}
		if (arrEnemyBullet.size() > 0) {
			for (int i = arrEnemyBullet.size() - 1; i >= 0; i--) {
				arrEnemyBullet.get(i).draw(g2d);
			}
		}
		if (arrExplosion.size() > 0) {
			for (int i = arrExplosion.size() - 1; i >= 0; i--) {
				if (arrExplosion.get(i).isActive()) {
					arrExplosion.get(i).draw(g2d);
				} else {
					arrExplosion.remove(i);
				}
			}
		}
	}

	// Doi huong cua player khi nhan phim
	public void changeOrientSpacePlayer(int orient) {
		player.changeOrient(orient);
	}

	// Di chuyen player
	public void moveSpacePlayer(int time) {
		player.move(time);
	}

	// Di chuyen dan cua player
	public void movePlayerBullet(int time) {
		for (int i = 0; i < arrPlayerBullet.size(); i++) {
			arrPlayerBullet.get(i).move(time);
			if (arrPlayerBullet.get(i).getY() < -5) {
				arrPlayerBullet.remove(i);
				i--;
			} else {
				Explosion explosion = null;
				for (int j = 0; j < arrEnemy.size(); j++) {
					if (arrPlayerBullet.get(i).isCollided(arrEnemy.get(j))) {
						arrEnemy.get(j).setHealth(
								arrPlayerBullet.get(i).getDamage());
						if (arrEnemy.get(j).getHealth() <= 0) {
							audioMgr.getSoundExplosion().play();
							explosion = new Explosion(arrPlayerBullet.get(i)
									.getX(), arrPlayerBullet.get(i).getY(),
									SIZE_EXPLOSION, imageMgr.getExplosion());
							arrExplosion.add(explosion);
							if (arrEnemy.get(j).getType() == NORMAL_ENEMY) {
								this.score++;
							} else {
								this.score += 5;
							}
							if (arrEnemy.get(j).isHaveSkill()) {
								Skill skill;
								int typeSkill = rand.nextInt(3);
								if (typeSkill == SKILL_BOOM) {
									skill = new Skill(arrEnemy.get(j).getX(),
											arrEnemy.get(j).getY() + 5,
											CommonValues.SIZE_SKILL_BOOM,
											typeSkill,
											imageMgr.getSkillBoomImg(),
											SKILL_SPEED);
								} else if (typeSkill == SKILL_HEART) {
									skill = new Skill(arrEnemy.get(j).getX(),
											arrEnemy.get(j).getY() + 5,
											CommonValues.SIZE_SKILL_HEART,
											typeSkill,
											imageMgr.getSkillHeartImg(),
											SKILL_SPEED);
								} else {
									skill = new Skill(arrEnemy.get(j).getX(),
											arrEnemy.get(j).getY() + 5,
											CommonValues.SIZE_SKILL_BULLET,
											typeSkill,
											imageMgr.getSkillBulletImg(),
											SKILL_SPEED);
								}
								arrSkill.add(skill);
							}
							arrEnemy.remove(j);
							j--;
						}
						arrPlayerBullet.remove(i);
						i--;
						break;
					}
				}
				if (i < 0) {
					return;
				}
				for (int j = 0; j < arrStone.size(); j++) {
					if (arrPlayerBullet.get(i).isCollided(arrStone.get(j))) {
						explosion = new Explosion(arrStone.get(j).getX(),
								arrStone.get(j).getY(), SIZE_EXPLOSION,
								imageMgr.getExplosion());
						arrExplosion.add(explosion);
						audioMgr.getSoundExplosion().play();
						arrStone.remove(j);
						j--;
						arrPlayerBullet.remove(i);
						i--;
						break;
					}
				}
			}
		}

	}

	// Di chuyen bullet cua enemy
	public void moveEnemyBullet(int time) {
		for (int i = 0; i < arrEnemyBullet.size(); i++) {
			arrEnemyBullet.get(i).move(time);
			if (arrEnemyBullet.get(i).getY() > CommonValues.HEIGHT_GUI + 10) {
				arrEnemyBullet.remove(i);
				i--;
			} else {
				if (arrEnemyBullet.get(i).isCollided(player)) {
					Explosion explosion = new Explosion(player.getX(),
							player.getY(), SIZE_EXPLOSION,
							imageMgr.getExplosion());
					arrExplosion.add(explosion);
					audioMgr.getSoundDie().play();
					if (arrEnemyBullet.get(i).getType() == NORMAL_BULLET) {
						this.lives--;
					} else {
						this.lives = 0;
					}
					arrEnemyBullet.remove(i);
					i--;
					break;
				}
			}
		}
	}

	// Di chuyen enemy space
	public void moveSpaceEnemy(int time) {
		for (int i = 0; i < arrEnemy.size(); i++) {
			arrEnemy.get(i).move(time);
			if (arrEnemy.get(i).isCollided(player)) {
				Explosion explosion = new Explosion(arrEnemy.get(i).getX(),
						arrEnemy.get(i).getY(), SIZE_EXPLOSION,
						imageMgr.getExplosion());
				arrExplosion.add(explosion);
				audioMgr.getSoundDie().play();
				this.lives--;
				arrEnemy.remove(i);
				i--;
				break;
			}
		}
	}

	// Di chuyen da
	public void moveStone(int time) {
		for (int i = 0; i < arrStone.size(); i++) {
			arrStone.get(i).move(time);
			if (arrStone.get(i).getY() > CommonValues.HEIGHT_GUI + 10) {
				arrStone.remove(i);
				i--;
			} else {
				if (arrStone.get(i).isCollided(player)) {
					audioMgr.getSoundDie().play();
					this.lives--;
					arrStone.remove(i);
					i--;
					break;
				}
			}
		}
	}

	// Di chuyen skills
	public void moveSkill(int time) {
		for (int i = 0; i < arrSkill.size(); i++) {
			arrSkill.get(i).move(time);
			if (arrSkill.get(i).getY() > CommonValues.HEIGHT_GUI + 10) {
				arrSkill.remove(i);
				i--;
			} else {
				if (arrSkill.get(i).isCollied(player)) {
					audioMgr.getSoundSkill().play();
					if (arrSkill.get(i).getTypeSkill() == SKILL_BOOM) {
						audioMgr.getSoundExplosion().play();
						this.score += 500;
						arrEnemy.clear();
						arrStone.clear();
					} else if (arrSkill.get(i).getTypeSkill() == SKILL_HEART) {
						this.lives++;
					} else {
						player.setSuperBullet();
					}
					arrSkill.remove(i);
					i--;
					break;
				}
			}
		}
	}

	public void moveExplosion(int time) {
		for (int i = arrExplosion.size() - 1; i >= 0; i--) {
			arrExplosion.get(i).move(time);
		}
	}

	// Xu li ban bullet
	public void playerFire() {
		if (player.isFire()) {
			audioMgr.getSoundShot().play();
			PlayerBullet bullet;
			if (player.isHaveSuperBullet()) {
				bullet = new PlayerBullet(player.getX() + player.getSize() / 2
						- SIZE_PLAYER_BULLET / 2 + 2, player.getY(),
						SIZE_PLAYER_BULLET, imageMgr.getBulletSuperImg(),
						SUPER_BULLET, BULLET_SPEED);
				player.downQuantityOfSuperBullet();
			} else {
				bullet = new PlayerBullet(player.getX() + player.getSize() / 2
						- SIZE_PLAYER_BULLET / 2 + 2, player.getY(),
						SIZE_PLAYER_BULLET, imageMgr.getBulletNormalImg(),
						NORMAL_BULLET, BULLET_SPEED);
			}
			arrPlayerBullet.add(bullet);
			player.setTimeOut();
		}
	}

	public void enemyFire() {
		for (int i = 0; i < arrEnemy.size(); i++) {
			EnemyBullet enemyBullet;
			if (arrEnemy.get(i).isFire()) {
				if (arrEnemy.get(i).getType() == NORMAL_ENEMY) {
					enemyBullet = new EnemyBullet(arrEnemy.get(i).getX()
							+ arrEnemy.get(i).getSize() / 2, arrEnemy.get(i)
							.getY() + arrEnemy.get(i).getSize(),
							SIZE_ENEMY_BULLET,
							imageMgr.getEnemyBulletNormalImg(), NORMAL_ENEMY,
							ENEMY_BULLET_SPEED);
				} else {
					enemyBullet = new EnemyBullet(arrEnemy.get(i).getX()
							+ arrEnemy.get(i).getSize() / 2, arrEnemy.get(i)
							.getY() + arrEnemy.get(i).getSize(),
							SIZE_ENEMY_BULLET,
							imageMgr.getEnemyBulletSuperImg(), SUPER_ENEMY,
							ENEMY_BULLET_SPEED);
				}
				arrEnemyBullet.add(enemyBullet);
				arrEnemy.get(i).setTimeOut();
			}
		}
	}

	public void setTimeOut() {
		player.setTimeOut();
		for (SpaceEnemy spaceEnemy : arrEnemy) {
			spaceEnemy.setTimeOut();
		}
	}

	public void downTimeOut(int value) {
		player.downTimeOut(value);
		for (SpaceEnemy spaceEnemy : arrEnemy) {
			spaceEnemy.downTimeOut(value / 2);
		}
	}

	public void setPlayer() {
		player.setLocation(CommonValues.WIDTH_GUI / 2 - 15,
				CommonValues.HEIGHT_GUI - 50);
	}

	public void resetAllObjects() {
		if (audioMgr.getSoundGameOver().isRunning()) {
			audioMgr.getSoundGameOver().stop();
		}
		audioMgr.getSoundBg().play();
		this.level = 1;
		this.lives = PLAYER_LIVES;
		this.score = 0;
		this.arrPlayerBullet.clear();
		this.arrExplosion.clear();
		this.initEnemy();
		this.initStone();
		this.setPlayer();
	}

	public boolean nextLevel() {
		if (arrEnemy.size() == 0 && this.score > 0) {
			this.level++;
			return true;
		}
		return false;
	}

	public boolean gameOver() {
		if (this.lives == 0) {
			audioMgr.getSoundBg().stop();
			audioMgr.getSoundGameOver().play();
			return true;
		}
		return false;
	}

	public boolean checkVictory() {
		if (this.level > 15) {
			audioMgr.getSoundGameOver().play();
			return true;
		}
		return false;
	}

	public int getLevel() {
		return level;
	}

	public int getScore() {
		return score;
	}

	public int getLives() {
		return lives;
	}

	public int getArrStoneSize() {
		return arrStone.size();
	}

}
