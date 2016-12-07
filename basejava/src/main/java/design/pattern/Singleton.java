package design.pattern;

/**
 * Created by liubo on 16/7/12.
 */
public class Singleton {
    private Singleton(){}
    private static Singleton instance = null;
    public static synchronized Singleton getInstance(){
        if (instance==null){
            instance= new Singleton();
        }
        return instance;
    }
}
