package design.pattern.template;

/**
 * @Author
 * @Description
 * @Date 2021/12/1
 */
public class TemplateTest {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
