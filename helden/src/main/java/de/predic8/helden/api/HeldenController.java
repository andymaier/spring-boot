package de.predic8.helden.api;

import de.predic8.helden.domain.Held;
import de.predic8.helden.repo.HeldRepository;
import de.predic8.helden.repo.NotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/helden")
public class HeldenController {

  Logger log = LoggerFactory.getLogger(HeldenController.class);

  @Autowired
  HeldRepository heldRepository;

  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Erfolgreich"),
      @ApiResponse(code = 401, message = "Du bist nicht authentifiziert."),
      @ApiResponse(code = 403, message = "Du darfst nicht.")
  })
  @ApiOperation("Hiermit werden alle Helden ausgegeben.")
  @GetMapping
  public List<Held> index() {
    log.info("Helden werden abgerufen.");
    return heldRepository.findAll();
  }

  @GetMapping("/{id}")
  public Held getById(@PathVariable UUID id) {
    log.info("Held mit der ID " + id.toString() + " abgerufen.");
    return heldRepository.findById(id).orElseThrow(NotFoundException::new);
  }


  @PostMapping
  public Held create(@RequestBody Held held, UriComponentsBuilder builder) {
    return heldRepository.save(held);
  }


  //@PostMapping
  //public ResponseEntity<Held> create(@RequestBody Held held, UriComponentsBuilder builder) {
  //  Held newHeld = heldRepository.save(held);
  //  URI uri = builder.path("/helden/{id}").buildAndExpand(newHeld.getId()).toUri();
  //  return ResponseEntity.created(uri).body(newHeld);
  // }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable UUID id) {
    Held byId = getById(id);
    heldRepository.delete(byId);
  }
}
