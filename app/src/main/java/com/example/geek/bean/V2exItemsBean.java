package com.example.geek.bean;

public class V2exItemsBean {
    private String image;
    private String commentCount ;
    private String text;
    private String text1;
    private String author;
    private String commentPeople;

    public V2exItemsBean() {

    }

    public V2exItemsBean(String image, String commentCount, String text, String text1, String author, String commentPeople) {
        this.image = image;
        this.commentCount = commentCount;
        this.text = text;
        this.text1 = text1;
        this.author = author;
        this.commentPeople = commentPeople;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommentPeople() {
        return commentPeople;
    }

    public void setCommentPeople(String commentPeople) {
        this.commentPeople = commentPeople;
    }

    @Override
    public String toString() {
        return "V2exItemsBean{" +
                "image='" + image + '\'' +
                ", commentCount='" + commentCount + '\'' +
                ", text='" + text + '\'' +
                ", text1='" + text1 + '\'' +
                ", author='" + author + '\'' +
                ", commentPeople='" + commentPeople + '\'' +
                '}';
    }
}
