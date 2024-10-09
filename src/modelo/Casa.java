/**
 * Classe que representa casas.
 * @author harumi7
 */

package modelo;

import java.io.Serial;

public class Casa extends Financiamento {
    @Serial
    private static final long serialVersionUID = 1L;

    private double tamanhoAreaConstruida;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoAreaConstruida,
                double tamanhoTerreno) {
        // Chama o construtor da superclasse Financiamento
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
        }

    public double getTamanhoAreaConstruida() {
        return this.tamanhoAreaConstruida;
    }

    public double getTamanhoTerreno() {
        return this.tamanhoTerreno;
    }

    /**
     * Calcula o pagamento mensal com a inclusão de uma taxa adicional de R$ 80.
     * @return double - Retorna o valor do pagamento mensal.
     */
    @Override
    public double calcularPagamentoMensal() {
        return super.calcularPagamentoMensal() + 80;
    }

    /**
     * Mostra na tela, além dos dados herdados da superclasse, os dados específicos da classe Casa, "Tamanho da área
     * construída" e "Tamanho do terreno".
     */
    @Override
    public void mostrarDadosFinanciamento() {
        super.mostrarDadosFinanciamento();
        System.out.println("Tamanho da área construída: " + this.getTamanhoAreaConstruida());
        System.out.println("Tamanho do terreno: " + this.getTamanhoTerreno());
    }
}