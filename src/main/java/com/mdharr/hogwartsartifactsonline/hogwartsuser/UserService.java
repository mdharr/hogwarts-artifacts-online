package com.mdharr.hogwartsartifactsonline.hogwartsuser;

import com.mdharr.hogwartsartifactsonline.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        // TODO: encode password before saving to database.
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
}
