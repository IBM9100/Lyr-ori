package LZSB.avada;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.util.BufferUtils;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.glfw.GLFW;
import com.jme3.system.lwjgl.LwjglWindow;
import java.awt.*;
import java.nio.IntBuffer;

public class Main extends SimpleApplication {

    public static void main(String[] args) {

        Main app = new Main();
        app.showSettings = false;


        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");

        settings.setResolution(1024,768);
        app.setSettings(settings);
        app.start();
    }
    private Spatial model;
    @Override
    public void simpleInitApp() {
        // FIX BUG: Mac OSX Retina Half-Size.
        IntBuffer framebufferSize = BufferUtils.createIntBuffer(2);
        long window = ((LwjglWindow)getContext()).getWindowHandle();
        GLFW.nglfwGetFramebufferSize(window, MemoryUtil.memAddress(framebufferSize), MemoryUtil.memAddress(framebufferSize) + 4);
        int width = framebufferSize.get(0);
        int height = framebufferSize.get(1);
        reshape(width, height);

        cam.setLocation(new Vector3f(0.41600543f, 3.2057908f, 6.6927643f));
        cam.setRotation(new Quaternion(-0.00414816f, 0.9817784f, -0.18875499f, -0.021575727f));
        flyCam.setMoveSpeed(10);
        viewPort.setBackgroundColor(ColorRGBA.LightGray);


        model = assetManager.loadModel("Models/arc/ArcDeTriomphe.obj");
        model.scale(0.05f);
        model.center();


        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1,-2,-3));
        AmbientLight ambientLight = new AmbientLight();

        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(1.6f));
        ambientLight.setColor(lightColor.mult(0.4f));

        rootNode.attachChild(model);
        rootNode.addLight(sun);
        rootNode.addLight(ambientLight);
    }

    @Override
    public void simpleUpdate(float tpf) {

        float speed = FastMath.HALF_PI;
        model.rotate(0,speed*tpf,0);
        //TODO: add update code
    }

}