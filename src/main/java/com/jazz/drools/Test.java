package com.jazz.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.HashMap;
import java.util.Map;

public class Test {
//http://blog.csdn.net/csujiangyu/article/details/52348003
    public static void main(String[] args) {
        System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("ksession-rules");

        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String,Room> name2room = new HashMap<String,Room>();
        for( String name: names ){
            Room room = new Room( name );
            name2room.put( name, room );
            ksession.insert( room );
            Sprinkler sprinkler = new Sprinkler( room );
            ksession.insert( sprinkler );
        }

        ksession.fireAllRules();
        /**
         * Everything is ok
         */

        Fire kitchenFire = new Fire(name2room.get( "kitchen" ));
        Fire officeFire = new Fire(name2room.get( "office" ));
        FactHandle kitchenFireHandle = ksession.insert( kitchenFire );
        FactHandle officeFireHandle = ksession.insert( officeFire );

        ksession.fireAllRules();
        /**
         * Raise the alarm
         Turn on the sprinkler for room kitchen
         Turn on the sprinkler for room office
         */

        ksession.delete( kitchenFireHandle );
        ksession.delete( officeFireHandle );
        ksession.fireAllRules();
        /**
         * Cancel the alarm
         Turn off the sprinkler for room office
         Turn off the sprinkler for room kitchen
         Everything is ok
         */
    }
}
