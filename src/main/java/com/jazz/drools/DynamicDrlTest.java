package com.jazz.drools;

/**
 * @Description:
 * @Team: 新金融业务研发团队
 * @Author BK
 * @Date 2017/6/6 17:36
 * @Version V2.0
 */

import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

/**
 *
 * drools6.4动态加载规则文件：第一次不命中，动态增加规则以后，命中
 *
 * @author caicongyang1
 * @version id: dynamicDrlTest, v 0.1 16/10/26 下午2:00 caicongyang1 Exp $$
 */
public class DynamicDrlTest {

    private static final String RULESFILE_NAME = "rules.drl";

    /**
     * 规则文件内容（可以从数据库中加载）
     */
    private static final String rules      = "package com.caicongyang.drools.test; import com.caicongyang.drools.fact.Message; rule \"Hello World \" when message:Message (status == \"0\") then System.out.println(\"hello, Drools!\"); end";

    public static void main(String[] args) throws Exception {

        KieServices kieServices = KieServices.Factory.get();

        /**
         * 指定kjar包
         */
        final ReleaseId releaseId = kieServices.newReleaseId("com", "caicongyang", "1.0.0");

        // 创建初始化的kjar
        InternalKieModule kJar = DroolsUtils.initKieJar(kieServices, releaseId);
        KieRepository repository = kieServices.getRepository();
        repository.addKieModule(kJar);
        KieContainer kieContainer = kieServices.newKieContainer(releaseId);
        KieSession session = kieContainer.newKieSession();
        Message message = new Message();
        message.setStatus("0");
        //同一个fact第一次不命中
        try {
            session.insert(message);
            session.fireAllRules();
        } catch (Exception e) {
        } finally {
            session.dispose();
        }
        System.out.println("-----first fire end-------");

        //新增一个规则文件
        kJar = DroolsUtils.createKieJar(kieServices, releaseId,
                new ResourceWrapper(ResourceFactory.newByteArrayResource(rules.getBytes()), RULESFILE_NAME));
        repository.addKieModule(kJar);
        kieContainer.updateToVersion(releaseId);

        //同一个fact再次过滤规则：命中
        session = kieContainer.newKieSession();
        try {
            session.insert(message);
            session.fireAllRules();
        } catch (Exception e) {
        } finally {
            session.dispose();
        }
        System.out.println("-----senond fire end-------");

    }

}