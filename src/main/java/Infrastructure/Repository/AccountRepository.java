package Infrastructure.Repository;

import Business.Port.AccountPort;
import Business.Model.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AccountRepository implements AccountPort {

    private final AccountPort accountJpaRepository;

    public AccountRepository(AccountPort accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }
    @Override
    public AccountEntity findByAccountNumber(String accountNumber) {
        AccountEntity accountEntity = accountJpaRepository.findByAccountNumber(accountNumber);
        return accountEntity;
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        AccountEntity accountEntity = accountJpaRepository.findByAccountNumber(accountNumber);
        accountEntity.setBalance(accountEntity.getBalance() + amount);
        accountJpaRepository.save(accountEntity);
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        AccountEntity accountEntity = accountJpaRepository.findByAccountNumber(accountNumber);
        if (accountEntity.getBalance() >= amount) {
            accountEntity.setBalance(accountEntity.getBalance() - amount);
            accountJpaRepository.save(accountEntity);
        } else {
            throw new RuntimeException("Insufficient funds");
        }
    }
}
