package fr.mireole.capabilitiesexample.capability.provider;

import fr.mireole.capabilitiesexample.capability.IPowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerCapability;
import net.minecraft.nbt.IntTag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerPowerProvider implements ICapabilitySerializable<IntTag> {
    private IPowerCapability holder;
    private final LazyOptional<IPowerCapability> lazyOptional = LazyOptional.of(() -> this.holder);

    public PlayerPowerProvider(IPowerCapability holder) {
        this.holder = holder;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return PowerCapability.POWER_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    @Override
    public IntTag serializeNBT() {
        return IntTag.valueOf(this.holder.getPower());
    }

    @Override
    public void deserializeNBT(IntTag nbt) {
        if (holder == null)
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        holder.setPower(nbt.getAsInt());
    }

}
