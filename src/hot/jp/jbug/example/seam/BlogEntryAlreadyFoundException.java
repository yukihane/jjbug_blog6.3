package jp.jbug.example.seam;

import javax.ejb.ApplicationException;

import org.jboss.seam.annotations.exception.Redirect;

@ApplicationException(rollback=true)
@Redirect(viewId="/error.xhtml", message="既にあるよ")
public class BlogEntryAlreadyFoundException extends Exception
{
    private static final long serialVersionUID = 1L;

    BlogEntryAlreadyFoundException()
   {
      super();
   }
}
