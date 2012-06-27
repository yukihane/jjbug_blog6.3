package jp.jbug.example.seam;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;


@Name("authenticator")
public class Authenticator
{
    @Logger Log log;
    
    @In Identity identity;
   
    @In 
    private Actor actor;

    public boolean authenticate()
    {
        log.info("authenticating #0", identity.getUsername());
        if (identity.getUsername().equals("kacho")) {
            identity.addRole("manager");            
        } else if (identity.getUsername().equals("admin")){
            identity.addRole("admin");
        } else {
            return false;
        }
        actor.setId(Identity.instance().getUsername());

        return true;
    }
}
