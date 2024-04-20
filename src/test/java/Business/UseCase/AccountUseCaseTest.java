package Business.UseCase;

import Business.Model.AccountEntity;
import Business.Port.AccountPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountUseCaseTest {

    @Mock
    private AccountPort accountPort;

    @InjectMocks
    private AccountUseCase accountUseCase;

    @Test
    void testGetAccount() {
        // Création d'un objet AccountEntity pour le test
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber("123456789");
        accountEntity.setBalance(1000.0);

        // Configuration du mock pour renvoyer l'objet AccountEntity lorsque la méthode findByAccountNumber() est appelée
        when(accountPort.findByAccountNumber("123456789")).thenReturn(accountEntity);

        // Appel de la méthode getAccount()
        AccountEntity result = accountUseCase.getAccount("123456789");

        // Vérification que la méthode findByAccountNumber() a été appelée une fois avec le bon paramètre
        verify(accountPort, times(1)).findByAccountNumber("123456789");

        // Vérification que l'objet AccountEntity retourné est bien celui attendu
        assertEquals(accountEntity, result);
    }

    @Test
    void testGetAccount_AccountNotFound() {
        // Configuration du mock pour renvoyer null lorsque la méthode findByAccountNumber() est appelée
        when(accountPort.findByAccountNumber("123456789")).thenReturn(null);

        // Appel de la méthode getAccount()
        AccountEntity result = accountUseCase.getAccount("123456789");

        // Vérification que la méthode findByAccountNumber() a été appelée une fois avec le bon paramètre
        verify(accountPort, times(1)).findByAccountNumber("123456789");

        // Vérification que l'objet AccountEntity retourné est null
        assertNull(result);
    }

    @Test
    void testDeposit() {
        // Configuration du mock pour ne rien faire lorsque la méthode deposit() est appelée
        doNothing().when(accountPort).deposit("123456789", 100.0);

        // Appel de la méthode deposit()
        accountUseCase.deposit("123456789", 100.0);

        // Vérification que la méthode deposit() a été appelée une fois avec les bons paramètres
        verify(accountPort, times(1)).deposit("123456789", 100.0);
    }

    @Test
    void testWithdraw() {
        // Configuration du mock pour ne rien faire lorsque la méthode withdraw() est appelée
        doNothing().when(accountPort).withdraw("123456789", 100.0);

        // Appel de la méthode withdraw()
        accountUseCase.withdraw("123456789", 100.0);

        // Vérification que la méthode withdraw() a été appelée une fois avec les bons paramètres
        verify(accountPort, times(1)).withdraw("123456789", 100.0);
    }

}
