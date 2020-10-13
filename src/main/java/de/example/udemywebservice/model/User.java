package de.example.udemywebservice.model;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private Integer id;

    @Size(min = 2,message = "name should have at least 2 characters")
    private String name;

    @Past
    private Date date;


    private List<Post> posts;

//public static final User EMPTY = new User(0, "not defined", new Date());
    //instead default constructor? right use?
    protected User(){} //default constructor notwendig?

    public User(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.posts = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bday=" + date +
                '}';
    }

    public int addPost(Post post){
        this.posts.add(post);
        post.setId(this.getPosts().indexOf(post));
        return this.getPosts().indexOf(post);

    }

    /*public String postsToString(){
        String result = "";
        for(Post post : posts){
            result= "PostId: " + post.getId() +
            "post: " + post + "/n";
        }
        return result;
    }*/
}
