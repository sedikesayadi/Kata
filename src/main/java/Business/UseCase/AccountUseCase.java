package Business.UseCase;

import Business.Model.AccountEntity;
import Business.Port.AccountPort;
import org.springframework.stereotype.Service;

@Service
public class AccountUseCase {

    private final AccountPort accountPort;

    public AccountUseCase(AccountPort accountPort) {
        this.accountPort = accountPort;
    }

    public AccountEntity getAccount(String accountNumber) {
        return accountPort.findByAccountNumber(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        accountPort.deposit(accountNumber, amount);
    }

    public void withdraw(String accountNumber, double amount) {
        accountPort.withdraw(accountNumber, amount);
    }
}