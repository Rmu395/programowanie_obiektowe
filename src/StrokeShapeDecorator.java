import java.util.Locale;

public class StrokeShapeDecorator implements Shape {
    private String color;
    private double width;

    public StrokeShapeDecorator(String color, double width) {
        this.color = color;
        this.width = width;
    }

    @Override
    public String toSvg(String parameters) {
        return String.format(
                Locale.ENGLISH,
                "stroke=\"%s\" stroke-width=\"%f\" ",
                color, width);
    }
}
