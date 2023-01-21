package projekt.VectorsAndMatrices;


import java.util.Objects;

public class Vec2f {
    private float x;
    private float y;


    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public Vec2f() {
        this.x = 0;
        this.y = 0;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    public Vec2f add(Vec2f second) {
        Vec2f ret = new Vec2f();
        ret.setX(this.getX() + second.getX());
        ret.setY(this.getY() + second.getY());

        return ret;
    }

    public Vec2f substract(Vec2f second) {
        Vec2f ret = new Vec2f();
        ret.setX(this.getX() - second.getX());
        ret.setY(this.getY() - second.getY());

        return ret;
    }


    public float length() {
        float ret;
        ret = (float) Math.sqrt((Math.pow(this.x,2) + Math.pow(this.y,2)));
        return ret;
    }

    public float length(Vec2f calcLength) {
        float ret;
        ret = (float) Math.sqrt((double) (calcLength.x + calcLength.y));
        return ret;
    }

    public Vec2f normalize() {
        float l = this.length();
        Vec2f ret = this.scalarMultiply((1.0f / l));
        ;

        return ret;
    }

    public Vec2f scalarMultiply(float second) {
        Vec2f ret = new Vec2f();
        ret.setX(second * this.getX());
        ret.setY(second * this.getY());
        return ret;
    }


    public float dot(Vec2f second) {
        float ret;
        ret = this.getX() * second.getX() + this.getY() * second.getY();
        return ret;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec2f vec2f)) return false;
        return Float.compare(vec2f.getX(), getX()) == 0 && Float.compare(vec2f.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    ;


}
