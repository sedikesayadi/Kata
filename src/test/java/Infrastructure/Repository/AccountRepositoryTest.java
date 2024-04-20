package Infrastructure.Repository;

import Business.Model.AccountEntity;
import Business.Port.AccountPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountRepositoryTest {

    @Mock
    private AccountPort accountPort;

    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountRepository = new AccountRepository(accountPort) {
            @Override
            public List<AccountEntity> findAll(Sort sort) {
                return null;
            }

            @Override
            public Page<AccountEntity> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends AccountEntity> S save(S entity) {
                return null;
            }

            @Override
            public <S extends AccountEntity> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<AccountEntity> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public List<AccountEntity> findAll() {
                return null;
            }

            @Override
            public List<AccountEntity> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(AccountEntity entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends AccountEntity> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends AccountEntity> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends AccountEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<AccountEntity> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Long> longs) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public AccountEntity getOne(Long aLong) {
                return null;
            }

            @Override
            public AccountEntity getById(Long aLong) {
                return null;
            }

            @Override
            public AccountEntity getReferenceById(Long aLong) {
                return null;
            }

            @Override
            public <S extends AccountEntity> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends AccountEntity> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends AccountEntity> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public <S extends AccountEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends AccountEntity> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends AccountEntity> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends AccountEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }

    @Test
    void testFindByAccountNumber() {
        String accountNumber = "123456789";
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);
        when(accountPort.findByAccountNumber(accountNumber)).thenReturn(accountEntity);

        AccountEntity result = accountRepository.findByAccountNumber(accountNumber);

        assertEquals(accountEntity, result);
        verify(accountPort, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void testDeposit() {
        String accountNumber = "123456789";
        double amount = 100.0;
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);
        accountEntity.setBalance(500.0);
        when(accountPort.findByAccountNumber(accountNumber)).thenReturn(accountEntity);

        accountRepository.deposit(accountNumber, amount);

        verify(accountPort, times(1)).findByAccountNumber(accountNumber);
        verify(accountPort, times(1)).save(accountEntity);
        assertEquals(600.0, accountEntity.getBalance());
    }

    @Test
    void testWithdraw_SufficientFunds() {
        String accountNumber = "123456789";
        double amount = 100.0;
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);
        accountEntity.setBalance(500.0);
        when(accountPort.findByAccountNumber(accountNumber)).thenReturn(accountEntity);

        accountRepository.withdraw(accountNumber, amount);

        verify(accountPort, times(1)).findByAccountNumber(accountNumber);
        verify(accountPort, times(1)).save(accountEntity);
        assertEquals(400.0, accountEntity.getBalance());
    }

    @Test
    void testWithdraw_InsufficientFunds() {
        String accountNumber = "123456789";
        double amount = 600.0;
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(accountNumber);
        accountEntity.setBalance(500.0);
        when(accountPort.findByAccountNumber(accountNumber)).thenReturn(accountEntity);

        assertThrows(RuntimeException.class, () -> accountRepository.withdraw(accountNumber, amount));

        verify(accountPort, times(1)).findByAccountNumber(accountNumber);
        verify(accountPort, times(0)).save(accountEntity);
    }
}