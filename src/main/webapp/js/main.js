(function () {
 
	$("#convertBtn").click( function() {
		var fahernheitValue = parseFloat($("#fahrenheitTxt").val());
		var urlStr = "services/f2c/" + fahernheitValue;
		$.ajax({
		  type: "GET",
		  url: urlStr,
		  dataType: "json"
		}).done(function(data) {
			$("#result").text(jQuery.parseJSON(data)['celsius']);
		});
	});
 
})();