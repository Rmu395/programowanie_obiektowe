import java.util.Locale;

public class Polygon {
    private Point[] points;

    public Polygon(Point[] points) {
        this.points = points;
    }

    public Polygon(Polygon copySrc) {
        this.points = copySrc.points;
    }


    public String toSvg() {
        String polygonSvg = "<polygon points=\"";
        for(Point point: points)
            polygonSvg += point.x + "," + point.y + " ";
        polygonSvg += "\" />";
        return polygonSvg;
    }
}
