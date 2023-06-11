package br.com.alura.screenmatch.excecao;

/**
 *
 * @author viniberaldo
 */
public class ErroDeConversaoDeAnoException extends RuntimeException {
    private String mensagem;

    public ErroDeConversaoDeAnoException(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
