package ru.hogoshi.bezier;

public abstract class Bezier {

    private final Point start = new Point(0, 0);
    private final Point end = new Point(1, 1);

    private Point point2;
    private Point point3;

    public Bezier(Point point2, Point point3) {
        setPoint2(point2);
        setPoint3(point3);
    }

    public Bezier() {}

    public abstract double getValue(double t);

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }

}