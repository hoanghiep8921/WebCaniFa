package application.data.service;

import application.data.model.Comment;
import application.data.repository.CommentRepository;
import application.model.Comment.CommentByUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public ArrayList<Comment> listCommentByProduct(int productId){
        return commentRepository.getAllByProductId(productId);
    }

    public void addNewComment(Comment comment){
        commentRepository.save(comment);
    }
    public boolean updateCategory(Comment comment) {
        try {
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public boolean deleteCategory(int commentId) {
        try {
            commentRepository.delete(commentId);
            return true;
        } catch (Exception e) {

        }
        return false;
    }
}
