package no.demo;

import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;

public class MuzakAnalyser {
	PApplet parent;
	Minim minim;
	AudioPlayer song;
	BeatDetect bd;
	
	public MuzakAnalyser(PApplet parent) {
		this.parent = parent;
		minim = new Minim(parent);
		song = minim.loadFile("milkman.mp3", 512);
		bd = new BeatDetect(song.bufferSize(), song.sampleRate());
		bd.detectMode(BeatDetect.FREQ_ENERGY);
		song.play();
	}
	
	private int count = 0;
	public float getBeat() {
		bd.detect(song.mix);
		if (bd.isKick()) {
			count = 60;
		}
		count--;
		
		return Math.max(count / 60.0f, 0.0f);
	}
}
