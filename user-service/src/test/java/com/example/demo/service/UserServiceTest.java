package com.example.demo.service;

import com.example.demo.jooq.tables.daos.UserDao;
import com.example.demo.jooq.tables.pojos.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;



public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByUsername() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setPassword("111111");
        List<UserEntity> list = List.of(userEntity);
        Mockito.when(userDao.fetchByUsernameTable("test"))
                .thenReturn(list);
        assertThat(userService.findByUsername("test").getUsername())
                .isEqualTo("test");
    }
}