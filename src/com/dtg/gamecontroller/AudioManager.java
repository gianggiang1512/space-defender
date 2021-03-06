package com.dtg.gamecontroller;

import java.applet.Applet;
import java.applet.AudioClip;

import javax.sound.midi.Sequencer;

public class AudioManager {

	private MidiEffect soundBg;
	private MidiEffect soundGameOver;

	private AudioClip soundSkill;
	private AudioClip soundDie;
	private AudioClip soundExplosion;
	private AudioClip soundShot;

	public AudioManager() {
		soundBg = new MidiEffect("soundBg.mid");
		soundBg.setLoop(Sequencer.LOOP_CONTINUOUSLY);
		soundGameOver = new MidiEffect("GameOver.mid");
		soundGameOver.setLoop(Sequencer.LOOP_CONTINUOUSLY);

		soundSkill = Applet.newAudioClip(getClass().getResource(
				"/AUDIOS/skill.wav"));
		soundDie = Applet.newAudioClip(getClass()
				.getResource("/AUDIOS/die.wav"));
		soundExplosion = Applet.newAudioClip(getClass().getResource(
				"/AUDIOS/explode.wav"));
		soundShot = Applet.newAudioClip(getClass().getResource(
				"/AUDIOS/shot.wav"));
	}

	public MidiEffect getSoundBg() {
		return soundBg;
	}

	public AudioClip getSoundSkill() {
		return soundSkill;
	}

	public MidiEffect getSoundGameOver() {
		return soundGameOver;
	}

	public AudioClip getSoundDie() {
		return soundDie;
	}

	public AudioClip getSoundExplosion() {
		return soundExplosion;
	}

	public AudioClip getSoundShot() {
		return soundShot;
	}

}
