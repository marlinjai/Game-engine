
package projekt.Geometry;

import projekt.VectorsAndMatrices.Matrix4;

public class BasicGeometry {

    private float[] vertexCoords;

    private int[] indices;

    private float[] color;

    private Matrix4 modelMatrix;


    public float[] getVertexCoords() {
        return vertexCoords;
    }

    public int[] getIndices() {
        return indices;
    }

    public float[] getColor() {
        return color;
    }

    public BasicGeometry(float[] vertexCoords, int[] indices, float[] color, Matrix4 modelMatrix) {
        this.vertexCoords = vertexCoords;
        this.indices = indices;
        this.color = color;
        this.modelMatrix = modelMatrix;
    }

}

