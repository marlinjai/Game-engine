package projekt.ObjectLoader;

import projekt.VectorsAndMatrices.Vec2f;
import projekt.VectorsAndMatrices.Vec3f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public List<Vec3f> vertices;
    public List<Vec3f> normals;
    public List<Vec2f> textures;

    public List<Integer> indices;
    public List<Faces> facesList;

    public float[] vertexArray;
    public int[] indicesArray;
    public float[] normalsArray;
    public float[] textureArray;


    public static Model getModel(String s) {
        try {
            return new Model(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Model(String path) throws IOException {

        vertices = new ArrayList<Vec3f>();
        normals = new ArrayList<Vec3f>();
        textures = new ArrayList<Vec2f>();
        indices = new ArrayList<Integer>();
        facesList = new ArrayList<Faces>();
        new ModelLoader(this, path);
    }

    public void processModelData(List<Faces> faces,
                                 List<Vec2f> textures, List<Vec3f> normals
                                 ){
    for (int i = 0; i < faces.size();i++){
        for (int j = 0; j< 3; j++){
            if (j == 0) {
                int currentVertexPointer = (int) (faces.get(i).getVertexIndices().getX()-1);
                indices.add(currentVertexPointer);
                Vec2f currentTex = textures.get((int) (faces.get(i).getUvIndices().getX()-1));
                textureArray[currentVertexPointer*2] = currentTex.getX();
                textureArray[currentVertexPointer*2+1] = currentTex.getY();
                Vec3f currentNorm = normals.get((int) (faces.get(i).getNormalIndices().getX()-1));
                normalsArray[currentVertexPointer*3] = currentNorm.getX();
                normalsArray[currentVertexPointer*3+1] = currentNorm.getY();
                normalsArray[currentVertexPointer*3+2] = currentNorm.getZ();
                j++;
            }
            if (j == 1) {
                int currentVertexPointer = (int) (faces.get(i).getVertexIndices().getY()-1);
                indices.add(currentVertexPointer);
                Vec2f currentTex = textures.get((int) (faces.get(i).getUvIndices().getY()-1));
                textureArray[currentVertexPointer*2] = currentTex.getX();
                textureArray[currentVertexPointer*2+1] = currentTex.getY();
                Vec3f currentNorm = normals.get((int) (faces.get(i).getNormalIndices().getY()-1));
                normalsArray[currentVertexPointer*3] = currentNorm.getX();
                normalsArray[currentVertexPointer*3+1] = currentNorm.getY();
                normalsArray[currentVertexPointer*3+2] = currentNorm.getZ();
                j++;
            }
            if (j == 2) {
                int currentVertexPointer = (int) (faces.get(i).getVertexIndices().getZ()-1);
                indices.add(currentVertexPointer);
                Vec2f currentTex = textures.get((int) (faces.get(i).getUvIndices().getZ()-1));
                textureArray[currentVertexPointer*2] = currentTex.getX();
                textureArray[currentVertexPointer*2+1] = currentTex.getY();
                Vec3f currentNorm = normals.get((int) (faces.get(i).getNormalIndices().getZ()-1));
                normalsArray[currentVertexPointer*3] = currentNorm.getX();
                normalsArray[currentVertexPointer*3+1] = currentNorm.getY();
                normalsArray[currentVertexPointer*3+2] = currentNorm.getZ();
                j++;
            }
        }
    }
        this.vertexArray = new float[vertices.size()*3];
        this.indicesArray = new int[indices.size()];

        int pointer = 0;
        for (Vec3f vertex: vertices) {
            vertexArray[pointer++] = vertex.getX();
            vertexArray[pointer++] = vertex.getY();
            vertexArray[pointer++] = vertex.getZ();
        }

        for (int i = 0; i <indices.size(); i++) {
            indicesArray[i] = indices.get(i);
        }

    }


    public Vec2f[] listToVec2Arr(List<Vec2f> convert) {
        Vec2f[] ret = new Vec2f[convert.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = convert.get(i);
        }
        return ret;
    }
    public Vec3f[] listToVec3Arr(List<Vec3f> convert) {
        Vec3f[] ret = new Vec3f[convert.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = convert.get(i);
        }
        return ret;
    }
    public int[] ListTointArr(List<Integer> convert) {
        int[] ret = new int[convert.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = convert.get(i);
        }
        return ret;
    }
}