package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/itens")
public class ItemController {

    private final List<Item> itens = new ArrayList<>();
    private long nextId = 1;

    // GET /api/itens  ── lista todos
    @GetMapping
    public List<Item> listarTodos() {
        return itens;           // nenhuma stream aqui
    }

    // GET /api/itens/{id}  ── busca um item específico
    @GetMapping("/{id}")
    public Item buscarPorId(@PathVariable Long id) {
        // loop tradicional em vez de stream()
        for (Item item : itens) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;            // ou lance exceção, se preferir
    }

    // POST /api/itens  ── cria um novo item
    @PostMapping
    public Item criarItem(@RequestBody Item novoItem) {
        novoItem.setId(nextId++);
        itens.add(novoItem);
        return novoItem;
    }

    // PUT /api/itens/{id}  ── atualiza item existente
    @PutMapping("/{id}")
    public Item atualizarItem(@PathVariable Long id,
                              @RequestBody Item atualizado) {

        Item existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(atualizado.getNome());
            existente.setQuantidade(atualizado.getQuantidade());
        }
        return existente;       // pode devolver 404 se for null
    }

    // DELETE /api/itens/{id}  ── remove item
    @DeleteMapping("/{id}")
    public void removerItem(@PathVariable Long id) {
        // iterator clássico para remover sem usar removeIf/lambda
        for (Iterator<Item> it = itens.iterator(); it.hasNext(); ) {
            if (it.next().getId().equals(id)) {
                it.remove();
                break;
            }
        }
    }
}
