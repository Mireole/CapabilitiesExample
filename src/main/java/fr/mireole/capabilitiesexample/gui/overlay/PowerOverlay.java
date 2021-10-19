package fr.mireole.capabilitiesexample.gui.overlay;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.mireole.capabilitiesexample.capability.PowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PowerOverlay extends GuiComponent{

    private static final Minecraft MC = Minecraft.getInstance();

    private static final Font font = MC.font;

    public static void render(PoseStack matrixStack){
        Player player = Minecraft.getInstance().player;
        int power = 0;
        if (player != null) {
            power = player.getCapability(PowerCapability.POWER_CAPABILITY).orElse(new PowerStorage()).getPower();
        }
        font.draw(matrixStack, "Power : " + power, 0, 0, 14737632);
    }
}
