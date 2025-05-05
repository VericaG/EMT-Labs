package mk.ukim.finki.emt2025b.emt2025b.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayHostDto;
import mk.ukim.finki.emt2025b.emt2025b.dto.CreateHostDto;
import mk.ukim.finki.emt2025b.emt2025b.model.projections.HostProjection;
import mk.ukim.finki.emt2025b.emt2025b.model.views.HostsPerCountryView;
import mk.ukim.finki.emt2025b.emt2025b.service.application.CountryApplicationService;
import mk.ukim.finki.emt2025b.emt2025b.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing hosts")


public class HostController {

    private final HostApplicationService hostService;
    private final CountryApplicationService countryService;

    public HostController(HostApplicationService hostService, CountryApplicationService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Operation(summary = "Земање на сите Hosts")
    @GetMapping
    public List<DisplayHostDto> findAll() {
        return hostService.findAll();
    }

    @Operation(summary = "Пребарување на Host по ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Додавање на Host по ID")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto host) {
        return hostService.save(host)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Едитирање на Host по ID")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto host) {
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

    @Operation(summary = "Get hosts by country", description = "Retrieves a list of countries and number of hosts.")
    @GetMapping("/by-country")
    public List<HostsPerCountryView> findHostsByCountry() {
        return hostService.findHostsByCountry();
    }

    @Operation(summary = "Get names and surnames of hosts", description = "Retrieves a list of hosts with their names and surnames.")
    @GetMapping("/names")
    public List<HostProjection> getNamesAndSurnamesForHosts() {
        return hostService.getNamesAndSurnames();
    }
}
