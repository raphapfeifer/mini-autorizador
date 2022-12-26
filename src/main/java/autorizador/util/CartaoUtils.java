package autorizador.util;

import autorizador.model.Cartao;

public class CartaoUtils {


    public static boolean validaSenha(String senhaAtual, String senhaInformada){
        return senhaAtual.equals(senhaInformada);
    }

    public static boolean validaSaldo(Double valorSaldoAtual, Double valorSaldoInformado) {
        return valorSaldoInformado <= valorSaldoAtual;
    }

    public static Double calculaTransacao(Double valorSaldoAtual, Double valorSaldoInformado) {
        return valorSaldoAtual - valorSaldoInformado;
    }
}
