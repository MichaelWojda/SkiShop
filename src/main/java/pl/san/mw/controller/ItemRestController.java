package pl.san.mw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.san.mw.model.Item;
import pl.san.mw.repositories.ItemRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/items/")
public class ItemRestController {

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Item>> getAll() {
        List<Item> list = itemRepository.getAll();
        return ResponseEntity.ok(list);


    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Item> getOne(@PathVariable Long id) {
        Item item = itemRepository.getById(id);
        item = Optional.ofNullable(item).orElse(new Item());
        return ResponseEntity.ok(item);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveItem(@RequestBody Item item) {
        System.out.println(item.getName());
        Item saved = itemRepository.createItem(item);
        if (item.getId() == null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(saved);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}


