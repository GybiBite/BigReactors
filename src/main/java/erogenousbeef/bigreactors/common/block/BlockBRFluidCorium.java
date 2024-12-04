package erogenousbeef.bigreactors.common.block;

import java.util.Random;

import erogenousbeef.bigreactors.init.BrBlocks;
import erogenousbeef.bigreactors.init.BrFluids;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBRFluidCorium extends BlockBRGenericFluid {

	public BlockBRFluidCorium() {
		super(BrFluids.fluidCorium, "corium", Material.LAVA);
		this.setQuantaPerBlock(4);
		this.setTickRandomly(true);
	}

	// Give mobs/players touching Corium Wither and Glowing, while damaging them
	@Override
	public void onEntityCollision(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase e = (EntityLivingBase) entity;
			e.addPotionEffect(new PotionEffect(MobEffects.WITHER, 600, 3, false, true));
			e.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 600, 0, false, false));
			e.attackEntityFrom(DamageSource.ON_FIRE, 3);
		}
	}

	/*
	 * If block is source block:
	 *    Trigger 1:6 of solidifying
	 *    Return
	 *    
	 * If block below *isn't* bedrock:
	 *    Set block below to air
	 * 
	 * Hopefully no exploit from other mods with really hard blocks that aren't
	 * supposed to be broken easily? There probably are but that isn't my problem
	 */
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		
		 final IBlockState CORIUM_SOURCE = BrFluids.fluidCorium.getBlock().getDefaultState();
		 final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
		 final IBlockState CORIUM = BrBlocks.blockCorium.getDefaultState();
		 
		 final IBlockState UNDER = worldIn.getBlockState(pos.down());
		 final IBlockState HERE = worldIn.getBlockState(pos);
		
		if(HERE.equals(CORIUM_SOURCE)) {
			if(rand.nextInt(6) < 1) {
				solidifyCorium(worldIn, pos);
				return;
			}
		}
		
		if (!UNDER.equals(BEDROCK) || !UNDER.getBlock().equals(BrFluids.fluidCorium.getBlock())
				|| !UNDER.equals(CORIUM)) {
			System.out.println("Setting block " + worldIn.getBlockState(pos.down()));
			worldIn.setBlockToAir(pos.down());
		}
	}
	
	private void solidifyCorium(World worldIn, BlockPos pos) {
		worldIn.setBlockState(pos, BrBlocks.blockCorium.getDefaultState());
		for(EnumFacing face : EnumFacing.values()) {
			if(worldIn.getBlockState(pos.offset(face)).getBlock().equals(BrFluids.fluidCorium.getBlock())) {
				solidifyCorium(worldIn, pos.offset(face));
			}
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		final Block UNDER = worldIn.getBlockState(pos.down()).getBlock();
		
		if(UNDER.equals(Blocks.AIR)) {
			worldIn.setBlockState(pos.down(), state);
			worldIn.setBlockToAir(pos);
		}
		
		super.updateTick(worldIn, pos, state, rand);
	}
	
	
}
