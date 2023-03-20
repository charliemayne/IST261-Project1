package com.example.ist261_project1;

public class PostData {

    public String[] postContents = new String[500];
    public String[] postUsers = new String[500];

    public int i = 0;
    public int j = 0;

    public void addToPostsContents(String postContent)
    {
        this.postContents[i] = postContent;
        i += 1;
    }

    public String[] getPostContent()
    {
        return postContents;
    }

    public void addToPostUsers(String postUser)
    {
        this.postUsers[j] = postUser;
        j += 1;
    }

    public String[] getPostUser()
    {
        return postUsers;
    }
}
