package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;

public class DemoMain extends PApplet {

	MuzakAnalyser analyser;
	
    public void setup() {
        size(1280, 1024);
        background(0);

        butterflyArrayList.add(b1);
        butterflyArrayList.add(b2);
        
        analyser = new MuzakAnalyser(this);
    }

    int x = 0;
    int y = 0;
    int x2 = 0;
    int y2 = 0;
    int color = 0;
    boolean turn = true;
    int speed = 5;


    int butterflyPosition = 0;
    boolean butterflyReturns = false;

    ArrayList<Butterfly> butterflyArrayList = new ArrayList<Butterfly>();

    Color red = new Color(100,0,0);
    Color blue = new Color(0,0,100);

    Butterfly b1 = new Butterfly(red,blue,300,0,1);
    Butterfly b2 = new Butterfly(red,blue,600,0,3);

    public class Butterfly {
        private Color bodyColor, wingColor;
        private int xOffset, yPosition;
        private int speed;


        public Butterfly(Color body, Color wing, int xOffset, int yOffset, int speed) {
            this.bodyColor = body;
            this.wingColor = wing;
            this.xOffset = xOffset;
            this.yPosition = yOffset;
            this.speed = speed;
        }

        public void move() {
            yPosition = yPosition + speed;
        }

        public void draw() {
            DrawButterflyMethod(bodyColor, wingColor, xOffset, yPosition);
        }
    }


    public void draw() {
        clear();

        for(Butterfly butterfly : butterflyArrayList){
            butterfly.move();
            butterfly.draw();
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

        if (butterflyPosition > 2000) {
            butterflyPosition = -600;
            //butterflyReturns = true;
        }
        if (butterflyPosition < 0) {
            butterflyReturns = false;
        }

        if (butterflyReturns) {
            butterflyPosition -= 2;
        } else {
            butterflyPosition += 2;
        }

        float beat = analyser.getBeat();
        if (beat > 0.0) {
        	fill(255, 0, 0);
        	stroke(255, 0, 0);
        	Render.circle(this, width / 2, height / 2, 100 * beat, 32);
        }

    }

    public void FlockOfButterflies() {
        Color bodyColor = new Color(140, 50, 7);
        Color wingColor = new Color(100, 0, 0);

        DrawButterflyMethod(bodyColor, wingColor, 600, butterflyPosition);

        bodyColor = new Color(78, 170, 90);
        wingColor = new Color(5, 110, 0);

        DrawButterflyMethod(bodyColor, wingColor, 400, butterflyPosition + 200);


        bodyColor = new Color(78, 50, 90);
        wingColor = new Color(5, 5, 230);

        DrawButterflyMethod(bodyColor, wingColor, 700, butterflyPosition - 700);

        bodyColor = new Color(98, 150, 90);
        wingColor = new Color(45, 10, 0);

        DrawButterflyMethod(bodyColor, wingColor, 1000, butterflyPosition - 300);
    }

    public class Color {
        public int R, G, B;

        public Color(int r, int g, int b) {
            this.R = r;
            this.G = g;
            this.B = b;
        }
    }

    private void DrawButterflyMethod(Color body, Color wings, int xOffset, int yOffset) {
        //Butterfly body
        stroke(50, 50, 100);
        fill(body.R, body.G, body.B);
        quad(
                xOffset, yOffset,
                xOffset + 100, yOffset,   //Punkg - 2
                xOffset + 100, yOffset + 300,   //Punkt - 3
                xOffset, yOffset + 300);


        //Wing color
        stroke(color, 50, 100);
        fill(wings.R, wings.G, wings.B);

        //Wing One
        quad(
                xOffset - 300 + x, yOffset - 100 + y,
                xOffset, yOffset,
                xOffset, yOffset + 300,
                xOffset - 300 + x, yOffset + 400 - y);


        //Wing Two
        quad(
                xOffset + 100, yOffset,
                xOffset + 400 - x, yOffset - 100 + x,
                xOffset + 400 - x, yOffset + 400 - x,
                xOffset + 100, yOffset + 300);

    }

    private void Butterfly() {
        //Butterfly body
        stroke(50, 50, 100);
        fill(80, 150, 50);
        quad(
                600, 200,
                700, 200,   //Punkg - 2
                700, 500,   //Punkt - 3
                600, 500);


        //Wing color
        stroke(color, 50, 100);
        fill(67, 3, 51);

        //Wing One
        quad(
                300 + x, 100 + y,
                600, 200,
                600, 500,
                300 + x, 600 - y);


        //Wing Two
        quad(
                700, 200,
                1000 - x, 100 + x,
                1000 - x, 600 - x,
                700, 500);


        if (x > 200) {
            turn = true;
        }

        if (x < 1) {
            turn = false;
        }


        if (turn) {
            x -= speed;
            y -= speed;
        } else {
            x += speed;
            y += speed;
        }
    }
}
