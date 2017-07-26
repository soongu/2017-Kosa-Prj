<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoderBy</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://code.highcharts.com/highcharts.js" defer></script>
<script src="https://code.highcharts.com/modules/exporting.js" defer></script>
<script type="text/javascript">
$(function() {
	Highcharts.chart('container', {
	    title: {
	        text: 'Combination chart'
	    },
	    xAxis: {
	        categories: ["setosa", "versicolor", "virginica"]
	    },
	    labels: {
	        items: [{
	            html: 'Iris data analytics',
	            style: {
	                left: '50px',
	                top: '18px',
	                color: (Highcharts.theme && Highcharts.theme.textColor) || 'black'
	            }
	        }]
	    },
	    series: <%= request.getAttribute("irisData") %>
	});
});
</script>
</head>
<body>
<h1>챠트</h1>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</body>
</html>