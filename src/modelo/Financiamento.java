/**
 * Classe que representa financiamentos.
 * @author harumi7
 */

package modelo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;
import java.text.NumberFormat;

public abstract class Financiamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    // Método construtor
    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    // Getters
    public double getValorImovel() {
        return this.valorImovel;
    }

    public int getPrazoFinanciamento() {
        return this.prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    // Outros métodos

    /**
     * Calcula o pagamento mensal de um imóvel.
     * @return double - Valor do pagamento mensal.
     */
    public double calcularPagamentoMensal() {
        int mesesEmUmAno = 12;

        // Calcula o valor mensal do imóvel
        double valorImovelMensal = getValorImovel() / calcularMesesPrazoFinanciamento();
        // Calcula a taxa de juros mensal e transforma em decimal
        double taxaMensal = (this.getTaxaJurosAnual() / mesesEmUmAno) / 100;

        return valorImovelMensal * (1 + taxaMensal);
    }

    /**
     * Calcula o pagamento total de um imóvel.
     * @return double - Valor total do pagamento.
     */
    public double calcularTotalPagamento() {
        return this.calcularPagamentoMensal() * calcularMesesPrazoFinanciamento();
    }

    /**
     * Calcula a quantidade de meses do prazo de financiamento.
     * @return double - Prazo de financiamento em meses.
     */
    public double calcularMesesPrazoFinanciamento() {
        int mesesEmUmAno = 12;

        return getPrazoFinanciamento() * mesesEmUmAno;
    }

    /**
     * Mostra na tela os dados do financiamento "Valor do imóvel" e "Valor do financiamento".
     */
    public void mostrarDadosFinanciamento() {
        // Formata os valores para o padrão brasileiro
        Locale localBrasil = Locale.of("pt", "BR");
        NumberFormat valorBrasileiro = NumberFormat.getCurrencyInstance(localBrasil);

        // Mostra os dados do financiamento na tela
        System.out.println("Valor do imóvel: " + valorBrasileiro.format(getValorImovel()));
        System.out.println("Valor do financiamento: " + valorBrasileiro.format(calcularTotalPagamento()));
    }
}