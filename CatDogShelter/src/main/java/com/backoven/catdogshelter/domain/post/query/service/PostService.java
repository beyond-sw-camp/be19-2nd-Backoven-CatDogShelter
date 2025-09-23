package com.backoven.catdogshelter.domain.post.query.service;

import com.backoven.catdogshelter.domain.post.query.dto.PostDetailDTO;
import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import com.backoven.catdogshelter.domain.post.query.mapper.PostDetailMapper;
import com.backoven.catdogshelter.domain.post.query.mapper.PostInventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    private final PostInventoryMapper postInventoryMapper;
    private final PostDetailMapper postDetailMapper;

    @Autowired
    public PostService(PostInventoryMapper postInventoryMapper, PostDetailMapper postDetailMapper) {
        this.postInventoryMapper = postInventoryMapper;
        this.postDetailMapper = postDetailMapper;
    }

    public List<PostInventoryDTO> getPostInventory() {
        return postInventoryMapper.selectPostInventory();
    }

    @Transactional(readOnly = true)
    public PostDetailDTO getPostDetail(int id){
        return postDetailMapper.selectPostDetail(id);
    }
}
