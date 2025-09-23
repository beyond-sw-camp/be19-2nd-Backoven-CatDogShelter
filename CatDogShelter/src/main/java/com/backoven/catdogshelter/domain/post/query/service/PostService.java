package com.backoven.catdogshelter.domain.post.query.service;

import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import com.backoven.catdogshelter.domain.post.query.mapper.PostInventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostInventoryMapper postInventoryMapper;

    @Autowired
    public PostService(PostInventoryMapper postInventoryMapper) {
        this.postInventoryMapper = postInventoryMapper;
    }

    public List<PostInventoryDTO> getPostInventory() {
        return postInventoryMapper.selectPostInventory();
    }
}
