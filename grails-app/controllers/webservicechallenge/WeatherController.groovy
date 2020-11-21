package webservicechallenge

class WeatherController {

    WeatherService weatherService

    def index() {
        redirect(action:'weather')

    }

    def weather() {
        def fromTemp = params.fromTempature
        def unit = params.unit
        def result = weatherService.convertTempUnits(fromTemp, unit)
        render(view:'weather', model: [result: result])
    }
}