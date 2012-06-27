package jp.jbug.example.seam;

import javax.ejb.Local;

@Local
public interface IBlogEntry {

    public void init();
    public void init(BlogEntry entry);
    public void confirm() throws BlogEntryAlreadyFoundException;
    public void save();
    public void update();
    public void destroy();

}