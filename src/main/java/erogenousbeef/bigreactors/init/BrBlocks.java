package erogenousbeef.bigreactors.init;

import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.block.BlockBRGenericFluid;
import erogenousbeef.bigreactors.common.block.BlockBRMetal;
import erogenousbeef.bigreactors.common.block.BlockBROre;
import erogenousbeef.bigreactors.common.multiblock.PartType;
import erogenousbeef.bigreactors.common.multiblock.PowerSystem;
import erogenousbeef.bigreactors.common.multiblock.block.*;
import erogenousbeef.bigreactors.common.multiblock.tileentity.*;
import erogenousbeef.bigreactors.common.multiblock.tileentity.creative.TileEntityReactorCreativeCoolantPort;
import erogenousbeef.bigreactors.common.multiblock.tileentity.creative.TileEntityTurbineCreativeSteamGenerator;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;

public final class BrBlocks {

    // Ores
    public static final BlockBROre brOre;

    // Metal blocks
    public static final BlockBRMetal blockMetals;

    // Reactor parts
    public static final BlockMultiblockGlass reactorGlass;
    public static final BlockMultiblockCasing reactorCasing;
    public static final BlockMultiblockController reactorController;
    public static final BlockMultiblockPowerTap reactorPowerTapRF;
    public static final BlockMultiblockPowerTap reactorPowerTapTesla;
    public static final BlockMultiblockIOPort reactorAccessPort;
    public static final BlockMultiblockIOPort reactorCoolantPort;
    public static final BlockReactorControlRod reactorControlRod;
    public static final BlockReactorRedNetPort reactorRedNetPort;
    public static final BlockMultiblockComputerPort reactorComputerPort;
    public static final BlockReactorRedstonePort reactorRedstonePort;
    public static final BlockReactorFuelRod reactorFuelRod;
    public static final BlockMultiblockIOPort reactorCreativeCoolantPort;

    // Turbine parts
    public static final BlockMultiblockGlass turbineGlass;
    public static final BlockMultiblockCasing turbineHousing;
    public static final BlockMultiblockController turbineController;
    public static final BlockMultiblockPowerTap turbinePowerTapRF;
    public static final BlockMultiblockPowerTap turbinePowerTapTesla;
    public static final BlockMultiblockComputerPort turbineComputerPort;
    public static final BlockMultiblockIOPort turbineFluidPort;
    public static final BlockTurbineRotorBearing turbineBearing;
    public static final BlockTurbineRotorShaft turbineRotorShaft;
    public static final BlockTurbineRotorBlade turbineRotorBlade;
    public static final BlockMultiblockIOPort turbineCreativeSteamGenerator;
    
    // Devices
    //public static final BlockBRDevice deviceCyaniteRep; // Bye Bye ...

    // Fluid blocks
    public static final BlockBRGenericFluid yellorium;
    public static final BlockBRGenericFluid cyanite;

    static void initialize() {
    }

