package design.pattern.factory.reflectfactory;

/**
 * @Author
 * @Description
 * @Date 2021/11/28
 */
public class ReflectFactory {
    private ReflectFactory(){}

    public static <T> T getInstance(String className, Class<T> clazz) {
        T instance = null;
        try {
            instance = (T)Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
