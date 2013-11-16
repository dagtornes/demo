package no.demo;

import processing.core.PApplet;

public class Scene1 implements Scene {

	private long duration;
	private long start;
	private Particles particles;

	public Scene1(PApplet parent, long duration) {
		this.start = System.currentTimeMillis();
		this.duration = duration;

		this.particles = new Particles(parent);
		this.particles.create(150);
	}

	@Override
	public void draw(PApplet render) {
		particles.draw(render);
	}

	@Override
	public boolean done() {
		return System.currentTimeMillis() > start + duration;
	}

}
