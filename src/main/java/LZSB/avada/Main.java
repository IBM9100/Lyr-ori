package LZSB.avada;

import LZSB.avada.Compatibility.NormalizationAppState;
import LZSB.avada.Lumos.LightAppState;
import LZSB.avada.Lumos.SkyAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main extends SimpleApplication {

    private static final Logger log = LogManager.getFormatterLogger(Main.class);

    public static void main(String[] args) {

        Main app = new Main();
        app.showSettings = false;


        AppSettings settings = new AppSettings(true);
        settings.setTitle("My Awesome Game");
        settings.setResolution(800,400);
        settings.setFrameRate(69);
        //settings.setRenderer(AppSettings.LWJGL_OPENGL45);
        settings.setSamples(4);
        app.setSettings(settings);
        app.start();
    }


    private Spatial model;
    @Override
    public void simpleInitApp() {
        stateManager.attachAll(new NormalizationAppState(),new SkyAppState(),new LightAppState());

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




        rootNode.attachChild(block);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

}