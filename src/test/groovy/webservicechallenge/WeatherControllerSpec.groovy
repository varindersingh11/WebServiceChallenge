package webservicechallenge


import spock.lang.Specification
import grails.test.mixin.TestFor



@TestFor(WeatherController)
class WeatherControllerSpec extends Specification  {

    def weatherService
    def result
    def controller

    def setup() {
        result = new Expando()
        result.fromTemp = 'Celsius'
        result.tempInCelsius = '97'
        result.tempInFahrenheit = '32'
        result.unit = '15'

        weatherService = Mock(WeatherService)

        controller = new WeatherController()
        controller.weatherService = weatherService
    }

    void "Testing render weather view"() {

        given:
        params.fromTempature = 'Celsius'
        params.unit = '10'
        weatherService.convertTempUnits('Celsius','10') >> result

        when:
        controller.weather()

        then:
        view == '/weather/weather'
        model.result.unit == '15'
    }
}
