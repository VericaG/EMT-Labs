package mk.ukim.finki.emt2025b.emt2025b.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.hosts_per_country")
@Immutable
public class HostsPerCountryView {

    @Id
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "num_hosts")
    private Integer numHosts;

    public HostsPerCountryView(Long countryId, Integer numHosts) {
        this.countryId = countryId;
        this.numHosts = numHosts;
    }

    public HostsPerCountryView() {
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Integer getNumHosts() {
        return numHosts;
    }

    public void setNumHosts(Integer numHosts) {
        this.numHosts = numHosts;
    }
}


