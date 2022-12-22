package br.com.mentorama.Mod02Atividade;

public class AlunoNaoEncontradoException extends RuntimeException {

    public AlunoNaoEncontradoException(String nome) {
        super("Aluno nome " + nome + " não encontrado.");
    }
    public AlunoNaoEncontradoException(Integer id) {
        super("Aluno id " + id + " não encontrado.");
    }
}
