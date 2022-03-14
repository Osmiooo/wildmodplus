package wild.mod.plus.frozenblockapi;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Item;
import wild.mod.plus.entity.AllayEntity;

import java.util.List;

public class AllayTrackItemGoal extends Goal {
    private final AllayEntity entity;
    Entity targeted = null;

    public AllayTrackItemGoal(AllayEntity entity) {this.entity = entity;}

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    }

    @Override
    public boolean canStart() {
        double distance = 999;
        ItemEntity closestItem = null;
        if (!entity.getMainHandStack().isEmpty() && entity.ignoranceTime<=0 && ((entity.getOffHandStack().getMaxCount()>entity.getOffHandStack().getCount()) || entity.getOffHandStack().isEmpty())) {
            Item searchFor = entity.getMainHandStack().getItem();
            List<ItemEntity> items = entity.world.getNonSpectatingEntities(ItemEntity.class, entity.getBoundingBox().expand(24.0D, 24.0D, 24.0D));
            for (ItemEntity itemEntity : items) {
                Item item = itemEntity.getStack().getItem();
                if (item==searchFor) {
                    double squaredDistance = entity.squaredDistanceTo(itemEntity);
                    if (squaredDistance<distance) {
                        distance = squaredDistance;
                        closestItem = itemEntity;
                        entity.targetEntity = itemEntity.getId();
                    }
                }
            }
        }
        if (closestItem!=null) {
            entity.targetEntity = closestItem.getId();
            targeted = closestItem;
            return true;
        } return false;
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

    @Override
    public void start() {
        if (targeted!=null) {
            if (targeted instanceof ItemEntity) {
                entity.getNavigation().startMovingTo(targeted, entity.speed);
                this.entity.getMoveControl().moveTo(targeted.getX(), targeted.getY()+0.5D, targeted.getZ(), 0.3);
                entity.ignoranceTime = 40;
                entity.getLookControl().lookAt(targeted);
            }
        }
    }

}