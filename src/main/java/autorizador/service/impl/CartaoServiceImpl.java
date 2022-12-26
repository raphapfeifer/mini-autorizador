package autorizador.service.impl;

import autorizador.dao.ICartaoDao;
import autorizador.enums.StatusEnum;
import autorizador.model.Cartao;
import autorizador.service.ICartaoService;
import autorizador.util.CartaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@Service
public class CartaoServiceImpl implements ICartaoService {

    @Autowired
    ICartaoDao dao;



    @Override
    @Async
    public CompletableFuture<Double> retornaSaldo(Long numeroCartao) {
        return CompletableFuture.completedFuture(dao.retornaSaldo(numeroCartao));
    }

    @Override
    @Async
    public CompletableFuture<Cartao> insert(Cartao cartao) {
        if (findById(cartao.getNumeroCartao()) == null) {
            return CompletableFuture.completedFuture(dao.saveAndFlush(cartao));
        }
        throw new UnsupportedOperationException("Esse cartão já está cadastrado");
    }

    @Override
    @Async
    public CompletableFuture<String> transacao(Cartao cartao) {
        try{
            Cartao cartaoVerificado = findById(cartao.getNumeroCartao());
            if(cartaoVerificado == null){
                return CompletableFuture.completedFuture(StatusEnum.CARTAO_INEXISTENTE.getDescricao());
            }
            String erros = verificaCampos(cartaoVerificado,cartao);
            if(erros.isEmpty()){
                cartao.setValorSaldo(CartaoUtils.calculaTransacao(cartaoVerificado.getValorSaldo(), cartao.getValorSaldo()));
                dao.saveAndFlush(cartao);
                return CompletableFuture.completedFuture(StatusEnum.TRANSACAO_EFETUADA.getDescricao());
            }
            return CompletableFuture.completedFuture(erros);
        }catch(Exception e){
            throw new UnsupportedOperationException( "" + e.getMessage());
        }
    }

    private String verificaCampos(Cartao cartaoVerificado,Cartao cartao){
        boolean ok = true;
        Map<StatusEnum,Boolean> erros = new HashMap<StatusEnum,Boolean>();
        String resposta = "";
        try{
            ok = CartaoUtils.validaSenha(cartaoVerificado.getSenha(), cartao.getSenha());
                erros.put(StatusEnum.SENHA_INVALIDA,ok);
            ok = CartaoUtils.validaSaldo(cartaoVerificado.getValorSaldo(), cartao.getValorSaldo());
                erros.put(StatusEnum.SALDO_INSUFICIENTE,ok);
            resposta = montaResposta(erros);

            return resposta;
        }catch(Exception e){
            throw new UnsupportedOperationException(resposta);
        }
    }

    private String montaResposta(Map<StatusEnum, Boolean> erros) {
        StringBuilder mensagem = new StringBuilder();
        erros.forEach((k,v) -> {
            if(v.booleanValue() == false){
                mensagem.append(k.getDescricao() + "\n");
            }
        });
        return mensagem.toString();
    }

    @Override
    @Async
    public Cartao findById(Long numeroCartao) {
        return dao.findOne(numeroCartao);
    }
}

