package application.controller.api;

import application.data.model.Category;
import application.data.model.Comment;
import application.data.model.User;
import application.data.service.CommentService;
import application.data.service.UserService;
import application.data.service.UserServiceImpl;
import application.model.BaseApiResult;
import application.model.Comment.CommentByUser;
import application.model.Comment.CommentDataModel;
import application.model.DataApiResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/addComment",method = RequestMethod.POST)
    public BaseApiResult createComment(@RequestBody CommentDataModel commentDataModel){
        DataApiResult result = new DataApiResult();
        try {
            if (!"".equals(commentDataModel.getUserId())
                    && !"".equals(commentDataModel.getComment())) {
                    Comment commentEntity = new Comment();
                    commentEntity.setComment(commentDataModel.getComment());
                    commentEntity.setProductId(commentDataModel.getProductId());
                    commentEntity.setUserId(commentDataModel.getUserId());
                    commentEntity.setLike(0);
                    commentEntity.setDislike(0);
                    commentEntity.setCreatedDate(new Date());
                    commentService.addNewComment(commentEntity);
                    result.setSuccess(true);
                    result.setMessage("Ok");
                    result.setData("Thành công");
                    return result;
            } else {
                result.setSuccess(false);
                result.setMessage("Invalid model");
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

    @RequestMapping("/listComment/{productId}")
    public BaseApiResult getListComment(@PathVariable int productId){
        DataApiResult result= new DataApiResult();
        ArrayList<Comment> listComment=commentService.listCommentByProduct(productId);
        ArrayList<CommentByUser> listCommentUser = new ArrayList<>();
        for(Comment c : listComment){
            CommentByUser tmp = new CommentByUser(c.getUserId(),userService.findUserById(c.getUserId()).getName(),c.getProductId(),c.getLike(),c.getDislike(),c.getComment(),c.getCreatedDate().toString());
            listCommentUser.add(tmp);
        }
        result.setData(listCommentUser);
        result.setSuccess(true);
        result.setMessage("OK");
        return result;
    }
}
