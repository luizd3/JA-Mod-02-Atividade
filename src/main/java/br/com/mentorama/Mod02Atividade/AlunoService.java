package br.com.mentorama.Mod02Atividade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {

    private final List<Aluno> listaAlunos;

    public AlunoService() {
        this.listaAlunos = new ArrayList<>();
    }

    public List<Aluno> findAll(String nome, Integer idade) {
        if (nome != null) {
            List<Aluno> listaAlunos = this.listaAlunos.stream()
                    .filter(aln -> aln.getNome().contains(nome))
                    .toList();
            if (listaAlunos.isEmpty()) {
                throw new AlunoNaoEncontradoException(nome);
            } else {
                return listaAlunos;
            }
        }
        if (idade != null) {
            List<Aluno> listaAlunos = this.listaAlunos.stream()
                    .filter(aln -> aln.getIdade().equals(idade))
                    .toList();
            if (listaAlunos.isEmpty()) {
                throw new AlunoNaoEncontradoException();
            } else {
                return listaAlunos;
            }
        }
        return listaAlunos;
    }

    public Aluno findById(Integer id) {
        return listaAlunos.stream()
                .filter(aln -> aln.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AlunoNaoEncontradoException(id));
    }

    public ResponseEntity<String> add(Aluno novoAluno) {
        if (novoAluno.getId() == null) {
            novoAluno.setId(listaAlunos.size() + 1);
        }
        listaAlunos.add(novoAluno);
        return new ResponseEntity<>("Aluno cadastrado. ID: " + String.valueOf(novoAluno.getId()), HttpStatus.CREATED);
    }

    public ResponseEntity<String> update(Aluno atualizaAluno) {
        listaAlunos.stream()
                .filter(aln -> aln.getId().equals(atualizaAluno.getId()))
                .forEach(aln -> {
                    aln.setNome(atualizaAluno.getNome());
                    aln.setIdade(atualizaAluno.getIdade());
                });
        return new ResponseEntity<>("Aluno ID " + atualizaAluno.getId() + " atualizado.", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> delete(Integer id) {
        listaAlunos.removeIf(aln -> aln.getId().equals(id));
        return new ResponseEntity<>("Aluno ID " + id + " apagado da lista.", HttpStatus.ACCEPTED);
    }

}
