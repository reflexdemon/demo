<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Fahrenheit to Celsius</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">

<link rel="stylesheet" href="css/bootstrap.css">
<style>
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<!-- link rel="stylesheet" href="css/bootstrap-responsive.min.css" -->
<link rel="stylesheet" href="css/main.css">

<!--[if lt IE 9]>
            <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
            <script>window.html5 || document.write('<script src="js/vendor/html5shiv.js"><\/script>')</script>
        <![endif]-->
</head>
<body>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<!-- This code is taken from http://twitter.github.com/bootstrap/examples/hero.html -->

	<div class="container">

		<div class="span4">
			<form class="form-signin">
				<h2 class="form-signin-heading">Fahrenheit to Celsius</h2>
				<input type="text" class="input-block-level"
					placeholder="Fahrenheit" id="iFahrenheit">
					<span class="badge badge-info" id="oCelsius">#.####</span>
				<button class="btn" id="f2c">Convert</button>
			</form>
		</div>
	</div>
	<!-- /container -->

	<script src="js/jquery/jquery-2.0.3.min.js"></script>
	<script src="js/jquery/jquery.json-2.4.js"></script>
	<script src="js/vendor/bootstrap.min.js"></script>
	<script src="js/vendor/html5shiv.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/main.js"></script>
</body>
</html>
