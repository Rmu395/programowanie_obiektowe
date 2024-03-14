public class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    @Override
    public String toSvg(String parameters) {
        return decoratedShape.toSvg(parameters);
    }

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }
}
