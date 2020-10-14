package de.example.udemywebservice.service;

import de.example.udemywebservice.exception.UserNotFoundException;
import de.example.udemywebservice.model.Post;
import de.example.udemywebservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        User mai = new User(0, "Mai", new Date());
       // mai.addPost(new Post("MaiLife"));
        users.add(mai);
        users.add(new User(1, "Mia", new Date()));
        users.add(new User(2, "May", new Date()));

    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User deleteById(int UserId){
        if(findOne(UserId) != null){
            return users.remove(UserId);
        }
        return null;
    }


   /* public Post deletePost(int UserId, int postId){
        if(findOne(UserId) != null){
            return users.get(UserId).getPosts().remove(postId);
        }
        return null;
    }

    public int save(Post post, int UserId){
        if(findOne(UserId) != null){
            return users.get(UserId).addPost(post);
        }
        return -1;
    }*/
}
