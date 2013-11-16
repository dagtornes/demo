package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DemoMain extends PApplet {

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "no.demo.DemoMain" });
	}
	
	private List<Scene> scenes;
	
	private static final long serialVersionUID = 1L;
	MuzakAnalyser analyser;
	Particles particles;
	Helix helix1, helix2;
	
    public void setup() {
    	helix1 = new Helix(150.0f, 10.0f, 0.075f);
    	helix2 = new Helix(150.0f, -10.0f, 0.075f);
    	helix1.phase = -10.0f;
    	helix2.phase = -10.725f;
    	
    	
        size(1280, 1024, P2D);
        background(0);


        analyser = new MuzakAnalyser(this);
        particles = new Particles(this);
        particles.create(150);

        scenes = new ArrayList<Scene>();
        scenes.add(new Scene1(this, 5000));
        scenes.add(new Scene3(this, analyser, 10 * 2000));
        scenes.add(new SceneButterflies(this, 150000));
        
    }

    
    public void draw() {
        clear();
        
        if (scenes.size() > 0) {
        	//System.out.println("DRAW!");
        	Scene current = scenes.get(0);
        	current.draw(this);
        	if (current.done()) {
        		scenes.remove(0);
        	}
        }
        
        float beat = analyser.getBeat();
        helix1.phase -= 0.0166 * 5.0f;
        helix1.amplitude = 10 * beat + 5;
        helix1.draw(this);
        helix2.phase -= 0.0166 * 5.0f;
        helix2.amplitude = -(10 * beat + 5);
        helix2.draw(this);
    }
}
