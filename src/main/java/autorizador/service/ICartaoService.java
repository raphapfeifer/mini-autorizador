package autorizador.service;

import autorizador.model.Cartao;

public interface ICartaoService {

    Double retornaSaldo(Long numeroCartao);

    Cartao insert(Cartao cartao);

    String transacao(Cartao cartao);

}
