package autorizador.controller;

import autorizador.dto.CartaoDto;
import autorizador.dto.mappers.ICartaoMapper;
import autorizador.model.Cartao;
import autorizador.service.ICartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CartaoController {

    private static final String BASE_URL = "cartoes";

    @Autowired
    ICartaoService service;

    @GetMapping(value ="cartoes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, params = {"numeroCartao"})
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Double> retornaSaldo(@RequestParam(name = "numeroCartao") Long numeroCartao){
        return ResponseEntity.ok(service.retornaSaldo(numeroCartao));
    }

    @PostMapping(value = "cartoes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Cartao> insert(@RequestBody CartaoDto cartaoDto){
        return ResponseEntity.ok(service.insert(ICartaoMapper.INSTANCE.dtoToEntity(cartaoDto)));
    }

    @PostMapping(value = "transacoes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> transacao(@RequestBody CartaoDto cartaoDto){
        return ResponseEntity.ok(service.transacao(ICartaoMapper.INSTANCE.dtoToEntity(cartaoDto)));
    }


}
