package LZSB.avada;

import LZSB.avada.Cameras.MainCamAppState;
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
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


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

    int checkAroundCount(boolean[][] pMap,int range,int x,int y){
        int count = 0;
        for(int i = -range;i <= range;i++){
            for(int j = -range;j <= range;j++){
                if(x+i < 0 || y+j < 0 ||(x+i == 0 && y+j == 0) || x+i == 50 || y+j == 50){
                    continue;
                }
                if (pMap[x+i][y+j]){
                    count += 1;
                }
            }
        }
        return count;
    }

    private Spatial model;
    @Override
    public void simpleInitApp() {
        stateManager.attachAll(new NormalizationAppState(),new SkyAppState(),new LightAppState(),new MainCamAppState());

        viewPort.setBackgroundColor(ColorRGBA.LightGray);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.setNumSamples(4);
        viewPort.addProcessor(fpp);

        String seed = "LZSB";
        Random random = new Random(seed.hashCode());

        long startTime = System.currentTimeMillis();

        boolean[][] map = new boolean[50][50];
        for (int i = 0; i < map.length;i++){
            for (int j = 0;j < map[i].length; j++){
                map[i][j] = random.nextBoolean();
            }
        }
        //times应该为偶数，以保证map得到正确的引用
        for (int times = 0; times <4; times++){
            boolean[][] frozenMap = new boolean[50][50];
            for (int i = 0; i < map.length;i++){

                for (int j = 0;j < map[i].length; j++){
                    if (map[i][j]){
                        frozenMap[i][j] = checkAroundCount(map, 1, i, j) >= 4;
                    }else {
                        frozenMap[i][j] = checkAroundCount(map, 1, i, j) >= 5;
                    }
                }
            }
            boolean[][] changer = frozenMap.clone();
            frozenMap = map.clone(); map = changer.clone();
        }
        Node block =  new Node();
        model = assetManager.loadModel("Models/block/grass_block/grass_block.obj");
        model.scale(1f);
        model.center();
        BoundingBox modelBox = (BoundingBox) model.getWorldBound();

        float offsetX = 0;
        for (int i = 0; i < map.length;i++){
            for (int j = 0;j < map[i].length; j++){
               if (map[i][j]){
                   block.attachChild(model.clone());
               }
               model.move(new Vector3f(2*modelBox.getXExtent(),0f,0f));
               offsetX +=2*modelBox.getXExtent();
            }
            model.move(new Vector3f(-offsetX,0f,0f));
            model.move(new Vector3f(0f,2*modelBox.getYExtent(),0f));
            offsetX = 0;
        }
        long endTime = System.currentTimeMillis();

        log.info("运行时间："+ (endTime - startTime) +"ms");






        //为了拿到 model 的尺寸

//        for (int i = 0;i<24;i++){
//            Spatial cloneSpatial = model.clone();
//            cloneSpatial.move(new Vector3f(,0f,0f));
//            block.attachChild(cloneSpatial);
//        }




        rootNode.attachChild(block);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

}