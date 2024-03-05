package model;

public class Post {
  Integer userId;
  Integer id;
  String title;
  String body;

  public Post() {
  }

  public Post(Integer userId, Integer id, String title, String body) {
    this.userId = userId;
    this.id = id;
    this.title = title;
    this.body = body;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getBody() {
    return body;
  }

}
