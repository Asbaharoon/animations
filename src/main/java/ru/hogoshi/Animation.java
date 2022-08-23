package ru.hogoshi;

import ru.hogoshi.util.Easing;
import ru.hogoshi.util.Easings;

/**
 * Main class
 */
public class Animation {

    /**
     * System.currentTimeMillis() from last animation start
     */
    private long start;
    /**
     * Last/Current animate method duration
     */
    private double duration;
    /**
     * Value from which animation is started
     */
    private double fromValue;
    /**
     * Value to which animation goes
     */
    private double toValue;
    /**
     * Returns current animation value (better usage: getValue())
     */
    private double value;

    /**
     * Animation type
     */
    private Easing easing = Easings.NONE;
    /**
     * Consoles animation things
     */
    private boolean debug = false;

    /**
     * Main method, use to animate value to something.
     *
     * @param valueTo toValue, value to which animation will go
     * @param duration duration, with which animation will animate
     */
    public void animate(double valueTo, double duration) {
        animate(valueTo, duration, Easings.NONE, false);
    }

    /**
     * Main method, use to animate value to something.
     *
     * @param valueTo toValue, value to which animation will go
     * @param duration duration, with which animation will animate
     * @param safe means will it update when animation isAlive or with the same targetValue
     */
    public void animate(double valueTo, double duration, boolean safe) {
        animate(valueTo, duration, Easings.NONE, safe);
    }

    /**
     * Main method, use to animate value to something
     *
     * @param valueTo toValue, value to which animation will go
     * @param duration duration, with which animation will animate
     * @param easing animation type, like formula for animation
     * @param safe means will it update when animation isAlive or with the same targetValue
     */
    public void animate(double valueTo, double duration, Easing easing, boolean safe) {
        if(safe && isAlive() && (valueTo == getFromValue() || valueTo == getToValue() || valueTo == getValue())) {
            if(isDebug()) System.out.println("Animate cancelled due to target val equals from val");
            return;
        }

        setToValue(valueTo);
        setFromValue(getValue());
        setStart(System.currentTimeMillis());
        setDuration(duration * 1000);
        setEasing(easing);

        if(isDebug()) System.out.println("#animate {\n    to value: " + getToValue() + "\n    from value: " + getValue() + "\n    duration: " + getDuration() + "\n}");
    }

    /**
     * Important method, use to update value. WARNING: IF U WILL NOT UPDATE, ANIMATION WILL NOT WORK
     *
     * @return returns if animation isAlive()
     */
    public boolean update() {
        double part = calculatePart();
        double foundVal;
        if(isAlive()) {
            part = getEasing().ease(part);
            foundVal = interpolate(getFromValue(), getToValue(), part);
        } else {
            setStart(0);
            foundVal = getToValue();
        }
        setValue(foundVal);
        return isAlive();
    }

    /**
     * Use if u want check if animation is animating
     *
     * @return returns if animation is animating
     */
    public boolean isAlive() {
        return !isDone();
    }

    /**
     * Use if u want check if animation is not animating
     *
     * @return returns if animation is animating
     */
    public boolean isDone() {
        return calculatePart() >= 1.0;
    }

    /**
     * Use if u want to get the current part of animation (from 0.0 to 1.0, like 0% and 100%)
     *
     * @return returns animation part
     */
    public double calculatePart() {
        return (double) (System.currentTimeMillis() - getStart()) / getDuration();
    }

    /**
     * Basic interpolation formula
     */
    public double interpolate(double start, double end, double pct) {
        return start + (end - start) * pct;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getFromValue() {
        return fromValue;
    }

    public void setFromValue(double fromValue) {
        this.fromValue = fromValue;
    }

    public double getToValue() {
        return toValue;
    }

    public void setToValue(double toValue) {
        this.toValue = toValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Easing getEasing() {
        return easing;
    }

    public void setEasing(Easing easing) {
        this.easing = easing;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}