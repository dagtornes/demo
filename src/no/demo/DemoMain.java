package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DemoMain extends PApplet {

	private List<Scene> scenes;
	
	private static final long serialVersionUID = 1L;
	MuzakAnalyser analyser;
	Particles particles;

    private int numSpectrumBoxes = 20;

    public void setup() {
        size(1280, 1024, P2D);
        background(0);

        for (int i = 0; i < 10; i++) {
            butterflyArrayList.add(GenerateRandomButterfly());
        }

        analyser = new MuzakAnalyser(this);
        particles = new Particles(this);
        particles.create(150);

        scenes = new ArrayList<Scene>();
        scenes.add(new Scene1(this, 10 * 1000));
        scenes.add(new Scene3(this, analyser, 10 * 2000));
    }



    private Butterfly GenerateRandomButterfly() {
        int Min = 0;
        int Max = 250;

        Color randColor1 = Color.random(Min, Max);
        Color randColor2 = Color.random(Min, Max);

        int PosMax = 2000;


        int pos = Min + (int) (Math.random() * ((PosMax - Min) + 1));

        int SpeedMax = 10;

        int position = Min + (int) (Math.random() * ((PosMax - Min) + 1));

        int speed = 1 + (int) (Math.random() * ((SpeedMax - 1) + 1));

        Butterfly b1 = new Butterfly(this, randColor1, randColor2, position, 0, speed);

        return b1;
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

    }


    private void DrawMirroredSpectrumBox(Color body, int position, int readout) {
        fill(body.R, body.G, body.B);
        int bottomY = 1000;
        int readOut = readout;
        int baseX = 100 * position;
        int boxWidth = 100;
        //Top
        quad(
                baseX, 0,
                baseX + boxWidth, 0,
                baseX + boxWidth, readOut,
                baseX, readOut);
        //Bottom
        quad(
                baseX, bottomY - readOut,
                baseX + boxWidth, bottomY - readOut,
                baseX + boxWidth, bottomY,
                baseX, bottomY);
    }

}
