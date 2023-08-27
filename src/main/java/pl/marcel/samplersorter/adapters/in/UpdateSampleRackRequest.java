package pl.marcel.samplersorter.adapters.in;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import pl.marcel.samplersorter.domain.command.UpdateSampleRackCommand;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Serdeable
@Introspected
record UpdateSampleRackRequest(@NotNull Long rackId, @NotEmpty Long sampleId) implements UpdateSampleRackCommand {
}
