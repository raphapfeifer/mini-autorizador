package autorizador.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CartaoUtilsTest {

    private static String senhaCorreta;
    private static String senhaInformadaCerta;
    private static String senhaInformadaErrada;

    private static Double valorSaldo;

    private static Double valorSaldoInformado;



    @BeforeAll
    public static void setup(){
        senhaCorreta = "1234";
        senhaInformadaCerta = "1234";
        senhaInformadaErrada = "12345";
        valorSaldo = 50.0;
        valorSaldoInformado = 50.0;
    }

    @Test
    void deveRetornarAValidacaoAprovada(){
        boolean resultado = CartaoUtils.validaSenha(senhaCorreta,senhaInformadaCerta);
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    void deveRetornarAValidacaoReprovada(){
        boolean resultado = CartaoUtils.validaSenha(senhaCorreta,senhaInformadaErrada);
        Assertions.assertThat(resultado).isFalse();
    }


    @Test
    void deveRetornarOValorAutorizadoParaATransacao(){
        boolean resultado = CartaoUtils.validaSaldo(valorSaldo,valorSaldoInformado);
        Assertions.assertThat(resultado).isTrue();
    }

    @Test
    void deveRetornarOValorReprovadoParaATransacao(){
        boolean resultado = CartaoUtils.validaSaldo(valorSaldo,(valorSaldoInformado + 1));
        Assertions.assertThat(resultado).isFalse();
    }

    @Test
    void deveRealizarATransacaoCorreta(){
        Double resultado = CartaoUtils.calculaTransacao(valorSaldo,valorSaldoInformado);
        Assertions.assertThat(resultado).isEqualTo(0.0);
    }
}
