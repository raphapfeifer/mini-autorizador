package autorizador.service;

import autorizador.model.Cartao;

import java.util.concurrent.CompletableFuture;

public interface ICartaoService {

    CompletableFuture<Double> retornaSaldo(Long numeroCartao);

    CompletableFuture<Cartao> insert(Cartao cartao);

    CompletableFuture<String> transacao(Cartao cartao);

    Cartao findById(Long numeroCartao);

}
