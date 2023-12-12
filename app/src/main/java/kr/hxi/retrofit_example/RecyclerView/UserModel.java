package kr.hxi.retrofit_example.RecyclerView;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private int id;

//    @SerializedName("avatar_url")
//    private String avatarUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;

    public UserModel(String login, int id, String name, String htmlUrl, String location) {
        this.login = login;
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.location = location;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

//    public String getAvatarUrl() {
//        return avatarUrl;
//    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
