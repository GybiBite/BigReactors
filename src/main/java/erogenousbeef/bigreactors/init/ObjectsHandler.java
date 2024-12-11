package erogenousbeef.bigreactors.init;

import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.block.BlockBRFluidCorium;
import erogenousbeef.bigreactors.common.block.BlockBRGenericFluid;
import erogenousbeef.bigreactors.common.block.BlockBRMetal;
import erogenousbeef.bigreactors.common.block.BlockBRMetalCorium;
import erogenousbeef.bigreactors.common.block.BlockBROre;
import erogenousbeef.bigreactors.common.item.ItemGeneric;
import erogenousbeef.bigreactors.common.item.ItemWrench;
import erogenousbeef.bigreactors.common.multiblock.PartType;
import erogenousbeef.bigreactors.common.multiblock.PowerSystem;
import erogenousbeef.bigreactors.common.multiblock.block.*;
import erogenousbeef.bigreactors.common.multiblock.tileentity.*;
import erogenousbeef.bigreactors.common.multiblock.tileentity.creative.TileEntityReactorCreativeCoolantPort;
import erogenousbeef.bigreactors.common.multiblock.tileentity.creative.TileEntityTurbineCreativeSteamGenerator;
import it.zerono.mods.zerocore.lib.config.ConfigHandler;
import it.zerono.mods.zerocore.lib.data.fixer.BlockReplacer;
import it.zerono.mods.zerocore.lib.data.fixer.ItemReplacer;
import it.zerono.mods.zerocore.lib.data.fixer.TileEntityNameFixer;
import it.zerono.mods.zerocore.lib.init.GameObjectsHandler;
import it.zerono.mods.zerocore.lib.data.fixer.IGameObjectDataWalker;
import it.zerono.mods.zerocore.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import zero.temp.RecipeHelper2;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class ObjectsHandler extends GameObjectsHandler {

    public ObjectsHandler(ConfigHandler... configs) {

        super(DATA_VERSION, configs);

        this._blockReplacer = new BlockReplacer(DATA_VERSION);
        this._itemReplacer = new ItemReplacer(DATA_VERSION);
        this._teFixer = new TileEntityNameFixer(DATA_VERSION, "minecraft:bigreactors");
        this.addBlockRemapper(this._blockReplacer);
        this.addItemRemapper(this._itemReplacer);
    }

    @Override
    protected void onRegisterBlocks(@Nonnull IForgeRegistry<Block> registry) {

        final boolean regCreativeParts = BigReactors.CONFIG.registerCreativeMultiblockParts;

        // make sure ore fluids are registered as well..
        BrFluids.initialize();

        // Ores
        registry.register(new BlockBROre("oreyellorite", "oreYellorite"));
        registry.register(new BlockBROre("oreanglesite", "oreAnglesite"));
        registry.register(new BlockBROre("orebenitoite", "oreBenitoite" ));

        // Metal blocks
        registry.register(new BlockBRMetal("blockyellorium", "blockYellorium"));
        registry.register(new BlockBRMetal("blockcyanite", "blockCyanite"));
        registry.register(new BlockBRMetal("blockgraphite", "blockGraphite"));
        registry.register(new BlockBRMetal("blockblutonium", "blockBlutonium"));
        registry.register(new BlockBRMetal("blockludicrite", "blockLudicrite"));
        registry.register(new BlockBRMetal("blocksteel", "blockSteel"));
        registry.register(new BlockBRMetalCorium("blockcorium", "blockCorium"));

        // Reactor parts
        registry.register(new BlockMultiblockCasing(PartType.ReactorCasing, "reactorcasing"));
        registry.register(new BlockMultiblockGlass(PartType.ReactorGlass, "reactorglass"));
        registry.register(new BlockMultiblockController(PartType.ReactorController, "reactorcontroller"));
        registry.register(new BlockMultiblockPowerTap(PartType.ReactorPowerTap, "reactorpowertaprf", PowerSystem.RedstoneFlux));
        registry.register(new BlockMultiblockPowerTap(PartType.ReactorPowerTap, "reactorpowertaptesla", PowerSystem.Tesla));
        registry.register(new BlockMultiblockIOPort(PartType.ReactorAccessPort, "reactoraccessport"));
        registry.register(new BlockMultiblockIOPort(PartType.ReactorCoolantPort, "reactorcoolantport"));
        registry.register(new BlockReactorControlRod("reactorcontrolrod"));
        registry.register(new BlockReactorRedNetPort("reactorrednetport"));
        registry.register(new BlockMultiblockComputerPort(PartType.ReactorComputerPort, "reactorcomputerport"));
        registry.register(new BlockReactorRedstonePort("reactorredstoneport"));
        registry.register(new BlockReactorFuelRod("reactorfuelrod"));

        if (regCreativeParts)
            registry.register(new BlockMultiblockIOPort(PartType.ReactorCreativeCoolantPort, "reactorcreativecoolantport"));

        // Turbine parts
        registry.register(new BlockMultiblockGlass(PartType.TurbineGlass, "turbineglass"));
        registry.register(new BlockMultiblockCasing(PartType.TurbineHousing, "turbinehousing"));
        registry.register(new BlockMultiblockController(PartType.TurbineController, "turbinecontroller"));
        registry.register(new BlockMultiblockPowerTap(PartType.TurbinePowerPort, "turbinepowertaprf", PowerSystem.RedstoneFlux));
        registry.register(new BlockMultiblockPowerTap(PartType.TurbinePowerPort, "turbinepowertaptesla", PowerSystem.Tesla));
        registry.register(new BlockMultiblockComputerPort(PartType.TurbineComputerPort, "turbinecomputerport"));
        registry.register(new BlockMultiblockIOPort(PartType.TurbineFluidPort, "turbinefluidport"));
        registry.register(new BlockTurbineRotorBearing("turbinebearing"));
        registry.register(new BlockTurbineRotorShaft("turbinerotorshaft"));
        registry.register(new BlockTurbineRotorBlade("turbinerotorblade"));

        if (regCreativeParts)
            registry.register(new BlockMultiblockIOPort(PartType.TurbineCreativeSteamGenerator, "turbinecreativesteamgenerator"));

        // - devices
        //deviceCyaniteRep = (BlockBRDevice)init.register(new BlockBRDevice(DeviceType.CyaniteReprocessor, "deviceCyaniteRep"));

        // Fluid blocks
        registry.register(new BlockBRGenericFluid(BrFluids.fluidYellorium, "yellorium", new MaterialLiquid(MapColor.YELLOW)));
        registry.register(new BlockBRGenericFluid(BrFluids.fluidCyanite, "cyanite", Material.LAVA));
        registry.register(new BlockBRFluidCorium());
    }

    @Override
    protected void registerTileEntity(@Nonnull Class<? extends TileEntity> tileEntityClass,
                                      @Nullable final IGameObjectDataWalker walker) {

        super.registerTileEntity(tileEntityClass, walker);

        final String name = tileEntityClass.getSimpleName().toLowerCase(Locale.ROOT);

        this._teFixer.addReplacement("minecraft:bigreactors" + name, new ResourceLocation(this.getModId(), name));
    }

    @Override
    protected void onRegisterTileEntities() {

        this.registerTileEntity(TileEntityReactorPart.class);
        this.registerTileEntity(TileEntityReactorGlass.class);
        this.registerTileEntity(TileEntityReactorController.class);
        this.registerTileEntity(TileEntityReactorPowerTapRedstoneFlux.class);
        this.registerTileEntity(TileEntityReactorPowerTapTesla.class);
        this.registerTileEntity(TileEntityReactorAccessPort.class, TileEntityReactorAccessPort.getObjectDataWalker());
        this.registerTileEntity(TileEntityReactorFuelRod.class);
        this.registerTileEntity(TileEntityReactorControlRod.class);
        this.registerTileEntity(TileEntityReactorRedstonePort.class);
        this.registerTileEntity(TileEntityReactorComputerPort.class);
        this.registerTileEntity(TileEntityReactorCoolantPort.class);
        this.registerTileEntity(TileEntityReactorCreativeCoolantPort.class);
        this.registerTileEntity(TileEntityReactorRedNetPort.class);

        this.registerTileEntity(TileEntityTurbinePart.class);
        this.registerTileEntity(TileEntityTurbinePowerTapRedstoneFlux.class);
        this.registerTileEntity(TileEntityTurbinePowerTapTesla.class);
        this.registerTileEntity(TileEntityTurbineFluidPort.class);
        this.registerTileEntity(TileEntityTurbinePartGlass.class);
        this.registerTileEntity(TileEntityTurbineRotorBearing.class);
        this.registerTileEntity(TileEntityTurbineRotorShaft.class);
        this.registerTileEntity(TileEntityTurbineRotorBlade.class);
        this.registerTileEntity(TileEntityTurbineCreativeSteamGenerator.class);
        this.registerTileEntity(TileEntityTurbineComputerPort.class);
        this.registerTileEntity(TileEntityTurbineController.class);
    }

    @Override
    protected void onRegisterItems(@Nonnull IForgeRegistry<Item> registry) {

        // Ingots
        registry.register(new ItemGeneric("ingotyellorium", "ingotYellorium"));
        registry.register(new ItemGeneric("ingotcyanite", "ingotCyanite"));
        registry.register(new ItemGeneric("ingotgraphite", "ingotGraphite"));
        registry.register(new ItemGeneric("ingotblutonium", "ingotBlutonium"));
        registry.register(new ItemGeneric("ingotludicrite", "ingotLudicrite"));
        registry.register(new ItemGeneric("ingotsteel", "ingotSteel"));

        // Dusts
        registry.register(new ItemGeneric("dustyellorium", "dustYellorium"));
        registry.register(new ItemGeneric("dustcyanite", "dustCyanite"));
        registry.register(new ItemGeneric("dustgraphite", "dustGraphite"));
        registry.register(new ItemGeneric("dustblutonium", "dustBlutonium"));
        registry.register(new ItemGeneric("dustludicrite", "dustLudicrite"));
        registry.register(new ItemGeneric("duststeel", "dustSteel"));

        // Minerals
        registry.register(new ItemGeneric("mineralanglesite"));
        registry.register(new ItemGeneric("mineralbenitoite"));

        // Reactor components
        registry.register(new ItemGeneric("reactorcasingcores"));

        // Turbine components
        registry.register(new ItemGeneric("turbinehousingcores"));

        // Miscellanea
        registry.register(new ItemWrench("wrench"));
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onRegisterOreDictionaryEntries() {

        // oreYellorium : for convenience of mods which fiddle with recipes
        OreDictionary.registerOre("oreYellorium", BrBlocks.oreYellorite.createItemStack());

        // Uranium and Plutonium aliases
        if (BigReactors.CONFIG.registerYelloriumAsUranium) {

            OreDictionary.registerOre("ingotUranium", ((ItemGeneric)this.getTrackedItem("ingotyellorium")).createItemStack());
            OreDictionary.registerOre("dustUranium", ((ItemGeneric)this.getTrackedItem("dustyellorium")).createItemStack());

            OreDictionary.registerOre("ingotPlutonium", ((ItemGeneric)this.getTrackedItem("ingotblutonium")).createItemStack());
            OreDictionary.registerOre("dustPlutonium", ((ItemGeneric)this.getTrackedItem("dustblutonium")).createItemStack());
        }

        // Patch up vanilla being stupid - most mods already do this, so it's usually a no-op

        if (!OreDictionaryHelper.doesOreNameExist("blockSnow"))
            OreDictionary.registerOre("blockSnow", new ItemStack(Blocks.SNOW, 1));

        if (!OreDictionaryHelper.doesOreNameExist("blockIce"))
            OreDictionary.registerOre("blockIce", new ItemStack(Blocks.ICE, 1));
    }

    /**
     * Register all the recipes for the blocks and items of this mod
     * Override in your subclass to register your recipes with the provided registry
     *
     * @param registry the recipes registry
     */
    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onRegisterRecipes(@Nonnull IForgeRegistry<IRecipe> registry) {

        // # Yellorite ore -> Yellorium ingot

        ItemStack result;

        if (BigReactors.CONFIG.registerYelloriteSmeltToUranium) {

            result = OreDictionaryHelper.getOre("ingotUranium");

            if (null == result) {

                BigReactors.getLogger().warn("Config value registerYelloriteSmeltToUranium is set to True, but there are no ores registered as ingotUranium in the ore dictionary! Falling back to using standard yellorium only.");
                result = OreDictionaryHelper.getOre("ingotYellorium");
            }

        } else {

            result = OreDictionaryHelper.getOre("ingotYellorium");
        }

        RecipeHelper2.addSmelting(BrBlocks.oreYellorite.createItemStack(), result, 0.5f);

        // Dusts -> ingots

        RecipeHelper2.addSmelting(BrItems.dustYellorium.createItemStack(), BrItems.ingotYellorium.createItemStack(), 0.0f);
        RecipeHelper2.addSmelting(BrItems.dustCyanite.createItemStack(), BrItems.ingotCyanite.createItemStack(), 0.0f);
        RecipeHelper2.addSmelting(BrItems.dustGraphite.createItemStack(), BrItems.ingotGraphite.createItemStack(), 0.0f);
        RecipeHelper2.addSmelting(BrItems.dustBlutonium.createItemStack(), BrItems.ingotBlutonium.createItemStack(), 0.0f);
        RecipeHelper2.addSmelting(BrItems.dustLudicrite.createItemStack(), BrItems.ingotLudicrite.createItemStack(), 0.0f);
        RecipeHelper2.addSmelting(BrItems.dustSteel.createItemStack(), BrItems.ingotSteel.createItemStack(), 0.0f);

        // Graphite

        result = OreDictionaryHelper.getOre("ingotGraphite");

        // # Coal -> Graphite
        if (BigReactors.CONFIG.registerCoalForSmelting) {
            RecipeHelper2.addSmelting(new ItemStack(Items.COAL, 1, 0), result, 1);
        }

        // # Charcoal -> Graphite
        if (BigReactors.CONFIG.registerCharcoalForSmelting) {
            RecipeHelper2.addSmelting(new ItemStack(Items.COAL, 1, 1), result, 1);
        }

    }

    /**
     * Register all your IDataFixer and IDataWalker
     */
    @Override
    protected void onRegisterDataFixers() {

        super.onRegisterDataFixers();

        this.registerDataFixer(FixTypes.CHUNK, this._blockReplacer);
        this.registerDataFixer(FixTypes.ITEM_INSTANCE, this._itemReplacer);
        this.registerDataFixer(FixTypes.BLOCK_ENTITY, this._teFixer);
        this.registerMissingBlocksReplacements();
        this.registerMissingItemsReplacements();
    }

    @SuppressWarnings("ConstantConditions")
    private void registerMissingBlocksReplacements() {

        ResourceLocation missingId;

        missingId = BigReactors.createResourceLocation("brore");
        this._blockReplacer.addReplacement(missingId, 0, BrBlocks.oreYellorite);
        this._blockReplacer.addReplacement(missingId, 1, BrBlocks.oreAnglesite);
        this._blockReplacer.addReplacement(missingId, 2, BrBlocks.oreBenitoite);

        missingId = BigReactors.createResourceLocation("blockmetals");
        this._blockReplacer.addReplacement(missingId, 0, BrBlocks.blockYellorium);
        this._blockReplacer.addReplacement(missingId, 1, BrBlocks.blockCyanite);
        this._blockReplacer.addReplacement(missingId, 2, BrBlocks.blockGraphite);
        this._blockReplacer.addReplacement(missingId, 3, BrBlocks.blockBlutonium);
        this._blockReplacer.addReplacement(missingId, 4, BrBlocks.blockLudicrite);
        this._blockReplacer.addReplacement(missingId, 5, BrBlocks.blockSteel);
        this._blockReplacer.addReplacement(missingId, 6, BrBlocks.blockCorium);
    }

    @SuppressWarnings("ConstantConditions")
    private void registerMissingItemsReplacements() {

        ResourceLocation missingId;

        missingId = BigReactors.createResourceLocation("brore");
        this._itemReplacer.addReplacement(missingId, 0, Item.getItemFromBlock(BrBlocks.oreYellorite));
        this._itemReplacer.addReplacement(missingId, 1, Item.getItemFromBlock(BrBlocks.oreAnglesite));
        this._itemReplacer.addReplacement(missingId, 2, Item.getItemFromBlock(BrBlocks.oreBenitoite));

        missingId = BigReactors.createResourceLocation("minerals");
        this._itemReplacer.addReplacement(missingId, 0, BrItems.mineralAnglesite);
        this._itemReplacer.addReplacement(missingId, 1, BrItems.mineralBenitoite);

        missingId = BigReactors.createResourceLocation("blockmetals");
        this._itemReplacer.addReplacement(missingId, 0, Item.getItemFromBlock(BrBlocks.blockYellorium));
        this._itemReplacer.addReplacement(missingId, 1, Item.getItemFromBlock(BrBlocks.blockCyanite));
        this._itemReplacer.addReplacement(missingId, 2, Item.getItemFromBlock(BrBlocks.blockGraphite));
        this._itemReplacer.addReplacement(missingId, 3, Item.getItemFromBlock(BrBlocks.blockBlutonium));
        this._itemReplacer.addReplacement(missingId, 4, Item.getItemFromBlock(BrBlocks.blockLudicrite));
        this._itemReplacer.addReplacement(missingId, 5, Item.getItemFromBlock(BrBlocks.blockSteel));
        this._itemReplacer.addReplacement(missingId, 6, Item.getItemFromBlock(BrBlocks.blockCorium));
        
        missingId = BigReactors.createResourceLocation("ingotmetals");
        this._itemReplacer.addReplacement(missingId, 0, BrItems.ingotYellorium);
        this._itemReplacer.addReplacement(missingId, 1, BrItems.ingotCyanite);
        this._itemReplacer.addReplacement(missingId, 2, BrItems.ingotGraphite);
        this._itemReplacer.addReplacement(missingId, 3, BrItems.ingotBlutonium);
        this._itemReplacer.addReplacement(missingId, 4, BrItems.ingotLudicrite);
        this._itemReplacer.addReplacement(missingId, 5, BrItems.ingotSteel);

        missingId = BigReactors.createResourceLocation("dustmetals");
        this._itemReplacer.addReplacement(missingId, 0, BrItems.dustYellorium);
        this._itemReplacer.addReplacement(missingId, 1, BrItems.dustCyanite);
        this._itemReplacer.addReplacement(missingId, 2, BrItems.dustGraphite);
        this._itemReplacer.addReplacement(missingId, 3, BrItems.dustBlutonium);
        this._itemReplacer.addReplacement(missingId, 4, BrItems.dustLudicrite);
        this._itemReplacer.addReplacement(missingId, 5, BrItems.dustSteel);
    }

    private static final int DATA_VERSION = 1;

    private final BlockReplacer _blockReplacer;
    private final ItemReplacer _itemReplacer;
    private final TileEntityNameFixer _teFixer;
}
