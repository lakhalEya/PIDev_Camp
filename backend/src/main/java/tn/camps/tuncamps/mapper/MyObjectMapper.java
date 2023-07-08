package tn.camps.tuncamps.mapper;

import org.springframework.stereotype.Component;
import tn.camps.tuncamps.dto.CommentDTO;
import tn.camps.tuncamps.dto.CommunitySpaceDTO;
import tn.camps.tuncamps.dto.PostDTO;
import tn.camps.tuncamps.persistence.entity.forum.Comment;
import tn.camps.tuncamps.persistence.entity.forum.CommunitySpace;
import tn.camps.tuncamps.persistence.entity.forum.Post;
import tn.camps.tuncamps.service.forum.ICommentService;
import tn.camps.tuncamps.service.forum.ICommunitySpace;
import tn.camps.tuncamps.service.forum.IPostService;

import java.util.*;

@Component
public class MyObjectMapper {

    private final ICommunitySpace communitySpaceService;
    private final IPostService postService;

    public MyObjectMapper(ICommunitySpace communitySpaceService,IPostService postService) {
        this.communitySpaceService = communitySpaceService;
        this.postService=postService;
    }

    public  Post toPost(PostDTO postDTO) throws Exception {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setCategory(postDTO.getCategory());
        post.setVisibility(postDTO.getVisibility());
        post.setContent(postDTO.getContent());
        post.setDatePublication(postDTO.getDatePublication());
        if(null!=postDTO.getComments() && !postDTO.getComments().isEmpty()){
            Set<Comment> comments = new HashSet<>();
            for (CommentDTO commentDTO : postDTO.getComments()) {
                Comment postComment =  new Comment();
                postComment.setContent(commentDTO.getContent());
                postComment.setDatePublication(commentDTO.getDatePublication());
                postComment.setIdComment(commentDTO.getIdComment());
                postComment.setPost(post);
                comments.add(postComment);
            }
            post.setComments(comments);
        }

        CommunitySpace communitySpace = communitySpaceService.retrieveCommunitySpace(postDTO.getCommunitySpaceId());
        if(null!=communitySpace){
            post.setCommunitySpace(communitySpace);
        }else{
            throw new Exception("community space not found with id "+postDTO.getCommunitySpaceId());
        }

        return post;
    }
    public  CommentDTO toCommentDto(Comment comment) throws Exception {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setIdComment(comment.getIdComment());
        commentDTO.setContent(comment.getContent());
        if(null!=comment.getPost()){
            commentDTO.setPostId(comment.getPost().getIdPost());
        }
        commentDTO.setDatePublication(comment.getDatePublication());
        return commentDTO;
    }
    public  Comment toComment(CommentDTO commentDTO) throws Exception {
        Comment comment = new Comment();
        comment.setIdComment(commentDTO.getIdComment());
        comment.setContent(commentDTO.getContent());

            Post post = postService.retrievePost(commentDTO.getPostId());
            if(null!=post){
                comment.setPost(post);
            }else{
                throw new Exception("Post not found with id "+commentDTO.getPostId());
            }
        comment.setDatePublication(commentDTO.getDatePublication());
        return comment;
    }
    public  PostDTO toPostDto(Post post) throws Exception {
        PostDTO postDTO = new PostDTO();
        postDTO.setIdPost(post.getIdPost());
        postDTO.setTitle(post.getTitle());
        postDTO.setCategory(post.getCategory());
        postDTO.setVisibility(post.getVisibility());
        postDTO.setContent(post.getContent());
        postDTO.setDatePublication(post.getDatePublication());
        if(null!=post.getComments() && !post.getComments().isEmpty()){
            List<CommentDTO> comments = new ArrayList<>();
            for (Comment comment: post.getComments()) {
                 comments.add(toCommentDto(comment));
            }
            postDTO.setComments(comments);
        }

        CommunitySpace communitySpace = post.getCommunitySpace();
        if(null!=communitySpace){
            postDTO.setCommunitySpaceId(communitySpace.getIdForum());
        }

        return postDTO;
    }

    public CommunitySpaceDTO toCommunitySpaceDto(CommunitySpace communitySpace) throws Exception {
        CommunitySpaceDTO communitySpaceDTO = new CommunitySpaceDTO();
        communitySpaceDTO.setIdForum(communitySpace.getIdForum());
        communitySpaceDTO.setDescription(communitySpace.getDescription());
        if(null!=communitySpace.getPosts() && !communitySpace.getPosts().isEmpty()){
            Set<PostDTO> postDTOS = new HashSet<>();
            for(Post post : communitySpace.getPosts()){
                postDTOS.add(toPostDto(post));
            }
            communitySpaceDTO.setPosts(postDTOS);
        }
        communitySpaceDTO.setFileData(communitySpace.getFileData());
        communitySpaceDTO.setFileName(communitySpace.getFileName());
        communitySpaceDTO.setTitle(communitySpace.getTitle());
        communitySpaceDTO.setCategory(communitySpace.getCategory());
        return communitySpaceDTO;
    }

    public CommunitySpace toCommunitySpace(CommunitySpaceDTO communitySpaceDTO) throws Exception {
        CommunitySpace communitySpace = new CommunitySpace();
        communitySpace.setIdForum(communitySpaceDTO.getIdForum());
        communitySpace.setDescription(communitySpaceDTO.getDescription());
        if(null!=communitySpaceDTO.getPosts() && !communitySpaceDTO.getPosts().isEmpty()){
            Set<Post> posts = new HashSet<>();
            for(PostDTO postDTO : communitySpaceDTO.getPosts()){
                posts.add(toPost(postDTO));
            }
            communitySpace.setPosts(posts);
        }
        communitySpace.setFileData(communitySpaceDTO.getFileData());
        communitySpace.setFileName(communitySpaceDTO.getFileName());
        communitySpace.setTitle(communitySpaceDTO.getTitle());
        return communitySpace;
    }
}
