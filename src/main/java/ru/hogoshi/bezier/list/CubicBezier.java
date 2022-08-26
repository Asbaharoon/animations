package ru.hogoshi.bezier.list;

import ru.hogoshi.bezier.Bezier;
import ru.hogoshi.bezier.Point;

public class CubicBezier extends Bezier {

    @Override
    public double getValue(double t) {
        double dt = 1.0 - t;
        double dt2 = dt * dt;
        double t2 = t * t;

        Point temp = getPoint2().copy();

        return getStart().copy()
                .scale(dt2, dt)
                .add(temp.scale(3 * dt2 * t))
                .add(temp.set(getPoint3()).scale(3 * dt * t2))
                .add(temp.set(getEnd()).scale(t2 * t))
                .getY();
    }

    public static class Builder {
        private CubicBezier bezier = new CubicBezier();

        public Builder(CubicBezier bezier) {
            this.bezier = bezier;
        }

        public Builder() {}

        public Builder setPoint2(Point point) {
            bezier.setPoint2(point);

            return this;
        }

        public Builder setPoint3(Point point) {
            bezier.setPoint3(point);

            return this;
        }

        public CubicBezier build() {
            return bezier;
        }
    }

}