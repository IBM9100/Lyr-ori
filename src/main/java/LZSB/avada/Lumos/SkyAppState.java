package LZSB.avada.Lumos;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

public class SkyAppState extends BaseAppState {
    private final AmbientLight ambientLight = new AmbientLight();
    private Node rootNode;
    @Override
    protected void initialize(Application app) {
        rootNode = ((SimpleApplication)app).getRootNode();

        ColorRGBA lightColor = new ColorRGBA();
        ambientLight.setColor(lightColor.mult(0.4f));
        turnOn();
    }

    private void turnOn(){
        rootNode.addLight(ambientLight);
    }
    private void turnOff(){
        rootNode.removeLight(ambientLight);
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
