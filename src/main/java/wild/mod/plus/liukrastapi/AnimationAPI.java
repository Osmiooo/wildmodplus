package wild.mod.plus.liukrastapi;

import net.minecraft.util.math.MathHelper;

import java.util.Objects;

public class AnimationAPI {

    private static final String errorprfx = "[AnimationAPI] ";

    /*
    ANIMATION API BY LIUKRAST - PRIVATE FOR @FROZENBLOCKSTUDIOS MODS ONLY
     */

    public static float easeInSine(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        if(time >= from && time <= from + to) {
            exit = -size * MathHelper.cos((time-from)/w * (float)Math.PI/2) + size;
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeOutSine(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        if(time >= from && time <= from + to) {
            exit = size * MathHelper.sin((time-from)/w * (float)Math.PI/2);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeInOutSine(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        if(time >= from && time <= from + to) {
            exit = -(size/2) * (MathHelper.cos((float)Math.PI * ((time-from)/w))-1);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeInBack(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        float s = 1.70158f;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(time >= from && time <= from + to) {
            exit = size*(timelessfrom/=w)*timelessfrom*((s+1)*timelessfrom - s);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeOutBack(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        float s = 1.70158f;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(time >= from && time <= from + to) {
            exit = size*((timelessfrom=time/w-1)*timelessfrom*((s+1)*timelessfrom + s) + 1);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeInOutBack(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        float s = 1.70158f;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(time >= from && time <= from + to) {
            if((timelessfrom/=w/2) < 1) {
                exit = size/2*(timelessfrom*timelessfrom*(((s*=(1.525f))+1)*timelessfrom - s));
            }
            else exit = size/2*((timelessfrom-=2)*timelessfrom*(((s*=(1.525f))+1)*timelessfrom + s) + 2);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeInElastic(float from, float to, float size, float time, float amount) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(timelessfrom == 0) return 0; if((timelessfrom/=w)==1) return size;
        float p=w*.3f;
        float s=p/4;
        if(time >= from && time <= from + to) {
            exit = -(amount *(float)Math.pow(2,10*(timelessfrom-=1)) * (float)Math.sin( (timelessfrom*w-s)*(2*(float)Math.PI)/p ));
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeOutElastic(float from, float to, float size, float time, float amount) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(timelessfrom == 0) return 0; if((timelessfrom/=w)==1) return size;
        float p=w*.3f;
        float s=p/4;
        if(time >= from && time <= from + to) {
            exit = (amount *(float)Math.pow(2,-10*timelessfrom) * (float)Math.sin( (timelessfrom*w-s)*(2*(float)Math.PI)/p ) + size);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeInOutElastic(float from, float to, float size, float time, float amount) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(timelessfrom == 0) return 0; if((timelessfrom/=w/2)==2) return size;
        float p=w*(.3f*1.5f);
        float s=p/4;
        if(time >= from && time <= from + to) {
            if(timelessfrom < 1) {
                exit = -.5f*(amount *(float)Math.pow(2,10*(timelessfrom-=1)) * (float)Math.sin( (timelessfrom*w-s)*(2*(float)Math.PI)/p ));
            } else {
                exit = amount *(float)Math.pow(2,-10*(timelessfrom-=1)) * (float)Math.sin( (timelessfrom*w-s)*(2*(float)Math.PI)/p )*.5f + size;
            }
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeInBounce(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(time >= from && time <= from + to) {
            exit = size - easeOutBounce(0, to, w, w-timelessfrom);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeOutBounce(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(time >= from && time <= from + to) {
            if ((timelessfrom/=w) < (1/2.75f)) {
                return size*(7.5625f*timelessfrom*timelessfrom);
            } else if (timelessfrom < (2/2.75f)) {
                return size*(7.5625f*(timelessfrom-=(1.5f/2.75f))*timelessfrom + .75f);
            } else if (timelessfrom < (2.5/2.75)) {
                return size*(7.5625f*(timelessfrom-=(2.25f/2.75f))*timelessfrom + .9375f);
            } else {
                return size*(7.5625f*(timelessfrom-=(2.625f/2.75f))*timelessfrom + .984375f);
            }
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float easeInOutBounce(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(time >= from && time <= from + to) {
            if(timelessfrom < w/2) {
                exit = easeInBounce(0, w, size, time*2) * .5f;
            } else {
                exit = easeOutBounce(0, w, size, time*2-w) * size * .5f * .5f;
            }
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    public static float linear(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        if(w < 0) {
            sendAnimationError("negative-time", from, to, size, time);
        }
        float timelessfrom = time-from;
        if(time >= from && time <= from + to) {
            exit = timelessfrom*(size/w);
        }
        if(time > from + to) {
            exit = size;
        }
        return exit;
    }

    private static void sendAnimationError(String type, float a, float b, float c, float t) {
        if(Objects.equals(type, "negative-time")) {
            System.out.println(errorprfx + "Error: unable to animate negative time: [ from=" + a + " , to=" + b + " ]");
        }
    }

}
