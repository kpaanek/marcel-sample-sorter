package pl.marcel.samplersorter.adapters.in;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import pl.marcel.samplersorter.domain.SampleRackCommandHandler;

import javax.validation.Valid;

@Controller("/sample-racks")
class SampleRackController {

    private final SampleRackCommandHandler commandHandler;

    SampleRackController(SampleRackCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @Put
    HttpResponse<Void> updateSampleRack(@Body @Valid UpdateSampleRackRequest request) {
        commandHandler.handle(request);
        return HttpResponse.noContent();
    }
}
