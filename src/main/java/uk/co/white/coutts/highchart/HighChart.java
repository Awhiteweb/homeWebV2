package uk.co.white.coutts.highchart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.vaadin.highcharts.AbstractHighChart;

import com.google.gson.Gson;
import com.vaadin.annotations.JavaScript;

import uk.co.white.coutts.controllers.DataController;
import uk.co.white.coutts.highchart.data.*;

@JavaScript( { "jquery-min.js", "highcharts.js", "highcharts-connector.js"} )
public class HighChart extends AbstractHighChart
{
	private static final long serialVersionUID = -1187313419703844748L;

	public static void main(String[] args )
	{
		HighChart h = new HighChart();
		DataController d = new DataController();
		h.printJSFile( d.getReadingsForJs() );
	}


	public String printJSFile( Map<String, List<String>> seriesData )
	{
		StringBuilder sb = new StringBuilder();
		for( String title : seriesData.keySet() )
		{
			sb.append( "{name:'" );
			sb.append( title );
			sb.append( "',data:[" );
			List<String> data = seriesData.get( title );
			int max = data.size();
			int current = 0;
			for( String d : data )
			{
				current++;
				sb.append( d );
				if( current != max )
					sb.append( "," );
			}
			sb.append( "]}" );
		}
		return String.format( READINGS_GRAPH, "Mock Data graph", sb );
	}

	private final String READINGS_GRAPH =
			"var options={" +
				"title:{text:'%s'}," +
				"xAxis:{type:'datetime'}," +
				"yAxis:{title:'usage'}," +
				"series:[" +
					"%s" +
				"]," +
				"legend:{enabled:false}," +
				"plotOptions:{" +
					"area:{" +
						"fillColor:{" +
							"linearGradient:{" +
								"x1:0," +
								"y1:0," +
								"x2:0," +
								"y2:1" +
							"}," +
							"stops:[" +
								"[0,Highcharts.getOptions().colors[0]]," +
								"[1,Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]" +
							"]" +
						"}," +
						"marker:{radius:2}," +
						"lineWidth:2," +
						"states:{" +
							"hover:{" +
								"lineWidth:1" +
							"}" +
						"}," +
						"threshold: null" +
					"}" +
				"}" +
			"}";
}
