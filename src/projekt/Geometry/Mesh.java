package projekt.Geometry;


import projekt.optimizedLoader.ModelData;
import projekt.optimizedLoader.OBJFileLoader;
import projekt.Color.Color;
import projekt.ObjectLoaderFirstTry.Model;
import projekt.VectorsAndMatrices.Matrix4;
import projekt.VectorsAndMatrices.Vec2f;
import projekt.VectorsAndMatrices.Vec3f;
import projekt.VectorsAndMatrices.Vec4f;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Mesh {

    public enum type {
        HEXAHEDRON, OCTAHEDRON, DODECAHEDRON, ICOSAHEDRON, TETRAHEDRON, MODEL, MODELII
    }
    private Model m;

    private ModelData md;
    private type type;
    private Matrix4 modelMatrix;
    private Vec4f[] colours;
    private Vec3f[] vertices;
    private Vec3f[] normals;
    private int[] indices;

    private Vec2f[] UvCoords;

    private Color meshColour = new Color(new Vec4f(0, 0, 0, 1));

    public Mesh(type type, Matrix4 modelMatrix) {
        this.setModelMatrix(modelMatrix);
        this.type = type;
        switch (type) {

            case HEXAHEDRON -> {
                Vec3f[] vertices = {
                        new Vec3f(1, 1, -1),    // 0
                        new Vec3f(-1, 1, -1),   // 1
                        new Vec3f(-1, -1, -1),  // 2
                        new Vec3f(1, -1, -1),   // 3
                        new Vec3f(1, 1, 1),     // 4
                        new Vec3f(-1, 1, 1),    // 5
                        new Vec3f(-1, -1, 1),   // 6
                        new Vec3f(1, -1, 1)     // 7
                };
                this.setVertices(vertices);

                Vec4f[] colours = {
                        this.getMeshColour().getWHITE(),
                        this.getMeshColour().getWHITE(),
                        this.getMeshColour().getWHITE(),
                        this.getMeshColour().getWHITE(),

                        this.getMeshColour().getWHITE(),
                        this.getMeshColour().getWHITE(),
                        this.getMeshColour().getWHITE(),
                        this.getMeshColour().getWHITE(),
                };
                this.setColours(colours);
                int[] indices = {

                        4, 5, 7,
                        5, 6, 7,
                        4, 7, 0,
                        7, 3, 0,
                        1, 6, 5,
                        1, 2, 6,
                        2, 1, 0,
                        0, 3, 2,
                        3, 6, 2,
                        6, 3, 7,
                        0, 1, 5,
                        0, 5, 4

                };
                this.setIndices(indices);
                this.normals = this.calcNormalsVec();

                Vec2f[] uvs = {

                        new Vec2f(0f, 0f),
                        new Vec2f(1f, 0f),
                        new Vec2f(1f, 1f),
                        new Vec2f(0f, 1f),

                        new Vec2f(1f, 0f),
                        new Vec2f(0f, 0f),
                        new Vec2f(0f, 1f),
                        new Vec2f(1f, 1f),

                };
                this.setUvCoords(uvs);

            }
            case TETRAHEDRON -> {
                OBJFileLoader loader = new OBJFileLoader();
                ModelData mod = loader.loadOBJ("/Users/marlinjai/IdeaProjects/Computergrafik/src/res/Objects3D/Thetraedron.obj");
                this.md = mod;
                Vec4f[]modelColor = new Vec4f[mod.getVertices().length];
                Arrays.fill(modelColor,this.getMeshColour().getGREEN());
                this.setColours(modelColor);
            }
            case OCTAHEDRON -> {
                OBJFileLoader loader = new OBJFileLoader();
                ModelData mod = loader.loadOBJ("/Users/marlinjai/IdeaProjects/Computergrafik/src/res/Objects3D/Octahedron.obj");
                this.md = mod;
                Vec4f[]modelColor = new Vec4f[mod.getVertices().length];
                Arrays.fill(modelColor,this.getMeshColour().getGREEN());
                this.setColours(modelColor);
            }
            case DODECAHEDRON -> {
                OBJFileLoader loader = new OBJFileLoader();
                ModelData mod = loader.loadOBJ("/Users/marlinjai/IdeaProjects/Computergrafik/src/res/Objects3D/Dodecahedron.obj");
                this.md = mod;
                Vec4f[]modelColor = new Vec4f[mod.getVertices().length];
                Arrays.fill(modelColor,this.getMeshColour().getGREEN());
                this.setColours(modelColor);

            }
            case ICOSAHEDRON -> {
                OBJFileLoader loader = new OBJFileLoader();
                ModelData mod = loader.loadOBJ("/Users/marlinjai/IdeaProjects/Computergrafik/src/res/Objects3D/Icosahedron.obj");
                this.md = mod;
                Vec4f[]modelColor = new Vec4f[mod.getVertices().length];
                Arrays.fill(modelColor,this.getMeshColour().getGREEN());
                this.setColours(modelColor);
            }
        }
    }

    public Mesh(type type, String path, Matrix4 modelMatrix) {
        this.setModelMatrix(modelMatrix);
        this.type = type;

               /*  Model mod = new Model(path);
                  mod.processModelData(mod.facesList,mod.textures, mod.normals);
                  this.m = mod;
                  Vec4f[]modelColor = new Vec4f[mod.vertices.size()];
                  Arrays.fill(modelColor,this.getMeshColour().getWHITE());
                  this.setColours(modelColor);*/ // Old model loader

        OBJFileLoader loader = new OBJFileLoader();
        ModelData mod = loader.loadOBJ(path);
        this.md = mod;
        Vec4f[]modelColor = new Vec4f[mod.getVertices().length];
        Arrays.fill(modelColor,this.getMeshColour().getWHITE());
        this.setColours(modelColor);
    }

    public ModelData getMd() {
        return md;
    }



   /* public float[] calcNormals() {
        float[] normals = new float[this.vertices.length];
        Vec3f normale;
        Set<Vec3f> facesNormals = new HashSet<>();
        for (int i = 0; i < this.vertices.length; i += 3) {
            int indexVertices = i / 3;
            for (int j = 0; j < this.indices.length; j += 3) {
                if (this.indices[j] == indexVertices || this.indices[j + 1] == indexVertices || this.indices[j + 2] == indexVertices) {


                    float x1 = this.vertices[this.indices[j] * 3];
                    float y1 = this.vertices[this.indices[j] * 3 + 1];
                    float z1 = this.vertices[this.indices[j] * 3 + 2];

                    float x2 = this.vertices[this.indices[j + 1] * 3];
                    float y2 = this.vertices[this.indices[j + 1] * 3 + 1];
                    float z2 = this.vertices[this.indices[j + 1] * 3 + 2];

                    float x3 = this.vertices[this.indices[j + 2] * 3];
                    float y3 = this.vertices[this.indices[j + 2] * 3 + 1];
                    float z3 = this.vertices[this.indices[j + 2] * 3 + 2];

                    Vec3f a = new Vec3f((x2 - x1), (y2 - y1), (z2 - z1));
                    Vec3f b = new Vec3f((x3 - x1), (y3 - y1), (z3 - z1));
                    //Vec3f c = new Vec3f((x2 - x3), (y2 - y3), (z2 - z3));

                    normale = a.cross(b);

                    facesNormals.add(normale.normalize());

                }
            }
            Vec3f sum = new Vec3f();
            for (Vec3f toAdd : facesNormals) {
                sum = sum.add(toAdd);
            }
            sum = sum.normalize();
            normals[i] = sum.getX();
            normals[i + 1] = sum.getY();
            normals[i + 2] = sum.getZ();
            facesNormals.removeAll(facesNormals);
        }
        return normals;
    }*/ //Old CalcNormals method

    public Vec3f[] calcNormalsVec() {
        Vec3f[] normals = new Vec3f[this.vertices.length];
        Vec3f normale;
        Set<Vec3f> facesNormals = new HashSet<>();
        for (int i = 0; i < this.vertices.length; i++) {
            for (int j = 0; j < this.indices.length; j += 3) {
                if (this.indices[j] == i || this.indices[j + 1] == i || this.indices[j + 2] == i) {

                    Vec3f a = this.vertices[this.indices[j]];
                    Vec3f b = this.vertices[this.indices[j + 1]];
                    Vec3f c = this.vertices[this.indices[j + 2]];


                    normale = (b.substract(a)).cross(c.substract(b));

                    facesNormals.add(normale.normalize());

                }
            }
            Vec3f sum = new Vec3f();
            for (Vec3f toAdd : facesNormals) {
                sum = sum.add(toAdd);
            }
            sum = sum.normalize();
            normals[i] = sum;
            facesNormals.removeAll(facesNormals);
        }
        return normals;
    }

    public Mesh.type getType() {
        return type;
    }

    public void setType(Mesh.type type) {
        this.type = type;
    }

    public Matrix4 getModelMatrix() {
        return modelMatrix;
    }

    public void setModelMatrix(Matrix4 modelMatrix) {
        this.modelMatrix = modelMatrix;
    }

    public Vec4f[] getColours() {
        return colours;
    }

    public void setColours(Vec4f[] colours) {
        this.colours = colours;
    }

    public Vec3f[] getVertices() {
        return vertices;
    }

    public void setVertices(Vec3f[] vertices) {
        this.vertices = vertices;
    }

    public Vec3f[] getNormals() {
        return normals;
    }

    public void setNormals(Vec3f[] normals) {
        this.normals = normals;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public Color getMeshColour() {
        return meshColour;
    }

    public void setMeshColour(Color meshColour) {
        this.meshColour = meshColour;
    }

    public Vec2f[] getUvCoords() {
        return UvCoords;
    }

    public void setUvCoords(Vec2f[] uvCoords) {
        UvCoords = uvCoords;
    }

    public float[] vec3ListToFloatArr(List<Vec3f> toConvert) {
        float[] ret = new float[toConvert.size() * 3];
        for (int i = 0; i < ret.length; i += 3) {
            ret[i] = toConvert.get(i / 3).getX();
            ret[i + 1] = toConvert.get(i / 3).getY();
            ret[i + 2] = toConvert.get(i / 3).getZ();
        }
        return ret;
    }

    public float[] vec2ListToFloatArr(List<Vec2f> toConvert) {
        float[] ret = new float[toConvert.size() * 2];
        for (int i = 0; i < ret.length; i += 2) {
            ret[i] = toConvert.get(i / 3).getX();
            ret[i + 1] = toConvert.get(i / 3).getY();

        }
        return ret;
    }

    public float[] vec2fArrToFloatArr(Vec2f[] arr) {
        float[] ret = new float[arr.length * 2];
        for (int i = 0; i < ret.length; i += 2) {
            ret[i] = arr[i / 2].getX();
            ret[i + 1] = arr[i / 2].getY();
        }
        return ret;
    }


    public float[] vec3fArrToFloatArr(Vec3f[] arr) {
        float[] ret = new float[arr.length * 3];
        for (int i = 0; i < ret.length; i += 3) {
            ret[i] = arr[i / 3].getX();
            ret[i + 1] = arr[i / 3].getY();
            ret[i + 2] = arr[i / 3].getZ();
        }
        return ret;
    }

    public Model getM() {
        return m;
    }

    public float[] vec4fArrToFloatArr(Vec4f[] arr) {
        float[] ret = new float[arr.length * 4];
        for (int i = 0; i < ret.length; i += 4) {
            ret[i] = arr[i / 4].getR();
            ret[i + 1] = arr[i / 4].getG();
            ret[i + 2] = arr[i / 4].getB();
            ret[i + 3] = arr[i / 4].getA();
        }
        return ret;
    }
}
