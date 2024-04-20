package Application.Controller;

import Application.Mapper.AccountAppMapper;
import Application.Model.AccountDto;
import Business.UseCase.AccountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountUseCase accountUseCase;
    private final AccountAppMapper accountMapper;

    @Autowired
    public AccountController(AccountUseCase accountUseCase, AccountAppMapper accountMapper) {
        this.accountUseCase = accountUseCase;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/accounts/{accountNumber}/deposit")
    public void deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        accountUseCase.deposit(accountNumber, amount);
    }

    @PostMapping("/accounts/{accountNumber}/withdraw")
    public void withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        accountUseCase.withdraw(accountNumber, amount);
    }

    @GetMapping("/accounts/{accountNumber}")
    public AccountDto getAccount(@PathVariable String accountNumber) {
        return accountMapper.toDto(accountUseCase.getAccount(accountNumber));
    }
}
