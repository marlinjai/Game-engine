package projekt.Inputs;


import org.lwjgl.glfw.GLFWCursorPosCallback;
import projekt.ProjectFiles.Projekt;
import projekt.VectorsAndMatrices.Vec2f;

import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;

public class MouseInput  {



    public Projekt p;

    public MouseInput(Projekt p) {
        this.p = p;
    }

    public  void processMouseInput() {
        glfwSetCursorPosCallback(this.p.getWindow(), (long windowHandle, double xPos, double yPos) -> {
            // Do something with the mouse position
            p.currentCamera.onMouse(xPos,yPos);
        });
    }




}

