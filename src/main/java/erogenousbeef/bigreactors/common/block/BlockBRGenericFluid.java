package erogenousbeef.bigreactors.common.block;

import erogenousbeef.bigreactors.common.BigReactors;
import it.zerono.mods.zerocore.lib.init.IGameObject;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

public class BlockBRGenericFluid extends BlockFluidClassic implements IGameObject {

	public BlockBRGenericFluid(Fluid fluid, String blockName, Material material) {

		super(fluid, material);
		this.setRegistryName(blockName);
		this.setUnlocalizedName(this.getRegistryName().toString());
        this.setCreativeTab(BigReactors.TAB);
		fluid.setBlock(this);
	}

    @Override
    public void onRegisterItemBlocks(@Nonnull IForgeRegistry<Item> registry) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onRegisterModels() {

		final ModelResourceLocation location = new ModelResourceLocation(BigReactors.MODID + ":fluid",
				this.getRegistryName().getResourcePath());

		ModelLoader.setCustomStateMapper(this, new StateMapperBase() {

			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return location;
			}
		});
	}

	@Override
    public void onRegisterOreDictionaryEntries() {
	}

	@Override
    public void onRegisterRecipes(@Nonnull IForgeRegistry<IRecipe> registry) {
	}

	/**
	 * Returns true if the block at (pos) is displaceable. Does not displace the block.
	 */
	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		return !this.isBlockAtLiquid(world, pos) && super.canDisplace(world, pos);
	}

	/**
	 * Attempt to displace the block at (pos), return true if it was displaced.
	 */
	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		return !this.isBlockAtLiquid(world, pos) && super.displaceIfPossible(world, pos);
	}

	private boolean isBlockAtLiquid(IBlockAccess world, BlockPos pos) {

		IBlockState state = world.getBlockState(pos);

		return state.getBlock().getMaterial(state).isLiquid();
	}
}
