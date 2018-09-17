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

    public void setPostId(int postId) {
        this.id = postId;
    }

    public void setPostTitle(String postTitle) {
        this.title = postTitle;
    }

    public void setPostCategory(String postCategory) {
        this.categories = postCategory;
    }

    public void setPostContent(String postContent) {
        this.content = postContent;
    }
}
