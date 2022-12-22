package br.com.mentorama.Mod02Atividade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class CadastroAlunosController {
    private final AlunoService alunoService = new AlunoService();

    @GetMapping
    public List<Aluno> findAll(@RequestParam(required = false) String nome, @RequestParam(required = false) Integer idade) {
        return alunoService.findAll(nome, idade);
    }
    @GetMapping("/{id}")
    public Aluno findById(@PathVariable("id") Integer id) {
        return alunoService.findById(id);
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