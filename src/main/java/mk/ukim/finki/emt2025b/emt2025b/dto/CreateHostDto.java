package mk.ukim.finki.emt2025b.emt2025b.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Country;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;


public record CreateHostDto (
         String name,

         String surname,

         Country country
) {

    public static CreateHostDto from (Host host) {
        return new CreateHostDto(
                host.getName(),
                host.getSurname(),
                host.getCountry()
        );
    }

    public static List<CreateHostDto> from (List<Host> hosts) {
        return hosts.stream().map(CreateHostDto::from).collect(Collectors.toList());
    }

    public Host toHost () {
        return new Host(name, surname, country);
    }
}
