package com.rev.demoSecurity.Service;

import com.rev.demoSecurity.model.UserPrincipal;
import com.rev.demoSecurity.model.Users;
import com.rev.demoSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class mUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=userRepo.findByUsername(username);
        if(user==null)
            throw new UsernameNotFoundException("username not found");
        return new UserPrincipal(user);
    }
}
