package com.backoven.catdogshelter.domain.adoption.query.service;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostDetailQueryDTO;
import com.backoven.catdogshelter.domain.adoption.query.dynamic.SearchCriteria;
import com.backoven.catdogshelter.domain.adoption.query.mapper.AdoptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

import com.backoven.catdogshelter.domain.adoption.query.dto.AdoptionPostAllQueryDTO;
import static com.backoven.catdogshelter.domain.adoption.query.template.Template.getSqlSession;


@Slf4j
@Service
public class AdoptionPostQueryServiceImpl implements AdoptionPostQueryService {
    private AdoptionMapper adoptionMapper;

    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionAllPosts() {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper = sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionMapper.selectAllAdoptionPosts();
        sqlSession.close();
        return adotpionPostList;
    }
    @Override
    public AdoptionPostDetailQueryDTO selectAdoptionPostById(int adoptionPostId) {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper = sqlSession.getMapper(AdoptionMapper.class);
        adoptionMapper.updateAdoptionPostView(adoptionPostId); // 조회수 증가
        sqlSession.commit(); // 조회수 update commit
        AdoptionPostDetailQueryDTO adoptionPostDetailDTO = adoptionMapper.selectAdoptionPostById(adoptionPostId);
        sqlSession.close();
        return adoptionPostDetailDTO;
    }
    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByView() {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper =sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionMapper.selectAllAdoptionPostsByView();
        sqlSession.close();
        return adotpionPostList;
    }
    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionAllPostsByLiked() {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper =sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionMapper.selectAllAdoptionPostByLiked();
        sqlSession.close();
        return adotpionPostList;
    }
    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionPostByKeyword(SearchCriteria keyword) {
        SqlSession sqlSession = getSqlSession();
        adoptionMapper =sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionMapper.selectAdoptionPostByKeyword(keyword);
        sqlSession.close();
        return adotpionPostList;
    }
    @Override
    public List<AdoptionPostAllQueryDTO> selectAdoptionPostByAnimalCondition(SearchCriteria animalCondition){
        SqlSession sqlSession = getSqlSession();
        adoptionMapper =sqlSession.getMapper(AdoptionMapper.class);
        List<AdoptionPostAllQueryDTO> adotpionPostList = adoptionMapper.selectAdoptionPostByAnimalCondition(animalCondition);
        sqlSession.close();
        return adotpionPostList;
    }
}
