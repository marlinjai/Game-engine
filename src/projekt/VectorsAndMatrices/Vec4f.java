package projekt.VectorsAndMatrices;

public class Vec4f {
    private float r;
    private float g;
    private float b;
    private float a;

    public Vec4f(float r, float g, float b, float a) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Vec4f(Vec3f a, float b) {
        this.r = a.getX();
        this.g = a.getY();
        this.b = a.getZ();
        this.a = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float length() {
        float ret;
        ret = (float) Math.sqrt(this.a + this.r + this.g + this.b);
        return ret;
    }

    ;

    public float length(Vec4f calcLength) {
        float ret;
        ret = (float) Math.sqrt(calcLength.getA() + calcLength.getR() + calcLength.getG() + calcLength.getB());
        return ret;
    }

    ;


}
