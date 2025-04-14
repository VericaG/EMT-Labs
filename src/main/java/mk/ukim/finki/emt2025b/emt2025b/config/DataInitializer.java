package mk.ukim.finki.emt2025b.emt2025b.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Accommodation;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Country;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.User;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.AccommodationCategory;
import mk.ukim.finki.emt2025b.emt2025b.model.enumerations.Role;
import mk.ukim.finki.emt2025b.emt2025b.repository.AccommodationRepository;
import mk.ukim.finki.emt2025b.emt2025b.repository.CountryRepository;
import mk.ukim.finki.emt2025b.emt2025b.repository.HostRepository;
import mk.ukim.finki.emt2025b.emt2025b.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {

        Country france = countryRepository.save(new Country("France", "Europe"));
        Country germany = countryRepository.save(new Country("Germany", "Europe"));
        Country italy = countryRepository.save(new Country("Italy", "Europe"));
        Country spain = countryRepository.save(new Country("Spain", "Europe"));
        Country netherlands = countryRepository.save(new Country("Netherlands", "Europe"));
        Country austria = countryRepository.save(new Country("Austria", "Europe"));
        Country switzerland = countryRepository.save(new Country("Switzerland", "Europe"));
        Country portugal = countryRepository.save(new Country("Portugal", "Europe"));
        Country greece = countryRepository.save(new Country("Greece", "Europe"));

        Host andreas = hostRepository.save(new Host("Andreas", "Müller", germany));
        Host camile = hostRepository.save(new Host("Camille", "Leroy", france));
        Host giovanni = hostRepository.save(new Host("Giovanni", "Ricci", italy));
        Host isabel = hostRepository.save(new Host("Isabel", "Fernandez", spain));
        Host gustavo = hostRepository.save(new Host("Gustavo", "Almeida", portugal));
        Host johan = hostRepository.save(new Host("Johan", "Van der Meer", netherlands));
        Host christophor = hostRepository.save(new Host("Christoph", "Schneider", austria));
        Host theo = hostRepository.save(new Host("Theo", "Papadopoulos", greece));

        accommodationRepository.save(new Accommodation("Villa Bella", AccommodationCategory.HOUSE, andreas, 5));
        accommodationRepository.save(new Accommodation("Villa Bella1", AccommodationCategory.MOTEL, isabel, 15));
        accommodationRepository.save(new Accommodation("Villa Bella2", AccommodationCategory.HOUSE, gustavo, 3));
        accommodationRepository.save(new Accommodation("Villa Bella3", AccommodationCategory.HOUSE, theo, 2));


        userRepository.save(new User("host", passwordEncoder.encode("host"), "host", "host", Role.ROLE_HOST));

        userRepository.save(new User("user", passwordEncoder.encode("user"), "user", "user", Role.ROLE_USER));
    }
}

