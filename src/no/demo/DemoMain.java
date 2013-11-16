package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;

public class DemoMain extends PApplet {

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

        for (int i = 0; i < numSpectrumBoxes; i++) {
            Color color = new Color(100, 1, 1);
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


        particles.draw(this);
        
        for (SpectrumBox spectrumBox : spectrumBoxArrayList) {
        	spectrumBox.draw();
        }
        
        for(Butterfly butterfly : butterflyArrayList){
            butterfly.move();
            butterfly.draw(x, y);
        }

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


        float beat = analyser.getBeat();
        if (beat > 0.0) {
        	fill(255, 0, 0);
        	stroke(255, 0, 0);
        	Render.circle(this, 20.0f, 20.0f, 18.0f * beat, 32);
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
