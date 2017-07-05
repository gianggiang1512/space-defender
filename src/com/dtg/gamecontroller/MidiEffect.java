package com.dtg.gamecontroller;

import java.io.IOException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MidiEffect {

	private Sequencer mSequencer;
	private Sequence sequence;

	public MidiEffect(String audioName) {
		URL url = getClass().getResource("/AUDIOS/" + audioName);
		try {
			sequence = MidiSystem.getSequence(url);
			mSequencer = MidiSystem.getSequencer();
			mSequencer.setSequence(sequence);
		} catch (InvalidMidiDataException | IOException e) {
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void setLoop(int count) {
		mSequencer.setLoopCount(count);
	}

	public void play() {
		if (mSequencer.isRunning() || mSequencer.isOpen()) {
			return;
		}
		try {
			mSequencer.setSequence(this.sequence);
			mSequencer.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
		mSequencer.start();
	}

	public void stop() {
		mSequencer.stop();
		mSequencer.close();
	}

	public boolean isRunning() {
		return mSequencer.isRunning();
	}
}
