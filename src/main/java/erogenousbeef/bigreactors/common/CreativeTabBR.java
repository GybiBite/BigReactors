package erogenousbeef.bigreactors.common;

import erogenousbeef.bigreactors.common.compat.CompatManager;
import erogenousbeef.bigreactors.init.BrBlocks;
import erogenousbeef.bigreactors.init.BrItems;
import it.zerono.mods.zerocore.lib.compat.ModIDs;
import it.zerono.mods.zerocore.util.CodeHelper;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CreativeTabBR extends CreativeTabs {

	public CreativeTabBR(String label) {
		super(label);
	}

	@SideOnly(Side.CLIENT)
	public ItemStack createIcon() {
		return BrBlocks.oreYellorite.createItemStack();
	}

	/**
	 * only shows items which have tabToDisplayOn == this
	 *
	 * @param list
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {

        CompatManager.ifPresent(ModIDs.MODID_PATCHOULI, () -> list.add(PatchouliAPI.instance.getBookStack("bigreactors:erguide")));

		this.addToDisplayList(list, BrBlocks.oreYellorite);
        this.addToDisplayList(list, BrBlocks.oreAnglesite);
        this.addToDisplayList(list, BrBlocks.oreBenitoite);
		this.addToDisplayList(list, BrItems.mineralAnglesite);
		this.addToDisplayList(list, BrItems.mineralBenitoite);

		this.addToDisplayList(list, BrItems.ingotYellorium);
		this.addToDisplayList(list, BrItems.ingotCyanite);
		this.addToDisplayList(list, BrItems.ingotGraphite);
		this.addToDisplayList(list, BrItems.ingotBlutonium);
		this.addToDisplayList(list, BrItems.ingotLudicrite);
		this.addToDisplayList(list, BrItems.ingotSteel);

		this.addToDisplayList(list, BrItems.dustYellorium);
		this.addToDisplayList(list, BrItems.dustCyanite);
		this.addToDisplayList(list, BrItems.dustGraphite);
		this.addToDisplayList(list, BrItems.dustBlutonium);
		this.addToDisplayList(list, BrItems.dustLudicrite);
		this.addToDisplayList(list, BrItems.dustSteel);

		this.addToDisplayList(list, BrBlocks.blockYellorium);
		this.addToDisplayList(list, BrBlocks.blockCyanite);
		this.addToDisplayList(list, BrBlocks.blockGraphite);
		this.addToDisplayList(list, BrBlocks.blockBlutonium);
		this.addToDisplayList(list, BrBlocks.blockLudicrite);
		this.addToDisplayList(list, BrBlocks.blockSteel);
		this.addToDisplayList(list, BrBlocks.blockCorium);
		this.addToDisplayList(list, BrItems.wrench);

		// Reactor parts

		this.addToDisplayList(list, BrItems.reactorCasingCores);
		this.addToDisplayList(list, BrBlocks.reactorCasing);
		this.addToDisplayList(list, BrBlocks.reactorGlass);
		this.addToDisplayList(list, BrBlocks.reactorController);
		this.addToDisplayList(list, BrBlocks.reactorFuelRod);
		this.addToDisplayList(list, BrBlocks.reactorControlRod);
		this.addToDisplayList(list, BrBlocks.reactorPowerTapRF);
		this.addToDisplayList(list, BrBlocks.reactorPowerTapTesla);
		this.addToDisplayList(list, BrBlocks.reactorAccessPort);
		this.addToDisplayList(list, BrBlocks.reactorCoolantPort);
		this.addToDisplayList(list, BrBlocks.reactorCreativeCoolantPort);
		this.addToDisplayList(list, BrBlocks.reactorComputerPort);
		this.addToDisplayList(list, BrBlocks.reactorRedstonePort);
		this.addToDisplayList(list, BrBlocks.reactorRedNetPort);

		// Turbine parts

		this.addToDisplayList(list, BrItems.turbineHousingCores);
		this.addToDisplayList(list, BrBlocks.turbineHousing);
		this.addToDisplayList(list, BrBlocks.turbineGlass);
		this.addToDisplayList(list, BrBlocks.turbineController);
		this.addToDisplayList(list, BrBlocks.turbineBearing);
		this.addToDisplayList(list, BrBlocks.turbineRotorShaft);
		this.addToDisplayList(list, BrBlocks.turbineRotorBlade);
		this.addToDisplayList(list, BrBlocks.turbinePowerTapRF);
		this.addToDisplayList(list, BrBlocks.turbinePowerTapTesla);
		this.addToDisplayList(list, BrBlocks.turbineFluidPort);
		this.addToDisplayList(list, BrBlocks.turbineCreativeSteamGenerator);
		this.addToDisplayList(list, BrBlocks.turbineComputerPort);
	}

	@SideOnly(Side.CLIENT)
	private void addToDisplayList(@Nonnull NonNullList<ItemStack> list, @Nullable Block block) {

		if (null != block)
			block.getSubBlocks(this, list);
	}

	@SideOnly(Side.CLIENT)
	private void addToDisplayList(@Nonnull NonNullList<ItemStack> list, @Nullable Item item) {

		if (null != item)
			item.getSubItems(this, list);
	}
}
