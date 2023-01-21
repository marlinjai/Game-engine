package projekt.Color;

import projekt.VectorsAndMatrices.Vec4f;

public class Color {
    private Vec4f color = new Vec4f(0,0,0,1);
    private  final Vec4f WHITE = new Vec4f(1,1,1,1);
    private  final Vec4f BLACK = new Vec4f(0,0,0,1);
    private  final Vec4f RED = new Vec4f(1,0,0,1);
    private  final Vec4f GREEN = new Vec4f(0,1,0,1);
    private  final Vec4f BLUE = new Vec4f(0,0,1,1);
    private  final Vec4f YELLOW = new Vec4f(1,1,0,1);
    private  final Vec4f CYAN = new Vec4f(0,1,1,1);
    private  final Vec4f MAGENTA = new Vec4f(1,0,1,1);

    public Vec4f getWHITE() {
        return WHITE;
    }

    public Vec4f getBLACK() {
        return BLACK;
    }

    public Vec4f getRED() {
        return RED;
    }

    public Vec4f getGREEN() {
        return GREEN;
    }

    public Vec4f getBLUE() {
        return BLUE;
    }

    public Vec4f getYELLOW() {
        return YELLOW;
    }

    public Vec4f getCYAN() {
        return CYAN;
    }

    public Vec4f getMAGENTA() {
        return MAGENTA;
    }

    public Vec4f getColor() {
        return color;
    }

    public void setColor(Vec4f color) {
        this.color = color;
    }

    public Color(Vec4f color) {
        this.color = color;
    }






}
