package application.data.repository;

import application.data.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    @Query("select c.id,c.productId,c.userId,c.comment,c.created,c.like,c.dislike from comment c where c.productId=?1")
//    ArrayList<Comment> listCommentByProduct(int productId);


    ArrayList<Comment> getAllByProductId(int productId);

}
