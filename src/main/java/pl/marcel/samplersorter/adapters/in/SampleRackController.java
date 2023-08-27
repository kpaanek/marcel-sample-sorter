package pl.marcel.samplersorter.adapters.in;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import pl.marcel.samplersorter.adapters.in.request.UpdateSampleRackRequest;
import pl.marcel.samplersorter.domain.SampleRackFacade;

import javax.validation.Valid;

@Controller("/sample-racks")
public class SampleRackController {

    private final SampleRackFacade sampleRackFacade;

    public SampleRackController(SampleRackFacade sampleRackFacade) {
        this.sampleRackFacade = sampleRackFacade;
    }

    @Put
    public HttpResponse<Void> updateSampleRack(@Body @Valid UpdateSampleRackRequest request) {
        sampleRackFacade.handle(request);
        return HttpResponse.noContent();
    }
}
