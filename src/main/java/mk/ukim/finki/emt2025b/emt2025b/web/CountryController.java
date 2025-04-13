package mk.ukim.finki.emt2025b.emt2025b.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt2025b.emt2025b.model.Country;
import mk.ukim.finki.emt2025b.emt2025b.model.dto.CountryDto;
import mk.ukim.finki.emt2025b.emt2025b.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @Operation(summary = "Земање на сите Countries")
    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @Operation(summary = "Пребарување на Country по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Додавање на Country по ID")
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto country) {
        return countryService.save(country)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Едитирање на Country по ID")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id, @RequestBody CountryDto country) {
        return countryService.update(id, country)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Бришење на Country по ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (countryService.findById(id).isPresent()) {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
