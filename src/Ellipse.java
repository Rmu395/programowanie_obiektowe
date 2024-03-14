import java.util.Locale;

public class Ellipse implements Shape {
    private Vec2 centre;
    private double rx, ry;
    private Style style;
    public Ellipse(Vec2 centre, double rx, double ry, Style style) {
        this.style = style;
        this.centre = centre;
        this.rx = rx;
        this.ry = ry;
    }

    public String toSvg(String parameters) {
        return String.format(Locale.ENGLISH,
                "<ellipse rx=\"%f\" ry=\"%f\" cx=\"%f\" cy=\"%f\"\n %s />",
                rx, ry, centre.x, centre.y, style.toSvg());
    }
}
