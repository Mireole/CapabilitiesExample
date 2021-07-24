package fr.mireole.capabilitiesexample.events;

import com.mojang.blaze3d.matrix.MatrixStack;
import fr.mireole.capabilitiesexample.gui.overlay.PowerOverlay;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OverlaysRenderer {

    private static Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onOverlaysRendered(RenderGameOverlayEvent.Post event){
        MatrixStack matrixStack = event.getMatrixStack();
        PowerOverlay.render(matrixStack);

    }

}
