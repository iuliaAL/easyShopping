package com.easyShopping.easyShopping;

import com.easyShopping.easyShopping.Mapper.Mapper;
import com.easyShopping.easyShopping.controller.UserController;
import com.easyShopping.easyShopping.dto.UserDto;
import com.easyShopping.easyShopping.model.Role;
import com.easyShopping.easyShopping.model.User;
import com.easyShopping.easyShopping.repository.UserRepository;
import com.easyShopping.easyShopping.service.Exception.UserServiceException;
import com.easyShopping.easyShopping.service.Exception.UserServiceExceptionCode;
import com.easyShopping.easyShopping.service.UserDetailServiceImpl;
import com.easyShopping.easyShopping.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
//@WithMockUser(username = "iulia")
public class EasyShoppingApplicationTests {
   /* @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDetailServiceImpl userDetailService;
    @MockBean
    private UserService userService;
    private static Mapper mapper= new Mapper();
    private static final UserDto PRE_USER_DTO = new UserDto(Long.valueOf(1),"username","pass","email@yahoo.com",Role.ROLE_USER)
    private static final Long PRE_USER_ID=Long.valueOf(1);

    @Test
    public void testLogIn() throws Exception
    {
        when(userService.login("email@yahoo.com","pass"));
        mockMvc.perform(post("login/"))
    }


	/*

public class IssueControllerTest {
    private static final String ISSUE_ENDPOINT_BEGINNING = "/issues";

    private static final IssueDTO PREINSERTED_ISSUE_DTO = new IssueDTO(1L, "test", "test", PREINSERTED_USER_1_DTO, LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)), false);
    private static final Integer PREINSERTED_ISSUE_ID = 1;

    @Test
    public void testGetIssueById_shouldBeSuccessful() throws Exception {
        when(issueServiceFacade.getIssueById(anyLong())).thenReturn(PREINSERTED_ISSUE_DTO);
        mockMvc.perform(get(ISSUE_ENDPOINT_BEGINNING + "/" + PREINSERTED_ISSUE_DTO.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(PREINSERTED_ISSUE_ID))).andExpect(status().isOk());
        verify(issueServiceFacade, times(1)).getIssueById(anyLong());
    }

    @Test
    public void testGetAllIssues_shouldBeSuccessful() throws Exception {
        when(issueServiceFacade.getAllIssues()).thenReturn(Arrays.asList(PREINSERTED_ISSUE_DTO));
        mockMvc.perform(get(ISSUE_ENDPOINT_BEGINNING).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(PREINSERTED_ISSUE_ID))).andExpect(status().isOk());
        verify(issueServiceFacade, times(1)).getAllIssues();
    }

    @Test
    public void testGetAllUnresolvedIssues_shouldBeSuccessful() throws Exception {
        when(issueServiceFacade.getAllUnresolvedIssues()).thenReturn(Arrays.asList(PREINSERTED_ISSUE_DTO));
        mockMvc.perform(get(ISSUE_ENDPOINT_BEGINNING + "/unresolved").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(PREINSERTED_ISSUE_ID))).andExpect(status().isOk());
        verify(issueServiceFacade, times(1)).getAllUnresolvedIssues();
    }

    @Test
    public void testAddNewIssue_shouldBeSuccessful() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mockMvc.perform(post(ISSUE_ENDPOINT_BEGINNING).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PREINSERTED_ISSUE_DTO)))
                .andExpect(status().isCreated());
        verify(issueServiceFacade, times(1)).addNewIssue(any(), anyString());
    }

    @Test
    public void testUpdateIssue_shouldBeSuccessful() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mockMvc.perform(put(ISSUE_ENDPOINT_BEGINNING).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(PREINSERTED_ISSUE_DTO)))
                .andExpect(status().isOk());
        verify(issueServiceFacade, times(1)).updateIssue(any(), anyString());
    }
	 */
}
