package pl.marcel.samplersorter.domain;

import jakarta.inject.Singleton;
import pl.marcel.samplersorter.domain.command.UpdateSampleRackCommand;
import pl.marcel.samplersorter.domain.ports.SampleRackRepository;
import pl.marcel.samplersorter.domain.ports.SampleRepository;

@Singleton
public class SampleRackCommandHandler {

    private final SampleRackRepository sampleRackRepository;
    private final SampleRepository sampleRepository;

    public SampleRackCommandHandler(SampleRackRepository sampleRackRepository, SampleRepository sampleRepository) {
        this.sampleRackRepository = sampleRackRepository;
        this.sampleRepository = sampleRepository;
    }

    public void handle(UpdateSampleRackCommand command) {
        var sampleRack = sampleRackRepository.findById(command.rackId()).orElseThrow(() ->
                new IllegalArgumentException("Sample Rack with id: %s not found.".formatted(command.rackId())));
        var sample = sampleRepository.findById(command.sampleId()).orElseThrow(() ->
                new IllegalArgumentException("Sample with id: %s not found.".formatted(command.sampleId())));
        var updatedSampleRack = sampleRack.addSample(sample);
        sampleRackRepository.save(updatedSampleRack);
    }
}
