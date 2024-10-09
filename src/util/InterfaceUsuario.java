/**
 * Classe que representa a interface do usuário
 * @author harumi
 */

package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario {
    static Scanner leitor = new Scanner(System.in);

    /**
     * Solicita ao usuário o valor do imóvel. Caso o valor digitado seja diferente de
     * número, ocorrerá uma exceção.
     * @return double - Valor do imóvel.
     */
    public static double solicitarValorImovel() {
        while (true) {
            try {
                System.out.print("Digite o valor do imóvel: R$ ");
                double valorImovel = leitor.nextDouble();

                double valorMinimoImovel = 1d;
                if (valorImovel < valorMinimoImovel) {
                    System.out.println("[Valor inválido, tente novamente.]");
                } else {
                    return valorImovel;
                }
            } catch (InputMismatchException e) {
                System.out.println("[Valor inválido: Digite apenas números.]");
                leitor.nextLine(); // <- Esvazia o scanner
            }
        }
    }

    /**
     * Solicita ao usuário o prazo de financiamento em anos. Caso o valor digitado seja diferente de número, ocorrerá
     * uma exceção. Se não, verifica se o prazo informado ultrapassa 35 anos.
     * @return int - Prazo do financiamento, em anos.
     */
    public static int solicitarPrazoFinanciamento() {
        while (true) {
            try {
                System.out.print("Digite o prazo do financiamento (em anos): ");
                int prazo = leitor.nextInt();

                int prazoMinimo = 1;
                int prazoMaximo = 35;
                if (prazo < prazoMinimo || prazo > prazoMaximo) {
                    System.out.println("[Valor inválido: O prazo não pode ultrapassar 35 anos.]");
                } else {
                    return prazo;
                }
            } catch (InputMismatchException e) {
                System.out.println("[Valor inválido: Digite apenas números.]");
                leitor.nextLine(); // <- Esvazia o scanner
            }
        }
    }

    /**
     * Solicita ao usuário a taxa de juros do financiamento. Caso o valor digitado seja diferente de número, ocorrerá
     * uma exceção. Verifica se o valor informado excede o mínimo ou o máximo estipulado.
     * @return double - Taxa de juros anual.
     */
    public static double solicitarTaxaJuros() {
        while (true) {
            try {
                System.out.print("Digite a taxa de juros anual (%): ");
                double taxaJuros = leitor.nextDouble();

                double taxaJurosMinima = 1d;
                double taxaJurosMaxima = 100_000_000d;
                if (taxaJuros < taxaJurosMinima || taxaJuros > taxaJurosMaxima) {
                    System.out.println("[Valor inválido, tente novamente.]");
                } else {
                    return taxaJuros;
                }
            } catch (InputMismatchException e) {
                System.out.println("[Valor inválido: Digite apenas números.]");
                leitor.nextLine(); // <- Esvazia o scanner
            }
        }
    }

    /**
     * Solicita ao usuário o tipo de zona. Verifica se a informação digitada é "Residencial" ou "Comercial", se não for
     * nenhum dos dois, informa que a zona é inválida.
     * @return String - Tipo de zona.
     */
    public static String solicitarTipoZona() {
        while (true) {
            System.out.print("Digite o tipo de zona (Residencial/Comercial): ");
            String tipoZona = leitor.next();

            if (!tipoZona.equals("Residencial") && !tipoZona.equals("Comercial")) {
                System.out.println("[Tipo de zona inválido, tente novamente.]");
            } else {
                System.out.println();
                return tipoZona;
            }
        }
    }
}