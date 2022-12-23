package autorizador.dto.mappers;

import autorizador.dto.CartaoDto;
import autorizador.model.Cartao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICartaoMapper extends IGenericMapper<Cartao, CartaoDto>{

    ICartaoMapper INSTANCE = Mappers.getMapper(ICartaoMapper.class);

}
