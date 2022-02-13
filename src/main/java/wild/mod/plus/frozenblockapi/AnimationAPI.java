package wild.mod.plus.frozenblockapi;

public class AnimationAPI {

    /*
    AnimationAPI
    By LiukRast

    Only for FrozenBlockStaff

    VERSION 2.5 BETA
     */

    public static float easeInSine(float fromTime, float toTime, float size, float time) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        if( x >= 0 && x <= w) {
            float eq = (time/w) - 1 - (fromTime/w);
            exit = eq*eq;
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return (-exit * size) + size;
        } else {
            return 0;
        }
    }

    public static float easeOutSine(float fromTime, float toTime, float size, float time) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        if( x >= 0 && x <= w) {
            float eq = (time/w) - (fromTime/w);
            exit = eq*eq;
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeInOutSine(float fromTime, float toTime, float size, float time) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        if( x >= 0 && x <= w) {
            exit = (-(float)Math.cos((x/w)*Math.PI)/2)+0.5f;
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeInBack(float fromTime, float toTime, float size, float time, float multiplier) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        float c3 = multiplier + 1;
        if( x >= 0 && x <= w) {
            exit = c3*(x/w)*(x/w)*(x/w)- multiplier *(x/w)*(x/w);
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeInBack(float fromTime, float toTime, float size, float time) {
        return easeInBack(fromTime, toTime, size, time, 1.5f);
    }

    public static float easeOutBack(float fromTime, float toTime, float size, float time, float multiplier) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        float c3 = multiplier + 1;
        if( x >= 0 && x <= w) {
            exit = 1+c3*(float)Math.pow(x/w - 1, 3)+ multiplier *(float)Math.pow(x/w - 1, 2);
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeOutBack(float fromTime, float toTime, float size, float time) {
        return easeOutBack(fromTime, toTime, size, time, 1.5f);
    }

    public static float easeInOutBack(float fromTime, float toTime, float size, float time, float multiplier) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        float c2 = multiplier * 1.525f;
        if( x >= 0 && x <= w) {
            if(x < 0.5*w) {
                exit = ((float)Math.pow(2*x/w, 2) * ((c2+1)*2*x/w - c2) )/2;
            } else {
                exit = ((float)Math.pow(2 * (x/w) - 2, 2) * ((c2 + 1) * ((x/w) * 2 - 2) + c2) + 2) / 2;
            }
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeInOutBack(float fromTime, float toTime, float size, float time) {
        return easeInOutBack(fromTime, toTime, size, time, 1.5f);
    }

    public static float easeInElastic(float fromTime, float toTime, float size, float time, int amount) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        int b = (2* amount)-1;
        if( x >= 0 && x <= w) {
            exit = (float)Math.cos(2*Math.PI*b*(x/w))*(x/w);
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeInElastic(float fromTime, float toTime, float size, float time) {
        int w = (int)(toTime - fromTime)/10;
        return easeInElastic(fromTime, toTime, size, time, w);
    }

    public static float easeOutElastic(float fromTime, float toTime, float size, float time, int amount) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        int b = (2* amount)-1;
        if( x >= 0 && x <= w) {
            exit = (float)(-Math.cos(2*Math.PI*b*(x/w))*(1-(x/w))+1);
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeOutElastic(float fromTime, float toTime, float size, float time) {
        int w = (int)(toTime - fromTime)/10;
        return easeOutElastic(fromTime, toTime, size, time, w);
    }

    public static float easeInOutElastic(float fromTime, float toTime, float size, float time, int amount) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        int b = (2* amount)-1;
        if( x >= 0 && x <= w) {
            exit = (float)(Math.sin(2*Math.PI*b*(x/w))*Math.sin(Math.PI*(x/w)))+(x/w);
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeInOutElastic(float fromTime, float toTime, float size, float time) {
        int w = (int)(toTime - fromTime)/10;
        return easeInOutElastic(fromTime, toTime, size, time, w);
    }

    public static float easeInBounce(float fromTime, float toTime, float size, float time, int amount) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        int b = (2* amount)-1;
        if( x >= 0 && x <= w) {
            exit = (float)Math.abs(Math.cos(2*Math.PI*b*(x/w))*(x/w));
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeInBounce(float fromTime, float toTime, float size, float time) {
        int w = (int)(toTime - fromTime)/10;
        return easeInBounce(fromTime, toTime, size, time, w);
    }

    public static float easeOutBounce(float fromTime, float toTime, float size, float time, int amount) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        int b = (2* amount)-1;
        if( x >= 0 && x <= w) {
            exit = (float)(-Math.abs(Math.cos(2*Math.PI*b*(x/w))*(1-(x/w)))+1);
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float easeOutBounce(float fromTime, float toTime, float size, float time) {
        int w = (int)(toTime - fromTime)/10;
        return easeOutBounce(fromTime, toTime, size, time, w);
    }

    public static float easeInOutBounce(float fromTime, float toTime, float size, float time, int amount) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        int b = (2* amount)-1;
        if( x >= 0 && x <= w) {
            exit = (float)Math.abs(Math.sin(2*Math.PI*b*(x/w))*Math.sin(Math.PI*(x/w)))+(x/w);
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return exit * size;
        } else {
            return 0;
        }
    }

    public static float linear(float fromTime, float toTime, float size, float time) {
        float exit = 0;
        float w = toTime - fromTime;
        float x = time - fromTime;
        if( x >= 0 && x <= w) {
            exit = x/w;
        }
        if (x > w) {
            return size;
        }
        if( x >= 0) {
            return (-exit * size);
        } else {
            return 0;
        }
    }

    public static float loopTime(float time, float size) {
        return time-(size * (float)Math.floor(time/size));
    }

    public static float boomerangTime(float time, float size) {
        float eq1 = time-(2*size * (float)Math.floor(time/(size*2)));
        float eq2 = -time+(2*size * (float)Math.floor(time/(size*2)))+(2*size);
        if(eq1>=0&&eq1<=size) {
            return eq1;
        } else {
            return eq2;
        }
    }

    public static float animationTimer(float time, float from, float to) {
        float x = time - from;
        float w = to - from;

        if(x>=0&&x<=w) {
            return x;
        } else {
            return 0;
        }
    }

}
