package mk.ukim.finki.emt2025b.emt2025b.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt2025b.emt2025b.dto.ReservationsListDto;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.User;
import mk.ukim.finki.emt2025b.emt2025b.model.exceptions.AccommodationNotAvailableException;
import mk.ukim.finki.emt2025b.emt2025b.model.exceptions.ReservationListNotFoundException;
import mk.ukim.finki.emt2025b.emt2025b.service.application.ReservationApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/reservationlist")
@Tag(name = "ReservationList API", description = "Endpoints for managing the reservation list")

public class ReservationListController {
    private final ReservationApplicationService reservationApplicationService;

    public ReservationListController(ReservationApplicationService reservationApplicationService) {
        this.reservationApplicationService = reservationApplicationService;
    }

    @Operation(
            summary = "Get active reservationList",
            description = "Retrieves the active reservationList for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "ReservationList retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "ReservationList not found")}
    )
    @GetMapping
    public ResponseEntity<ReservationsListDto> getActiveReservationList(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return reservationApplicationService.getActiveReservationList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add book to ReservationList",
            description = "Adds an accommodation to the ReservationList for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Accommodation added to ReservationList successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Accommodation not found")}
    )
    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<ReservationsListDto> addAccommodationToList(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationApplicationService.addAccommodationToList(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/confirmreservations")
    public ResponseEntity<Void> confirmReservations(Authentication authentication, @RequestBody int numRooms) {
        User user = (User) authentication.getPrincipal();
        try {
            reservationApplicationService.confirmReservations(user.getUsername(), numRooms);
            return ResponseEntity.ok().build();
        } catch (ReservationListNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AccommodationNotAvailableException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
