package com.backoven.catdogshelter.domain.volunteer.query.service;

import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationQueryDTO;
import com.backoven.catdogshelter.domain.volunteer.query.dto.VolunteerAssociationSearchCond;
import com.backoven.catdogshelter.domain.volunteer.query.mapper.VolunteerAssociationQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerAssociationQueryServiceImpl implements VolunteerAssociationQueryService {

    private final VolunteerAssociationQueryMapper mapper;

    @Override
    public VolunteerAssociationQueryDTO selectVolunteerAssociation(Integer id) {
        return mapper.findAssociationById(id);
    }

    @Override
    public List<VolunteerAssociationQueryDTO> selectVolunteerAssociationsBySearch(VolunteerAssociationSearchCond cond) {
        // 기본값 세팅 (PageResponse 없이)
        if (cond.getLimit() == null || cond.getLimit() <= 0) cond.setLimit(10);
        if (cond.getOffset() == null || cond.getOffset() < 0) cond.setOffset(0);

        // orderBy 화이트리스트
        String ob = cond.getOrderBy();
        if (ob == null || ob.isBlank()) cond.setOrderBy("createdAt"); // 기본 정렬키
        String dir = cond.getOrderDir();
        if (dir == null || (!dir.equalsIgnoreCase("ASC") && !dir.equalsIgnoreCase("DESC"))) {
            cond.setOrderDir("DESC");
        }
        return mapper.searchAssociations(cond);
    }
}
