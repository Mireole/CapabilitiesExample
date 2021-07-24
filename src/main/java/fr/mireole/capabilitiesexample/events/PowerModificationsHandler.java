package fr.mireole.capabilitiesexample.events;

import fr.mireole.capabilitiesexample.capability.IPowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerStorage;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PowerModificationsHandler {

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        LazyOptional<IPowerCapability> power_capability = event.getPlayer().getCapability(PowerCapability.POWER_CAPABILITY);
        if(power_capability.orElse(new PowerStorage()).getPower() >= 60) {
            power_capability.ifPresent(cap -> cap.reducePower(60));
        }
        else{
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        event.player.getCapability(PowerCapability.POWER_CAPABILITY).ifPresent(cap -> cap.increasePower(1));
    }

}
