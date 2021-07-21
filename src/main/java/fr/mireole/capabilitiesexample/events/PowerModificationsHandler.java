package fr.mireole.capabilitiesexample.events;

import fr.mireole.capabilitiesexample.CapabilitiesExample;
import fr.mireole.capabilitiesexample.capability.IPowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerCapability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PowerModificationsHandler {

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        if(!event.getPlayer().world.isRemote) {
            LazyOptional<IPowerCapability> power_capability = event.getPlayer().getCapability(PowerCapability.POWER_CAPABILITY);
            if(power_capability.orElse(null).getPower() >= 60) {
                power_capability.ifPresent(cap -> cap.reducePower(60));
                CapabilitiesExample.LOGGER.info(event.getPlayer().getName().getString() + " power : " + power_capability.orElse(null).getPower());
            }
            else{
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!event.player.world.isRemote) {
            event.player.getCapability(PowerCapability.POWER_CAPABILITY).ifPresent(cap -> cap.increasePower(1));
        }
    }

}
