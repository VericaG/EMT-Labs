package mk.ukim.finki.emt2025b.emt2025b.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt2025b.emt2025b.model.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.dto.HostDto;
import mk.ukim.finki.emt2025b.emt2025b.service.CountryService;
import mk.ukim.finki.emt2025b.emt2025b.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;
    private final CountryService countryService;

    public HostController(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Operation(summary = "Земање на сите Hosts")
    @GetMapping
    public List<Host> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "Пребарување на Host по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id) {
        return hostService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Додавање на Host по ID")
    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody HostDto host) {
        return hostService.save(host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Едитирање на Host по ID")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostDto host) {
        return hostService.update(id, host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Бришење на Host по ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (hostService.findById(id).isPresent()) {
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
