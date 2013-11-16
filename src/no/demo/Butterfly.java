package no.demo;


public class Butterfly {
    private static final int color = 0;

	private final DemoMain demoMain;
	private Color bodyColor, wingColor;
    private int xOffset, yPosition;
    private int speed;


    public Butterfly(DemoMain demoMain, Color body, Color wing, int xOffset, int yOffset, int speed) {
        this.demoMain = demoMain;
		this.bodyColor = body;
        this.wingColor = wing;
        this.xOffset = xOffset;
        this.yPosition = yOffset;
        this.speed = speed;
    }

    public void move() {
        if(yPosition > 2000){
            int Min = 0;
            int PosMax = 2000;

            int SpeedMax = 10;

            int position = Min + (int)(Math.random() * ((PosMax - Min) + 1));

            int speed =  1 + (int)(Math.random() * ((SpeedMax - 1) + 1));
            xOffset = position;
            this.speed = speed;
            yPosition = -300;
        }
        yPosition = yPosition + speed;
    }

    public void draw(int x, int y) {
    	demoMain.stroke(50, 50, 100);
    	demoMain.fill(bodyColor.R, bodyColor.G, bodyColor.B);
    	demoMain.quad(
                xOffset, yPosition,
                xOffset + 100, yPosition,   //Punkg - 2
                xOffset + 100, yPosition + 300,   //Punkt - 3
                xOffset, yPosition + 300);


        //Wing color
    	demoMain.stroke(color, 50, 100);
    	demoMain.fill(wingColor.R, wingColor.G, wingColor.B);

        //Wing One
    	demoMain.quad(
                xOffset - 300 + x, yPosition - 100 + y,
                xOffset, yPosition,
                xOffset, yPosition + 300,
                xOffset - 300 + x, yPosition + 400 - y);


        //Wing Two
    	demoMain.quad(
                xOffset + 100, yPosition,
                xOffset + 400 - x, yPosition - 100 + x,
                xOffset + 400 - x, yPosition + 400 - x,
                xOffset + 100, yPosition + 300);

        //this.demoMain.DrawButterflyMethod(bodyColor, wingColor, xOffset, yPosition);
    }
}