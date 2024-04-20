package Application.Mapper;

import Application.Model.AccountDto;
import Business.Model.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountAppMapper {

    AccountAppMapper INSTANCE = Mappers.getMapper(AccountAppMapper.class);

    AccountDto toDto(AccountEntity account);

    AccountEntity toEntity(AccountDto accountDto);
}
