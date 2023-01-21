package projekt.VectorsAndMatrices;

import java.util.Objects;

public class Vec3fNormFace extends Vec3f {
    private float sizeOfTriangle;

    public Vec3fNormFace(Vec3f normalize) {
        this.setX(normalize.getX());
        this.setY(normalize.getY());
        this.setZ(normalize.getZ());
        this.setSizeOfTriangle(0);
    }

    public Vec3fNormFace() {

    }


    public float getSizeOfTriangle() {
        return sizeOfTriangle;
    }

    public void setSizeOfTriangle(float sizeOfTriangle) {
        this.sizeOfTriangle = sizeOfTriangle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec3fNormFace that)) return false;
        if (!super.equals(o)) return false;
        return Float.compare(that.getSizeOfTriangle(), getSizeOfTriangle()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSizeOfTriangle());
    }

}
