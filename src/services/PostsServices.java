package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import interfaces.IPostService;
import model.Post;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PostsServices implements IPostService {
  private final Gson gson = new Gson();

  public List<Post> getPosts() {
    try {
      URL url = new URL("https://jsonplaceholder.typicode.com/posts");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
          response.append(line);
        }

        Type listType = new TypeToken<ArrayList<Post>>() {
        }.getType();
        return gson.fromJson(response.toString(), listType);

      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      // TODO: handle exception
    }
    return new ArrayList<>();
  }

  @Override
  public Post getPostById(Integer id) {
    try {
      URL url = new URL("https://jsonplaceholder.typicode.com/posts/"+id);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
          response.append(line);
        }

        Type post = new TypeToken<Post>() {
        }.getType();
        return gson.fromJson(response.toString(), post);

      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      // TODO: handle exception
    }

    return new Post();
  }

  @Override
  public Post createPost(Post post) {
    try {

      URL url = new URL("https://jsonplaceholder.typicode.com/posts/");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/");
      con.setRequestProperty("Accept", "application/json");
      con.setDoOutput(true);

      String jsonInputString = gson.toJson(post);

      try(OutputStream os = con.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      Type postResponse = new TypeToken<Post>() {
      }.getType();

      try(BufferedReader br = new BufferedReader(
              new InputStreamReader(con.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine.trim());
        }

        postResponse = gson.fromJson(response.toString(), postResponse);
        System.out.println(response);
      }


      return (Post) postResponse;

    } catch (Exception e) {
      System.out.println(e.getMessage());
      // TODO: handle exception
    }

    return new Post();
  }

  @Override
  public Post updatePost(Post post) {
    try {

      String jsonInputString = "";
      Type postType = new TypeToken<Post>() {
      }.getType();

      URL url = new URL("https://jsonplaceholder.typicode.com/posts/"+post.getId());
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
          response.append(line);
        }

        jsonInputString =  gson.fromJson(response.toString(), postType);
      }

      con.disconnect();

      URL urlUpdate = new URL("https://jsonplaceholder.typicode.com/posts/"+post.getId());
      HttpURLConnection con2 = (HttpURLConnection) urlUpdate.openConnection();
      con2.setRequestMethod("PUT");
      con2.setRequestProperty("Content-Type", "application/");
      con2.setRequestProperty("Accept", "application/json");
      con2.setDoOutput(true);


      try(OutputStream os = con2.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      Post postResponse = new Post();

      try(BufferedReader br = new BufferedReader(
              new InputStreamReader(con2.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine.trim());
        }

        postResponse = gson.fromJson(jsonInputString, postType);
        System.out.println(response);
      }


      return postResponse;

    } catch (Exception e) {
      System.out.println(e.getMessage());
      // TODO: handle exception
    }

    return new Post();
  }

  @Override
  public void deletePost(Integer id) {

    URL url = null;
    try {
      url = new URL("https://jsonplaceholder.typicode.com/posts/"+id);
    } catch (MalformedURLException exception) {
      exception.printStackTrace();
    }
    HttpURLConnection httpURLConnection = null;
    try {
      httpURLConnection = (HttpURLConnection) url.openConnection();
      httpURLConnection.setRequestProperty("Content-Type",
              "application/x-www-form-urlencoded");
      httpURLConnection.setRequestMethod("DELETE");
      System.out.println(httpURLConnection.getResponseCode());
    } catch (IOException exception) {
      exception.printStackTrace();
    } finally {
      if (httpURLConnection != null) {
        httpURLConnection.disconnect();
      }
    }

  }

}
