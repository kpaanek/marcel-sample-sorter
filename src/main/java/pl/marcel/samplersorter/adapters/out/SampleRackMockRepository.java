package pl.marcel.samplersorter.adapters.out;

import jakarta.inject.Singleton;
import pl.marcel.samplersorter.domain.model.PatientDetails;
import pl.marcel.samplersorter.domain.model.Sample;
import pl.marcel.samplersorter.domain.model.SampleRack;
import pl.marcel.samplersorter.domain.ports.SampleRackRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class SampleRackMockRepository implements SampleRackRepository {

    private static final Map<Long, SampleRack> SAMPLE_RACK_MAP = new ConcurrentHashMap<>() {
        {
            put(1L, new SampleRack(1));
            put(2L, new SampleRack(2, Set.of(new Sample(1, new PatientDetails(
                    25, "COMPANY_X", "DISTRICT_X", "DEFECT_X"
            )))));
            put(3L, new SampleRack(2, Set.of(new Sample(1, new PatientDetails(
                    30, "COMPANY_A", "DISTRICT_A", "DEFECT_A"
            )))));
        }
    };

    @Override
    public Optional<SampleRack> findById(long id) {
        return Optional.ofNullable(SAMPLE_RACK_MAP.get(id));
    }

    @Override
    public void save(SampleRack sampleRack) {
        SAMPLE_RACK_MAP.put(sampleRack.getId(), sampleRack);
    }
}
