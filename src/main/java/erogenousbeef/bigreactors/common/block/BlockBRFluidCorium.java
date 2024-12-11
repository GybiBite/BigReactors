package erogenousbeef.bigreactors.common.block;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Nonnull;

import erogenousbeef.bigreactors.init.BrBlocks;
import erogenousbeef.bigreactors.init.BrFluids;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
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
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockBRFluidCorium extends BlockBRGenericFluid {
	
	/**
	 * Amount of random ticks to ignore before the fluid can solidify
	 */
	public static final PropertyInteger SOLID_TIMER = PropertyInteger.create("antisolidify", 0, 100);
	
	/**
	 * All blocks in this list will not be melted/eaten by Corium fluid
	 */
	public static final ArrayList<Block> BLOCK_WHITELIST = new ArrayList<Block>();
	
	public BlockBRFluidCorium() {
		super(BrFluids.fluidCorium, "corium", Material.LAVA);
		this.setQuantaPerBlock(4);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 0).withProperty(SOLID_TIMER, 4));
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

	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (worldIn.getBlockState(pos).getValue(LEVEL) == 0) {
			if (state.getValue(SOLID_TIMER) == 0) {
				if (rand.nextInt(4) < 1) {
					solidifyCorium(worldIn, pos);
					return;
				}
			}
			else {
				worldIn.setBlockState(pos, state.withProperty(SOLID_TIMER, state.getValue(SOLID_TIMER) - 1), 0);
			}
		}

		// Don't eat whitelisted blocks
		if (!BLOCK_WHITELIST.contains(worldIn.getBlockState(pos.down()).getBlock())) {
			worldIn.setBlockToAir(pos.down());
		}
	}

	private void solidifyCorium(World worldIn, BlockPos pos) {
		if (!worldIn.isRemote) {
			worldIn.setBlockState(pos, BrBlocks.blockCorium.getDefaultState());
			for (EnumFacing face : EnumFacing.values()) {
				if (worldIn.getBlockState(pos.offset(face)).getBlock().equals(BrFluids.fluidCorium.getBlock())) {
					solidifyCorium(worldIn, pos.offset(face));
				}
			}
		}
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		final Block UNDER = worldIn.getBlockState(pos.down()).getBlock();
		
		if (UNDER.equals(Blocks.AIR)) {
			worldIn.setBlockState(pos.down(), state);
			worldIn.setBlockToAir(pos);
		}
		
		super.updateTick(worldIn, pos, state, rand);
	}
	
	// Fine, I'll have my own block state! With blackjack and timers!
	@Override
    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer.Builder(this)
                .add(LEVEL)
                .add(FLUID_RENDER_PROPS.toArray(new IUnlistedProperty<?>[0]))
                .add(SOLID_TIMER)
                .build();
    }
	
	static {
		BLOCK_WHITELIST.add(Blocks.BEDROCK);
		BLOCK_WHITELIST.add(BrFluids.fluidCorium.getBlock());
	}
}
