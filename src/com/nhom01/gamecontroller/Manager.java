package com.nhom01.gamecontroller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nhom01.entities.EnemyBullet;
import com.nhom01.entities.Explosion;
import com.nhom01.entities.PlayerBullet;
import com.nhom01.entities.Skill;
import com.nhom01.entities.SpaceEnemy;
import com.nhom01.entities.SpacePlayer;
import com.nhom01.entities.Stone;
import com.nhom01.values.CommonValues;

public class Manager {

	private static final int RANDOM_RANGE_QUANTITY_STONE = 6;
	private static final int X_LABEL = 10;
	private static final int Y_SCORE_LABEL = CommonValues.HEIGHT_GUI - 80;
	private static final int Y_LEVEL_LABEL = Y_SCORE_LABEL + 30;
	private static final int Y_LIVES_LABEL = Y_LEVEL_LABEL + 30;
	private static final int REMOVE_PLAYER_BULLET = -5;
	private static final int VICTORY_LEVEL = 25;

	private int level;
	private int score;
	private int lives;
	private Random rand;
	private AudioManager audioMgr;
	private SpacePlayer player;
	private List<PlayerBullet> arrPlayerBullet;
	private List<EnemyBullet> arrEnemyBullet;
	private List<SpaceEnemy> arrEnemy;
	private List<Stone> arrStone;
	private List<Skill> arrSkill;
	private List<Explosion> arrExplosion;

	public Manager() {
		audioMgr = new AudioManager();
		rand = new Random();
		level = 1;
		score = 0;
		lives = CommonValues.PLAYER_LIVES;
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
		if (audioMgr.getSoundPlay().isRunning()) {
			return;
		}
		audioMgr.getSoundPlay().play();
	}

	// Khoi tao cac doi tuong
	public void initPlayer() {
		player = new SpacePlayer();
	}

	public void initEnemy() {
		arrSkill.clear();
		arrEnemyBullet.clear();
		arrEnemy.clear();
		SpaceEnemy enemy;
		for (int i = 0; i < 10 * level + 5; i++) {
			enemy = new SpaceEnemy(this.level);
			enemy.setTimeOut();
			arrEnemy.add(enemy);
		}
	}

	public void initStone() {
		arrStone.clear();
		Stone stone;
		int numberOfStone = rand.nextInt(RANDOM_RANGE_QUANTITY_STONE) + level;
		if (this.level > 1) {
			for (int i = 0; i < numberOfStone; i++) {
				stone = new Stone();
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
			if (arrPlayerBullet.get(i).getY() < REMOVE_PLAYER_BULLET) {
				arrPlayerBullet.remove(i);
				i--;
			} else {
				Explosion explosion = null;
				for (int j = 0; j < arrEnemy.size(); j++) {
					if (arrPlayerBullet.get(i).isCollided(arrEnemy.get(j))) {
						arrEnemy.get(j).setHealth(
								arrPlayerBullet.get(i).getDamage());
						explosion = new Explosion(
								arrPlayerBullet.get(i).getX(), arrPlayerBullet
										.get(i).getY());
						arrExplosion.add(explosion);
						if (arrEnemy.get(j).getHealth() <= 0) {
							audioMgr.getSoundExplosion().play();
							if (arrEnemy.get(j).getType() == CommonValues.NORMAL_ENEMY) {
								this.score += 5;
							} else if (arrEnemy.get(j).getType() == CommonValues.SUPER_ENEMY) {
								this.score += 8;
							} else {
								this.score += 10;
							}
							if (arrEnemy.get(j).isHaveSkill()) {
								Skill skill = new Skill(arrEnemy.get(j).getX(),
										arrEnemy.get(j).getY() + 5);
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
								arrStone.get(j).getY());
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
							player.getY());
					arrExplosion.add(explosion);
					audioMgr.getSoundDie().play();
					if (arrEnemyBullet.get(i).getType() == CommonValues.NORMAL_BULLET) {
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
						arrEnemy.get(i).getY());
				arrExplosion.add(explosion);
				audioMgr.getSoundDie().play();
				this.lives--;
				arrEnemy.remove(i);
				i--;
				break;
			}
		}
	}

	// Di chuyen stone
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
					if (arrSkill.get(i).getTypeSkill() == CommonValues.SKILL_BOOM) {
						this.score += arrEnemy.size() * 10;
						arrEnemy.clear();
						arrStone.clear();
						audioMgr.getSoundExplosion().play();
					} else if (arrSkill.get(i).getTypeSkill() == CommonValues.SKILL_HEART) {
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
						- CommonValues.SIZE_PLAYER_BULLET / 2, player.getY(),
						CommonValues.SUPER_BULLET);
				player.downQuantityOfSuperBullet();
			} else {
				bullet = new PlayerBullet(player.getX() + player.getSize() / 2
						- CommonValues.SIZE_PLAYER_BULLET / 2, player.getY(),
						CommonValues.NORMAL_BULLET);
			}
			arrPlayerBullet.add(bullet);
			player.setTimeOut();
		}
	}

	public void enemyFire() {
		for (int i = 0; i < arrEnemy.size(); i++) {
			EnemyBullet enemyBullet;
			if (arrEnemy.get(i).isFire()) {
				enemyBullet = new EnemyBullet(arrEnemy.get(i).getX()
						+ arrEnemy.get(i).getSize() / 2 - 2, arrEnemy.get(i)
						.getY() + arrEnemy.get(i).getSize(), arrEnemy.get(i)
						.getType());
				enemyBullet.setImage();
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
		if (audioMgr.getSoundPlay().isRunning()) {
			audioMgr.getSoundPlay().stop();
		}
		audioMgr.getSoundPlay().play();
		this.level = 1;
		this.lives = CommonValues.PLAYER_LIVES;
		this.score = 0;
		this.arrPlayerBullet.clear();
		this.arrExplosion.clear();
		this.initEnemy();
		this.initStone();
		this.setPlayer();
	}

	public boolean nextLevel() {
		if (arrEnemy.size() == 0) {
			player.setCanFire(false);
			if (arrPlayerBullet.size() > 0) {
				return false;
			}
			if (this.score > 0) {
				player.setCanFire(true);
				this.level++;
				return true;
			}
		}
		return false;
	}

	public boolean gameOver() {
		if (this.lives == 0) {
			audioMgr.getSoundPlay().stop();
			audioMgr.getSoundGameOver().play();
			return true;
		}
		return false;
	}

	public boolean checkVictory() {
		if (this.level >= VICTORY_LEVEL) {
			audioMgr.getSoundPlay().stop();
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
