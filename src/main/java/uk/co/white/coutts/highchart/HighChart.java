package uk.co.white.coutts.highchart;

import java.util.List;
import java.util.Map;

import org.vaadin.highcharts.AbstractHighChart;

import uk.co.white.coutts.controllers.DataController;

import com.vaadin.annotations.JavaScript;

@JavaScript( { "jquery-min.js", "highcharts.js", "highcharts-more.js", "highcharts-connector.js"} )
public class HighChart extends AbstractHighChart
{
	private static final long serialVersionUID = -1187313419703844748L;

	public static void main(String[] args )
	{
		HighChart h = new HighChart();
		DataController d = new DataController();
		h.getJsAreaChart( "Mock Chart Data", d.getReadingsForJs() );
		h.getPolarChart( "Mock Chart Data", d.getReadingsForJs() );
	}


	public String getJsAreaChart( String chartTitle, Map<String, List<String>> seriesData )
	{
		StringBuilder sb = new StringBuilder();
		int mapSize = seriesData.size();
		int mapCurrent = 0;
		for( String title : seriesData.keySet() )
		{
			mapCurrent++;
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
			if( mapCurrent != mapSize )
				sb.append( "," );
		}
		return String.format( READINGS_GRAPH, chartTitle, sb );
	}
	
	public String getPolarChart( String chartTitle, Map<String, List<String>> seriesData )
	{
		StringBuilder sb = new StringBuilder();
		for( String title : seriesData.keySet() )
		{
			sb.append( "{type:'area'," );
			sb.append( "name:'" );
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
		System.out.println( String.format( READINGS_GRAPH, chartTitle, sb ) );
		return String.format( READINGS_POLAR_GRAPH, chartTitle, sb );
	}

	private final String READINGS_GRAPH =
			"var options={" +
				"chart:{type:'area'}," +
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
	
	private final String READINGS_POLAR_GRAPH =
			"var options={" +
				"chart:{polar:true}," +
				"title:{text:'%s'}," +
				"pane:{" +
					"startAngle:0," +
					"endAngle:360" +
				"}," +
				"xAxis:{" +
					"tickInterval:30," +
					"min:0," +
					"max:360," +
					"labels:{formatter:function(){return this.value;}}," +
					"type:'datetime'" +
				"}," +
				"yAxis:{min:0}," +
				"series:[" +
					"%s" +
				"]," +
				"legend:{enabled:false}," +
				"plotOptions:{" +
					"series:{" +
						"pointStart:0," +
						"pointInterval:30" +
					"}," +
					"column:{" +
						"pointPadding:0," +
						"groupPadding:0" +
					"}," +
				"}" +
			"}";
}
