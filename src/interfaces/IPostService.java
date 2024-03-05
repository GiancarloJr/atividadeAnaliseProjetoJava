package interfaces;

import java.util.List;

import model.Post;

public interface IPostService {
  List<Post> getPosts();

  Post getPostById(Integer id);

  Post createPost(Post post);

  Post updatePost(Post post);

  void deletePost(Integer id);
}
