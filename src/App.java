import java.util.List;

import interfaces.IPostService;
import model.Post;

public class App {
    public static void main(String[] args) throws Exception {
        IPostService postService = new services.PostsServices();
        List<Post> posts = postService.getPosts();
        Post postById = postService.getPostById(1);

        posts.forEach(post -> {
            System.out.println("-----------------------");
            System.out.println("Title: - " + post.getTitle());
            System.out.println("Body:" + post.getBody());
            System.out.println("----------------------");
        });

        System.out.println("--------BY ID---------------");
        System.out.println("Title: - " + postById.getTitle());
        System.out.println("Body:" + postById.getBody());
        System.out.println("----------------------");

    }
}
