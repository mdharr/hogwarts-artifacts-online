package com.mdharr.hogwartsartifactsonline.hogwartsuser;

import com.mdharr.hogwartsartifactsonline.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<HogwartsUser> findAll() {
        return this.userRepository.findAll();
    }

    public HogwartsUser findById(Integer userId) {
        HogwartsUser user = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        return user;
    }

    public HogwartsUser save(HogwartsUser newUser) {
        // TODO: encode plain text password before saving to database.
        newUser.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        return this.userRepository.save(newUser);
    }

    /**
     * We are not using this update to change user password.
     *
     * @param userId
     * @param update
     * @return
     */
    public HogwartsUser update(Integer userId, HogwartsUser update) {
        HogwartsUser foundUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        foundUser.setUsername(update.getUsername());
        foundUser.setEnabled(update.isEnabled());
        foundUser.setRoles(update.getRoles());
        return this.userRepository.save(foundUser);
    }

    public void delete(Integer userId) {
        HogwartsUser foundUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("user", userId));
        this.userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username) // First, we need to find this user from the database
                .map(MyUserPrincipal::new) // If found wrap the return user instance in a MyUserPrincipal instance, was: hogwartsUser -> new MyUserPrincipal(hogwartsUser)
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " is not found.")); // Otherwise throw an exception
    }
}
