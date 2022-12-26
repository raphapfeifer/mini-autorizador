package autorizador.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    CARTAO_INEXISTENTE("Cartão inexistente"),
    SENHA_INVALIDA("Senha inválida"),
    SALDO_INSUFICIENTE("Saldo insuficiente"),
    TRANSACAO_EFETUADA("Trasanção realizada com sucesso!");

    private final String descricao;

}
