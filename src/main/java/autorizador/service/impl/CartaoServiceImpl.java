package autorizador.service.impl;

import autorizador.dao.ICartaoDao;
import autorizador.model.Cartao;
import autorizador.service.ICartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoServiceImpl implements ICartaoService {

    @Autowired
    ICartaoDao dao;

    @Override
    public Double retornaSaldo(Long numeroCartao) {
        return dao.retornaSaldo(numeroCartao);
    }

    @Override
    public Cartao insert(Cartao cartao) {
        if(dao.findOne(cartao.getNumeroCartao()) == null){
            return dao.saveAndFlush(cartao);
        }
        throw new UnsupportedOperationException("Esse cartão já está cadastrado");
    }

    @Override
    public String transacao(Cartao cartao) {




        return null;
    }
}
