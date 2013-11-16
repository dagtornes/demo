package no.demo;

import processing.core.PApplet;

/**
 * Created with IntelliJ IDEA.
 * User: nomarsyv
 * Date: 16.11.13
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class SpectrumBox {
    private Color spectrumColor;
    private int position;
    private int numSpectrumBoxes;
    private int top;
    private PApplet parent;
    private MuzakAnalyser analyser;

    public SpectrumBox(PApplet parent, MuzakAnalyser analyser,Color color, int position, int numSpectrumBoxes,int top) {
        spectrumColor = color;
        this.position = position;
        this.top = top;
        this.parent = parent;
        this.analyser = analyser;
        this.numSpectrumBoxes = numSpectrumBoxes;


    }

    public void draw() {

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

    private void DrawMirroredSpectrumBox(Color body, int position, int readout) {
        parent.fill(body.R, body.G, body.B);
        int bottomY = 1000;
        int readOut = readout;
        int baseX = 100 * position;
        int boxWidth = 100;
        //Top
        parent.quad(
                baseX, 0,
                baseX + boxWidth, 0,
                baseX + boxWidth, readOut,
                baseX, readOut);
        //Bottom
        parent.quad(
                baseX, bottomY - readOut,
                baseX + boxWidth, bottomY - readOut,
                baseX + boxWidth, bottomY,
                baseX, bottomY);
    }
}
