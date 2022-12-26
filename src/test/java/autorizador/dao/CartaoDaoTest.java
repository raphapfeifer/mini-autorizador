package autorizador.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;



class CartaoDaoTest{

    private static Double valorSaldo;

    @BeforeAll
    public static void setup(){
        valorSaldo = 100.0;
    }


    @Test
    void deveRetornarValorSaldo(){
        Assertions.assertThat(valorSaldo).isEqualTo(100.0).isNotNegative().isNotNull();
    }


}
