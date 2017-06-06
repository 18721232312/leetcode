package com.jazz.drools;

import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import java.io.StringReader;
import java.util.Collection;

public class HelloDroolsNew {

    private static KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    private static Collection<KnowledgePackage> pkgs;
    private static KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    private static StatefulKnowledgeSession ksession;

    public static void main(final String[] args) {

        initDrools();
        initMessageObject();
        fireRules();

    }

    private static void initDrools(){

        // this will parse and compile in one step
        // read from file
        kbuilder.add( ResourceFactory.newClassPathResource( "/hellodrools/testrules.drl",HelloDroolsNew.class), ResourceType.DRL );

        // read second rule from String
        String myRule = "import hellodrools.Message rule \"Hello World 2\" when message:Message (type==\"Test\") then System.out.println(\"Test, Drools!\"); end";
        Resource myResource = ResourceFactory.newReaderResource(new StringReader(myRule));
        kbuilder.add(myResource, ResourceType.DRL);

        // Check the builder for errors
        if ( kbuilder.hasErrors() ) {
            System.out.println( kbuilder.getErrors().toString() );
            throw new RuntimeException( "Unable to compile drl\"." );
        }

        // get the compiled packages (which are serializable)
        pkgs = kbuilder.getKnowledgePackages();

        // add the packages to a knowledgebase (deploy the knowledge packages).
        kbase.addKnowledgePackages( pkgs );

        ksession = kbase.newStatefulKnowledgeSession(); }

    private static void fireRules(){
        ksession.fireAllRules();
        ksession.dispose();
    }

    private static void initMessageObject() {
        Message msg = new Message();
        msg.setType("Test");
        ksession.insert(msg);
    }
}