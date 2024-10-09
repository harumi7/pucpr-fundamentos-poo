/**
 * Classe que representa terrenos.
 * @author harumi7
 */

package modelo;

import java.io.Serial;

public class Terreno extends Financiamento {
    @Serial
    private static final long serialVersionUID = 1L;

    private String tipoZona; // Residencial ou comercial

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
        // Chama o construtor da superclasse Financiamento
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    public String getTipoZona() {
        return this.tipoZona;
    }

    /**
     * Calcula o pagamento total do imóvel com um acréscimo de 2%.
     * @return - Total do pagamento com o novo acréscimo.
     */
    @Override
    public double calcularTotalPagamento() {
        int doisPorCento = 2;

        // Transforma 2% em decimal
        double acrescimo = doisPorCento / 100.0;

        return super.calcularTotalPagamento() * (1 + acrescimo);
    }

    /**
     *  Mostra na tela, além dos dados herdados da superclasse, o dado específico da classe Terreno, "Tipo de zona".
     */
    @Override
    public void mostrarDadosFinanciamento() {
        super.mostrarDadosFinanciamento();
        System.out.println("Tipo de zona: " + this.getTipoZona());
    }
}