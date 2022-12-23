package autorizador.dto.mappers;

public interface IGenericMapper<E,D> {

    D entityToDto(E entity);

    E dtoToEntity(D dto);
}
