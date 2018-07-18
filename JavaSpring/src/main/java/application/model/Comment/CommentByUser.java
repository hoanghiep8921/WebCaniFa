package application.model.Comment;

import application.extension.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class CommentByUser {
    private int userId;

    private String userName;

    private int productId;

    private int like;

    private int dislike;

    private String comment;

    private String created;


    public CommentByUser(int userId, String userName, int productId, int like, int dislike, String comment, String created) {
        this.userId = userId;
        this.userName = userName;
        this.productId = productId;
        this.like = like;
        this.dislike = dislike;
        this.comment = comment;
        this.created = created;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
