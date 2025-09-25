package com.backoven.catdogshelter.domain.adoption.query.service;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailDTO;
import com.backoven.catdogshelter.domain.adoption.query.mapper.AdoptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostQueryDTO;
import static com.backoven.catdogshelter.domain.adoption.query.template.Template.getSqlSession;


@Slf4j
@Service
public class AdoptionPostQueryServiceImpl implements AdoptionPostQueryService {
    private AdoptionMapper adoptionMapper;

    public List<AdoptionPostQueryDTO> selectAdoptionAllPosts() {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper = sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostQueryDTO> adotpionPostList = adoptionMapper.selectAllAdoptionPosts();
        sqlSession.close();
        return adotpionPostList;
    }

    public AdoptionPostDetailDTO selectAdoptionPostById(int adoptionPostId) {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper =sqlSession.getMapper(AdoptionMapper.class);
        AdoptionPostDetailDTO adoptionPostDetailDTO = adoptionMapper.selectAdoptionPostById(adoptionPostId);
        sqlSession.close();
        return adoptionPostDetailDTO;
    }

    public List<AdoptionPostQueryDTO> selectAdoptionPostByKeyword(String keyword) {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper =sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostQueryDTO> adotpionPostList = adoptionMapper.selectAdoptionPostByKeyword(keyword);
        sqlSession.close();
        return adotpionPostList;
    }

    public List<AdoptionPostQueryDTO> selectAdoptionPostByAnimalCondition(Map<String, String> animalCodition){
        SqlSession sqlSession = getSqlSession();
        adoptionMapper =sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostQueryDTO> adotpionPostList = adoptionMapper.selectAdoptionPostByAnimalCondition(animalCodition);
        sqlSession.close();
        return adotpionPostList;
    }
}
