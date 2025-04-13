package mk.ukim.finki.emt2025b.emt2025b.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.emt2025b.emt2025b.model.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.dto.AccommodationDto;
import mk.ukim.finki.emt2025b.emt2025b.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Operation(summary = "Земање на сите Accommodations")
    @GetMapping
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @Operation(summary = "Пребарување на Accommodation по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return accommodationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Додавање на Accommodation по ID")
    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDto accommodation) {
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Едитирање на Accommodation по ID")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> update(@PathVariable Long id, @RequestBody AccommodationDto accommodation) {
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
    public List<Accommodation> recommendSimilarAccomodations(@PathVariable Long id) {
        Accommodation accommodation = accommodationService.findById(id).get();
        return accommodationService.findRecommendedAccomodations(accommodation.getCategory(), id);
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

}
