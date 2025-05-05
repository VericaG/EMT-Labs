package mk.ukim.finki.emt2025b.emt2025b.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt2025b.emt2025b.dto.DisplayAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.dto.CreateAccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.model.views.AccommodationsByHostView;
import mk.ukim.finki.emt2025b.emt2025b.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")

public class AccommodationController {

    private final AccommodationApplicationService accommodationService;

    public AccommodationController(AccommodationApplicationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Operation(summary = "Земање на сите Accommodations")
    @GetMapping
    public List<DisplayAccommodationDto> findAll() {
        return accommodationService.findAll();
    }

    @Operation(summary = "Пребарување на Accommodation по ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Додавање на Accommodation по ID")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Едитирање на Accommodation по ID")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto accommodation) {
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Изнајмување на Accommodation по ID")
    @PutMapping("/reserve/{id}")
    public ResponseEntity<Accommodation> reserve(@PathVariable Long id, @RequestBody int rooms) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.reserveAccommodation(id, rooms);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @Operation(summary = "Предложување на слични Accomodations")
    @GetMapping("/recommend/{id}")
    public List<DisplayAccommodationDto> recommendSimilarAccomodations(@PathVariable Long id) {
        DisplayAccommodationDto accommodation = accommodationService.findById(id).get();
        return accommodationService.findRecommendedAccomodations(accommodation.category(), id);
    }


    @Operation(summary = "Бришење на Accommodation по ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get accommodations by host", description = "Retrieves a list of hosts and number of accommodations.")
    @GetMapping("/by-host")
    public List<AccommodationsByHostView> findAccommodationsByHost() {
        return accommodationService.findAccommodationsByHost();
    }

}
