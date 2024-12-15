package fr.eni.ludotheque.security;

import fr.eni.ludotheque.bo.Utilisateurs;
import fr.eni.ludotheque.dal.UtilisateursRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateursRepositoryImpl utilisateursRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UtilisateursRepositoryImpl utilisateursRepository, PasswordEncoder passwordEncoder) {
        this.utilisateursRepository = utilisateursRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateurs user = utilisateursRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé" + username));
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        System.out.println("USERNAME: " + user.getUsername() + " PASSWORD: " + encodedPassword);
        System.out.println("USERNAME: " + user.getUsername() + " PASSWORD: " + user.getPassword());

        return User.builder()
                    .username(user.getUsername())
                    .password(encodedPassword)
                    .roles(user.getRole())
                    .build();





    }
}
