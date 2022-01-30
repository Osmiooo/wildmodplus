package wild.mod.plus.liukrastapi;

public class AnimationAPI {

    /*
    AnimationAPI
    By LiukRast

    Only for FrozenBlockStaff

    VERSION 2.3
     */

    public static float easeInSine(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
        if( x >= 0 && x <= w) {
            float eq = (time/w) - 1 - (from/w);
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

    public static float easeOutSine(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
        if( x >= 0 && x <= w) {
            float eq = (time/w) - (from/w);
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

    public static float easeInOutSine(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeInBack(float from, float to, float size, float time, float multiplier) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeInBack(float from, float to, float size, float time) {
        return easeInBack(from, to, size, time, 1.5f);
    }

    public static float easeOutBack(float from, float to, float size, float time, float multiplier) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeOutBack(float from, float to, float size, float time) {
        return easeOutBack(from, to, size, time, 1.5f);
    }

    public static float easeInOutBack(float from, float to, float size, float time, float multiplier) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeInOutBack(float from, float to, float size, float time) {
        return easeInOutBack(from, to, size, time, 1.5f);
    }

    public static float easeInElastic(float from, float to, float size, float time, int amount) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeInElastic(float from, float to, float size, float time) {
        int w = (int)(to - from)/10;
        return easeInElastic(from, to, size, time, w);
    }

    public static float easeOutElastic(float from, float to, float size, float time, int amount) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeOutElastic(float from, float to, float size, float time) {
        int w = (int)(to - from)/10;
        return easeOutElastic(from, to, size, time, w);
    }

    public static float easeInOutElastic(float from, float to, float size, float time, int amount) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeInOutElastic(float from, float to, float size, float time) {
        int w = (int)(to - from)/10;
        return easeInOutElastic(from, to, size, time, w);
    }

    public static float easeInBounce(float from, float to, float size, float time, int amount) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float easeInBounce(float from, float to, float size, float time) {
        int w = (int)(to - from)/10;
        return easeInBounce(from, to, size, time, w);
    }

    public static float easeOutBounce(float from, float to, float size, float time, int amount) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
        int b = (2* amount)-1;
        if( x >= 0 && x <= w) {
            exit = (float)(Math.abs(-Math.cos(2*Math.PI*b*(x/w))*(1-(x/w)))+1);
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

    public static float easeOutBounce(float from, float to, float size, float time) {
        int w = (int)(to - from)/10;
        return easeOutBounce(from, to, size, time, w);
    }

    public static float easeInOutBounce(float from, float to, float size, float time, int amount) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

    public static float linear(float from, float to, float size, float time) {
        float exit = 0;
        float w = to - from;
        float x = time - from;
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

}
