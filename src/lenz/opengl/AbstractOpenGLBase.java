package lenz.opengl;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.nio.IntBuffer;

public abstract class AbstractOpenGLBase {
    static {
        System.setProperty("java.awt.headless", "true");
    }

    protected abstract void init();

    protected abstract void update();

    protected abstract void render();

    protected abstract void destroy();

    long window;

    public int width = 700;
    public int height = 700;

    public float aspectRatio = (float) this.width / (float) this.height;


    public void start(String title/*, int width, int height*/) {
        System.out.println("LWJGL " + Version.getVersion());


        long window = openWindow(title, width, height);
        GL.createCapabilities(); // internally connects OpenGL and GLFW's current context
        System.out.println("OpenGL " + glGetString(GL_VERSION));

        init(); // custom user initialization

        while (!glfwWindowShouldClose(window)) {
            update(); // update internal
            IntBuffer w = BufferUtils.createIntBuffer(1);
            IntBuffer h = BufferUtils.createIntBuffer(1);
            glfwGetWindowSize(window, w, h);
            width = w.get(0);
            height = h.get(0);

            render(); // fill OpenGL buffer with image

            ProcessInput(window);

            glfwSwapBuffers(window); // double buffering (displays image)

            glfwPollEvents(); // poll window events like key presses
        }

        destroy(); // custom user destruction

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    protected abstract void ProcessInput(long window);


    private long openWindow(String title, int width, int height) {
        GLFWErrorCallback.createPrint(System.err).set(); // print errors to systemErr
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will  be resizable

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // otherwise macOS only supports OpenGL 2
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor()); // get desktop resolution
        glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2); // center window


        glfwMakeContextCurrent(window);

        glfwSwapInterval(1); // v-sync

        glfwShowWindow(window);

        return window;
    }


}

