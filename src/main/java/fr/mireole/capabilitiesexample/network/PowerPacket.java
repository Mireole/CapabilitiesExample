package fr.mireole.capabilitiesexample.network;

import fr.mireole.capabilitiesexample.capability.IPowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class PowerPacket {

    private final int power;

    public PowerPacket(int power) {
        this.power = power;
    }

    public PowerPacket(IPowerCapability instance) {
        this.power = instance.getPower();
    }

    public static void encode(PowerPacket packet, FriendlyByteBuf buffer){
        buffer.writeInt(packet.power);
    }

    public static PowerPacket decode(FriendlyByteBuf buffer){
        return new PowerPacket(buffer.readInt());
    }

    public static void handle(PowerPacket packet, Supplier<NetworkEvent.Context> contextSupplier){
        NetworkEvent.Context context = contextSupplier.get();
        if(context.getDirection().getReceptionSide() == LogicalSide.CLIENT){
            context.enqueueWork(() -> {
                if (Minecraft.getInstance().player != null) {
                    Minecraft.getInstance().player.getCapability(PowerCapability.POWER_CAPABILITY)
                            .ifPresent(capa -> capa.setPower(packet.power));
                }
            });
        }
        context.setPacketHandled(true);
    }

}
