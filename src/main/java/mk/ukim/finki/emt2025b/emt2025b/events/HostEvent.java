package mk.ukim.finki.emt2025b.emt2025b.events;

import lombok.Getter;
import mk.ukim.finki.emt2025b.emt2025b.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostEvent extends ApplicationEvent {

    private LocalDateTime when;

    public HostEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }


}
