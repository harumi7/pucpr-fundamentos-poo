/**
 * Classe que representa a manipulação de arquivos.
 * @author harumi
 */

package modelo;

import java.io.*;
import java.util.ArrayList;

public class ManipulacaoArquivos {
    public static void salvarDadosEmArquivoTexto(ArrayList<Financiamento> financiamentos, String nomeArquivo) {
        try {
            // Cria um escritor de arquivo
            PrintWriter escrever = new PrintWriter(new FileWriter(nomeArquivo));
            for (Financiamento financiamento : financiamentos) {
                // Escreve os dados da clase generalizada
                escrever.printf("%.2f, %.2f, %.2f, " + financiamento.getPrazoFinanciamento() + ", ",
                        financiamento.getValorImovel(), financiamento.calcularTotalPagamento(),
                        financiamento.getTaxaJurosAnual());

                // Escreve os dados das classes especializadas
                if (financiamento instanceof Casa) {
                    escrever.println(((Casa) financiamento).getTamanhoTerreno() + ", "
                            + ((Casa) financiamento).getTamanhoAreaConstruida());

                } else if (financiamento instanceof Apartamento) {
                    escrever.println(((Apartamento) financiamento).getVagasGaragem() + ", "
                            + ((Apartamento) financiamento).getNumeroAndar());

                } else if (financiamento instanceof Terreno) {
                    escrever.println(((Terreno) financiamento).getTipoZona());
                }
            }
            // Fecha o "escrever" e salva os dados no arquivo
            escrever.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void lerDadosDeArquivoTexto(String nomeArquivo) {
        try {
            // Cria um leitor de arquivo
            BufferedReader ler = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            System.out.println("##### Leitura dos dados do arquivo de texto #####");
            // Realiza a leitura de linha por linha, enquanto existirem linhas
            while ((linha = ler.readLine()) != null) {
                System.out.println(linha);
            }
            System.out.println();
            // Fecha o "ler" e salva os dados no arquivo
            ler.close();
        } catch (IOException e) {
            try {
                // Se o arquivo a ser lido não existir, tenta criar um arquivo
                new PrintWriter(new FileWriter("financiamentos.txt"));
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    public static void salvarDadosEmArquivoSerializado(ArrayList<Financiamento> financiamentos, String nomeArquivo) {
        try {
            ObjectOutputStream escrever = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
            escrever.writeObject(financiamentos);
            escrever.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Financiamento> lerDadosDeArquivoSerializado(String nomeArquivo) {
        ArrayList<Financiamento> financiamentos = new ArrayList<>();
        try {
            ObjectInputStream ler = new ObjectInputStream(new FileInputStream(nomeArquivo));
            financiamentos = (ArrayList<Financiamento>) ler.readObject();
            System.out.println("##### Leitura dos dados serializados #####");
        } catch (IOException | ClassNotFoundException e) {
            try {
                // Se o arquivo a ser lido não existir, tenta criar um arquivo
                new ObjectOutputStream(new FileOutputStream(nomeArquivo));
            } catch (IOException ex) {
                e.printStackTrace(System.err);
            }
        }
        return financiamentos;
    }
}