package com.kodgemisi.course.ecommerce.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

   public void createUser(RegistrationDTO registrationDTO){
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles= new HashSet<>();
        roles.add(roleRepository.findByRoleName(Role.RoleName.ROLE_USER));
        user.setRoles(roles);
        userRepository.save(user);

    }

    public void createAdmin(User user){
        userRepository.save(user);
    }
}
