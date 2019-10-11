package com.epam.news.model.news;

import com.epam.news.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Provides comments for entity {@link News}.
 *
 * <p>
 * Connected with class {@link News}
 * and {@link User} with ORM relation @ManyToOne
 * <p>
 *
 * @author Oleg Aliyev
 */
@Table
@Entity(name = "Comments")
@NamedQueries({
        @NamedQuery(
                name = "getCommentsByNewsId",
                query = "select c from Comments"
                        + " c where news_id = :id ORDER BY c.id DESC"
        )
})
public class Comment {

    /**
     * newsId is generated with sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq",
            sequenceName = "comment_seq", allocationSize = 1)
    private Long id;

    /**
     * comment content.
     */
    @Column
    @Size(max = 300, message = "{validation.comment.content.size}")
    @NotNull(message = "Please provide a content")
    private String content;

    /**
     * news is mapped by news id.
     */
    @ManyToOne
    @JoinColumn(name = "news_id")
    @JsonIgnore
    private News news;


    /**
     * user is mapped by user id.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Comment(final String content, final News news, final User user) {
        this.content = content;
        this.news = news;
        this.user = user;
    }

    public Comment() {
    }

    public Comment(final Long id, final String content) {
        this.id = id;
        this.content = content;
    }

    public Comment(final News news, final User user) {
        this.news = news;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @JsonIgnore
    public News getNews() {
        return news;
    }

    @JsonProperty
    public void setNews(final News news) {
        this.news = news;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(final User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{"
                + "id="
                + id
                + ", content='"
                + content
                + '\''
                + ", news="
                + news
                + '}';
    }
}
