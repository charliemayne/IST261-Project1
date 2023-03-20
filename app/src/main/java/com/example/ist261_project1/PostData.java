package com.example.ist261_project1;

public class PostData {

    public String[] postContents = new String[500];
    public int[] postUsers = new int[500];
    public String[] postUsernames = new String[500];

    public int i = 0;
    public int j = 0;
    public int k = 0;

    public void addToPostsContents(String postContent)
    {
        this.postContents[i] = postContent;
        i += 1;
    }

    public String[] getPostContent()
    {
        return postContents;
    }

    public void addToPostUsers(int postUser)
    {
        this.postUsers[j] = postUser;
        j += 1;
    }

    public int[] getPostUser()
    {
        return postUsers;
    }

    public void addToPostUsernames(String postUsername)
    {
        this.postUsernames[k] = postUsername;
        k += 1;
    }

    public String[] getPostUsernames()
    {
        return postUsernames;
    }

}
