package no.demo;

import processing.core.PApplet;

public class DemoMain extends PApplet {
    public void setup() {
        size(1280, 1024);
        background(0);
    }

    int x = 0;
    int y = 0;
    int x2 = 0;
    int y2 = 0;
    int color = 0;
    boolean turn = true;

    public void draw() {
        clear();
        if (mousePressed) {
            x = mouseX;
            y = mouseY;
            x2 = pmouseX;
            y2 = pmouseY;
            line(mouseX, mouseY, pmouseX, pmouseY);
        }



        //Butterfly body
        stroke(50,50,100);
        fill(10,50,50);
        quad(
                600,200,
                700,200,   //Punkg - 2
                700,500,   //Punkt - 3
                600,500);


        //Wing color
        stroke(color,50,100);
        fill(67,3,51);

        //Wing One
        quad(
                300,100,
                600,200,
                600,500,
                300,600);

        //Wing Two
        quad(
                700,200,
                1000,100,
                1000,600,
                700,500);

        //quad(x, y, x2, y2, 69, 63, 30, 76);
        //quad(38+x, 31+y, 86+x, 20+y, 69, 63, 30, 76);


    }
}
