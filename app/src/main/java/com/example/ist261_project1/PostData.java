package com.example.ist261_project1;

public class PostData {

    public String[] postContents = new String[500];

    public int i = 0;

    public void addToPostsContents(String postContent)
    {
        this.postContents[i] = postContent;
        i += 1;
    }


    public String[] getPostContent()
    {
        return postContents;
    }


}
