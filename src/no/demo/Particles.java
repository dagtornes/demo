package no.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import processing.core.PApplet;

public class Particles {
	
	private class Particle {

		public float x = 0.0f, y = 0.0f;
		public Particle(float x, float y) {
			this.x = x;
			this.y = y;
		}
		
		public void update() {
			y += 40.0f * DT;
		}

		public void draw(PApplet render) {
			float size = 1.75f;
			
			render.fill(255, 255, 255);
			render.stroke(255, 255, 255);
			render.quad(
					x - size, y - size,
					x + size, y - size,
					x + size, y + size,
					x - size, y + size);
		}

		public boolean contained(int width, int height) {
			return x > 0.0f && x < width && y > 0.0 && y < height;
		}
		
	}
	
	private final static float DT = 0.016666f;
	private PApplet parent;
	private ArrayList<Particle> particles;
	
	private void recreate() {
		Iterator<Particle> i = particles.iterator();
		int removed = 0;
		while (i.hasNext()) {
			Particle p = i.next();
			if (!p.contained(parent.width, parent.height)) {
				i.remove();
				removed++;
			}
		}
		create(removed);
	}
	
	public void create(int count) {
		Random r = new Random();
		for (int i = 0; i != count; ++i) {
			float x = r.nextFloat() * parent.width;
			float y = r.nextFloat() * parent.height;
			
			particles.add(new Particle(x, y));
		}
	}
	
	public Particles(PApplet parent) {
		this.parent = parent;
		this.particles = new ArrayList<Particle>();
	}
	
	
	
	public void draw(PApplet parent) {
		for (Particle particle : particles) {
			particle.update();
			particle.draw(parent);
		}
		recreate();
	}
}
