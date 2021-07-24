package fr.mireole.capabilitiesexample.capability;

import fr.mireole.capabilitiesexample.network.NetworkInit;
import fr.mireole.capabilitiesexample.network.PowerPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.PacketDistributor;

public class PlayerPowerStorage extends PowerStorage {

    private ServerPlayerEntity player;

    public PlayerPowerStorage(ServerPlayerEntity player) {
        this.player = player;
    }

    @Override
    public void setPower(int value) {
        super.setPower(value);

        if(player.connection != null) {
            player.getCapability(PowerCapability.POWER_CAPABILITY).ifPresent(capability -> NetworkInit.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> this.player), new PowerPacket(capability)
            ));
        }
    }
}
