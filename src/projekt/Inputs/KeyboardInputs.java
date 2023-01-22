package projekt.Inputs;

import projekt.ProjectFiles.Projekt;
import projekt.VectorsAndMatrices.Vec3f;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class KeyboardInputs {

    public KeyboardInputs() {
    }

    private boolean camSpeedDecr = false;
    private boolean camSpeedIncr = false;



    public void ProcessInput(long window, Projekt p) {


        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS && !camSpeedDecr){

                p.currentCamera.setCameraSpeed(p.currentCamera.getCameraSpeed()-0.005f);
                System.out.println("decreased camera speed");
                camSpeedDecr = true;
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_RELEASE && camSpeedDecr){

            camSpeedDecr = false;

        }

        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS && !camSpeedIncr){
            p.currentCamera.setCameraSpeed(p.currentCamera.getCameraSpeed()+0.005f);
            System.out.println("increased camera speed");
            camSpeedIncr = true;
        }
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_RELEASE && camSpeedIncr){

            camSpeedIncr = false;

        }


        if (glfwGetKey((window), GLFW_KEY_ESCAPE) == GLFW_PRESS) {
            glfwSetWindowShouldClose(window, true);
        }
        if (glfwGetKey((window), GLFW_KEY_RIGHT) == GLFW_PRESS) {
            Vec3f rightMove = p.currentCamera.getnLookAt().cross(p.currentCamera.getvUp());
            Vec3f rightMoveNorm = rightMove.normalize();
            rightMoveNorm = rightMoveNorm.scalarMultiply(p.currentCamera.getCameraSpeed());
            p.currentCamera.cameraPosv3 = p.currentCamera.cameraPosv3.add(rightMoveNorm);
        }
        if (glfwGetKey((window), GLFW_KEY_LEFT) == GLFW_PRESS) {
            Vec3f leftMove = p.currentCamera.getvUp().cross(p.currentCamera.getnLookAt());
            Vec3f leftMoveNorm = leftMove.normalize();
            leftMoveNorm = leftMoveNorm.scalarMultiply(p.currentCamera.getCameraSpeed());
            p.currentCamera.cameraPosv3 = p.currentCamera.cameraPosv3.add(leftMoveNorm);
        }

        if (glfwGetKey((window), GLFW_KEY_DOWN) == GLFW_PRESS) {
            Vec3f upMove = p.currentCamera.getU().cross(p.currentCamera.getvUp());
            Vec3f forwardMove = upMove.normalize();
            forwardMove = forwardMove.scalarMultiply(p.currentCamera.getCameraSpeed());
            p.currentCamera.cameraPosv3 = p.currentCamera.cameraPosv3.add(forwardMove);
        }

        if (glfwGetKey((window), GLFW_KEY_UP) == GLFW_PRESS) {
            Vec3f downMove = p.currentCamera.getvUp().cross(p.currentCamera.getU());
            Vec3f downMoveNorm = downMove.normalize();
            downMoveNorm = downMoveNorm.scalarMultiply(p.currentCamera.getCameraSpeed());
            p.currentCamera.cameraPosv3 = p.currentCamera.cameraPosv3.add(downMoveNorm);
        }
    }
}
