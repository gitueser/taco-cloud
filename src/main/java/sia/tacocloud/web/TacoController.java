package sia.tacocloud.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.data.TacoRepository;
import sia.tacocloud.entity.Taco;

import java.util.Optional;

@RestController
@RequestMapping("/api/tacos")
//@RequestMapping(params = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

    private final TacoRepository tacoRepo;

    public TacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    //    @GetMapping(params = "recent")
    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        System.out.println("DASFSDG");
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        Page<Taco> tacos = tacoRepo.findAll(page);
        return tacos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        return optTaco
                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
