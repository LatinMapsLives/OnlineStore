package by.demidov_a_r.onlinestore.service;


import by.demidov_a_r.onlinestore.dto.UserCreateEditDTO;
import by.demidov_a_r.onlinestore.dto.UserFilter;
import by.demidov_a_r.onlinestore.dto.UserReadDTO;
import by.demidov_a_r.onlinestore.mapper.UserCreateEditMapper;
import by.demidov_a_r.onlinestore.mapper.UserReadMapper;
import by.demidov_a_r.onlinestore.model.entity.Cart;
import by.demidov_a_r.onlinestore.model.entity.User;
import by.demidov_a_r.onlinestore.model.repository.QPredicates;
import by.demidov_a_r.onlinestore.model.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.demidov_a_r.onlinestore.model.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserCreateEditMapper userCreateEditMapper;
    private final UserReadMapper userReadMapper;

    @Transactional
    public Optional<UserReadDTO> register(UserCreateEditDTO userCreateEditDTO) {
        return Optional.of(Optional.of(userCreateEditDTO)
                .map(userCreateEditMapper::mapTo)
                .map(user -> {
                    user.setCart(Cart.builder().build());
                    return user;
                })
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::mapTo)
                .orElseThrow());
    }



    public Optional<UserReadDTO> authenticate(String login, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(login, password);
        return Optional.ofNullable(userReadMapper.mapTo(user.get()));
    }

    public List<UserReadDTO> findAll() {
        return userRepository.findAll().stream().map(userReadMapper::mapTo).toList();
    }

    public Page<UserReadDTO> findAll(UserFilter filter, Pageable pageable) {
        Predicate predicate = QPredicates.builder().add(filter.firstName(), user.personalInfo.firstName::containsIgnoreCase)
                .add(filter.lastName(), user.personalInfo.lastName::containsIgnoreCase)
                .add(filter.email(), user.personalInfo.email::containsIgnoreCase).build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::mapTo);
    }

    public List<UserReadDTO> findAll(UserFilter filter) {
        return userRepository.findAllByFilter(filter).stream().map(userReadMapper::mapTo).toList();
    }

    public Optional<UserReadDTO> findById(Long id) {
        return userRepository.findById(id).map(userReadMapper::mapTo);
    }

    @Transactional
    public Optional<UserReadDTO> update(Long id, UserCreateEditDTO userCreateEditDTO) {
        return userRepository.findById(id)
                .map(entity -> {
                    userCreateEditMapper.copy(userCreateEditDTO, entity);
                    return entity;
                })
                .map(userRepository::saveAndFlush).map(userReadMapper::mapTo);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(entity -> new org.springframework.security.core.userdetails.User(
                        entity.getUsername(),
                        entity.getPassword(),
                        Collections.singleton(entity.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
