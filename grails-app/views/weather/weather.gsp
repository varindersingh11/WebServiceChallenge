<%--
  Created by IntelliJ IDEA.
  User: varinder singh
  Date: 19-11-2020
  Time: 22:31
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
</head>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous">

</script>
<body>

<p>


<g:form name="myForm" controller="weather" action="weather" >
    <div class="form-group">
        <div class="row">
        <div class="col-sm-4">
            <label>Select Celsius/Fahrenheit :</label>
        </div>
        <div class="col-sm-4">
        <g:select from="${['Celsius', 'Fahrenheit']}" name="fromTempature"
                                       optionKey=""
                  value="${result?.fromTemp}"/>
        </div>
    </div>
    </div>

<p>
    <div class="form-group">

    <div class="row">
    <div class="col-sm-4">
    <label>Value :</label>
    </div>
    <div class="col-sm-4">
    <g:field  type="number" name="unit" value="${result?.unit}"/>
    </div>
    </div>
</div>
    <g:submitButton name="convert" value="Convert" class="btn btn-success" />

</g:form>


<div>
<g:if test="${result}">
    <div class="col-sm-8 alert alert-info">
    Temparature in Celsius = ${result?.tempInCelsius}
    <br/>
Temparature in Fahrenheit = ${result?.tempInFahrenheit}
    </div>
</g:if>
</div>
</body>
</html>