package projekt.VectorsAndMatrices;

import java.util.Objects;

public class Vec3f {
    private float x;
    private float y;
    private float z;

    public Vec3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3f() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Vec3f add(Vec3f second) {
        Vec3f ret = new Vec3f();
        ret.setX(this.getX() + second.getX());
        ret.setY(this.getY() + second.getY());
        ret.setZ(this.getZ() + second.getZ());
        return ret;
    }

    public Vec3f substract(Vec3f second) {
        Vec3f ret = new Vec3f();
        ret.setX(this.getX() - second.getX());
        ret.setY(this.getY() - second.getY());
        ret.setZ(this.getZ() - second.getZ());
        return ret;
    }

    ;

    public float length() {
        float ret;
        ret = (float) Math.sqrt((Math.pow(this.x,2) + Math.pow(this.y,2) + Math.pow(this.z,2)));
        return ret;
    }

    public float length(Vec3f calcLength) {
        float ret;
        ret = (float) Math.sqrt((double) (calcLength.x + calcLength.y + calcLength.z));
        return ret;
    }

    public Vec3f normalize() {
        float l = this.length();
        Vec3f ret = this.scalarMultiply((1.0f / l));
        ;

        return ret;
    }

    public Vec3f scalarMultiply(float second) {
        Vec3f ret = new Vec3f();
        ret.setX(second * this.getX());
        ret.setY(second * this.getY());
        ret.setZ(second * this.getZ());
        return ret;
    }

    ;



    public float dot(Vec3f second) {
        float ret;
        ret = this.getX() * second.getX() + this.getY() * second.getY() + this.getZ() * second.getZ();
        return ret;
    }

    ;

    public Vec3f cross(Vec3f second) {
        Vec3f ret = new Vec3f();
        ret.setX((this.getY() * second.getZ()) - (this.getZ() * second.getY()));
        ret.setY((this.getZ() * second.getX()) - (this.getX() * second.getZ()));
        ret.setZ((this.getX() * second.getY()) - (this.getY() * second.getX()));
        if (ret.x == -0) { ret.setX(0);}
        if (ret.y == -0) { ret.setY(0);}
        if (ret.z == -0) { ret.setZ(0);}

        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec3f vec3f)) return false;
        return Float.compare(vec3f.getX(), getX()) == 0 && Float.compare(vec3f.getY(), getY()) == 0 && Float.compare(vec3f.getZ(), getZ()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    ;


}
