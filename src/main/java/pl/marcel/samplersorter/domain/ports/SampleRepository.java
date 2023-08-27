package pl.marcel.samplersorter.domain.ports;

import pl.marcel.samplersorter.domain.model.Sample;

import java.util.Optional;

public interface SampleRepository {

    Optional<Sample> findById(long id);
}
