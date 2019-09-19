package com.epam.news.model.news;

import com.epam.news.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Table
@Entity(name = "Comments")
@NamedQueries({
        @NamedQuery(
                name = "getCommentsByNewsId",
                query = "select c from Comments c where news_id = :id ORDER BY c.id DESC"
        )
})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "news_id")
    @JsonIgnore
    private News news;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Comment(String content, News news, User user) {
        this.content = content;
        this.news = news;
        this.user = user;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonIgnore
    public News getNews() {
        return news;
    }

    @JsonProperty
    public void setNews(News news) {
        this.news = news;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", news=" + news +
                '}';
    }
}
