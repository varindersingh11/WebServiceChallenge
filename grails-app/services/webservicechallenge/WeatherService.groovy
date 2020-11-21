package webservicechallenge

import grails.gorm.transactions.Transactional
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import org.xml.sax.SAXException

@Transactional
class WeatherService {

    def convertTempUnits(def fromTemp, def unit) {
        try {
            def resp = callWebService(fromTemp, unit)

            def xmlResult = resp.getBody().get()
            def list = new XmlParser().parseText(xmlResult)

            def result = prepareFinalResult(fromTemp, unit, list)
            return result
        } catch(IOException | SAXException | Exception ex){
            log.error("Exception raise in WeatherService.convertTempUnits " + ex.getMessage())
        }
    }

    def callWebService(def fromTemp, def unit) {
        try {
            String baseUrl = "http://www.q88.com"
            HttpClient client = HttpClient.create(baseUrl.toURL())
            HttpRequest request = HttpRequest.GET(UriBuilder.of('/WS/Q88WSInternal.asmx/ConvertTemperature')
                    .queryParam('property', fromTemp)
                    .queryParam('val', unit)
                    .build())

            HttpResponse<String> response = client.toBlocking().exchange(request, String)
            return response
        } catch(Exception ex) {
            log.error("Exception raised while calling web service " + ex.getMessage())
        }
    }

    def prepareFinalResult(def fromTemp, def unit, def webServiceResult) {

        def result = new Expando()
        result.fromTemp = fromTemp
        result.tempInCelsius = webServiceResult?.Celsius.text()
        result.tempInFahrenheit = webServiceResult?.Fahrenheit.text()
        result.unit = unit

        return result

    }
}
