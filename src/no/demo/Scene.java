package no.demo;

import processing.core.PApplet;

public interface Scene {
	public void draw(PApplet render);
	public boolean done();
}
