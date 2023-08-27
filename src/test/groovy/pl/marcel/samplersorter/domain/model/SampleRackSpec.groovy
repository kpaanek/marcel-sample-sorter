package pl.marcel.samplersorter.domain.model

import spock.lang.Specification
import spock.lang.Unroll

import static pl.marcel.samplersorter.domain.model.SampleRackSpecCreators.*

class SampleRackSpec extends Specification {

    def "should place patients sample into the empty rack"() {
        given:
        def sampleRack = createEmptySampleRack(1)
        def sample = createSample(
                1, 35, "COMPANY_B", "DISTRICT_B", "VISION_DEFECT_B")
        when:
        def result = sampleRack.addSample(sample)
        then:
        result.getSamples().size() == 1
    }

    def "should place patients samples into the same rack"() {
        given:
        def sampleRack = createSampleRack(
                1, 1, 30, "COMPANY_A", "DISTRICT_A", "VISION_DEFECT_A")
        def sample = createSample(
                1, 35, "COMPANY_B", "DISTRICT_B", "VISION_DEFECT_B")
        when:
        def result = sampleRack.addSample(sample)
        then:
        result.getSamples().size() == 2
    }

    @Unroll
    def "should not place patients samples into the same rack when the business rules are violated"() {
        given:
        def sampleRack = createSampleRack(
                1, 1, 30, "COMPANY_A", "DISTRICT_A", "VISION_DEFECT_A")
        def sample = createSample(2, patientAge, companyName, cityDistrict, visionDefect)
        when:
        sampleRack.addSample(sample)
        then:
        thrown(IllegalArgumentException.class)
        where:
        patientAge  | companyName   | cityDistrict  | visionDefect
        30          | "COMPANY_B"   | "DISTRICT_B"  | "VISION_DEFECT_B"
        35          | "COMPANY_A"   | "DISTRICT_B"  | "VISION_DEFECT_B"
        35          | "COMPANY_B"   | "DISTRICT_B"  | "VISION_DEFECT_A"
    }
}
