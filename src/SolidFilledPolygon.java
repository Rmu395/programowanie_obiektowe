import java.util.Locale;

public class SolidFilledPolygon extends Polygon{
    private String color;
    public SolidFilledPolygon(int count, String color) {
        super(count, new Style(null, null, null));
        this.color = color;
    }

    @Override
    public String toSvg(String parameters) {
        return super.toSvg(String.format(Locale.ENGLISH,
                "fill=\"%s\" %s ", color, parameters));
    }
}
