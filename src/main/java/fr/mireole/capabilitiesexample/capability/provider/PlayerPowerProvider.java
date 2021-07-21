package fr.mireole.capabilitiesexample.capability.provider;

import fr.mireole.capabilitiesexample.capability.IPowerCapability;
import fr.mireole.capabilitiesexample.capability.PowerCapability;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerPowerProvider implements ICapabilitySerializable<INBT> {
    private IPowerCapability holder = PowerCapability.POWER_CAPABILITY.getDefaultInstance();
    private final LazyOptional<IPowerCapability> lazyOptional = LazyOptional.of(() -> this.holder);


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return PowerCapability.POWER_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    @Override
    public INBT serializeNBT() {
        return PowerCapability.POWER_CAPABILITY.writeNBT(this.holder, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        PowerCapability.POWER_CAPABILITY.readNBT(holder, null, nbt);    }
}
