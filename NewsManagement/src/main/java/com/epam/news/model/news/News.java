package com.epam.news.model.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;


/**
 * Provides ORM for table News in database.
 *
 * @author Oleg Aliyev
 */
@Table
@NamedQueries({
        @NamedQuery(
                name = "getAllNews",
                query = "select  n from News n"),

})
@Entity(name = "News")
public class News {

    /**
     * news id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_seq")
    @SequenceGenerator(name = "news_seq",
            sequenceName = "news_seq", allocationSize = 1)
    private long id;

    /**
     * list of comments of news.
     */
    @OneToMany(mappedBy = "news",
            fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    private Collection<Comment> comments;

    /**
     * news title.
     */
    @Column
    private String title;

    /**
     * news date.
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "date_creation")
    private LocalDateTime date;

    @PrePersist
    private void prePersist() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }

    /**
     * news brief.
     */
    @Column
    private String brief;

    /**
     * news content.
     */
    @Column
    private String content;

    public News() {
    }

    public News(final long id, final String title,
                final LocalDateTime date, final String brief) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
    }

    public News(final String title, final LocalDateTime date,
                final String brief, final String content) {
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    public News(final long id, final
    String title, final String brief, final String content) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.content = content;
    }

    public News(final String title,
                final String brief, final String content) {
        this.title = title;
        this.brief = brief;
        this.content = content;
    }

    public News(final long id, final String title,
                final LocalDateTime date,
                final String brief, final String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    private void setTime() {
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(final Collection<Comment> comments) {
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(final String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{"
                + "id="
                + id
                + ", title='"
                + title
                + '\''
                + ", date='"
                + date
                + '\''
                + ", brief='"
                + brief
                + '\''
                + ", content='"
                + content
                + '\''
                + '}';
    }

    @Override
    public boolean equals(final Object object) {

        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        News news = (News) object;
        return  id == news.id
                && title.equals(news.title)
                && brief.equals(news.brief)
                && content.equals(news.content);
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

}

