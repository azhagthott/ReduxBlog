package com.android.dev.reduxblog.model;

public class Post {

    public int id;
    public String title;
    public String categories;
    public String content;

    public Post() {
    }

    public Post(int postId) {
        this.id = postId;
    }

    public int getPostId() {
        return id;
    }

    public void setPostId(int postId) {
        this.id = postId;
    }

    public String getPostTitle() {
        return title;
    }

    public void setPostTitle(String postTitle) {
        this.title = postTitle;
    }

    public String getPostCategory() {
        return categories;
    }

    public void setPostCategory(String postCategory) {
        this.categories = postCategory;
    }

    public String getPostContent() {
        return content;
    }

    public void setPostContent(String postContent) {
        this.content = postContent;
    }
}
