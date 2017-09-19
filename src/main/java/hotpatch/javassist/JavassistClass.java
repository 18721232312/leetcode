package hotpatch.javassist;

/**
 * Created by XUJING592 on 2017/9/19.
 */
public class JavassistClass{
    private String name="default";
    public JavassistClass(){
        name="me";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void execute(){
        System.out.println(name);
        System.out.println("execute ok");
    }
}