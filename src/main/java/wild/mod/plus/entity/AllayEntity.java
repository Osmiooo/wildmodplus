package wild.mod.plus.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import wild.mod.plus.frozenblockapi.FlyRandomlyGoal;

import java.util.List;
import java.util.function.Predicate;

public class AllayEntity extends FlyingEntity {
    public boolean hasItem;
    public static final Predicate<ItemEntity> CAN_TAKE = null;

    public AllayEntity(EntityType<? extends AllayEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AllayEntity.AllayMoveControl(this);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
    }

    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        int s = this.activeItemStack.getCount();
        if ((!itemStack.isEmpty() && !hasItem)) {
            this.hasItem = true;
            if (!this.world.isClient) {
                this.world.playSound(null, this.getBlockPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.NEUTRAL, 1F, 1F);
            }

            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            this.navigation.stop();
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(itemStack.getItem()));
            this.world.sendEntityStatus(this, (byte) 7);

            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }

    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new FlyRandomlyGoal(this));
        this.setCanPickUpLoot(true);
        List<ItemEntity> list = AllayEntity.this.world.getEntitiesByClass(ItemEntity.class, AllayEntity.this.getBoundingBox().expand(8.0D, 8.0D, 8.0D), AllayEntity.CAN_TAKE);


    }

    public boolean canPickupItem(ItemStack stack) {
        Item item = stack.getItem();
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        return itemStack.isEmpty() && item.isFood() && !itemStack.getItem().isFood();
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("hasItem", this.hasItem);
    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.hasItem = nbt.getBoolean("hasItem");
    }

    static class AllayMoveControl extends MoveControl {
        private final AllayEntity allay;
        private int collisionCheckCooldown;


        public AllayMoveControl(AllayEntity allay) {
            super(allay);
            this.allay = allay;
        }

        public void tick() {
            if (this.state == MoveControl.State.MOVE_TO) {
                if (this.collisionCheckCooldown-- <= 0) {
                    this.collisionCheckCooldown += this.allay.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.allay.getX(), this.targetY - this.allay.getY(), this.targetZ - this.allay.getZ());
                    double d = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.willCollide(vec3d, MathHelper.ceil(d))) {
                        this.allay.setVelocity(this.allay.getVelocity().add(vec3d.multiply(0.1D)));
                    } else {
                        this.state = MoveControl.State.WAIT;


                    }
                }

            }

        }


        private boolean willCollide(Vec3d direction, int steps) {
            Box box = this.allay.getBoundingBox();

            for (int i = 1; i < steps; ++i) {
                box = box.offset(direction);
                if (!this.allay.world.isSpaceEmpty(this.allay, box)) {
                    return false;
                }
            }

            return true;
        }}

}