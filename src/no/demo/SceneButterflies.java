package no.demo;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

public class SceneButterflies implements Scene {

	private long start;
	private long duration;

	@Override
	public boolean done() {
		return System.currentTimeMillis() > start + duration;
	}

	private List<Butterfly> butterflies;
	private int x, y;
	private boolean turn;
	private int speed = 5;

	private Butterfly GenerateRandomButterfly(PApplet parent) {
		int Min = 0;
		int Max = 250;

		Color randColor1 = Color.random(Min, Max);
		Color randColor2 = Color.random(Min, Max);

		int PosMax = 2000;

		int SpeedMax = 10;

		int position = Min + (int) (Math.random() * ((PosMax - Min) + 1));

		int speed = 1 + (int) (Math.random() * ((SpeedMax - 1) + 1));

		return new Butterfly(parent, randColor1, randColor2, position, 0, speed);
	}

	public SceneButterflies(PApplet parent, long duration) {
		this.butterflies = new ArrayList<Butterfly>();
		this.start = System.currentTimeMillis();
		this.duration = duration;

		for (int i = 0; i != 10; ++i) {
			this.butterflies.add(GenerateRandomButterfly(parent));
		}

		this.x = 0;
		this.y = 0;
	}

	@Override
	public void draw(PApplet render) {

		if (turn) {
			x -= speed;
			y -= speed;
		} else {
			x += speed;
			y += speed;
		}

		if (x > 200) {
			turn = true;
		}

		if (x < 1) {
			turn = false;
		}

		for (Butterfly butterfly : butterflies) {
			butterfly.move();
			butterfly.draw(x, y);
		}

	}

}
