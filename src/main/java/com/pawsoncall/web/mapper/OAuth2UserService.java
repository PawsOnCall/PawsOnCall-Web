package com.pawsoncall.web.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pawsoncall.web.domain.User;
import com.pawsoncall.web.util.Provider;

@Service
public class OAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    public void onAuthenticationSuccess(String curEmail, String curFirstName, String curLastName) {
        User user = userRepository.findByEmail(curEmail);
        if (user == null) {
            user = new User();
            user.setEmail(curEmail);
            user.setProvider(Provider.GOOGLE.name());
            user.setRole("USER");
            user.setFirstName(curFirstName);
            user.setLastName(curLastName);

            userRepository.save(user);
        }
    }
}
