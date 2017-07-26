<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>CoderBy</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Resources -->
<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
<script src="https://www.amcharts.com/lib/3/serial.js"></script>
<script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
<link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
<script src="https://www.amcharts.com/lib/3/themes/light.js"></script>

<!-- Chart code -->
<script>
var chart = AmCharts.makeChart("chartdiv", {
    "theme": "light",
    "type": "serial",
    "dataProvider": <%= request.getAttribute("irisData") %>,
    "valueAxes": [{
        "stackType": "3d",
        "unit": "",
        "position": "left",
        "title": "Petal Average",
    }],
    "startDuration": 1,
    "graphs": [{
        "balloonText": "Average of Petal.Width [[category]]: <b>[[value]]</b>",
        "fillAlphas": 0.5,
        "lineAlpha": 0.2,
        "title": "Petal.Width",
        "type": "column",
        "valueField": "petalWidth"
    }, {
        "balloonText": "Average of Petal.Length [[category]]: <b>[[value]]</b>",
        "fillAlphas": 0.6,
        "lineAlpha": 0.2,
        "title": "Petal.Length",
        "type": "column",
        "valueField": "petalLength"
    }],
    "plotAreaFillAlphas": 0.1,
    "depth3D": 60,
    "angle": 30,
    "categoryField": "species",
    "categoryAxis": {
        "gridPosition": "start"
    },
    "export": {
        "enabled": true
     }
});
jQuery('.chart-input').off().on('input change',function() {
    var property    = jQuery(this).data('property');
    var target      = chart;
    chart.startDuration = 0;

    if ( property == 'topRadius') {
        target = chart.graphs[0];
        if ( this.value == 0 ) {
          this.value = undefined;
        }
    }

    target[property] = this.value;
    chart.validateNow();
});
</script>
</head>
<body>
<h1>IRIS</h1>
<div id="chartdiv" style="min-width: 310px; height: 500px; margin: 0 auto"></div>
</body>
</html>



