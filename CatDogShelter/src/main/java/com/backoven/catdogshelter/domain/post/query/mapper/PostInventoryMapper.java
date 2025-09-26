package com.backoven.catdogshelter.domain.post.query.mapper;

/* xml의 내용을 가져와서 Mapping하는 인터페이스 */

import com.backoven.catdogshelter.domain.post.query.dto.PostInventoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostInventoryMapper {

    // 조회 할때 파라미터 값이 필요 없다.
    List<PostInventoryDTO> selectPostInventory();

}
