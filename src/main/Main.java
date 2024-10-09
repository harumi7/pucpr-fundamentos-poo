/**
 * Classe principal.
 * @author harumi7
 */

package main;

import modelo.*;
import util.InterfaceUsuario;

import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;

public class Main {
    // Método main() indica o começo da execução do programa
    public static void main(String[] args) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        System.out.println("==============================================");
        System.out.println("\tSistema de Simulação de Financiamentos");
        System.out.println("==============================================");

        // Leitura do arquivo de texto
        ManipulacaoArquivos.lerDadosDeArquivoTexto("financiamentos.txt");

        // Leitura do arquivo serializado
        ArrayList<Financiamento> financiamentosLidos = ManipulacaoArquivos.lerDadosDeArquivoSerializado(
                "financiamentos.bin"
        );
        for (Financiamento financiamento : financiamentosLidos) {
            System.out.printf("%.2f, %.2f, " + financiamento.getPrazoFinanciamento() + ", ",
                    financiamento.getValorImovel(), financiamento.getTaxaJurosAnual());

            if (financiamento instanceof Casa) {
                System.out.printf("%.2f, %.2f\n", ((Casa) financiamento).getTamanhoTerreno(),
                        ((Casa) financiamento).getTamanhoAreaConstruida());

            } else if (financiamento instanceof Apartamento) {
                System.out.println(((Apartamento) financiamento).getVagasGaragem() + ", "
                        + ((Apartamento) financiamento).getNumeroAndar());

            } else if (financiamento instanceof Terreno) {
                System.out.println(((Terreno) financiamento).getTipoZona() + "\n");
            }
        }

        System.out.println("==================");
        System.out.println("\tSimulações");
        System.out.println("==================");

        // Cria financiamentos e adiciona na ArrayList
        // Mostra na tela que os financiamentos foram simulados pelo programa
        System.out.println("##### Simulando financiamento: Casa #####");
        financiamentos.add(new Casa(550000, 20, 10, 100,
                150));
        System.out.println("[SIMULADO PELO PROGRAMA]\n");

        System.out.println("##### Simulando financiamento: Casa #####");
        financiamentos.add(new Casa(250000, 5, 5, 150,
                200));
        System.out.println("[SIMULADO PELO PROGRAMA]\n");

        System.out.println("##### Simulando financiamento: Apartamento #####");
        financiamentos.add(new Apartamento(300000, 20, 5, 50,
                17));
        System.out.println("[SIMULADO PELO PROGRAMA]\n");

        System.out.println("##### Simulando financiamento: Apartamento #####");
        financiamentos.add(new Apartamento(400000, 25, 5, 35,
                5));
        System.out.println("[SIMULADO PELO PROGRAMA]\n");

        // Solicita valores e armazena nas variáveis
        // Por causa do static, os métodos pertencem à classe util.InterfaceUsuario, e não à instância dela
        System.out.println("##### Simulando financiamento: Terreno #####");
        double valorImovelUsuario = InterfaceUsuario.solicitarValorImovel();
        int prazoFinanciamentoUsuario = InterfaceUsuario.solicitarPrazoFinanciamento();
        double taxaJurosUsuario = InterfaceUsuario.solicitarTaxaJuros();
        String tipoZona = InterfaceUsuario.solicitarTipoZona();

        // Cria um novo financiamento a partir dos valores solicitados e adiciona na ArrayList
        financiamentos.add(new Terreno(valorImovelUsuario, prazoFinanciamentoUsuario, taxaJurosUsuario, tipoZona));

        System.out.println("======================");
        System.out.println("\tFinanciamentos");
        System.out.println("======================");

        // Cria acumuladores
        double totalImoveis = 0d; // <- Acumulador para valores de imóveis
        double totalFinanciamentos = 0d; // <- Acumulador para valores de financiamentos

        // Mostra os financiamentos na tela (loop for-each)
        // Informa o tipo de financiamento simulado (Casa, Apartamento, Terreno)
        for (Financiamento financiamento : financiamentos) {
            System.out.print("##### Financiamento " + (financiamentos.indexOf(financiamento) + 1));
            if (financiamento instanceof Casa) {
                System.out.println(": Casa #####");
            } else if (financiamento instanceof Apartamento) {
                System.out.println(": Apartamento #####");
            } else if (financiamento instanceof Terreno) {
                System.out.println(": Terreno #####");
            }

            financiamento.mostrarDadosFinanciamento();

            // Calcula o valor total de todos o imóveis e o valor total de todos os financiamentos
            totalImoveis += financiamento.getValorImovel();
            totalFinanciamentos += financiamento.calcularTotalPagamento();

            System.out.println("................................");
        }

        // Formata os valores para o padrão brasileiro
        Locale localBrasil = Locale.of("pt", "BR");
        NumberFormat valorBrasileiro = NumberFormat.getCurrencyInstance(localBrasil);

        // Mostra na tela: valor total dos imóveis e valor total dos financiamentos
        System.out.println("Total de todos os imóveis: " + valorBrasileiro.format(totalImoveis));
        System.out.println("Total de todos os financiamentos: " + valorBrasileiro.format(totalFinanciamentos));

        // Salva os dados dos financiamentos em arquivo de texto
        ManipulacaoArquivos.salvarDadosEmArquivoTexto(financiamentos, "financiamentos.txt");

        // Salva os dados serializados dos financiamentos em arquivo
        ManipulacaoArquivos.salvarDadosEmArquivoSerializado(financiamentos, "financiamentos.bin");
    }
}