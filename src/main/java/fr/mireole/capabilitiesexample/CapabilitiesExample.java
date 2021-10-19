package fr.mireole.capabilitiesexample;

import fr.mireole.capabilitiesexample.capability.PowerCapability;
import fr.mireole.capabilitiesexample.events.OverlaysRenderer;
import fr.mireole.capabilitiesexample.events.PowerModificationsHandler;
import fr.mireole.capabilitiesexample.network.NetworkInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CapabilitiesExample.MOD_ID)
public class CapabilitiesExample {
    public static final String MOD_ID = "capabilitiesexample";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public CapabilitiesExample() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(PowerCapability.class);
        MinecraftForge.EVENT_BUS.register(PowerModificationsHandler.class);
        MinecraftForge.EVENT_BUS.register(OverlaysRenderer.class);
    }

    private void setup(final FMLCommonSetupEvent event) {

        NetworkInit.init();


    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

}
