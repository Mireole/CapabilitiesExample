package fr.mireole.capabilitiesexample.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class PowerCapability{

    @CapabilityInject(IPowerCapability.class)
    public static Capability<IPowerCapability> POWER = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IPowerCapability.class, new Capability.IStorage<IPowerCapability>() {

            @Override
            public INBT writeNBT(Capability<IPowerCapability> capability, IPowerCapability instance, Direction side)
            {
                return IntNBT.valueOf(instance.getPower());
            }

            @Override
            public void readNBT(Capability<IPowerCapability> capability, IPowerCapability instance, Direction side, INBT nbt)
            {
                if (!(instance instanceof IPowerCapability))
                    throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
                instance.setPower(((IntNBT)nbt).getInt());
            }
        },
        () -> new PowerStorage());
    }
}
