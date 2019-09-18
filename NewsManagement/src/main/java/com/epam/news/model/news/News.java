package com.epam.news.model.news;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Table
/*@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllNews",

                select * from news full join ( select * from Comments where news_id = News.id ) on Comments.news_id = news.id;
                *//*query = "select new News(n.id, n.title, n.date, n.brief ) from News n"*//*
                query = "select * from News n " +
                        "join (select * from Comments c where rownum < 3) ON c.news_id = n.id"
                *//*query = "select n from News n  left join n.comments c "*//*
        )*//*,
        @NamedQuery(
                name = "getNewsWithTwoRecentComments",
                query = "select n from News n  join n.comments c "

        )*//*
})*/

@NamedQueries({
        @NamedQuery(
                name = "getAllNews",
               /* query = "select new News(n.id, n.title, n.date, n.brief ) from News n"
                query = "select n from News n join n.comments c where rownum < 3  "*/
                query = "select n from News n  join fetch n.comments c "
        ),
        /*@NamedQuery(
                name = "getNewsWithTwoRecentComments",
                query = "select n from News n  join n.comments c "

        )*/
})
@Entity(name = "News")
public class News {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_seq")
    @SequenceGenerator(name = "news_seq", sequenceName = "news_seq", allocationSize = 1)
    private long id;

    @OneToMany(mappedBy = "news", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Comment> comments;

    @Column
    private String title;

    @Column(name = "date_creation")
    private LocalDateTime date;

    @PrePersist
    private void prePersist() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }

    @Column
    private String brief;

    @Column
    private String content;

    public News() {
    }

    public News(long id, String title, LocalDateTime date, String brief) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
    }

    public News(String title, LocalDateTime date, String brief, String content) {
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    public News(long id, String title, String brief, String content) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
    }

    public News(String title, String brief, String content) {
        this.title = title;
        this.brief = brief;
        this.content = content;
    }

    public News(long id, String title, LocalDateTime date, String brief, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    private void setTime() {
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", brief='" + brief + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        News news = (News) obj;
        return id == news.id &&
                title.equals(news.title) &&
                date.equals(news.date) &&
                brief.equals(news.brief) &&
                content.equals(news.content);
    }
}
