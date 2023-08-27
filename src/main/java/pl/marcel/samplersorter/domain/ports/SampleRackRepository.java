package pl.marcel.samplersorter.domain.ports;

import pl.marcel.samplersorter.domain.model.SampleRack;

import java.util.Optional;

public interface SampleRackRepository {

    Optional<SampleRack> findById(long id);

    void save(SampleRack sampleRack);
}
