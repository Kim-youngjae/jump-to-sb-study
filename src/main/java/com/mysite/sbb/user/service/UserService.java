package com.mysite.sbb.user.service;

import com.mysite.sbb.user.entity.SiteUser;
import com.mysite.sbb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);

        /**
         * 사용자의 비밀번호를 저장할 때에는 보안을 위해서 반드시 암호화해서 저장해야 한다.
         * 암호화를 위해 시큐리티의 BCryptPasswordEncoder클래스를 사용하여 암호화 한다음
         * 비밀번호를 저장한다.
         */
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
}
