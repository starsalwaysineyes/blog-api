package com.jt.service.impl;

import com.jt.common.util.UserUtils;
import com.jt.entity.Article;
import com.jt.entity.Comment;
import com.jt.repository.ArticleRepository;
import com.jt.repository.CommentRepository;
import com.jt.repository.UserRepository;
import com.jt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Integer saveComment(Comment comment) {

        return commentRepository.save(comment).getId();
    }


    @Override
    @Transactional
    public void deleteCommentById(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            commentRepository.delete(comment);
        }
    }

    @Override
    public List<Comment> listCommentsByArticle(Integer id) {
        Article a = new Article();
        a.setId(id);
        return commentRepository.findByArticleAndLevelOrderByCreateDateDesc(a, "0");
    }

    @Override
    @Transactional
    public Comment saveCommentAndChangeCounts(Comment comment) {

        int count = 1;
        Article a = articleRepository.findById(comment.getArticle().getId()).orElse(null);
        a.setCommentCounts(a.getCommentCounts() + count);

        comment.setAuthor(UserUtils.getCurrentUser());
        comment.setCreateDate(new Date());

        //设置level
        if(null == comment.getParent()){
            comment.setLevel("0");
        }else{
            if(null == comment.getToUser()){
                comment.setLevel("1");
            }else{
                comment.setLevel("2");
            }
        }

        return commentRepository.save(comment);

    }

    @Override
    @Transactional
    public void deleteCommentByIdAndChangeCounts(Integer id) {
        int count = 1;
        Comment c = commentRepository.findById(id).orElse(null);
        Article a = c.getArticle();

        a.setCommentCounts(a.getCommentCounts() - count);

        commentRepository.delete(c);
    }


}
