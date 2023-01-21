
package projekt.Geometry;

import projekt.Color.Color;
import projekt.VectorsAndMatrices.Matrix4;
import projekt.VectorsAndMatrices.Vec3f;
import projekt.VectorsAndMatrices.Vec4f;

import java.util.LinkedList;
import java.util.List;

public class Hexaedron {


    public float[] vertices = {
            1, 1, -1,       //A
            -1, 1, -1,      //B
            -1, -1, -1,     //C
            1, -1, -1,      //D


            1, 1, 1,        //E
            -1, 1, 1,       //F
            -1, -1, 1,      //G
            1, -1, 1        //H

    };

    private Vec3f[] verticesAsvec = {
            new Vec3f(1, 1, -1),
            new Vec3f(-1, 1, -1),
            new Vec3f(-1, -1, -1),
            new Vec3f(1, -1, -1),
            new Vec3f(1, 1, 1),
            new Vec3f(-1, 1, 1),
            new Vec3f(-1, -1, 1),
            new Vec3f(1, -1, 1)
    };
Color edit = new Color(new Vec4f(1,1,1,1));

    private Vec4f[] coloursAsVec = {
            edit.getBLUE(),
            edit.getGREEN(),
            edit.getCYAN(),
            edit.getMAGENTA(),

            edit.getRED(),
            edit.getYELLOW(),
            edit.getBLACK(),
            edit.getWHITE(),
    };


    private float[] colours = {
            0f, 0f, 1.0f,     // A
            1.0f, 1.0f, 1f,   // B
            0f, 1f, 1f,       // C
            1f, 0.2f, 0.5f,   // D

            1, 1, 1,          // E
            1f, 0f, 0,        // F
            1, 0, 0.5f,       // G
            0, 1, 0.5f        // H

    };

    public Vec3f[] getVerticesAsvec() {
        return verticesAsvec;
    }

    public Vec4f[] getColoursAsVec() {
        return coloursAsVec;
    }

    public int[] getIndices() {
        return indices;
    }

    private int[] indices = {

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

    public Hexaedron() {
    }


}

