package daggerok.admin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static java.util.Arrays.asList;

@Configuration
@ComponentScan(basePackageClasses = AdminUserRepository.class)
public class AdminTestData {

    @Bean
    CommandLineRunner testData(AdminUserRepository adminUserRepository) {

        return args -> adminUserRepository.encodePasswordAndSave(new AdminUser()
                .setRoles(asList("ADMIN", "SUPERADMIN"))
                .setUsername("superadmin")
                .setPassword("admin")
                .setEnabled(true));
    }
}
