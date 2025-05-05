package mk.ukim.finki.emt2025b.emt2025b.listeners;

import mk.ukim.finki.emt2025b.emt2025b.events.HostEvent;
import mk.ukim.finki.emt2025b.emt2025b.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostEvent(HostEvent event) {
        this.hostService.refreshMaterializedView();

    }
}
