package jp.jbug.example.seam;

import static javax.persistence.PersistenceContextType.EXTENDED;

import java.io.Serializable;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("blogEntryAction")
public class BlogEntryAction implements Serializable, IBlogEntry {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(type = EXTENDED)
    private EntityManager em;

    @In(required = false)
    @Out(required = false)
    private BlogEntry blogEntry;

    @In
    private FacesMessages facesMessages;

    @In
    private Events events;

    @Out(scope=ScopeType.BUSINESS_PROCESS, required=false)
    long blogEntryId;

    @Logger
    private Log log;

    @Begin
    @Restrict("#{s:hasRole('admin')}")
    public void init() {
        log.info("ブログ登録画面前処理");
    }

    @Begin
    @Restrict("#{s:hasPermission('blogEntryAction','init',null)}")
    public void init(BlogEntry entry) {
        log.info("ブログ変更画面前処理");
        this.blogEntry = entry;
    }

    @Begin(join=true)
    public void confirm() throws BlogEntryAlreadyFoundException {
        int count = em.createQuery(
                "from BlogEntry where blogDate = #{blogEntry.blogDate}")
        .getResultList()
        .size();

        if (count > 0 && blogEntry.getBlogEntryId()==null) {
            throw new BlogEntryAlreadyFoundException();            
        }
    }

    @End
    @CreateProcess(definition="kairan", processKey="#{blogEntry.blogEntryId}")
    public void save() {
        em.persist(blogEntry);
        facesMessages.add("登録しました");
        log.info("#{blogEntry.blogEntryId} entried");
        blogEntryId = blogEntry.getBlogEntryId();
        events.raiseTransactionSuccessEvent("blogUpdated");
    }

    @End
    public void update() {
        facesMessages.add("変更しました");
        log.info("#{blogEntry.blogEntryId} 変更しました");
        events.raiseTransactionSuccessEvent("blogUpdated");
    }

    @Remove
    @Destroy
    public void destroy() {
        log.info(this + "　は破棄されました");
    }

}
