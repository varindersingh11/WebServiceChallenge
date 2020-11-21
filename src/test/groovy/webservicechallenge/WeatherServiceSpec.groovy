package webservicechallenge

import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
class WeatherServiceSpec extends Specification {

    def service

    def setup() {
        service = new WeatherService()
    }

    void "Test webservice results"() {
        when:
        def result = service.convertTempUnits('Celsius', '32')

        then:
        result.unit == '32'
    }
}
