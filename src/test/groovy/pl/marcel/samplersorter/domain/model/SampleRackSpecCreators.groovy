package pl.marcel.samplersorter.domain.model

final class SampleRackSpecCreators {

    private SampleRackSpecCreators() {}

    static def createEmptySampleRack(def sampleRackId) {
        return new SampleRack(sampleRackId, Collections.emptySet())
    }

    static def createSampleRack(def sampleRackId, def sampleId, def patientAge, def companyName, def cityDistrict, def visionDefect) {
        return new SampleRack(sampleRackId, Set.of(
                createSample(sampleId, patientAge, companyName, cityDistrict, visionDefect))
        )
    }

    static def createSample(def sampleId, def patientAge, def companyName, def cityDistrict, def visionDefect) {
        new Sample(sampleId, new PatientDetails(patientAge, companyName, cityDistrict, visionDefect))
    }
}
