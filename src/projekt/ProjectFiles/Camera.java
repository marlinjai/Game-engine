package projekt.ProjectFiles;

import projekt.VectorsAndMatrices.Matrix4;
import projekt.VectorsAndMatrices.Vec2f;
import projekt.VectorsAndMatrices.Vec3f;


public class Camera {

    public int MARGIN = 10;
    public float edgeStep = 1f;

    public Vec3f cameraPosv3 = new Vec3f(0f, 0f, 0f);
    private Vec3f u = new Vec3f(1.0f, 0.0f, 0.0f);
    private Vec3f vUp = new Vec3f(0.0f, 1.0f, 0.0f);
    private Vec3f nLookAt = new Vec3f(0.0f, 0.0f, 1.0f);
    private float cameraSpeed = clamp(0, 0.05f, 5);

    private int windowHeight;
    private int windowWidth;

    private float angleH;
    private float angleV;

    private boolean onUpperEdge;
    private boolean onLowerEdge;
    private boolean onLeftEdge;
    private boolean onRightEdge;

    private Matrix4 view;

    public void setMousePos(Vec2f mousePos) {
        this.mousePos = mousePos;
    }

    private Vec2f mousePos = new Vec2f();

    public float getCameraSpeed() {
        return cameraSpeed;
    }

    public void setCameraSpeed(float cameraSpeed) {
        this.cameraSpeed = cameraSpeed;
    }


    public Vec3f getU() {
        return u;
    }

    public void setU(Vec3f u) {
        this.u = u;
    }

    public Vec3f getvUp() {
        return vUp;
    }

    public void setvUp(Vec3f vUp) {
        this.vUp = vUp;
    }

    public Vec3f getnLookAt() {
        return nLookAt;
    }

    public void setnLookAt(Vec3f nLookAt) {
        this.nLookAt = nLookAt;
    }

    public Vec3f getCameraPosv3() {
        return cameraPosv3;
    }

    public void setCameraPosv3(Vec3f cameraPosv3) {
        this.cameraPosv3 = cameraPosv3;
    }


    public Camera() {
    }


    public Camera(Vec3f cameraPosv3, Vec3f vUp, Vec3f nLookAt, int windowHeight, int windowWidth) {
        this.cameraPosv3 = cameraPosv3;
        this.vUp = vUp.normalize();
        this.nLookAt = nLookAt.normalize();
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.init();
    }

    private void init() {
        Vec3f hTarget = new Vec3f(this.nLookAt.getX(), 0, this.nLookAt.getZ()).normalize();

        float angle = (float) Math.toDegrees(Math.asin(Math.abs(hTarget.getZ())));

        if (hTarget.getZ() >= 0.0f) {
            if (hTarget.getX() >= 0.0f) {
                this.angleH = 360.0f - angle;
            } else {
                this.angleH = 180.0f + angle;
            }
        } else {
            if (hTarget.getX() >= 0.0f) {
                this.angleH = angle;
            } else {
                this.angleH = 180.0f - angle;
            }
        }

        this.angleV = (float) -Math.toDegrees(Math.asin(this.nLookAt.getY()));

        onUpperEdge = false;
        onLowerEdge = false;
        onLeftEdge = false;
        onRightEdge = false;
        mousePos.setX(this.windowWidth / 2);
        mousePos.setY(this.windowHeight / 2);

    }

    public void onMouse(double x, double y) {
        double DeltaX = x - mousePos.getX();
        double DeltaY = y - mousePos.getY();

        mousePos.setX((float) x);
        mousePos.setY((float) y);

        angleH += (float) DeltaX / 20.0f;
        angleV += (float) DeltaY / 50.0f;

        if (DeltaX == 0) {
            if (x <= MARGIN) {
                onLeftEdge = true;
            } else if (x >= (windowWidth - MARGIN)) {
                onRightEdge = true;
            }
        } else {
            onLeftEdge = false;
            onRightEdge = false;
        }

        if (DeltaY == 0) {
            if (y <= MARGIN) {
                onUpperEdge = true;
            } else if (y >= (windowHeight - MARGIN)) {
                onLowerEdge = true;
            }
        } else {
            onUpperEdge = false;
            onLowerEdge = false;
        }

        this.update();
    }

    public void onRender() {

        boolean shouldUpdate = false;

        if (onLeftEdge) {
            angleH -= edgeStep;
            shouldUpdate = true;
        } else if (onRightEdge) {
            angleH += edgeStep;
            shouldUpdate = true;
        }

        if (onUpperEdge) {
            if (angleV > -90.0f) {
                angleV -= edgeStep;
                shouldUpdate = true;
            }
        } else if (onLowerEdge) {
            if (angleV < 90.0f) {
                angleV += edgeStep;
                shouldUpdate = true;
            }
        }

        if (shouldUpdate) {
            this.update();
        }
    }

    public void update() {
        Vec3f Yaxis = new Vec3f(0.0f, 1.0f, 0.0f);

        // Rotate the view vector by the horizontal angle around the vertical axis
        Vec3f View = new Vec3f(1.0f, 0.0f, 0.0f);
        View.Rotate(angleH, Yaxis);
        View.normalize();

        // Rotate the view vector by the vertical angle around the horizontal axis
        Vec3f U = Yaxis.cross(View);
        U.normalize();
        View.Rotate(angleV, U);

        this.nLookAt = View.normalize();
        this.vUp = this.nLookAt.cross(U).normalize();
    }

    public Matrix4 getView() {
        return view;
    }

    public void setView(Matrix4 view) {
        this.view = view;
    }

    public Matrix4 getCameraMatrix() {
        Vec3f n = this.nLookAt.normalize();
        Vec3f u = (this.vUp.cross(n)).normalize();
        Vec3f vUP = n.cross(u).normalize();
        Matrix4 changeOfWorldToCamera = new Matrix4(u.getX(),vUP.getX(),n.getX(),0.0f,u.getY(),vUP.getY(),n.getY(),0.0f,u.getZ(),vUP.getZ(),n.getZ(),0.0f,0,0, 0,1.0f);
        Matrix4 cameraTransformationMatrix = new Matrix4();
        Matrix4 cameraTranslation = new Matrix4(1, 0, 0, -this.getCameraPosv3().getX(),0,1,0,-this.getCameraPosv3().getY(), 0,0,1,-this.getCameraPosv3().getZ(),0,0,0,1);
        cameraTransformationMatrix = cameraTranslation.multiply(changeOfWorldToCamera);
        cameraTransformationMatrix.printMatrix();
        return cameraTransformationMatrix;
    }

    public Matrix4 getCMatrix() {
        Matrix4 ret = new Matrix4();
        ret = ret.initCameraTransform(this.cameraPosv3,this.nLookAt,this.vUp);
        return ret;
    };




    public static float clamp(float min, float value, float max) {
        return Math.max(min, Math.min(max, value));
    }


}