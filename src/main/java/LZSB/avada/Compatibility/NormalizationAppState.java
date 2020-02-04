package LZSB.avada.Compatibility;

import com.jme3.app.Application;
import com.jme3.app.LegacyApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.system.lwjgl.LwjglWindow;
import com.jme3.util.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

public class NormalizationAppState extends AbstractAppState {
    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        // FIX BUG: Mac OSX Retina Half-Size.
        IntBuffer framebufferSize = BufferUtils.createIntBuffer(2);
        long window = ((LwjglWindow) app.getContext()).getWindowHandle();
        GLFW.nglfwGetFramebufferSize(window, MemoryUtil.memAddress(framebufferSize), MemoryUtil.memAddress(framebufferSize) + 4);
        int width = framebufferSize.get(0);
        int height = framebufferSize.get(1);
        ((LegacyApplication)app).reshape(width, height);
    }
}
