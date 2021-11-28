package design.pattern.factory;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("绘制正方形！");
    }
}
