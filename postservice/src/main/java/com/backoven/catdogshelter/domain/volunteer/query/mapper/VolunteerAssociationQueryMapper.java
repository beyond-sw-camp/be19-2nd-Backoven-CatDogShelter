package com.backoven.catdogshelter.domain.volunteer.query.mapper;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationQueryDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationSearchCond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VolunteerAssociationQueryMapper {

    // 파라미터 이름을 명시해도 되고(권장), 안 해도 되지만 XML은 cond.*로 접근해야 함
    List<VolunteerAssociationQueryDTO> searchAssociations(@Param("cond") VolunteerAssociationSearchCond cond);

    VolunteerAssociationQueryDTO findAssociationById(@Param("id") Integer id);
}

