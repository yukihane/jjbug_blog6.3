package jp.jbug.example.seam.bpm;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.log.Log;

@Name("kairan")
public class Kairan 
{
   
    @In(scope=ScopeType.BUSINESS_PROCESS) 
    private long blogEntryId;

    @Logger
    private Log log;

    @StartTask @EndTask
   public void done() {
   
        log.info("blogEntryId=" + blogEntryId + " is approved!!");
        
   }

}
