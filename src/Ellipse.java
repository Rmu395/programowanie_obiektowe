import java.util.Locale;

public class Ellipse extends Shape {
    private Point centre;
    private double rx, ry;
    public Ellipse(Point centre, double rx, double ry, Style style) {
        super(style);
        this.centre = centre;
        this.rx = rx;
        this.ry = ry;
    }

    @Override
    public String toSvg() {
        return String.format(Locale.ENGLISH,
                "<ellipse rx=\"%f\" ry=\"%f\" cx=\"%f\" cy=\"%f\"\n %s />",
                rx, ry, centre.x, centre.y, style.toSvg());
    }
}
