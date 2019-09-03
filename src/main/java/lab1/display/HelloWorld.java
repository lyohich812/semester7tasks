package lab1.display;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class HelloWorld {

    private Settings settings = new Settings();
    private Thread thread = new Thread(settings);

    // The window handle
    private long window;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        thread.start();

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(800, 800, "Hello World!", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();


        // Set the clear color
        glClearColor(0.3f, 0.6f, 0.7f, 0.0f);

        long lastTime = 0;

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.

        //ХУЯРИТЬ В ЭТОМ ЦИКЛЕ
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glEnable(GL_DEPTH_TEST);

            glBegin(GL_LINES);
            glColor3f(0.0f, 0.0f,0.0f);
            glVertex3d(settings.xAxis[0], settings.yAxis[0], settings.zAxis[0]);
            glVertex3d(settings.xAxis[1], settings.yAxis[1], settings.zAxis[1]);
            glEnd();

            glBegin(GL_LINES);
            glColor3f(0.0f, 0.0f,0.0f);
            glVertex3d(settings.xAxis[2], settings.yAxis[2], settings.zAxis[2]);
            glVertex3d(settings.xAxis[3], settings.yAxis[3], settings.zAxis[3]);
            glEnd();

            glBegin(GL_LINES);
            glColor3f(0.0f, 0.0f,0.0f);
            glVertex3d(settings.xAxis[4], settings.yAxis[4], settings.zAxis[4]);
            glVertex3d(settings.xAxis[5], settings.yAxis[5], settings.zAxis[5]);
            glEnd();

            glBegin(GL_POLYGON);    //передняя грань
            glColor3f(1.0f, 1.0f, 1.0f);
            glVertex3d(settings.x[0], settings.y[0], settings.z[0]);
            glVertex3d(settings.x[1], settings.y[1], settings.z[1]);
            glVertex3d(settings.x[2], settings.y[2], settings.z[2]);
            glVertex3d(settings.x[3], settings.y[3], settings.z[3]);
            glEnd();

            glBegin(GL_POLYGON);    //задняя грань
            glColor3f(1.0f, 0.0f, 1.0f);
            glVertex3d(settings.x[4], settings.y[4], settings.z[4]);
            glVertex3d(settings.x[5], settings.y[5], settings.z[5]);
            glVertex3d(settings.x[6], settings.y[6], settings.z[6]);
            glVertex3d(settings.x[7], settings.y[7], settings.z[7]);
            glEnd();

            glBegin(GL_POLYGON);    //левая грань
            glColor3f(0.0f, 1.0f, 0.0f);
            glVertex3d(settings.x[8], settings.y[8], settings.z[8]);
            glVertex3d(settings.x[9], settings.y[9], settings.z[9]);
            glVertex3d(settings.x[10], settings.y[10], settings.z[10]);
            glVertex3d(settings.x[11], settings.y[11], settings.z[11]);
            glEnd();

            glBegin(GL_POLYGON);    //правая грань
            glColor3f(0.0f, 0.0f, 1.0f);
            glVertex3d(settings.x[12], settings.y[12], settings.z[12]);
            glVertex3d(settings.x[13], settings.y[13], settings.z[13]);
            glVertex3d(settings.x[14], settings.y[14], settings.z[14]);
            glVertex3d(settings.x[15], settings.y[15], settings.z[15]);
            glEnd();

            glBegin(GL20.GL_POLYGON);   //верхняя грань
            glColor3f(1.0f, 0.0f, 0.0f);
            glVertex3d(settings.x[16], settings.y[16], settings.z[16]);
            glVertex3d(settings.x[17], settings.y[17], settings.z[17]);
            glVertex3d(settings.x[18], settings.y[18], settings.z[18]);
            glVertex3d(settings.x[19], settings.y[19], settings.z[19]);
            glEnd();

            glBegin(GL20.GL_POLYGON);   //нихняя грань
            glColor3f(0.6f, 0.6f, 0.6f);
            glVertex3d(settings.x[20], settings.y[20], settings.z[20]);
            glVertex3d(settings.x[21], settings.y[21], settings.z[21]);
            glVertex3d(settings.x[22], settings.y[22], settings.z[22]);
            glVertex3d(settings.x[23], settings.y[23], settings.z[23]);
            glEnd();

            glFlush();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {

        new HelloWorld().run();
    }

}