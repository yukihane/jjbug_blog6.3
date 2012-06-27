package jp.jbug.example.seam;

import javax.ejb.Local;

@Local
public interface IBlogList {

    public void getBlogEntries();
    public void deleteBlogEntry();
    public void deleteComment(BlogEntry entry);
    public void destroy();
    
}