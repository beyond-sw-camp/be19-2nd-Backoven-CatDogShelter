package com.backoven.catdogshelter.domain.post.query.service;

import com.backoven.catdogshelter.domain.post.query.dto.PostDetailDTO;
import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import com.backoven.catdogshelter.domain.post.query.mapper.PostDetailMapper;
import com.backoven.catdogshelter.domain.post.query.mapper.PostInventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("postQueryService")
public class PostService {

    private final PostInventoryMapper postInventoryMapper;
    private final PostDetailMapper postDetailMapper;

    @Autowired
    public PostService(PostInventoryMapper postInventoryMapper, PostDetailMapper postDetailMapper) {
        this.postInventoryMapper = postInventoryMapper;
        this.postDetailMapper = postDetailMapper;
    }

    public List<PostInventoryDTO> selectPostInventory() {
        return postInventoryMapper.selectPostInventory();
    }

    /* 지금은 DTO에서 readOnly로 select문 사용하기 때문에 Transactional가 굳이 필요하지 않지만 연습삼아 넘어봄 */
    /* 실무에서는 insert, update, delete 등 여러 sql문이 사용될 때 중간에 이상 현상이 발생하면 롤백하기 위해서 사용됨 */
    @Transactional(readOnly = true)
    public PostDetailDTO selectPostDetail(int id){
        return postDetailMapper.selectPostDetail(id);
    }
}
