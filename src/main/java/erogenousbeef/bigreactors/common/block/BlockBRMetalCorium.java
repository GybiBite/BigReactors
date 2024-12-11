package erogenousbeef.bigreactors.common.block;

import java.util.List;
import java.util.Random;

import erogenousbeef.bigreactors.init.BrBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBRMetalCorium extends BlockBRMetal {

	private static final int RAD_RANGE = 2;

	public BlockBRMetalCorium(String blockName, String oreDictionaryName) {
		super(blockName, oreDictionaryName);
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, BrBlocks.blockCorium, 10);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		BlockPos[] corners = new BlockPos[] {
				new BlockPos(pos.getX() - RAD_RANGE, pos.getY() - RAD_RANGE, pos.getZ() - RAD_RANGE),
				new BlockPos(pos.getX() + RAD_RANGE, pos.getY() + RAD_RANGE, pos.getZ() + RAD_RANGE) };

		List<EntityLivingBase> nearbys = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(corners[0], corners[1]));

		for (EntityLivingBase e : nearbys) {
			e.addPotionEffect(new PotionEffect(MobEffects.WITHER, 200, 0, false, true));
		}
		
		worldIn.scheduleUpdate(pos, BrBlocks.blockCorium, 10);
	}
}
