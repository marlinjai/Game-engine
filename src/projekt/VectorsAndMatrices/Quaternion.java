package projekt.VectorsAndMatrices;

import static java.lang.Math.acos;
import static java.lang.Math.atan2;

public class Quaternion {
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    private float x, y, z, w;

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Quaternion(float angle, Vec3f v) {
        float halfAngleInRadians = (float) Math.toRadians(angle / 2.0);

        float sineHalfAngle = (float) Math.sin(halfAngleInRadians);
        float cosHalfAngle = (float) Math.cos(halfAngleInRadians);

        this.x = v.getX() * sineHalfAngle;
        this.y = v.getY() * sineHalfAngle;
        this.z = v.getZ() * sineHalfAngle;
        this.w = cosHalfAngle;
    }

    public void quaternionNormalize() {
        float len = (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2) + Math.pow(this.w, 2));

        this.x /= len;
        this.y /= len;
        this.z /= len;
        this.w /= len;
    }

    public Quaternion conjugate() {
        Quaternion ret = new Quaternion(-1 * this.x, -1 * this.y, -1 * this.z,  this.w);
        return ret;
    }

    public Quaternion multiply(Quaternion q, Vec3f v) {
        float w = -(q.x * v.getX()) - (q.y * v.getY()) - (q.z * v.getZ());
        float x = (q.w * v.getX()) + (q.y * v.getZ()) - (q.z * v.getY());
        float y = (q.w * v.getY()) + (q.z * v.getX()) - (q.x * v.getZ());
        float z = (q.w * v.getZ()) + (q.x * v.getY()) - (q.y * v.getX());

        Quaternion ret = new Quaternion(x, y, z, w);
        return ret;
    }

    public Quaternion multiply(Quaternion l, Quaternion r) {

        float w = (l.w * r.w) - (l.x * r.x) - (l.y * r.y) - (l.z * r.z);
        float x = (l.x * r.w) + (l.w * r.x) + (l.y * r.z) - (l.z * r.y);
        float y = (l.y * r.w) + (l.w * r.y) + (l.z * r.x) - (l.x * r.z);
        float z = (l.z * r.w) + (l.w * r.z) + (l.x * r.y) - (l.y * r.x);

        Quaternion ret = new Quaternion(x, y, z, w);
        return ret;
    }

    public Vec3f toDegrees() {
        float a = (float) Math.toDegrees(atan2(this.x * this.z + this.y * this.w, this.x * this.w - this.y * this.z));
        float b = (float) Math.toDegrees(acos(-1 * this.x * this.x - this.y * this.y - this.z * this.z - this.w * this.w));
        float c = (float)  Math.toDegrees(atan2(this.x * this.z - this.y * this.w, this.x * this.w + this.y * this.z));

        Vec3f ret = new Vec3f(a,b,c);
        return ret;
    }


}
