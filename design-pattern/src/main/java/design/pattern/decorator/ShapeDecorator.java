package design.pattern.decorator;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public abstract class ShapeDecorator implements ShapeComponent{
    protected ShapeComponent shapeComponent;

    public ShapeDecorator(ShapeComponent shapeComponent) {
        this.shapeComponent = shapeComponent;
    }

    public void setShapeComponent(ShapeComponent shapeComponent) {
        this.shapeComponent = shapeComponent;
    }

    @Override
    public void draw() {
        if (null != shapeComponent) {
            this.shapeComponent.draw();
        }
    }
}
