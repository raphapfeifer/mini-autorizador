package autorizador.dao;

import autorizador.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartaoDao extends JpaRepository<Cartao,Long> {
}
