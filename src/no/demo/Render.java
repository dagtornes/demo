package no.demo;

import processing.core.PApplet;

public class Render {

	public static void circle(PApplet screen, float x, float y, float r, int segments) {
		screen.pushMatrix();
		
		screen.beginShape(PApplet.TRIANGLE_FAN);
		screen.vertex(x, y);
		for (int i = 0; i != segments; ++i) {
			double radians = (2.0 * Math.PI * i) / (segments - 1);
			float xo = (float) Math.cos(radians);
			float yo = (float) Math.sin(radians);
			screen.vertex(x + r * xo, y + r * yo);
		}
		
		screen.endShape();
		screen.popMatrix();
	}
}
