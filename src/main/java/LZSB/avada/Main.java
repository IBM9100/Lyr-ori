package LZSB.avada;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.util.BufferUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.glfw.GLFW;
import com.jme3.system.lwjgl.LwjglWindow;

import java.nio.IntBuffer;

public class Main extends SimpleApplication {

    private static final Logger log = LogManager.getFormatterLogger(Main.class);

    public static void main(String[] args) {

        Main app = new Main();
        app.showSettings = false;


        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");
        settings.setResolution(800,400);
        settings.setFrameRate(69);
        settings.setRenderer(AppSettings.LWJGL_OPENGL45);
        settings.setSamples(4);
        app.setSettings(settings);
        app.start();
    }

    public void osAdaptation(){
        if (GLFW.glfwGetVersionString().contains("retina")){
            // FIX BUG: Mac OSX Retina Half-Size.
            IntBuffer framebufferSize = BufferUtils.createIntBuffer(2);
            long window = ((LwjglWindow)getContext()).getWindowHandle();
            GLFW.nglfwGetFramebufferSize(window, MemoryUtil.memAddress(framebufferSize), MemoryUtil.memAddress(framebufferSize) + 4);
            int width = framebufferSize.get(0);
            int height = framebufferSize.get(1);
            reshape(width, height);
        }
    }
    private Spatial model;
    @Override
    public void simpleInitApp() {
        osAdaptation();

        flyCam.setMoveSpeed(10);
        viewPort.setBackgroundColor(ColorRGBA.LightGray);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.setNumSamples(4);
        viewPort.addProcessor(fpp);



        Node block =  new Node();

        model = assetManager.loadModel("Models/block/grass_block/grass_block.obj");
        model.scale(1f);
        model.center();
        block.attachChild(model);

        //为了拿到 model 的尺寸
        BoundingBox modelBox = (BoundingBox) model.getWorldBound();
        for (int i = 0;i<24;i++){
            Spatial cloneSpatial = model.clone();
            cloneSpatial.move(new Vector3f((i+1) * 2 *modelBox.getXExtent(),0f,0f));
            block.attachChild(cloneSpatial);
        }

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1,-2,-3));
        AmbientLight ambientLight = new AmbientLight();

        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(1.6f));
        ambientLight.setColor(lightColor.mult(0.4f));


        rootNode.attachChild(block);
        rootNode.addLight(sun);
        rootNode.addLight(ambientLight);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

}