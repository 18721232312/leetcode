package hotpatch.attach;

/**
 *
 *
 * java -javaagent:agent.jar TestAgent
 * Created by XUJING592 on 2017/9/19.
 */
public class TestAgent {

    public static void main(String[] args) {
        TestAgent ta = new TestAgent();
        ta.test();
    }

    public void test() {
        System.out.println("I'm TestAgent");
    }

}