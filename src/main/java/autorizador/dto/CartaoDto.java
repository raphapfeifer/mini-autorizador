package autorizador.dto;

import lombok.Data;


@Data
public class CartaoDto {

    private Long numeroCartao;
    private String senha;
    private Double valorSaldo;

}
