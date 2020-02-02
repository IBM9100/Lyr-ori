package LZSB.avada;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class Main extends SimpleApplication {

    private Geometry geo;

    public static void main(String[] args) {

        Main app = new Main();
        app.showSettings = false;

        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");
        app.setSettings(settings);

        app.start();

    }

    @Override
    public void simpleInitApp() {
        Mesh box = new Box(1,1,1);
        Material material = new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");
        geo = new Geometry("Box");
        geo.setMesh(box);
        geo.setMaterial(material);
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1,-2,-3));

        rootNode.attachChild(geo);
        rootNode.addLight(sun);
    }

    @Override
    public void simpleUpdate(float tpf) {
        float speed = FastMath.TWO_PI;
        geo.rotate(0,speed*tpf,0);
        //TODO: add update code
    }

}