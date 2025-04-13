package mk.ukim.finki.emt2025b.emt2025b.service;

import mk.ukim.finki.emt2025b.emt2025b.model.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> update(Long id,  HostDto host);

    Optional<Host> save(HostDto host);

    void deleteById(Long id);

}
