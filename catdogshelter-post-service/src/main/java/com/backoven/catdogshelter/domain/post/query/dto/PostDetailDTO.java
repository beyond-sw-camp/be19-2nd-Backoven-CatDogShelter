package com.backoven.catdogshelter.domain.post.query.dto;

/* PostDetailDTO와 PostInventoryDTO와 겹쳐지는 내용(번호, 제목, 작성일, 작성자, 조회 수, 추천수)이 많으므로 상속 관계 사용 */
/* PostInventory 타입으로 선언 시 같은 값을 중복해서 사용하므로 유지보수성이 떨어짐 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDetailDTO extends PostInventoryDTO {
    private String ratingName;
    private String content;
    private List<PostFilesDTO> files;
    private List<PostCommentDTO> comments;
}
