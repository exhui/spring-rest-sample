package vip.maxhub.web.sample.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jinlei on 2017/4/21.
 */
public class CustomUserDetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException(username + " not found");
        }

        UserDetails user = new User("admin",
            "admin",
            AuthorityUtils.createAuthorityList("ADMIN"));

        return user;
    }
}
