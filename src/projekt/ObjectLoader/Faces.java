package projekt.ObjectLoader;

import projekt.VectorsAndMatrices.Vec3f;

public class Faces {

    private Vec3f vertexIndices;
    private Vec3f uvIndices;
    private Vec3f normalIndices;


    public Faces(Vec3f v, Vec3f uv, Vec3f vn) {
        this.vertexIndices = v;
        this.uvIndices =uv;
        this.normalIndices = vn;
    }

    public Vec3f getVertexIndices() {
        return vertexIndices;
    }

    public void setVertexIndices(Vec3f vertexIndices) {
        this.vertexIndices = vertexIndices;
    }

    public Vec3f getUvIndices() {
        return uvIndices;
    }

    public void setUvIndices(Vec3f uvIndices) {
        this.uvIndices = uvIndices;
    }

    public Vec3f getNormalIndices() {
        return normalIndices;
    }

    public void setNormalIndices(Vec3f normalIndices) {
        this.normalIndices = normalIndices;
    }
}
