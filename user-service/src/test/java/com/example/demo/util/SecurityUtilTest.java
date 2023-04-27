package com.example.demo.util;

import com.huagui.common.base.context.OperationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.mock;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class SecurityUtilTest {

/*
    @BeforeAll
    public void setUp() {
        // mock静态方法
        MockedStatic<SecurityUtil> mockSpringUtil = Mockito.mockStatic(SecurityUtil.class);
        mockSpringUtil.when(() -> SecurityUtil.getMD5("test")).thenReturn("098f6bcd4621d373cade4e832627b4f6");

    }
*/

    @Test
    void getMD5() {
        String md5 = SecurityUtil.getMD5("test");
        assertEquals(md5, "098f6bcd4621d373cade4e832627b4f6");
        assertNull(SecurityUtil.getMD5(null));

        MockedStatic<MessageDigest> messageDigest = Mockito.mockStatic(MessageDigest.class);
        messageDigest.when(() -> MessageDigest.getInstance("md5")).thenThrow(new NoSuchAlgorithmException("NoSuchAlgorith"));
        OperationException operationException = assertThrows(OperationException.class, () -> SecurityUtil.getMD5("test"));
        assertEquals(operationException.getMessage(), "NoSuchAlgorith");

    }

    @Test
    void SecurityUtil(){
        SecurityUtil securityUtil = mock(SecurityUtil.class);
        //verify(securityUtil).SecurityUtil();
    }
}
