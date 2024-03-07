import java.util.Locale;

public class Polygon {
    private Point[] points;

    public Polygon(Point[] points) {
        this.points = points;
    }

    public String toSvg() {
        return String.format(Locale.ENGLISH, "<polygon points=\"%f,%f %f,%f %f,%f %f,%f\" style=\"stroke-width:5\" />", points[0].x, points[0].y, points[1].x, points[1].y, points[2].x, points[2].y, points[3].x, points[3].y);
    }
}
