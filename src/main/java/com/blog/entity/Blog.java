package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客类
 */
public class Blog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    //摘要
    private String summary;
    private Date releaseDate;
    private Integer clickHit;
    private Integer replyHit;
    //内容
    private String content;

    private BlogType blogType;

    private String keyWord;
    //纯文本格式(不带html标签)
    private String contentNotag;
    //发表时间
    private String releaseDateStr;

    private Integer blogCount;

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getContentNotag() {
        String temp = null;
        //除去html
        temp = this.getContent().replaceAll("</?[^>]+>", "");
        //除去空格
        contentNotag = temp.replaceAll("&nbsp;", "");
        return contentNotag;
    }

    public void setContentNotag(String contentNotag) {
        this.contentNotag = contentNotag;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

}
