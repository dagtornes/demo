package no.demo;

public class Color {
    public int R, G, B;

    public static Color random(int Min, int Max) {
    	int random1 = Min + (int)(Math.random() * ((Max - Min) + 1));
        int random2 = Min + (int)(Math.random() * ((Max - Min) + 1));
        int random3 = Min + (int)(Math.random() * ((Max - Min) + 1));
        
        return new Color(random1,random2,random3);
    }
    
    public Color(int r, int g, int b) {
        this.R = r;
        this.G = g;
        this.B = b;
    }
}