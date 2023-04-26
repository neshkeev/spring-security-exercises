package com.luxoft.spingsecurity.acl;

import com.luxoft.spingsecurity.acl.dto.CompanyDto;
import com.luxoft.spingsecurity.acl.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringSecurityAclMain.class)
public class CompanyServiceTest {

    @Test
    public void getAllUnauthenticated(@Autowired CompanyService companyService) {
        assertThrows(AuthenticationCredentialsNotFoundException.class, companyService::getAll);
    }

    @Test
    @WithMockUser(username = "user1")
    public void getByIdUnauthenticated(@Autowired CompanyService companyService) {
        assertThrows(AccessDeniedException.class, () -> companyService.getById(1L));
    }

    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void getByIdAuthenticated(@Autowired CompanyService companyService) {
        assertDoesNotThrow(() -> companyService.getById(1001L));
    }

    @Test
    @WithAnonymousUser
    public void getByIdAnonymous(@Autowired CompanyService companyService) {
        assertThrows(AccessDeniedException.class, () -> companyService.getById(1L));
    }

    @Test
    @WithMockUser(username = "user1")
    public void getCreateCompanyNoPermissions(@Autowired CompanyService companyService) {
        assertThrows(AccessDeniedException.class, () -> companyService.createCompany(new CompanyDto(1, "Hello"), 1));
    }
    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void getCreateCompanyAuthenticated(@Autowired CompanyService companyService) {
        assertDoesNotThrow(() -> companyService.createCompany(new CompanyDto(1, "Hello"), 3001));
    }
}