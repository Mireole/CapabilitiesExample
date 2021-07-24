package fr.mireole.capabilitiesexample.capability;

import fr.mireole.capabilitiesexample.CapabilitiesExample;
import fr.mireole.capabilitiesexample.capability.provider.PlayerPowerProvider;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class PowerCapability{

    @CapabilityInject(IPowerCapability.class)
    public static Capability<IPowerCapability> POWER_CAPABILITY = null;

    public static final ResourceLocation CAP_KEY = new ResourceLocation(CapabilitiesExample.MOD_ID, "power");

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

    @SubscribeEvent
    public static void attachToEntities(AttachCapabilitiesEvent<Entity> event)
    {
        if(event.getObject() instanceof PlayerEntity)
        {
            IPowerCapability capability;
            if(event.getObject() instanceof ServerPlayerEntity){
                capability = new PlayerPowerStorage((ServerPlayerEntity)event.getObject());
            }
            else {
                capability = PowerCapability.POWER_CAPABILITY.getDefaultInstance();
            }

            PlayerPowerProvider provider = new PlayerPowerProvider(capability);
            event.addCapability(CAP_KEY, provider);
        }
    }

}
