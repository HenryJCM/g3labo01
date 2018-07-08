var stompClient = null;
var tiempo;

/*
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").show();
    }
    //$("#greetings").html("");
}*/

function connect() {
    var socket = new SockJS('/g3-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        //console.log('Connected: ' + frame);
        stompClient.subscribe('/tema/resultadoClima', function (resultadoClima) {
            mostrarDatosClima(JSON.parse(resultadoClima.body));
        });
        
       tiempo = setInterval(realizarConsultaWS, 60000);    
    });
}

/*
function realizarConsultaWS() {
	stompClient.send("/g3labo01/busquedaClima", {}, JSON.stringify({'year': $("#year").value , 'mes': $("#mes").value}));
	
}*/

function realizarConsultaWS(){
	
	$.ajax({
		url: '/busquedaClima',
		type: 'POST',
		contentType: "application/json; charset=utf-8",
		// el tipo de información que se espera de respuesta
		dataType: "json",
		// la información a enviar
		// (también es posible utilizar una cadena de datos)
		data: JSON.stringify({'year': $("#year").val() , 'mes': $("#mes").val()}),
		// código a ejecutar si la petición es satisfactoria;
		// la respuesta es pasada como argumento a la función
		success: function(resultadoClima) {
			   
			mostrarDatosClima(resultadoClima);
		},
		error : function(xhr, status) {
			alert('Disculpe, existio un problema -- '+xhr+" -- "+status);
		},
	});
	
	
}

function mostrarDatosClima(resultadoClima) {
	console.log("año: " + resultadoClima.year);
	console.log("mes: " + resultadoClima.mes);
	console.log("temperatura: " + resultadoClima.temp);
	console.log("precipitacion: " + resultadoClima.precip);
	$("#yearResultado").text(resultadoClima.year);
	$("#mesResultado").text(resultadoClima.mes);
	
	if(resultadoClima.temp == 0)
		$("#valorTemperatura").text("0");
	else
		$("#valorTemperatura").text(resultadoClima.temp);
	
	if(resultadoClima.precip == 0)
		$("#valorPrecipitacion").text("0");
	else
		$("#valorPrecipitacion").text(resultadoClima.precip);
	
	document.getElementById("divData").style.display = "block";
	document.getElementById("divTemp").style.display = "block";
	document.getElementById("divPrecip").style.display = "block";
	graficarTempYPrecip(resultadoClima.temp, resultadoClima.precip);
	
}


/*
$(function () {
	
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() { clearInterval(tiempo);  connect(); });
});

*/


$(function () {
	
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#send" ).click(function() {
    	clearInterval(tiempo); 
    	realizarConsultaWS(); 
    	tiempo = setInterval(realizarConsultaWS, 60000); });
});


////////// Graficos y Colores Temperatura y Precipitaciones

function graficarTempYPrecip(temp, precip) { 
    Highcharts.setOptions({
        lang: {
            decimalPoint: '.',
            thousandsSep: ','
        }
    });
	
    graficarTemperatura(temp);
	graficarPrecipitacion(precip);
};


function graficarPrecipitacion(valorPrecipitacion) {
	var tituloPrecip = "Año: " + $("#yearResultado").text() + " Mes: " + $("#mesResultado").text();
	
	var colorPrecipitacion;
	
	if(valorPrecipitacion > 500) {
		colorPrecipitacion = '#212123';
	}
	else if(valorPrecipitacion <= 500 && valorPrecipitacion >= 200) {
		colorPrecipitacion = '#787B83';
	}
	else if(valorPrecipitacion < 200) {
		colorPrecipitacion = '#F2E522';
	}
	
    var graficoPrecipitacion = Highcharts.chart('precipitacion', {
        chart: {
            type: 'column',
            margin: 120,
            options3d: {
					enabled: false,
            	alpha: 15,
            	beta: 15,
            	depth: 110
            }
        },
        title: {
            text: tituloPrecip
        },
        xAxis: {
        	title: {
                text: $("#yearResultado").text()
            },
            categories: ['']
           
        },
        yAxis: {
            title: {
                text: 'Precipitacion'
            },
            tickInterval: 50
        },
        tooltip: {
          	pointFormat: "{point.y} mm"
        },
        plotOptions: {
        	column: {
        		depth: 60,
          		stacking: true,
           		grouping: false,
          		groupZPadding: 10
            },
            series:{
            	color: colorPrecipitacion
            }
        },
        series: [{
            name: 'Precipitacion',
            data: [{
            	
            	y: valorPrecipitacion
            	
            }]
        }]
    });
}


function graficarTemperatura(valorTemperatura) {
	
	var tituloTemp = "Año: " + $("#yearResultado").text() + " Mes: " + $("#mesResultado").text();
	
	var colorTemperatura;
	
	if(valorTemperatura > 30) {
		colorTemperatura = '#FA3B08';
	}
	else if(valorTemperatura <= 30 && valorTemperatura >= 15) {
		colorTemperatura = '#F2E522';
	}
	else if(valorTemperatura < 15) {
		colorTemperatura = '#0813FA';
	}
	
    var graficoTemperatura = Highcharts.chart('temperatura', {
        chart: {
            type: 'column',
            margin: 120,
            options3d: {
					enabled: false,
            	alpha: 15,
            	beta: 15,
            	depth: 110
            }
        },
        title: {
            text: tituloTemp
        },
        xAxis: {
        	title: {
                text: $("#yearResultado").text()
            },
            categories: ['']
           
        },
        yAxis: {
            title: {
                text: 'Temperatura'
            },
            tickInterval: 5.0
        },
        tooltip: {
          	pointFormat: "{point.y} Celsius"
        },
        plotOptions: {
        	column: {
        		depth: 60,
          		stacking: true,
           		grouping: false,
          		groupZPadding: 10
            },
            series:{
            	color: colorTemperatura 
            }
        },
        series: [{
            name: 'Temperatura',
            data: [{
            	
            	y: valorTemperatura 
            	
            }]
        }]
    });
}



