package wild.mod.plus.frozenblockapi;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import wild.mod.plus.entity.AllayEntity;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class AllayTrackItemGoal extends Goal {
    private final AllayEntity entity;
    private List<ItemEntity> items = null;
    Item searchFor = null;
    ItemEntity target = null;

    public AllayTrackItemGoal(AllayEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canStart() {
        double distance = 999;
        ItemEntity closestItem = null;
        if (!entity.getMainHandStack().isEmpty() && entity.ignoranceTime<=0 && ((entity.getOffHandStack().getMaxCount()>entity.getOffHandStack().getCount())) || entity.getOffHandStack().isEmpty()) {
            searchFor = entity.getMainHandStack().getItem();
            items = entity.world.getNonSpectatingEntities(ItemEntity.class, entity.getBoundingBox().expand(8.0D, 8.0D, 8.0D));
            for (ItemEntity itemEntity : items) {
                Item item = itemEntity.getStack().getItem();
                if (item==searchFor) {
                    double squaredDistance = entity.squaredDistanceTo(itemEntity);
                    if (squaredDistance<distance) {
                        distance = squaredDistance;
                        closestItem = itemEntity;
                    }
                }
            }
        }
        if (closestItem!=null) {
            target=closestItem;
            return true;
        } return false;
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

    @Override
    public void start() {
        if (target!=null) {
            entity.getNavigation().startMovingTo(target, entity.speed);
            entity.ignoranceTime = 120;
            entity.getLookControl().lookAt(target);
        }
    }

}