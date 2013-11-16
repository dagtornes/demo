package no.demo;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: nomarsyv
 * Date: 16.11.13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */
public class Scene3 implements Scene {

    private long duration;
    private long start;
    private ArrayList<SpectrumBox> spectrumBoxes = new ArrayList<SpectrumBox>();
    private MuzakAnalyser analyser;
    private PApplet parent;
    private int numSpectrumBoxes = 20;

    public Scene3(PApplet parent, MuzakAnalyser analyser,long duration){
        this.start = System.currentTimeMillis();
        this.duration = duration;
        this.analyser = analyser;
        this.parent = parent;

        for (int i = 0; i < numSpectrumBoxes; i++) {
            spectrumBoxes.add(GenerateRandomSpectrumBox(i,numSpectrumBoxes));
        }
    }
    private SpectrumBox GenerateRandomSpectrumBox(int position, int numSpectrumBoxes){
        int Min = 0;
        int Max = 250;

        int random1 = Min + (int) (Math.random() * ((Max - Min) + 1));
        int random2 = Min + (int) (Math.random() * ((Max - Min) + 1));
        int random3 = Min + (int) (Math.random() * ((Max - Min) + 1));
        Color randColor1 = new Color(random1, random2, random3);
        return new SpectrumBox(parent,analyser,randColor1,position,numSpectrumBoxes,0);
    }


    @Override
    public void draw(PApplet render) {
        for (SpectrumBox spectrumBox : spectrumBoxes) {
            spectrumBox.draw();
        }
    }

    @Override
    public boolean done() {
        return System.currentTimeMillis() > start + duration;
    }
}
