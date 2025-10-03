package com.backoven.catdogshelter.domain.user.command.application.service;

import com.backoven.catdogshelter.common.entity.QuestionCategoryEntity;
import com.backoven.catdogshelter.common.entity.SigunguEntity;
import com.backoven.catdogshelter.common.entity.UserEntity;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyPasswordUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;
import com.backoven.catdogshelter.domain.user.command.domain.aggregate.enumeration.UserStatus;
import com.backoven.catdogshelter.domain.user.command.domain.repository.QuestionCategoryRepository;
import com.backoven.catdogshelter.domain.user.command.domain.repository.SigunguRepository;
import com.backoven.catdogshelter.domain.user.command.domain.repository.UserRepository;
import com.backoven.catdogshelter.domain.user.security.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SigunguRepository sigunguRepository;
    private final QuestionCategoryRepository questionCategoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           SigunguRepository sigunguRepository,
                           QuestionCategoryRepository questionCategoryRepository,
                           ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sigunguRepository = sigunguRepository;
        this.questionCategoryRepository = questionCategoryRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 비밀번호 암호화
    @Override
    public void registUser(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO,UserEntity.class);
        log.info("Service 계층에서 DTO -> Entity: {}", UserEntity.class);

        // UserDTO로 넘어온 사용자의 암호(평문)를 BCrypt 암호화(다이체스트)
        userEntity.setEncryptPwd(bCryptPasswordEncoder.encode(userDTO.getUserPassword()));
        userRepository.save(userEntity);
    }

    /* 설명. 단순 회원정보 조회에서 =>
    *   + 회원정보 + 회원의 주문내약(Order(다른 도메인)) */
    @Override
    public UserDTO getUserById(Integer userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        /* 설명. 회원이 주문한 내역은 Order서비스에서
        *       Feign client 방식으로 조회해서 가져오기*/
//        List<ResponseOrderDTO> orderList = orderServiceClient.getUserOrders(memNo);
//        userDTO.setOrders(orderList);

        return userDTO;
    }

    @Transactional
    public UserDTO modifyUser(int userId, RequestModifyUserDTO updatedUser) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 수정할 값만 반영
        if (updatedUser.getDetailAddress() != null) {
            userEntity.setDetailAddress(updatedUser.getDetailAddress());
        }
        if (updatedUser.getSigunguId() != null) {
            SigunguEntity sigungu = sigunguRepository.findById(updatedUser.getSigunguId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 시군구가 존재하지 않습니다."));
            userEntity.setSigungu(sigungu);
        }
        if (updatedUser.getQuestionCategoryId() != null) {
            QuestionCategoryEntity questionCategory = questionCategoryRepository.findById(updatedUser.getQuestionCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 질문 카테고리가 존재하지 않습니다."));
            userEntity.setQuestionCategory(questionCategory);
        }
        if (updatedUser.getAnswer() != null) {
            userEntity.setAnswer(updatedUser.getAnswer());
        }

        userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDTO.class);
    }

    // 유저서비스디테일 물려받아서 오버라이딩
    /* 설명. spring security 사용 시 프로바이더에서 활용할 로그인용 메소드
    *       -> UserDetails 타입을 반환하는 메소드(상속받아서 오버라이딩) */
    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        /* 설명. 넘어온 email과 일치하는 email을 가진 회원을 조회해서
        *       UserEntity로 반환(조회) */
        // 쿼리 메소드를 활용한 email where절 활용
        UserEntity loginUser = userRepository.findByUserAccount(userAccount);
        /* 설명. 사용자가 로그인 시 아이디을 잘못 입력 했다면 */
        if(loginUser == null){
            throw new UsernameNotFoundException(userAccount + " 아이디의 유저는 존재하지 않습니다.");
        }

        /* 설명. DB에서 조회 된 해당 회원이
        *       가진 권한들을 가져와 List<GrantedAuthority>로 전환*/
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new CustomUserDetails(
                loginUser.getUserId(),
                loginUser.getUserAccount(),
                loginUser.getEncryptPwd(),
                grantedAuthorities
        );
        // 이 객체에 username, password, authorities 빼고 나머지 담음
//        MemberDTO memberDTO = new MemberDTO();
//        UserImpl userImpl =
//                new UserImpl(loginUser.getEmail(),
//                        loginUser.getEncryptPwd(),
//                        grantedAuthorities);
//        userImpl.setDetails(memberDTO);

//        return userImpl;
    }

    @Override
    @Transactional
    public void modifyUserPassword(int userId, RequestModifyPasswordUserDTO updatedUser) {
        UserEntity foundUser = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        // 기존 비밀번호와 일치하지 않다면 예외처리
        if(!bCryptPasswordEncoder.matches(updatedUser.getCurrentPwd(), foundUser.getEncryptPwd())){
            throw new IllegalArgumentException();
        }
        // 새로운 비밀번호가 기존비밀번호와 일치한다면 예외처리
        if (bCryptPasswordEncoder.matches(updatedUser.getNewPwd(), foundUser.getEncryptPwd())) {
            throw new IllegalArgumentException("새 비밀번호가 기존과 동일합니다.");
        }
        // 지정질문
        boolean questionOk = foundUser.getQuestionCategory() != null &&
                Objects.equals(updatedUser.getQuestionId(),
                        foundUser.getQuestionCategory().getId());

        String updatedUserAnswer = updatedUser.getAnswer();
        String foundUserAnswer  = foundUser.getAnswer();

        // 답변이 동일하다면
        boolean answerOk = updatedUserAnswer != null && foundUserAnswer != null
                && updatedUserAnswer.trim().equalsIgnoreCase(foundUserAnswer.trim());

        if (questionOk && answerOk) {
            // 새 비밀번호로 갱신
            foundUser.setEncryptPwd(bCryptPasswordEncoder.encode(updatedUser.getNewPwd()));
            return;
        }
        throw new IllegalArgumentException("질문/답변 불일치");
    }

    @Override
    @Transactional
    public void deleterUserByPassword(int userId, String currentPwd) {
        UserEntity foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 기존 비밀번호와 일치하지 않다면 예외처리
        if (!bCryptPasswordEncoder.matches(currentPwd, foundUser.getEncryptPwd())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        // 탈퇴 -> soft delete (상태 변경)
        foundUser.setUserStatus(UserStatus.CANCEL);
    }
}
