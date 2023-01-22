import projekt.VectorsAndMatrices.Matrix4;

public class tmp {


    /*public Matrix4 getCameraMatrix() {
        Matrix4 cameraTransformationMatrix = new Matrix4();
        Matrix4 changeOfWorldToCamera = new Matrix4(this.getU().getX(), this.getU().getY(), this.getU().getZ(), 0, this.getvUp().getX(), this.getvUp().getY(), this.getvUp().getZ(), 0, this.getnLookAt().getX(), this.getnLookAt().getY(), this.getnLookAt().getZ(), 0, 0, 0, 0, 1);
        Matrix4 cameraTranslation = new Matrix4(1, 0, 0, -this.getCameraPosv3().getX(),0,1,0,-this.getCameraPosv3().getY(), 0,0,1,-this.getCameraPosv3().getZ(),0,0,0,1);
        cameraTransformationMatrix = changeOfWorldToCamera.multiply(cameraTranslation);
        cameraTransformationMatrix.printMatrix();
        return cameraTransformationMatrix;


    public Matrix4 initCameraTransform() {
        Vec3f n = this.nLookAt.normalize();
        Vec3f u = (this.vUp.cross(n)).normalize();
        Vec3f vUP = n.cross(u).normalize();
        Matrix4 ret = new Matrix4(u.getX(),vUP.getX(),n.getX(),0.0f,u.getY(),vUP.getY(),n.getY(),0.0f,u.getZ(),vUP.getZ(),n.getZ(),0.0f,-this.cameraPosv3.getX(),-this.cameraPosv3.getY(), -this.cameraPosv3.getZ(),1.0f);
        ret.printMatrix();
        return ret;
    }


    }*/


}
