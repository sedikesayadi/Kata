package Business.Port;

import Business.Model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountPort extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByAccountNumber(String accountNumber);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
}
