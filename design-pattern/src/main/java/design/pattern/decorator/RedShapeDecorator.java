package design.pattern.decorator;

/**
 * @Author
 * @Description
 * @Date 2021/12/5
 */
public class RedShapeDecorator extends ShapeDecorator{
    public RedShapeDecorator(ShapeComponent shapeComponent) {
        super(shapeComponent);
    }

    @Override
    public void draw() {
        super.draw();
        this.setRedBorder(shapeComponent);
    }

    public void setRedBorder(ShapeComponent decoratorShape) {
        System.out.println("涂红色");
    }
}
