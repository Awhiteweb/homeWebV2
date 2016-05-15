var options = {
	title: {
		text: "%s"
	},
	xAxis: {
		type: "datetime"
	},
	yAxis: {
		title: "usage"
	},
	series [
	    {
	    	name: "data",
	    	data: %s
	    }
	],
	legend: {
		enabled: false
	},
	plotOptions: {
		area: {
			fillColor: {
				linearGradient: {
					x1: 0,
					y1: 0,
					x2: 0,
					y2: 1
				},
				stops: [
                    [0, Highcharts.getOptions().colors[0]],
                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                ]
			}
            marker: {
                radius: 2
            },
            lineWidth: 2,
            states: {
                hover: {
                    lineWidth: 1
                }
            },
            threshold: null
		}
	}
}