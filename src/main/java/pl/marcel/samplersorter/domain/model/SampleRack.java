package pl.marcel.samplersorter.domain.model;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleRack {

    private final long id;
    private final Set<Sample> samples;

    public SampleRack(long id) {
        this.id = id;
        this.samples = new HashSet<>();
    }

    public SampleRack(long id, Set<Sample> samples) {
        this.id = id;
        this.samples = new HashSet<>(samples);
    }

    public long getId() {
        return id;
    }

    public Set<Sample> getSamples() {
        return Collections.unmodifiableSet(samples);
    }

    public SampleRack addSample(Sample sample) {
        if (isSampleRackAssigmentNotAvailable(sample)) {
            throw new IllegalArgumentException(
                    "Cannot assign sample with id: %s to rack with id: %s.".formatted(sample.id(), id));
        }
        return new SampleRack(id, Stream.concat(samples.stream(), Stream.of(sample)).collect(Collectors.toSet()));
    }

    private boolean isSampleRackAssigmentNotAvailable(Sample sample) {
        return samples.stream().anyMatch(rackSample -> reduceSampleRackAssigmentPredicates().test(rackSample, sample));
    }

    private BiPredicate<Sample, Sample> reduceSampleRackAssigmentPredicates() {
        return getSampleRackAssigmentPredicates().stream().reduce(BiPredicate::or).orElse((x, y) -> false);
    }

    private List<BiPredicate<Sample, Sample>> getSampleRackAssigmentPredicates() {
        return List.of(
                samePatientAgeSamplePredicate,
                samePatientCompanySamplePredicate,
                samePatientDistrictSamplePredicate,
                samePatientVisionDefectSamplePredicate
        );
    }

    private final BiPredicate<Sample, Sample> samePatientAgeSamplePredicate =
            (s1, s2) -> s1.patientDetails().age() == s2.patientDetails().age();

    private final BiPredicate<Sample, Sample> samePatientCompanySamplePredicate =
            (s1, s2) -> s1.patientDetails().company().equals(s2.patientDetails().company());

    private final BiPredicate<Sample, Sample> samePatientDistrictSamplePredicate =
            (s1, s2) -> s1.patientDetails().cityDistrict().equals(s2.patientDetails().cityDistrict());

    private final BiPredicate<Sample, Sample> samePatientVisionDefectSamplePredicate =
            (s1, s2) -> s1.patientDetails().visionDefect().equals(s2.patientDetails().visionDefect());

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleRack sampleRack = (SampleRack) o;
        return Objects.equals(samples, sampleRack.samples);
    }

    @Override
    public int hashCode() {
        return Objects.hash(samples);
    }
}
