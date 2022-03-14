package wild.mod.plus.frozenblockapi;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.Item;
import wild.mod.plus.entity.AllayEntity;

import java.util.List;

public class AllayPickUpItemGoal extends Goal {
    private final AllayEntity entity;
    public ItemEntity pickUp;

    public AllayPickUpItemGoal(AllayEntity entity) {this.entity = entity;}

    @Override
    public boolean canStart() {
        double distance = 999;
        ItemEntity closestItem = null;
        List<ItemEntity> items = entity.world.getNonSpectatingEntities(ItemEntity.class, entity.getBoundingBox().expand(4D, 4D, 4D));
        for (ItemEntity itemEntity : items) {
            Item item = itemEntity.getStack().getItem();
            if (item==entity.getMainHandStack().getItem()) {
                double squaredDistance = entity.squaredDistanceTo(itemEntity);
                if (squaredDistance<distance) {
                    distance = squaredDistance;
                    closestItem = itemEntity;
                }
            }
        } pickUp = closestItem;
        if (pickUp!=null) {
            return entity.squaredDistanceTo(pickUp) < 2 && !pickUp.getStack().isEmpty() && (pickUp.getStack().getItem()==entity.getOffHandStack().getItem() || entity.getOffHandStack().isEmpty()) && !entity.getMainHandStack().isEmpty();
        } return false;
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

    @Override
    public void start() {
        if (pickUp!=null) {
            Item item = pickUp.getStack().getItem();
            if (entity.getOffHandStack().isEmpty()) {
                entity.sendPickup(pickUp, pickUp.getStack().getCount());
                entity.equipStack(EquipmentSlot.OFFHAND, pickUp.getStack());
                pickUp.getStack().decrement(pickUp.getStack().getCount());
            } else if (item==entity.getOffHandStack().getItem()) {
                int max = entity.getOffHandStack().getMaxCount();
                int amount = pickUp.getStack().getCount();
                int able = max-amount;
                if (amount<able) {
                    entity.sendPickup(pickUp, amount);
                    entity.getOffHandStack().increment(amount);
                    pickUp.getStack().decrement(amount);
                } else {
                    entity.sendPickup(pickUp, able);
                    entity.getOffHandStack().increment(able);
                    pickUp.getStack().decrement(able);
                }
            }
        }
    }

}