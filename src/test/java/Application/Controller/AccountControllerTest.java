package Application.Controller;

import Application.Mapper.AccountAppMapper;
import Application.Model.AccountDto;
import Business.Model.AccountEntity;
import Business.UseCase.AccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
// import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountControllerTest {

    @Mock
    private AccountUseCase accountUseCase;

    @Mock
    private AccountAppMapper accountMapper;

    private AccountController accountController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        accountController = new AccountController(accountUseCase, accountMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void deposit() throws Exception {
        String accountNumber = "123456789";
        double amount = 100.0;

        mockMvc.perform(post("/accounts/{accountNumber}/deposit", accountNumber)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());

        verify(accountUseCase).deposit(accountNumber, amount);
    }

    @Test
    void withdraw() throws Exception {
        String accountNumber = "123456789";
        double amount = 100.0;

        mockMvc.perform(post("/accounts/{accountNumber}/withdraw", accountNumber)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk());

        verify(accountUseCase).withdraw(accountNumber, amount);
    }

    @Test
    void getAccount() throws Exception {
        String accountNumber = "123456789";
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);
        accountEntity.setBalance(1000.0);
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(accountNumber);
        accountDto.setBalance(1000.0);

        when(accountUseCase.getAccount(accountNumber)).thenReturn(accountEntity);
        when(accountMapper.toDto(accountEntity)).thenReturn(accountDto);

        mockMvc.perform(get("/accounts/{accountNumber}", accountNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(accountUseCase).getAccount(accountNumber);
        verify(accountMapper).toDto(accountEntity);
    }
}
