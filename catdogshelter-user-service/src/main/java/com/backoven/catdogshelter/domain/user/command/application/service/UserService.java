package com.backoven.catdogshelter.domain.user.command.application.service;


import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyPasswordUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.requestdto.RequestModifyUserDTO;
import com.backoven.catdogshelter.domain.user.command.application.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

// solid... 를 위해 서비스 인터페이스
public interface UserService extends UserDetailsService {

    void registUser(UserDTO userDTO);

    UserDTO getUserById(Integer userId);

    UserDTO modifyUser(int userId, RequestModifyUserDTO updatedUser);

    void modifyUserPassword(int userId, RequestModifyPasswordUserDTO updatedUser);

    void deleterUserByPassword(int userId, String currentPwd);
}
