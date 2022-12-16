package br.com.mentorama.Mod01Atividade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/alunos")
public class CadastroAlunosController {

    private final List<Aluno> listaAlunos;

    // Construtor para definir e inicializar a lista de alunos:
    public CadastroAlunosController() {
        this.listaAlunos = new ArrayList<>();
    }

    @GetMapping
    public List<Aluno> findAll(@RequestParam(required = false) String nome, @RequestParam(required = false) Integer idade) {
        Stream<Aluno> listaAlunosStream = listaAlunos.stream();
        if (nome != null)
            listaAlunosStream = listaAlunosStream.filter(aln -> aln.getNome().contains(nome));
        if (idade != null)
            listaAlunosStream = listaAlunosStream.filter(aln -> aln.getIdade().equals(idade));
        return listaAlunosStream.collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Aluno findById(@PathVariable("id") Integer id) {
        return listaAlunos.stream()
                .filter(aln -> aln.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody final Aluno novoAluno) {
        if (novoAluno.getId() == null) {
            novoAluno.setId(listaAlunos.size() + 1);
        }
        listaAlunos.add(novoAluno);
        return new ResponseEntity<>("Aluno cadastrado. ID: " + String.valueOf(novoAluno.getId()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody final Aluno atualizaAluno) {
        listaAlunos.stream()
                .filter(aln -> aln.getId().equals(atualizaAluno.getId()))
                .forEach(aln -> {
                    aln.setNome(atualizaAluno.getNome());
                    aln.setIdade(atualizaAluno.getIdade());
                });
        return new ResponseEntity<>("Aluno ID " + atualizaAluno.getId() + " atualizado.", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        listaAlunos.removeIf(aln -> aln.getId().equals(id));
        return new ResponseEntity<>("Aluno ID " + id + " apagado da lista.", HttpStatus.ACCEPTED);
    }

}