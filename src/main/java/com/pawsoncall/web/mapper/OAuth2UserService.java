package com.pawsoncall.web.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawsoncall.web.domain.ERole;
import com.pawsoncall.web.domain.Role;
import com.pawsoncall.web.domain.User;
import com.pawsoncall.web.util.Provider;

@Service
public class OAuth2UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void onAuthenticationSuccess(String curEmail, String curFirstName, String curLastName) {
        User user = userRepository.findByEmail(curEmail);
        if (user == null) {
            user = new User();
            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            user.setEmail(curEmail);
            user.setProvider(Provider.GOOGLE.name());
            user.setRoles(roles);
            user.setFirstName(curFirstName);
            user.setLastName(curLastName);

            userRepository.save(user);
        }
    }
}
