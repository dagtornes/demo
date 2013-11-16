package no.demo;

import processing.core.PApplet;

public class Helix {

	public float centerline;
	public float amplitude;
	public float period;
	public float phase = 0.0f;
	
	public Helix(float centerline, float amplitude, float period) {
		this.centerline = centerline;
		this.amplitude = amplitude;
		this.period = period;
	}
	
	public void draw(PApplet render) {
		int centerline = 150;
		int segments = 150;
		
		for (int seg = 0; seg != segments; ++seg) {
			
			float y1 = (render.height * seg)  / segments;
			float y2 = (render.height * (seg + 1)) / segments;
			
			float x_offset_1 = (float) (amplitude * Math.cos(y1 * period + phase));
			float x_offset_2 = (float) (amplitude * Math.cos(y2 * period + phase));
			
			render.line(centerline + x_offset_1, y1, centerline + x_offset_2, y2);
		}
	}
}
