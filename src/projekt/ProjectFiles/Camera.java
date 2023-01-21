package projekt.ProjectFiles;

import projekt.VectorsAndMatrices.Matrix4;
import projekt.VectorsAndMatrices.Vec3f;

public class Camera {

    public Vec3f cameraPosv3 = new Vec3f(0f, 0f, 0f);
    private Vec3f uv3 = new Vec3f(1.0f, 0.0f, 0.0f);
    private Vec3f vv3 = new Vec3f(0.0f, 1.0f, 0.0f);
    private Vec3f nv3 = new Vec3f(0.0f, 0.0f, 1.0f);

    private float cameraSpeed = 0.05f;

    public float getCameraSpeed() {
        return cameraSpeed;
    }

    public void setCameraSpeed(float cameraSpeed) {
        this.cameraSpeed = cameraSpeed;
    }




    public Vec3f getUv3() {
        return uv3;
    }

    public void setUv3(Vec3f uv3) {
        this.uv3 = uv3;
    }

    public Vec3f getVv3() {
        return vv3;
    }

    public void setVv3(Vec3f vv3) {
        this.vv3 = vv3;
    }

    public Vec3f getNv3() {
        return nv3;
    }

    public void setNv3(Vec3f nv3) {
        this.nv3 = nv3;
    }


    public Matrix4 getCameraMatrix() {
        Matrix4 cameraTransformationMatrix = new Matrix4();
        Matrix4 ret = cameraTransformationMatrix.initCameraTransform(this.cameraPosv3,this.nv3,this.vv3);
        return ret;
    }

    public Camera() {
    }


}