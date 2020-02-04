package LZSB.avada.Cameras;

import LZSB.avada.Main;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.FlyByCamera;

public class MainCamAppState extends AbstractAppState {
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);
        FlyByCamera flyCam = ((SimpleApplication)app).getFlyByCamera();
        flyCam.setMoveSpeed(10);
    }
}
