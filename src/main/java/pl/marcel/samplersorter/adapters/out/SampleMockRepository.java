package pl.marcel.samplersorter.adapters.out;

import jakarta.inject.Singleton;
import pl.marcel.samplersorter.domain.model.PatientDetails;
import pl.marcel.samplersorter.domain.model.Sample;
import pl.marcel.samplersorter.domain.ports.SampleRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
class SampleMockRepository implements SampleRepository {

    private static final Map<Long, Sample> SAMPLE_MAP = new ConcurrentHashMap<>() {
        {
            put(1L, new Sample(1, new PatientDetails(
                    40, "COMPANY_C", "DISTRICT_C", "DEFECT_C"
            )));
            put(2L, new Sample(2, new PatientDetails(
                    30, "COMPANY_B", "DISTRICT_B", "DEFECT_B"
            )));
            put(3L, new Sample(3, new PatientDetails(
                    35, "COMPANY_A", "DISTRICT_B", "DEFECT_B"
            )));
            put(4L, new Sample(4, new PatientDetails(
                    35, "COMPANY_B", "DISTRICT_A", "DEFECT_B"
            )));
            put(5L, new Sample(5, new PatientDetails(
                    35, "COMPANY_B", "DISTRICT_B", "DEFECT_A"
            )));

        }
    };

    @Override
    public Optional<Sample> findById(long id) {
        return Optional.ofNullable(SAMPLE_MAP.get(id));
    }
}
