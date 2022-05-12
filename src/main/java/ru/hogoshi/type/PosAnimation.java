package ru.hogoshi.type;

import ru.hogoshi.Animation;
import ru.hogoshi.util.Easing;
import ru.hogoshi.util.Easings;
import ru.hogoshi.util.V2;

/**
 * Use if u need to animation the position
 */
public class PosAnimation {

    private final Animation x = new Animation();
    private final Animation y = new Animation();

    /**
     * Main method, use to animate position
     * @return returns method performed
     */
    public boolean animate(V2 posTo, double duration) {
        return animate(posTo, duration, false);
    }

    /**
     * Main method, use to animate position
     * @return returns method performed
     */
    public boolean animate(V2 posTo, double duration, boolean safe) {
        return animate(posTo, duration, Easings.NONE, safe);
    }

    /**
     * Main method, use to animate position
     * @return returns method performed
     */
    public boolean animate(V2 posTo, double duration, Easing easing, boolean safe) {
        return getX().animate(posTo.getX(), duration, easing, safe) &&
                getY().animate(posTo.getY(), duration, easing, safe);
    }

    /**
     * Updates all coordinates
     * @return all v2s alive
     */
    public boolean update() {
        return getX().update() && getY().update();
    }

    /**
     * Get position
     * @return positions via utility V2 class
     */
    public V2 getPosition() {
        return new V2(getX().getValue(), getY().getValue());
    }

    /**
     * @return all coordinates alive
     */
    public boolean isAlive() {
        return getX().isAlive() && getY().isAlive();
    }

    /**
     * @return all coordinates done
     */
    public boolean isDone() {
        return !isAlive();
    }

    public Animation getX() {
        return x;
    }

    public Animation getY() {
        return y;
    }
}