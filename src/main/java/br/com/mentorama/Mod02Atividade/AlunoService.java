package br.com.mentorama.Mod02Atividade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlunoService {

    private List<Aluno> listaAlunos;

    public AlunoService() {
        this.listaAlunos = new ArrayList<>();
    }

    public List<Aluno> findAll(String nome, Integer idade) {
        Stream<Aluno> listaAlunosStream = listaAlunos.stream();
        if (nome != null)
            listaAlunosStream = listaAlunosStream.filter(aln -> aln.getNome().contains(nome));
        if (idade != null)
            listaAlunosStream = listaAlunosStream.filter(aln -> aln.getIdade().equals(idade));
        return listaAlunosStream.collect(Collectors.toList());
    }

    public Aluno findById(Integer id) {
        return listaAlunos.stream()
                .filter(aln -> aln.getId().equals(id))
                .findFirst()
                .orElse(null);
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
