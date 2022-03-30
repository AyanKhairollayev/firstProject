package com.web.simplewebapplication.controller;

import com.web.simplewebapplication.dto.request.ItemCreateDtoRequest;
import com.web.simplewebapplication.models.Item;
import com.web.simplewebapplication.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items/")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ItemCreateDtoRequest itemCreateDtoRequest) {
        return ResponseEntity.ok(itemService.createItem(itemCreateDtoRequest));
    }

    @GetMapping()
    public ResponseEntity<List<Item>> showAll() {
        return ResponseEntity.ok(itemService.showAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Item>> showById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.showById(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateItem(@PathVariable("id") Long id, @RequestBody ItemCreateDtoRequest itemCreateDtoRequest) {
        return ResponseEntity.ok(itemService.updateItem(id, itemCreateDtoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itemService.deleteItem(id));
    }
}
