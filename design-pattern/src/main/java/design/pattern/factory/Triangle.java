package design.pattern.factory;

/**
 * @Author
 * @Description
 * @Date 2021/11/29
 */
public class Triangle implements Shape{
    @Override
    public void draw() {
        System.out.println("绘制三角形！");
    }
}
