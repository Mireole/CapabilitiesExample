package fr.mireole.capabilitiesexample.gui.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import fr.mireole.capabilitiesexample.capability.PlayerPowerStorage;
import fr.mireole.capabilitiesexample.capability.PowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PowerOverlay extends AbstractGui{

    private static Minecraft MC = Minecraft.getInstance();

    public static void render(MatrixStack matrixStack){
        int width = MC.getMainWindow().getScaledWidth();
        int height = MC.getMainWindow().getScaledHeight();
        PlayerEntity player = Minecraft.getInstance().player;
        int power = player.getCapability(PowerCapability.POWER_CAPABILITY).orElse(new PowerStorage()).getPower();
        RenderSystem.pushMatrix();
        MC.fontRenderer.drawString(matrixStack, "Power : " + power, 0, 0, 14737632);
        RenderSystem.popMatrix();
    }
}
