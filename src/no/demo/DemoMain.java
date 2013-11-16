package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DemoMain extends PApplet {

	private List<Scene> scenes;
	
	private static final long serialVersionUID = 1L;
	MuzakAnalyser analyser;
	Particles particles;
	Helix helix1, helix2;
	
    private int numSpectrumBoxes = 20;

    public void setup() {
    	helix1 = new Helix(150.0f, 10.0f, 0.075f);
    	helix2 = new Helix(150.0f, -10.0f, 0.075f);
    	helix2.phase = 0.0f;
    	
    	
    	scenes = new ArrayList<Scene>();
    	scenes.add(new Scene1(this, 5000));
    	scenes.add(new SceneButterflies(this, 150000));
    	
        size(1280, 1024, P2D);
        background(0);


        analyser = new MuzakAnalyser(this);
        particles = new Particles(this);
        particles.create(150);

        scenes = new ArrayList<Scene>();
        scenes.add(new Scene1(this, 10 * 1000));
        scenes.add(new Scene3(this, analyser, 10 * 2000));
    }




    int x = 0;
    int y = 0;
    int x2 = 0;
    int y2 = 0;
    int color = 0;
    boolean turn = true;
    int speed = 5;

    ArrayList<Butterfly> butterflyArrayList = new ArrayList<Butterfly>();
    ArrayList<SpectrumBox> spectrumBoxArrayList = new ArrayList<SpectrumBox>();




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
        helix1.amplitude = 10 * beat + 5;
        helix1.draw(this);
        helix2.amplitude = -(10 * beat + 5);
        helix2.draw(this);
    }
}
