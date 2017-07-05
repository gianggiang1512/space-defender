package com.dtg.values;

import java.awt.Font;
import java.awt.Toolkit;

public class CommonValues {

	//Thong so width, height cua man hinh
	public static final int WIDTH_GUI = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int HEIGHT_GUI = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	//Thong so width, height cac phim menu
	public static final int WIDTH_BUTTON = 200;
	public static final int HEIGHT_BUTTON = 50;
	
	//id cua cac CardLayout
	public static final String MENU_ID = "0";
	public static final String PLAY_ID = "1";
	public static final String HUONG_DAN_ID = "2";
	
	//Cac bien huong len, xuong , phai ,trai
	public static final int ORIENT_UP = 0;
	public static final int ORIENT_DOWN = 1;
	public static final int ORIENT_LEFT = 2;
	public static final int ORIENT_RIGHT = 3;
	
	//Font chu
	public static final Font NORMAL_FONT = new Font("Tahoma", Font.BOLD, 12);
	
	//Kich thuoc cua player, enemy
	public static final int SIZE_PLAYER = 42;
	public static final int SIZE_NORMAL_ENEMY = 32;
	public static final int SIZE_SUPER_ENEMY = 37;
	
	//Kich thuoc cac loai da
	public static final int SIZE_NORMAL_STONE = 37;
	public static final int SIZE_BIG_STONE = 50;
	public static final int SIZE_SMALL_STONE = 24;
	
	//Kich thuoc cac skill
	public static final int SIZE_SKILL_BOOM = 20;
	public static final int SIZE_SKILL_HEART = 48;
	public static final int SIZE_SKILL_BULLET = 32;
}
