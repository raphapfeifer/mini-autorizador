package autorizador.dao;

import autorizador.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartaoDao extends JpaRepository<Cartao,Long> {

    @Query("SELECT model.valorSaldo FROM Cartao model WHERE model.numeroCartao = :numeroCartao")
    Double retornaSaldo(@Param("numeroCartao") Long numeroCartao);
}
