package autorizador.service;

import autorizador.model.Cartao;

public interface ICartaoService {

    Cartao findById(Long numeroCartao);

    Cartao insert(Cartao cartao);

}
