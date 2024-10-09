/**
 * Classe que representa apartamentos.
 * @author harumi7
 */

package modelo;

import java.io.Serial;

public class Apartamento extends Financiamento {
    @Serial
    private static final long serialVersionUID = 1L;

    private int vagasGaragem;
    private int numeroAndar;

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem,
                       int numeroAndar) {
        // Chama o construtor da superclasse Financiamento
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    public int getVagasGaragem() {
        return this.vagasGaragem;
    }

    public int getNumeroAndar() {
        return this.numeroAndar;
    }

    /**
     * Calcula o pagamento mensal usando o sistema de amortização PRICE.
     * @return double - Valor do pagamento mensal.
     */
    @Override
    public double calcularPagamentoMensal() {
        int mesesEmUmAno = 12;
        double taxaMensal = (getTaxaJurosAnual() / mesesEmUmAno) / 100;
        double meses = calcularMesesPrazoFinanciamento();

        return (getValorImovel() * taxaMensal * Math.pow(1 + taxaMensal, meses)) / (Math.pow(1 + taxaMensal, meses) - 1);
    }

    /**
     * Mostra na tela, além dos dados herdados da superclasse, os dados específicos da classe Apartamento, "Vagas na
     * garagem" e "Número do andar".
     */
    @Override
    public void mostrarDadosFinanciamento() {
        super.mostrarDadosFinanciamento();
        System.out.println("Vagas na garagem: " + this.getVagasGaragem());
        System.out.println("Número do andar: " + this.getNumeroAndar());
    }
}