package no.demo;

import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

public class MuzakAnalyser {
	PApplet parent;
	Minim minim;
	AudioPlayer song;

	BeatDetect bd;
	FFT fft;

	public MuzakAnalyser(PApplet parent) {
		this.parent = parent;
		minim = new Minim(parent);
		song = minim.loadFile("milkman.mp3", 512);
		bd = new BeatDetect(song.bufferSize(), song.sampleRate());
		bd.detectMode(BeatDetect.FREQ_ENERGY);
		song.play();

		fft = new FFT(song.bufferSize(), song.sampleRate());
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

	public void drawSpectrum() {
		fft.forward(song.mix);

		parent.pushMatrix();
		
		parent.translate(0.0f, (float) parent.height);
		parent.scale(3.0f);
		parent.stroke(255, 0, 0, 128);
		for (int i = 0; i < fft.specSize(); i++) {
			parent.line(i, 0, i, -fft.getBand(i) * 4);
		}
		
		parent.popMatrix();
	}
}
