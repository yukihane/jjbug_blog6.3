package jp.jbug.example.seam;

import javax.ejb.Local;

@Local
public interface IComment {

    public void init(BlogEntry entry);
    public void save();
    public void destroy();

}