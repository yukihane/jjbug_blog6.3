package jp.jbug.example.seam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.jboss.seam.annotations.Name;

@Entity
@Name("comment")
public class Comment implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long commentId;

    @Column(nullable = false, length = 20)
    private String commentater;

    @Column(nullable = false, length = 100)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "blogEntryId", referencedColumnName = "blogEntryId")
    // @PrimaryKeyJoinColumn
    private BlogEntry blogEntry;

    public Comment() {
    }

    public Long getCommentId() {
        return this.commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public BlogEntry getBlogEntry() {
        return this.blogEntry;
    }

    public void setBlogEntry(BlogEntry blogEntry) {
        this.blogEntry = blogEntry;
    }

    public String getCommentater() {
        return this.commentater;
    }

    public void setCommentater(String commentater) {
        this.commentater = commentater;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
