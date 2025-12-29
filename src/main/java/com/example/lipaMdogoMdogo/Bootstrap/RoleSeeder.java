package com.example.lipaMdogoMdogo.Bootstrap;

import com.example.lipaMdogoMdogo.models.Role;
import com.example.lipaMdogoMdogo.models.RoleEnum;
import com.example.lipaMdogoMdogo.repository.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadRoles();
    }
    private void loadRoles(){
        RoleEnum[] roleNames = new RoleEnum[] {RoleEnum.ADMIN, RoleEnum.USER, RoleEnum.SUPER_ADMIN, RoleEnum.LOAN_APPROVER};
        Map<RoleEnum, String> roleDescription = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.LOAN_APPROVER, "Loan approver role",
                RoleEnum.SUPER_ADMIN, "Super admin can create admins and other users"
        );
        Arrays.stream(roleNames).forEach((roleName)->{
            Optional<Role> optionalRole = roleRepository.findByName(roleName);
           optionalRole.ifPresentOrElse(System.out::println, ()->{
               Role roleToCreate = new Role();
               roleToCreate.setName(roleName);
               roleToCreate.setDescription(roleDescription.get(roleName));
               roleRepository.save(roleToCreate);
           });
        });

    }
}
