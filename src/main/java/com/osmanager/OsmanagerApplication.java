package com.osmanager;

import com.osmanager.entity.Role;
import com.osmanager.entity.Usuario;
import com.osmanager.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OsmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsmanagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner configurarAdmin(UsuarioRepository repository, PasswordEncoder encoder) {
        return args -> {

            Usuario admin = repository.findByEmail("admin@osmanager.com").orElse(new Usuario());

            admin.setNome("Administrador");
            admin.setEmail("admin@osmanager.com");

            admin.setSenha(encoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            repository.save(admin);

            System.out.println("=====================================================");
            System.out.println(" USUÁRIO ADMIN CONFIGURADO COM SUCESSO PELO SPRING!");
            System.out.println("E-mail: admin@osmanager.com");
            System.out.println("Senha: admin123");
            System.out.println("=====================================================");
        };
    }
}