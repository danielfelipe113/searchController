package com.unir.exampledfc.Search.controller;

import com.unir.exampledfc.Search.entity.Critic;
import com.unir.exampledfc.Search.service.CriticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/critic")
public class CriticController {
    private final CriticService criticService;

    public CriticController(CriticService criticService) {
        this.criticService = criticService;
    }

    @GetMapping
    public ResponseEntity<List<Critic>> getAllCritics() {
        return ResponseEntity.ok(criticService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Critic> getCriticById(@PathVariable Long id) {
        return criticService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Critic> createCritic(@RequestBody Critic critic) {
        Critic savedCritic = criticService.save(critic);
        return ResponseEntity.ok(savedCritic);
    }

    @PutMapping("/{id}")
    public Critic updateCritic(@PathVariable Long id, @RequestBody Critic critic) {
        return criticService.update(id, critic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCritic(@PathVariable Long id) {
        criticService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

