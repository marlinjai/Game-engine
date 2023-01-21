package projekt.ProjectFiles;

import lenz.opengl.Texture;
import projekt.Geometry.Mesh;
import projekt.VectorsAndMatrices.Matrix4;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class VertexArrayObject {


    public Mesh mesh;
    public int loc;
    public Matrix4 modelMatrix;

    public Texture texture;


    public VertexArrayObject(Mesh mesh) {
        this.mesh = mesh;
        this.modelMatrix = mesh.getModelMatrix();
        loc = glGenVertexArrays();
        glBindVertexArray(this.loc);
        this.vbo();

    }

    public VertexArrayObject(Mesh mesh, Texture texture) {
        this.mesh = mesh;
        this.modelMatrix = mesh.getModelMatrix();
        this.texture = texture;
        glBindTexture(GL_TEXTURE_2D,texture.getId());
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
        loc = glGenVertexArrays();
        glBindVertexArray(this.loc);
        this.vbo();

    }


    public void setModelMatrix(Matrix4 modelMatrix) {
        this.modelMatrix = modelMatrix;
        this.mesh.setModelMatrix(modelMatrix);
    }

    public void vbo() {

        if(this.mesh.getType() == Mesh.type.MODEL) {

            int vboCoords = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboCoords);
            glBufferData(GL_ARRAY_BUFFER, this.mesh.getM().vertexArray, GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);

            int vboIndices = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboIndices);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, this.mesh.getM().indicesArray, GL_STATIC_DRAW);

            if(this.mesh.getM().textureArray != null) {
                int vboUV = glGenBuffers();
                glBindBuffer(GL_ARRAY_BUFFER,vboUV);
                glBufferData(GL_ARRAY_BUFFER, this.mesh.getM().textureArray, GL_STATIC_DRAW);
                glVertexAttribPointer(3,2,GL_FLOAT,false,0,0);
                glEnableVertexAttribArray(3);
            }
            int vboNormals = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboNormals);
            glBufferData(GL_ARRAY_BUFFER, this.mesh.getM().normalsArray, GL_STATIC_DRAW);
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(2);

        }
        else {
            int vboCoords = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboCoords);
            glBufferData(GL_ARRAY_BUFFER, this.mesh.vec3fArrToFloatArr(this.mesh.getVertices()), GL_STATIC_DRAW);
            glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(0);


            int vboIndices = glGenBuffers();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboIndices);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, this.mesh.getIndices(), GL_STATIC_DRAW);


            if (this.mesh.getUvCoords() != null) {
                int vboUV = glGenBuffers();
                glBindBuffer(GL_ARRAY_BUFFER, vboUV);
                glBufferData(GL_ARRAY_BUFFER, this.mesh.vec2fArrToFloatArr(this.mesh.getUvCoords()), GL_STATIC_DRAW);
                glVertexAttribPointer(3, 2, GL_FLOAT, false, 0, 0);
                glEnableVertexAttribArray(3);
            }
            int vboNormals = glGenBuffers();
            glBindBuffer(GL_ARRAY_BUFFER, vboNormals);
            glBufferData(GL_ARRAY_BUFFER, this.mesh.vec3fArrToFloatArr(this.mesh.getNormals()), GL_STATIC_DRAW);
            glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);
            glEnableVertexAttribArray(2);

        }
        int vboColors = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColors);
        glBufferData(GL_ARRAY_BUFFER, this.mesh.vec4fArrToFloatArr(this.mesh.getColours()), GL_STATIC_DRAW);
        glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);





    }

}