    static {

        final InitHandler init = InitHandler.INSTANCE;
        final boolean regCreativeParts = BigReactors.CONFIG.registerCreativeMultiblockParts;

        // register blocks

        // - ores
        brOre = (BlockBROre)init.register(new BlockBROre("brore"));

        // - metal blocks
        blockMetals = (BlockBRMetal)init.register(new BlockBRMetal("blockmetals"));
        
        // - reactor parts
        reactorCasing = (BlockMultiblockCasing)init.register(new BlockMultiblockCasing(PartType.ReactorCasing, "reactorcasing"));
        reactorGlass = (BlockMultiblockGlass)init.register(new BlockMultiblockGlass(PartType.ReactorGlass, "reactorglass"));
        reactorController = (BlockMultiblockController)init.register(new BlockMultiblockController(PartType.ReactorController, "reactorcontroller"));
        reactorPowerTapRF = (BlockMultiblockPowerTap)init.register(new BlockMultiblockPowerTap(PartType.ReactorPowerTap, "reactorpowertaprf", PowerSystem.RedstoneFlux));
        reactorPowerTapTesla = (BlockMultiblockPowerTap)init.register(new BlockMultiblockPowerTap(PartType.ReactorPowerTap, "reactorpowertaptesla", PowerSystem.Tesla));
        reactorAccessPort = (BlockMultiblockIOPort)init.register(new BlockMultiblockIOPort(PartType.ReactorAccessPort, "reactoraccessport"));
        reactorCoolantPort = (BlockMultiblockIOPort)init.register(new BlockMultiblockIOPort(PartType.ReactorCoolantPort, "reactorcoolantport"));
        reactorControlRod = (BlockReactorControlRod)init.register(new BlockReactorControlRod("reactorcontrolrod"));
        reactorRedNetPort = (BlockReactorRedNetPort)init.register(new BlockReactorRedNetPort("reactorrednetport"));
        reactorComputerPort = (BlockMultiblockComputerPort)init.register(new BlockMultiblockComputerPort(PartType.ReactorComputerPort, "reactorcomputerport"));
        reactorRedstonePort = (BlockReactorRedstonePort)init.register(new BlockReactorRedstonePort("reactorredstoneport"));
        reactorFuelRod = (BlockReactorFuelRod)init.register(new BlockReactorFuelRod("reactorfuelrod"));
        reactorCreativeCoolantPort = !regCreativeParts ? null : (BlockMultiblockIOPort)init.register(new BlockMultiblockIOPort(PartType.ReactorCreativeCoolantPort, "reactorcreativecoolantport"));

        // - turbine parts
        turbineGlass = (BlockMultiblockGlass)init.register(new BlockMultiblockGlass(PartType.TurbineGlass, "turbineglass"));
        turbineHousing = (BlockMultiblockCasing)init.register(new BlockMultiblockCasing(PartType.TurbineHousing, "turbinehousing"));
        turbineController = (BlockMultiblockController)init.register(new BlockMultiblockController(PartType.TurbineController, "turbinecontroller"));
        turbinePowerTapRF = (BlockMultiblockPowerTap)init.register(new BlockMultiblockPowerTap(PartType.TurbinePowerPort, "turbinepowertaprf", PowerSystem.RedstoneFlux));
        turbinePowerTapTesla = (BlockMultiblockPowerTap)init.register(new BlockMultiblockPowerTap(PartType.TurbinePowerPort, "turbinepowertaptesla", PowerSystem.Tesla));
        turbineComputerPort = (BlockMultiblockComputerPort)init.register(new BlockMultiblockComputerPort(PartType.TurbineComputerPort, "turbinecomputerport"));
        turbineFluidPort = (BlockMultiblockIOPort)init.register(new BlockMultiblockIOPort(PartType.TurbineFluidPort, "turbinefluidport"));
        turbineBearing = (BlockTurbineRotorBearing)init.register(new BlockTurbineRotorBearing("turbinebearing"));
        turbineRotorShaft = (BlockTurbineRotorShaft)init.register(new BlockTurbineRotorShaft("turbinerotorshaft"));
        turbineRotorBlade = (BlockTurbineRotorBlade)init.register(new BlockTurbineRotorBlade("turbinerotorblade"));
        turbineCreativeSteamGenerator = !regCreativeParts ? null : (BlockMultiblockIOPort)init.register(new BlockMultiblockIOPort(PartType.TurbineCreativeSteamGenerator, "turbinecreativesteamgenerator"));

        // - devices
        //deviceCyaniteRep = (BlockBRDevice)init.register(new BlockBRDevice(DeviceType.CyaniteReprocessor, "deviceCyaniteRep"));

        // - fluid blocks
        yellorium = init.register(new BlockBRGenericFluid(BrFluids.fluidYellorium, "yellorium", new MaterialLiquid(MapColor.YELLOW)));
        cyanite = init.register(new BlockBRGenericFluid(BrFluids.fluidCyanite, "cyanite", Material.LAVA));

        // - register block tile entities

        //init.register(TileEntityCyaniteReprocessor.class);

        init.register(TileEntityReactorPart.class);
        init.register(TileEntityReactorGlass.class);
        init.register(TileEntityReactorController.class);
        init.register(TileEntityReactorPowerTapRedstoneFlux.class);
        init.register(TileEntityReactorPowerTapTesla.class);
        init.register(TileEntityReactorAccessPort.class);
        init.register(TileEntityReactorFuelRod.class);
        init.register(TileEntityReactorControlRod.class);
        init.register(TileEntityReactorRedstonePort.class);
        init.register(TileEntityReactorComputerPort.class);
        init.register(TileEntityReactorCoolantPort.class);
        init.register(TileEntityReactorCreativeCoolantPort.class);
        init.register(TileEntityReactorRedNetPort.class);

        init.register(TileEntityTurbinePart.class);
        init.register(TileEntityTurbinePowerTapRedstoneFlux.class);
        init.register(TileEntityTurbinePowerTapTesla.class);
        init.register(TileEntityTurbineFluidPort.class);
        init.register(TileEntityTurbinePartGlass.class);
        init.register(TileEntityTurbineRotorBearing.class);
        init.register(TileEntityTurbineRotorShaft.class);
        init.register(TileEntityTurbineRotorBlade.class);
        init.register(TileEntityTurbineCreativeSteamGenerator.class);
        init.register(TileEntityTurbineComputerPort.class);
        init.register(TileEntityTurbineController.class);
    }
}
