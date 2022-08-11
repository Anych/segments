package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {

    public Segment(Point start, Point end) throws RuntimeException {
        x1 = start.getX();
        y1 = start.getY();
        x2 = end.getX();
        y2 = end.getY();
        
        if (x1 == x2 && y1 == y2 ) {
            throw new RuntimeException();
        }
    }

    double length() { return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); }

    Point middle() { return new Point((x1 + x2) / 2, (y1 + y2) / 2); }

    Point intersection(Segment another) {
        double a1 = y2 - y1;
        double b1 = x1 - x2;
        double c1 = a1 * x1 + b1 * y1;

        double a2 = another.y2 - another.y1;
        double b2 = another.x1 - another.x2;
        double c2 = a2 * another.x1 + b2 * another.y1;
        double delta1 = a1 * b2 - a2 * b1;

        double d1 = (x1 - another.x1) * (another.y1 - another.y2) - (y1 - another.y1) * (another.x1 - another.x2);
        double delta2 = (x1 - x2) * (another.y1 - another.y2) - (y1 - y2) * (another.x1 - another.x2);
        double t = d1 / delta2;

        double d2 = (x1 - another.x1) * (y1 - y2) - (y1 - another.y1) * (x1 - x2);
        double delta3 = (x1 - x2) * (another.y1 - another.y2) - (y1 - y2) * (another.x1 - another.x2);
        double u = d2 / delta3;

        if (delta1 == 0 || delta2 == 0 || delta3 == 0) { return null; }
        else if (t < 0 || u < 0 || t > 1 || u > 1) { return null; }
        else { return new Point((b2 * c1 - b1 * c2) / delta1, (a1 * c2 - a2 * c1) / delta1); }

    }
    
    double x1, y1, x2, y2;
}
