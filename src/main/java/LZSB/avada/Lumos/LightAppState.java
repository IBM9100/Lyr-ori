package LZSB.avada.Lumos;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class LightAppState extends BaseAppState {
    private final DirectionalLight sun = new DirectionalLight();
    private Node rootNode;
    @Override
    protected void initialize(Application app) {
        rootNode = ((SimpleApplication)app).getRootNode();

        sun.setDirection(new Vector3f(-1,-2,-3));
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(1.6f));
    }

    private void turnOn(){
        rootNode.addLight(sun);
    }
    private void turnOff(){
        rootNode.removeLight(sun);
    }

    @Override
    protected void cleanup(Application app) {
        turnOff();
    }

    @Override
    protected void onEnable() {
        turnOn();
    }

    @Override
    protected void onDisable() {
        turnOff();
    }
}
