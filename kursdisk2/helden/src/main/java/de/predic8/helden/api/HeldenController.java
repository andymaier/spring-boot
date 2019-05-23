package de.predic8.helden.api;

import de.predic8.helden.domain.Held;
import de.predic8.helden.repo.HeldRepository;
import de.predic8.helden.repo.NotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/helden")
public class HeldenController {

    Logger log = LoggerFactory.getLogger(HeldenController.class);

    private HeldRepository repo;

    public HeldenController(HeldRepository heldRepository) {
        this.repo = heldRepository;
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "unauthorized"),
            @ApiResponse(code = 403, message = "forbidden")}
    )
    @ApiOperation("Liste aller Helden")
    public List<Held> index() {
        log.info("Helden wurden abgerufen");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Held getById(@PathVariable UUID id) {
        return repo.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Held> create(@RequestBody Held held, UriComponentsBuilder builder) {
        Held newHeld = repo.save(held);
        URI uri = builder.path("/helden/{id}").buildAndExpand(newHeld.getId()).toUri();
        return ResponseEntity.created(uri).body(newHeld);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        Held held = repo.findById(id).orElseThrow(NotFoundException::new);
        repo.delete(held);
    }

    @PutMapping("/{id}")
    public Held modify(@RequestBody Held held, @PathVariable UUID id){
        repo.findById(id).orElseThrow(NotFoundException::new);
        held.setId(id);
        return repo.save(held);
    }

    @GetMapping("/actions/search")
    public List<Held> search(@RequestParam String name) {
        return repo.findByNameIgnoreCaseContaining(name);
    }
}
