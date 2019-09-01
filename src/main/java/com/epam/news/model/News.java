package com.epam.news.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name = "News")
public class News {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="news_seq", sequenceName="news_seq",allocationSize=1)
    private int id;

    @Column
    private String title;

    @Basic
    @Column(name = "date_creation")
    private LocalDateTime date;

    @Column
    private String brief;

    @Column
    private String content;

    public News() {
    }

    public News(int id, String title, LocalDateTime date, String brief) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
    }

    public News(String title, LocalDateTime date, String brief) {
        this.title = title;
        this.date = date;
        this.brief = brief;
    }

    public News(int id, String title, LocalDateTime date, String brief, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    public News(String title, LocalDateTime date, String brief, String content) {
        this.title = title;
        this.date = date;
        this.brief = brief;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        News news = (News) obj;
        return  id == news.id &&
                title.equals(news.title) &&
                date.equals(news.date) &&
                brief.equals(news.brief) &&
                content.equals(news.content);
    }
}
