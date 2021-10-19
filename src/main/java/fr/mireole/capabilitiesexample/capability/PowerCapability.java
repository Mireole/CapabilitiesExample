package fr.mireole.capabilitiesexample.capability;

import fr.mireole.capabilitiesexample.CapabilitiesExample;
import fr.mireole.capabilitiesexample.capability.provider.PlayerPowerProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PowerCapability{

    public static Capability<IPowerCapability> POWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    public static final ResourceLocation CAP_KEY = new ResourceLocation(CapabilitiesExample.MOD_ID, "power");

    @SubscribeEvent
    public static void attachToEntities(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof Player)
        {
            IPowerCapability capability;
            if(event.getObject() instanceof ServerPlayer){
                capability = new PlayerPowerStorage((ServerPlayer)event.getObject());
            }
            else {
                capability = new PowerStorage();
            }

            PlayerPowerProvider provider = new PlayerPowerProvider(capability);
            event.addCapability(CAP_KEY, provider);
        }
    }

    @SubscribeEvent
    public void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(IPowerCapability.class);
    }

}
