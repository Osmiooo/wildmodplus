package wild.mod.plus.entity;

import net.minecraft.block.NoteBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import wild.mod.plus.liukrastapi.AllayTemptGoal;
import wild.mod.plus.liukrastapi.FlyRandomlyGoal;

import java.util.List;
import java.util.function.Predicate;

public class AllayEntity extends FlyingEntity {


    public AllayEntity(EntityType<? extends AllayEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new AllayMoveControl(this);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
    }

    public static final Predicate<ItemEntity> CAN_TAKE = null;

//    public ActionResult interactMob(PlayerEntity player, Hand hand) {
//        ItemStack itemStack = player.getStackInHand(hand);
//        if (itemStack.isItemEqual(Items.COOKIE.getDefaultStack())) {
//            if (!player.getAbilities().creativeMode) {
//                itemStack.decrement(1);
//            }
//            this.setOwner(player);
//            this.navigation.stop();
//            this.setTarget(null);
//            this.setSitting(true);
//            this.world.sendEntityStatus(this, (byte) 7);
//
//            return ActionResult.SUCCESS;
//        }
//        return super.interactMob(player, hand);
//    }




    private int eatingTime;

    protected void initGoals() {

        this.goalSelector.add(3, new FlyRandomlyGoal(this));
        this.setCanPickUpLoot(true);
        List<ItemEntity> list = AllayEntity.this.world.getEntitiesByClass(ItemEntity.class, AllayEntity.this.getBoundingBox().expand(8.0D, 8.0D, 8.0D), AllayEntity.CAN_TAKE);
        this.goalSelector.add(4, new AllayTemptGoal(this, 0.4D, Ingredient.ofItems(new ItemConvertible[]{Items.COOKIE}), false));
      //  this.goalSelector.add(4, new TemptGoal(this, 1.0D, Ingredient.ofItems(new ItemConvertible[]{Items.COOKIE}), false));


    }

    public boolean canPickupItem(ItemStack stack) {
        Item item = stack.getItem();
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        return itemStack.isEmpty() || this.eatingTime > 0 && item.isFood() && !itemStack.getItem().isFood();
    }




    public boolean isOnGround() {
        return this.onGround;
    }

//    @Nullable
//    @Override
//    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
//        return null;
//    }


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


//        BlockPos blockPos = new BlockPos(10, 10, 10);
//
//        Box box = (new Box(blockPos.add(-10, -10, -10), blockPos.add(10, 10, 10)));
//        List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
//        Iterator<PlayerEntity> var11 = list.iterator();
//        PlayerEntity playerEntity;
//            while (var11.hasNext()) {
//            playerEntity = var11.next();
//            if (playerEntity.getBlockPos().isWithinDistance(blockPos, (dist + 1))) {
//                playerEntity.addStatusEffect(new StatusEffectInstance(RegisterStatusEffects.DARKNESS, 300, 0, true, false, false));



//    }
//}}}
















