package br.com.mentorama.Mod02Atividade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class CadastroAlunosController {
    private final AlunoService alunoService = new AlunoService();

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll(@RequestParam(required = false) String nome, @RequestParam(required = false) Integer idade) {
        try {
            return new ResponseEntity<>(alunoService.findAll(nome, idade), HttpStatus.OK);
        } catch (AlunoNaoEncontradoException e) {
            throw new AlunoNaoEncontradoException(nome);
        }
    }

    @ExceptionHandler({AlunoNaoEncontradoException.class})
    public ResponseEntity<String> handle(final AlunoNaoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(alunoService.findById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> add(@RequestBody final Aluno novoAluno) {
        return alunoService.add(novoAluno);
    }
    @PutMapping
    public ResponseEntity<String> update(@RequestBody final Aluno atualizaAluno) {
        return alunoService.update(atualizaAluno);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return alunoService.delete(id);
    }

}