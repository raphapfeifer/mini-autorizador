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

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin
public class CartaoController {

    private static final String BASE_URL = "cartoes";

    @Autowired
    ICartaoService service;

    @GetMapping(value ="cartoes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, params = {"numeroCartao"})
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody CompletableFuture<ResponseEntity> retornaSaldo(@RequestParam(name = "numeroCartao") Long numeroCartao){
        return service.retornaSaldo(numeroCartao).<ResponseEntity>thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "cartoes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody CompletableFuture<ResponseEntity> insert(@RequestBody CartaoDto cartaoDto){
        return service.insert(ICartaoMapper.INSTANCE.dtoToEntity(cartaoDto)).thenApply(ResponseEntity::ok);
    }

    @PostMapping(value = "transacoes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody CompletableFuture<ResponseEntity> transacao(@RequestBody CartaoDto cartaoDto){
        return service.transacao(ICartaoMapper.INSTANCE.dtoToEntity(cartaoDto)).thenApply(ResponseEntity::ok);
    }

}
