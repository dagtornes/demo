package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class DemoMain extends PApplet {
	private static final long serialVersionUID = 1L;

	private List<Scene> scenes;
	
	MuzakAnalyser analyser;
	Particles particles;

    private int numSpectrumBoxes = 20;
    
    public void setup() {
    	
    	scenes = new ArrayList<Scene>();
    	scenes.add(new Scene1(this, 5000));
    	scenes.add(new SceneButterflies(this, 150000));
    	
        size(1280, 1024, P2D);
        background(0);
        for (int i = 0; i < numSpectrumBoxes; i++) {
            spectrumBoxArrayList.add(GenerateRandomSpectrumBox(i));
        }

        analyser = new MuzakAnalyser(this);
        particles = new Particles(this);
        particles.create(150);
    }

    private SpectrumBox GenerateRandomSpectrumBox(int position){
        int Min = 0;
        int Max = 250;

        int random1 = Min + (int) (Math.random() * ((Max - Min) + 1));
        int random2 = Min + (int) (Math.random() * ((Max - Min) + 1));
        int random3 = Min + (int) (Math.random() * ((Max - Min) + 1));
        Color randColor1 = new Color(random1, random2, random3);
        return new SpectrumBox(randColor1,position,0);
    }


    ArrayList<Butterfly> butterflyArrayList = new ArrayList<Butterfly>();
    ArrayList<SpectrumBox> spectrumBoxArrayList = new ArrayList<SpectrumBox>();


    public class SpectrumBox {
        private Color spectrumColor;
        private int position;
        private int top;

        public SpectrumBox(Color color, int position, int top) {
            spectrumColor = color;
            this.position = position;
            this.top = top;
        }

        public void draw() {

            float beat = analyser.getBeat();
            //int beatSize = (int) (beat *500);

            int numBands = analyser.getNumBands();
            int loops = numBands / numSpectrumBoxes;
            float sum = 0.0f;
            for (int i = position; i != position + loops; ++i) {
                sum += analyser.getBand(i);
            }
            sum /= loops;
            int beatSize = (int) (100.0f * sum);

            if(beatSize > 500){
                beatSize = 500;
            }

            if(beatSize > top){
                top = beatSize;
            }

            DrawMirroredSpectrumBox(this.spectrumColor, this.position, this.top);

            top -= 10;
        }
    }

    public void draw() {
        clear();
        
        if (scenes.size() > 0) {
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
