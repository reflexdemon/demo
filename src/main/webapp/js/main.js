$("#f2c").click(function() {
	f2c();
});

function f2c() {
	var f = iFahrenheit.value;
	console.log(' f = ' + f);

	 jQuery.ajax({
	 type: "GET",
	 url: "./services/f2c/" + f,
	 contentType: "application/json",
	 dataType: "json",
	 success: function (data, status, jqXHR) {
	 console.log(' data = ' + data);
	 $('#oCelsius').replaceWith(c);
	 var c = $.evalJSON(data).celsius;
	 console.log(' c = ' + c);
	 },
	
	 error: function (jqXHR, status) {
	 $('#oCelsius').replaceWith('jqXHR:status' + jqXHR + ':' + status);
	 iFahrenheit.value = f;
	 }
	 });

//	$.getJSON("./services/f2c/" + f, function(data) {
//		console.log(' data = ' + data);
//	}, function(eData) {
//		console.log(' eData = ' + eData);
//	}
//	
//	);
//	console.log('outside');

}
