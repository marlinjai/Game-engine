package projekt.ProjectFiles;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;


import static org.lwjgl.glfw.GLFW.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;


import lenz.opengl.Texture;
import projekt.Geometry.Mesh;
import projekt.VectorsAndMatrices.Matrix4;
import projekt.VectorsAndMatrices.Vec3f;


import java.util.*;


public class Projekt extends AbstractOpenGLBase {

    private ShaderProgram shaderProgram;
    private float rotationAngle = 0;
    public Camera myCamera1 = new Camera();


    private List<VertexArrayObject> VaoListe = new LinkedList<>();
    private int[] matrixLocations = new int[4];
    Matrix4 projection;
    Matrix4 view;


    public static void main(String[] args) {


        new Projekt().start("CG Projekt Marlin Jai"/*, width, height*/);
        /*MyFrame x = new MyFrame();
        float red = x.color1.getRed() / 255.0F;
        float green = x.color1.getRed() / 255.0F;
        float blue = x.color1.getRed() / 255.0F;*/
    }


    @Override
    protected void init() {
        shaderProgram = new ShaderProgram("projekt");
        glUseProgram(shaderProgram.getId());

        Texture textureI = new Texture("texture1.png");
        Texture textureII = new Texture("texture2.jpeg",10);
        Texture textureIII = new Texture("texture3.png",5);



        Mesh hex = new Mesh(Mesh.type.HEXAHEDRON, new Matrix4().rotateY(rotationAngle).rotateX(rotationAngle).translate(10, 4, -10));
        VaoListe.add(new VertexArrayObject(hex,textureIII));

        Mesh model = new Mesh(Mesh.type.MODEL, new Matrix4());
        VaoListe.add(new VertexArrayObject(model,textureII));

        Mesh modelII = new Mesh(Mesh.type.MODEL, "/Users/marlinjai/IdeaProjects/Computergrafik/src/res/Objects3D/spaceScooterN.obj",new Matrix4().translate(0,0,-18));
        VaoListe.add(new VertexArrayObject(modelII,textureI));

        matrixLocations[0] = glGetUniformLocation(shaderProgram.getId(), "modelMatrix");
        matrixLocations[1] = glGetUniformLocation(shaderProgram.getId(), "matProjection");
        matrixLocations[2] = glGetUniformLocation(shaderProgram.getId(), "viewMatrix");
        matrixLocations[3] = glGetUniformLocation(shaderProgram.getId(), "cameraPosition");

        glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
        glEnable(GL_CULL_FACE); // backface culling aktivieren
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    }

    protected void ProcessInput(long window) {
        if (glfwGetKey((window), GLFW_KEY_ESCAPE) == GLFW_PRESS) {
            glfwSetWindowShouldClose(window, true);
        }
        if (glfwGetKey((window), GLFW_KEY_RIGHT) == GLFW_PRESS) {
            Vec3f rightMove = myCamera1.getNv3().cross(myCamera1.getVv3());
            Vec3f rightMoveNorm = rightMove.normalize();
            rightMoveNorm = rightMoveNorm.scalarMultiply(myCamera1.getCameraSpeed());
            this.myCamera1.cameraPosv3 = this.myCamera1.cameraPosv3.add(rightMoveNorm);
        }
        if (glfwGetKey((window), GLFW_KEY_LEFT) == GLFW_PRESS) {
            Vec3f leftMove = myCamera1.getVv3().cross(myCamera1.getNv3());
            Vec3f leftMoveNorm = leftMove.normalize();
            leftMoveNorm = leftMoveNorm.scalarMultiply(myCamera1.getCameraSpeed());
            this.myCamera1.cameraPosv3 = this.myCamera1.cameraPosv3.add(leftMoveNorm);
        }

        if (glfwGetKey((window), GLFW_KEY_UP) == GLFW_PRESS) {
            Vec3f upMove = myCamera1.getUv3().cross(myCamera1.getNv3());
            Vec3f upMoveNorm = upMove.normalize();
            upMoveNorm = upMoveNorm.scalarMultiply(myCamera1.getCameraSpeed());
            this.myCamera1.cameraPosv3 = this.myCamera1.cameraPosv3.add(upMoveNorm);
        }

        if (glfwGetKey((window), GLFW_KEY_DOWN) == GLFW_PRESS) {
            Vec3f downMove = myCamera1.getNv3().cross(myCamera1.getUv3());
            Vec3f downMoveNorm = downMove.normalize();
            downMoveNorm = downMoveNorm.scalarMultiply(myCamera1.getCameraSpeed());
            this.myCamera1.cameraPosv3 = this.myCamera1.cameraPosv3.add(downMoveNorm);
        }
    }


    @Override
    public void update() {
        rotationAngle = (rotationAngle - 0.3F);

        projection = new Matrix4(1, 200, ((float) this.width / (float) this.height), 75.0f);
        glUniformMatrix4fv(matrixLocations[1], false, projection.getValuesAsArray());
        Vec3f cameraPosition = myCamera1.cameraPosv3;
        view = myCamera1.getCameraMatrix();
        glUniform3f(matrixLocations[3], cameraPosition.getX(), cameraPosition.getY(), cameraPosition.getZ());
        glUniformMatrix4fv(matrixLocations[2], false, view.getValuesAsArray());
        VaoListe.get(0).setModelMatrix(new Matrix4().rotateY(rotationAngle).translate(0, 5, -10).rotateZ(0));
        VaoListe.get(1).setModelMatrix(new Matrix4().rotateY(rotationAngle).translate(0, 1, -5).rotateZ(0));

    }


    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);





        // hier vorher erzeugte VAOs zeichnen

        for (int i = 0; i < VaoListe.size(); i++) {
            VertexArrayObject draw = VaoListe.get(i);
            if (draw.mesh.getType() == Mesh.type.MODEL) {
                glUniformMatrix4fv(matrixLocations[0], false, draw.modelMatrix.getValuesAsArray());
                if (draw.texture != null) {
                    glBindTexture(GL_TEXTURE_2D, draw.texture.getId());
                }
                    glBindVertexArray(draw.loc);

                glDrawElements(GL_TRIANGLES,  draw.mesh.getM().indicesArray.length, GL_UNSIGNED_INT,0);}
            else {
                glUniformMatrix4fv(matrixLocations[0], false, draw.modelMatrix.getValuesAsArray());
                if (draw.texture != null) {
                    glBindTexture(GL_TEXTURE_2D, draw.texture.getId());
                }
                glBindVertexArray(draw.loc);
                glDrawElements(GL_TRIANGLES, draw.mesh.getIndices().length, GL_UNSIGNED_INT, 0);}

        }
    }

    @Override
    public void destroy() {
    }
}
