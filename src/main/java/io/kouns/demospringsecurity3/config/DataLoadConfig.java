package io.kouns.demospringsecurity3.config;


import io.kouns.demospringsecurity3.entities.Privilege;
import io.kouns.demospringsecurity3.entities.Role;
import io.kouns.demospringsecurity3.entities.User;
import io.kouns.demospringsecurity3.entities.enums.TypePrivilege;
import io.kouns.demospringsecurity3.entities.enums.UserType;
import io.kouns.demospringsecurity3.repositories.PrivilegeRepository;
import io.kouns.demospringsecurity3.repositories.RoleRepository;
import io.kouns.demospringsecurity3.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataLoadConfig {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${admin.role}")
    private String adminRole;

    @Value("${registerUser.email}")
    private String registerEmail;
    @Value("${registerUser.firstName}")
    private String registerFirstName;
    @Value("${registerUser.lastName}")
    private String registerLastName;
    @Value("${registerUser.password}")
    private String password;

    public DataLoadConfig(RoleRepository roleRepository, PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void loadData() {
        loadRole();
    }


    public void loadRole() {
        if (roleRepository.count() > 0) return;

        List<Privilege> privilegeAdmin = new ArrayList<>();
        for (int i = 1; i < TypePrivilege.values().length; i++) {
            String description = TypePrivilege.values()[i].toString().replace("_", " ");
            privilegeAdmin.add(privilegeRepository.save(new Privilege(TypePrivilege.values()[i], description)));
        }

        Role roleAdmin = new Role(adminRole, adminRole);
        roleAdmin.setPrivileges(privilegeAdmin);
        roleAdmin = roleRepository.save(roleAdmin);


        User user = new User();
        user.setEmail(registerEmail);
        user.setUsername(registerFirstName);
        user.setFirstname(registerFirstName);
        user.setLastname(registerLastName);
        user.setPassword(this.passwordEncoder.encode(password));
        user.setRole(roleAdmin);
        user.setUserType(UserType.DASHBOARD_USER);
        userRepository.save(user);



        User user2 = new User();
        user2.setEmail("registerEmail@gmail.com");
        user2.setUsername(registerFirstName);
        user2.setFirstname(registerFirstName);
        user2.setLastname(registerLastName);
        user2.setPassword(this.passwordEncoder.encode(password));
        user2.setRole(roleAdmin);
        user2.setDeleted(true);
        user2.setUserType(UserType.DASHBOARD_USER);
        userRepository.save(user2);
    }
}
