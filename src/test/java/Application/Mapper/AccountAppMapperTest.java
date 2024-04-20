package Application.Mapper;

import Application.Model.AccountDto;
import Business.Model.AccountEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountAppMapperTest {

    @Test
    void testToDto() {
        // Création d'un objet AccountEntity pour le test
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber("123456789");
        accountEntity.setBalance(1000.0);

        // Appel de la méthode toDto() pour convertir l'objet AccountEntity en AccountDto
        AccountDto accountDto = AccountAppMapper.INSTANCE.toDto(accountEntity);

        // Vérification que les données ont été correctement mappées
        assertEquals("123456789", accountDto.getAccountNumber());
        assertEquals(1000.0, accountDto.getBalance());
    }

    @Test
    void testToEntity() {
        // Création d'un objet AccountDto pour le test
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber("123456789");
        accountDto.setBalance(1000.0);

        // Appel de la méthode toEntity() pour convertir l'objet AccountDto en AccountEntity
        AccountEntity accountEntity = AccountAppMapper.INSTANCE.toEntity(accountDto);

        // Vérification que les données ont été correctement mappées
        assertEquals("123456789", accountEntity.getAccountNumber());
        assertEquals(1000.0, accountEntity.getBalance());
    }

}
