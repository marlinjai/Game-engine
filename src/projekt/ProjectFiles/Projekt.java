package projekt.ProjectFiles;

import static org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;


import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;


import lenz.opengl.Texture;
import org.w3c.dom.Text;
import projekt.Geometry.Mesh;
import projekt.Inputs.KeyboardInputs;
import projekt.Inputs.MouseInput;
import projekt.VectorsAndMatrices.Matrix4;
import projekt.VectorsAndMatrices.Vec3f;


import java.util.*;


public class Projekt extends AbstractOpenGLBase {

    private ShaderProgram shaderProgramI;
    private ShaderProgram shaderProgramII;

    public boolean isShaderProgramI;
    public boolean isShaderProgramII;


    private KeyboardInputs projectKeyboardInputs = new KeyboardInputs();
    private MouseInput projectMouseInput = new MouseInput(this);
    private float rotationAngle = 0;

    public Vec3f cameraPosv3 = new Vec3f(0f, 0f, 0f);
    private Vec3f vUp = new Vec3f(0.0f, 1.0f, 0.0f);
    private Vec3f nLookAt = new Vec3f(0.0f, 0.0f, 1.0f);
    public Camera currentCamera = new Camera(cameraPosv3, vUp, nLookAt, this.width, this.height);


    private List<VertexArrayObject> VaoListe = new LinkedList<>();
    private int[] matrixLocations = new int[4];
    Matrix4 projection;
    Matrix4 view;


    public static void main(String[] args) {


        new Projekt().start("CG Projekt Marlin Jai"/*, width, height*/);

    }


    public void useShader(ShaderProgram use) {
        glUseProgram(use.getId());
        if (use.getId() == this.shaderProgramI.getId()) {
            isShaderProgramII = false;
            isShaderProgramI = true;
        }
        if (use.getId() == this.shaderProgramII.getId()) {
            isShaderProgramI = false;
            isShaderProgramII = true;
        }
    }

    @Override
    protected void init() {

        shaderProgramI = new ShaderProgram("textureShader");
        shaderProgramII = new ShaderProgram("phongShader");


        useShader(shaderProgramI);

        Texture textureI = new Texture("texture3small.png",10);
        Texture textureII = new Texture("texture2.jpeg", 10);
        Texture textureIII = new Texture("texture3.png");
        Texture textureGold = new Texture("gold.png");
        Texture texHoney = new Texture("honeyComb.jpeg");




        Mesh hex = new Mesh(Mesh.type.HEXAHEDRON, new Matrix4().rotateX(rotationAngle).translate(10, 4, -10));
        VaoListe.add(new VertexArrayObject(hex, textureIII, this));

        Mesh modelII = new Mesh(Mesh.type.MODEL, "/Users/marlinjai/IdeaProjects/Computergrafik/src/res/Objects3D/cubeHomer.obj", new Matrix4().translate(0,0,-20));
        VaoListe.add(new VertexArrayObject(modelII, textureI,  this));

        Mesh model = new Mesh(Mesh.type.TETRAHEDRON, new Matrix4().rotateY(0).translate(0,1,-2));
        VaoListe.add(new VertexArrayObject(model,texHoney, this));


        Mesh modelIII = new Mesh(Mesh.type.MODEL, "/Users/marlinjai/IdeaProjects/Computergrafik/src/res/Objects3D/MayaHead.obj", new Matrix4().translate(-3,3,-10).scale(0.8f,0.8f,0.8f));
        VaoListe.add(new VertexArrayObject(modelIII, textureGold, this));




        matrixLocations[0] = glGetUniformLocation(shaderProgramI.getId(), "modelMatrix");
        matrixLocations[1] = glGetUniformLocation(shaderProgramI.getId(), "matProjection");
        matrixLocations[2] = glGetUniformLocation(shaderProgramI.getId(), "viewMatrix");


        glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
        glEnable(GL_CULL_FACE); // backface culling aktivieren
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    }


    protected void ProcessInput(long window) {
        projectKeyboardInputs.ProcessInput(window, this);
        projectMouseInput.processMouseInput();
        currentCamera.updateWindow(this.width, this.height);
    }


    @Override
    public void update(long window) {
        rotationAngle = (rotationAngle - 0.3F);

        projection = new Matrix4(1, 200, ((float) this.width / (float) this.height), 75.0f);
        glUniformMatrix4fv(matrixLocations[1], false, projection.getValuesAsArray());
        currentCamera.setView(currentCamera.getCMatrix());
        view = currentCamera.getView();
        glUniformMatrix4fv(matrixLocations[2], false, view.getValuesAsArray());
        VaoListe.get(0).setModelMatrix(new Matrix4().rotateX(rotationAngle).translate(0, 2, -10));
        VaoListe.get(1).setModelMatrix(new Matrix4().rotateY(rotationAngle).translate(0, -1, -5).scale(2,2,2).multiply(VaoListe.get(0).modelMatrix));
        VaoListe.get(2).setModelMatrix(new Matrix4().rotateY(rotationAngle).scale(0.4f,0.4f,0.4f).translate(0,-1,-3));


    }


    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        this.currentCamera.onRender();
        // hier vorher erzeugte VAOs zeichnen


        for (int i = 0; i < VaoListe.size(); i++) {
            VertexArrayObject draw = VaoListe.get(i);
            if (draw.mesh.getType() == Mesh.type.MODEL ||draw.mesh.getType() == Mesh.type.TETRAHEDRON ||draw.mesh.getType() == Mesh.type.OCTAHEDRON ||draw.mesh.getType() == Mesh.type.DODECAHEDRON ||draw.mesh.getType() == Mesh.type.ICOSAHEDRON) {
                glUniformMatrix4fv(matrixLocations[0], false, draw.modelMatrix.getValuesAsArray());
                if (draw.texture != null && this.isShaderProgramI) {
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,GL_NEAREST);
                    glBindTexture(GL_TEXTURE_2D, draw.texture.getId());
                }
                glBindVertexArray(draw.loc);

                glDrawElements(GL_TRIANGLES, draw.mesh.getMd().getIndices().length, GL_UNSIGNED_INT, 0);
            } else {
                glUniformMatrix4fv(matrixLocations[0], false, draw.modelMatrix.getValuesAsArray());
                if (draw.texture != null && this.isShaderProgramI) {
                    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,GL_LINEAR);
                    glBindTexture(GL_TEXTURE_2D, draw.texture.getId());
                }
                glBindVertexArray(draw.loc);
                glDrawElements(GL_TRIANGLES, draw.mesh.getIndices().length, GL_UNSIGNED_INT, 0);
            }

        }
    }

    @Override
    public void destroy() {
    }
}
