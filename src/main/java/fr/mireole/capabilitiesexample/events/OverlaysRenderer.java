package fr.mireole.capabilitiesexample.events;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.mireole.capabilitiesexample.gui.overlay.PowerOverlay;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OverlaysRenderer {

    @SubscribeEvent
    public static void onOverlaysRendered(RenderGameOverlayEvent.Post event){
        PoseStack matrixStack = event.getMatrixStack();
        PowerOverlay.render(matrixStack);

    }

}
