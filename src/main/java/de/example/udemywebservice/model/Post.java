package de.example.udemywebservice.model;

public class Post {

    private Integer id; //id = arraylist index
    private String post;

    public Post(String post) {
        this.post = post;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString(){
        return "id= " + id + ", post: " + post;
    }
}
