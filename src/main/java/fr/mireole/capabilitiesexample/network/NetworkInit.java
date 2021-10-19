package fr.mireole.capabilitiesexample.network;

import fr.mireole.capabilitiesexample.CapabilitiesExample;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

public class NetworkInit {

    public static final String NETWORK_VERSION = "1.0.0";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(CapabilitiesExample.MOD_ID, "network"),
            () -> NETWORK_VERSION, version -> version.equals(NETWORK_VERSION),
            version -> version.equals(NETWORK_VERSION));

    public static void init() {
        CHANNEL.registerMessage(0, PowerPacket.class, PowerPacket::encode, PowerPacket::decode, PowerPacket::handle);
    }

}
