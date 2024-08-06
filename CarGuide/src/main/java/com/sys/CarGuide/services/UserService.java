package com.sys.CarGuide.services;

import com.sys.CarGuide.models.dto.UserDTO;
import com.sys.CarGuide.models.entities.Role;
import com.sys.CarGuide.models.projection.UserDetailsProjection;
import com.sys.CarGuide.repositories.UserRepository;
import com.sys.CarGuide.services.mapper.UserMapper;
import com.sys.CarGuide.utils.LoggedUser;
import lombok.RequiredArgsConstructor;
import com.sys.CarGuide.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = userRepository.searchUserAndRoleByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        user.setId(result.get(0).getId());
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    public User authenticated() {
        try {
            String userName = loggedUser.getLoggedUserName();
            return userRepository.findByEmail(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid user");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO loggedUserData() {
        User entity = authenticated();
        return userMapper.toUserDTO(entity);
    }

}
