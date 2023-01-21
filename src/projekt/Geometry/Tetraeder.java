/*
package projekt.Geometry;

import projekt.VectorsAndMatrices.Matrix4;

public class Tetraeder extends BasicGeometry {
    public static final float Hd = (float) (Math.sqrt(3.0) / 2.0);
    public static final float Hp = (float) Math.sqrt(2.0 / 3.0);

    private float[] vertexCoords;

    private int[] indices;

    private float[] color;

    private Matrix4 modelMatrix = new Matrix4();

    private static final float[] tetraeder = {
            0F, 0F, Hd,
            0.5F, 0F, 0F,
            -0.5F, 0F, 0F,
            0F, Hp, (float) ((1.0 / 3.0) * Hd)
    };

    private static final int[] indicesTetraeder = {
            0, 2, 1,
            0, 1, 3,
            0, 3, 2,
            1, 2, 3,
    };

    public void setModelMatrix(Matrix4 modelMatrix) {
        this.modelMatrix = modelMatrix;
    }

    public Matrix4 getModelMatrix() {
        return modelMatrix;
    }

    private static float[] colorTetraeder = {
            1.0f, 0f, 1.0f,
            1.0f, 1.0f, 0f,
            0f, 1f, 0f,
            1f, 1f, 1f,


    };


    public Tetraeder() {
        super(tetraeder, indicesTetraeder, colorTetraeder, new Matrix4());

    }


}
*/
