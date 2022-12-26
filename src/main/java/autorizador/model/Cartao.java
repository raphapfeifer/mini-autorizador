package autorizador.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CARTAO")
@Data
@EqualsAndHashCode
public class Cartao {

    @Id
    @Column(name = "NUMERO_CARTAO")
    private Long numeroCartao;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "VALOR_SALDO")
    private Double valorSaldo;

}
