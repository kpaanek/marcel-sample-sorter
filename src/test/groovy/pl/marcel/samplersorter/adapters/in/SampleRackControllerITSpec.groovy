package pl.marcel.samplersorter.adapters.in

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class SampleRackControllerITSpec extends Specification {

    @Inject
    RequestSpecification requestSpecification

    def "should place patients sample into the empty rack"() {
        expect:
        requestSpecification
                .given()
                    .body('''
                        {
                            "rackId": "1",
                            "sampleId": "1"
                        }
                    ''')
                    .contentType(ContentType.JSON)
                .when()
                    .put('/sample-racks')
                .then()
                    .statusCode(204)

    }

    def "should place patients samples into the same rack"() {
        expect:
        requestSpecification
                .given()
                    .body('''
                            {
                                "rackId": "2",
                                "sampleId": "1"
                            }
                        ''')
                    .contentType(ContentType.JSON)
                .when()
                    .put('/sample-racks')
                .then()
                    .statusCode(204)
    }

    def "should not place patients samples of the same age into the same rack"() {
        expect:
        requestSpecification
                .given()
                    .body('''
                            {
                                "rackId": "2",
                                "sampleId": "2"
                            }
                        ''')
                    .contentType(ContentType.JSON)
                .when()
                    .put('/sample-racks')
                .then()
                    .statusCode(400)
    }

    def "should not place patients samples working in the same company into the same rack"() {
        expect:
        requestSpecification
                .given()
                    .body('''
                                {
                                    "rackId": "2",
                                    "sampleId": "3"
                                }
                            ''')
                    .contentType(ContentType.JSON)
                .when()
                    .put('/sample-racks')
                .then()
                    .statusCode(400)
    }

    def "should not place patients samples who live in the same city district into the same rack"() {
        expect:
        requestSpecification
                .given()
                    .body('''
                                    {
                                        "rackId": "2",
                                        "sampleId": "4"
                                    }
                                ''')
                    .contentType(ContentType.JSON)
                .when()
                    .put('/sample-racks')
                .then()
                    .statusCode(400)
    }

    def "should not place patients samples with the same vision defect into the same rack"() {
        expect:
        requestSpecification
                .given()
                    .body('''
                                        {
                                            "rackId": "2",
                                            "sampleId": "5"
                                        }
                                    ''')
                    .contentType(ContentType.JSON)
                .when()
                    .put('/sample-racks')
                .then()
                    .statusCode(400)
    }
}
