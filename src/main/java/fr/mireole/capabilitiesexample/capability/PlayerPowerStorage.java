package fr.mireole.capabilitiesexample.capability;

import fr.mireole.capabilitiesexample.network.NetworkInit;
import fr.mireole.capabilitiesexample.network.PowerPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fmllegacy.network.PacketDistributor;

public class PlayerPowerStorage extends PowerStorage {

    private final ServerPlayer player;

    public PlayerPowerStorage(ServerPlayer player) {
        this.player = player;
    }

    @Override
    public void setPower(int value) {
        super.setPower(value);

        if (player.connection != null) {
            player.getCapability(PowerCapability.POWER_CAPABILITY).ifPresent(capability -> NetworkInit.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> this.player), new PowerPacket(capability)
            ));
        }
    }
}
