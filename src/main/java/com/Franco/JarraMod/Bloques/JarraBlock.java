package com.Franco.JarraMod.Bloques;

import com.Franco.JarraMod.particulas.ModParticula;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class JarraBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE= Block.box(2,0,2,14,13,14);
    public static final MapCodec<JarraBlock> CODEC=simpleCodec(JarraBlock::new);
    public static final BooleanProperty SHRIEKING = BlockStateProperties.SHRIEKING;

    protected JarraBlock(Properties p_49224_) {
        super(p_49224_);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new JarraBlockEntity(pPos,pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return super.getTicker(pLevel, pState, pBlockEntityType);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock()!=pNewState.getBlock()) {
            if (pLevel.getBlockEntity(pPos) instanceof JarraBlockEntity j) {
                j.dropeo();
                pLevel.updateNeighbourForOutputSignal(pPos, this);
                if (pLevel instanceof ServerLevel s) {
                    double centroX = pPos.getX() + 0.5;
                    double centroY = pPos.getY() + 0.5;
                    double centroZ = pPos.getZ() + 0.5;
                    for (int i = 0; i < 200; i++) {
                        double a = (2 * Math.PI * i) / 200;
                        double dirX = Math.cos(a);
                        double dirZ = Math.sin(a);
                        s.sendParticles (ModParticula.JARRA_PARTICULA.get(), centroX + (dirX * 1.5), centroY, centroZ + (dirZ * 1.5), 0, dirX * 0.4, 0.0, dirZ * 0.4, 1.0);
                    }

                }

            }
        }
        super.onRemove(pState,pLevel,pPos,pNewState,pMovedByPiston);
    }



    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
         if (!pLevel.isClientSide) {
             if (pLevel.getBlockEntity(pPos) instanceof JarraBlockEntity j) {
                 if (pStack.isEmpty()) {
                     j.obtenerLootTable(pPlayer);
                     pPlayer.setItemInHand(InteractionHand.MAIN_HAND,j.inventario.extractItem(j.ultimoItem(),j.inventario.getStackInSlot(j.ultimoItem()).getMaxStackSize(),false));
                     System.out.println(j.ultimoItem());
                     pLevel.playSound(pPlayer,pPos,SoundEvents.ITEM_FRAME_ADD_ITEM,SoundSource.BLOCKS,1f,2f);
                     System.out.println("obtuvo");
                 }else {
                     if (j.hayLugar()) {
                         j.obtenerLootTable(pPlayer);
                         j.inventario.insertItem(j.primerVacio(), pStack.copy(), false);
                         pPlayer.getItemInHand(pHand).shrink(pStack.copy().getMaxStackSize());
                         pLevel.playSound(pPlayer, pPos, SoundEvents.BAMBOO_BREAK, SoundSource.BLOCKS, 1f, 2f);
                         System.out.println("se puso");
                     }
                 }

             }
         }
        return ItemInteractionResult.SUCCESS;
    }


}
