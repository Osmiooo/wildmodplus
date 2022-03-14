package wild.mod.plus.frozenblockapi;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import wild.mod.plus.entity.AllayEntity;

import java.util.EnumSet;
import java.util.Random;

public class AllayFlyRandomlyGoal extends Goal {
    private final AllayEntity entity;

    public AllayFlyRandomlyGoal(AllayEntity entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (entity.ignoranceTime>0) {return false;}
        double f;
        double e;
        MoveControl moveControl = this.entity.getMoveControl();
        if (!moveControl.isMoving()) {
            return true;
        }
        double d = moveControl.getTargetX() - this.entity.getX();
        double g = d * d + (e = moveControl.getTargetY() - this.entity.getY()) * e + (f = moveControl.getTargetZ() - this.entity.getZ()) * f;
        return g < 1.0 || g > 3600.0;
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }

    @Override
    public void start() {
        Random random = this.entity.getRandom();
        double d = this.entity.getX() + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
        double e = this.entity.getY() + (double)((random.nextFloat() * 1.0f - 0.5f) * 10.0f);
        double f = this.entity.getZ() + (double)((random.nextFloat() * 2.0f - 1.0f) * 16.0f);
        this.entity.getMoveControl().moveTo(d, e, f, 0.2);
    }
}