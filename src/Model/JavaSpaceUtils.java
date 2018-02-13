package Model;

import net.jini.space.JavaSpace;
import net.jini.core.transaction.server.TransactionManager;
import java.rmi.RMISecurityManager;
import net.jini.core.discovery.LookupLocator;
import net.jini.core.lookup.ServiceRegistrar;
import net.jini.core.lookup.ServiceTemplate;

/**
 * Created by Aaron on 13/02/2018.
 */
public class JavaSpaceUtils {

    public static JavaSpace getSpace(String host){

        JavaSpace space = null;
        try{
            LookupLocator lookUpLo = new LookupLocator("jini://" + host);
            ServiceRegistrar serviceReg = lookUpLo.getRegistrar();
            Class c = Class.forName("net.jini.space.JavaSpace");
            Class[] classTemplate = {c};
            space = (JavaSpace) serviceReg.lookup(new ServiceTemplate(null, classTemplate, null));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return space;
    }

    public static JavaSpace getSpace(){
        return getSpace("Aaron-PC");
    }

    public static TransactionManager getManager(String host){

        if(System.getSecurityManager() == null)
        {
            System.setSecurityManager(new RMISecurityManager());
        }

        TransactionManager manager = null;

        try{
            LookupLocator lookUpLocator = new LookupLocator("jini://" + host);
            ServiceRegistrar serviceReg = lookUpLocator.getRegistrar();
            Class c = Class.forName("net.jini.core.transaction.server.TransactionManager");
            Class[] classTemplate = {c};
            manager = (TransactionManager) serviceReg.lookup(new ServiceTemplate(null, classTemplate, null));
        }catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        return manager;
    }

    public static TransactionManager getManager(){
        return getManager("Aaron-PC");
    }
}
