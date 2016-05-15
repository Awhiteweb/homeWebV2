package uk.co.white.coutts.model;

import uk.co.white.coutts.model.data.AbstractReading;
import uk.co.white.coutts.model.data.ElectricityReading;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.Date;

/**
 * Created by Alex on 14/05/2016.
 */
public class MockData
{
    public static MockData getMockData()
    {
        mockData = mockData == null ? new MockData() : mockData;
        return mockData;
    }

    private static MockData mockData;

    public List<AbstractReading> newReadings()
    {
        List<AbstractReading> set = new LinkedList<>();
        AbstractReading r;
        SimpleDateFormat sdt = new SimpleDateFormat( "yyyy-M-dd" );
        Random rand = new Random();
        Float f = 1000f + ( rand.nextFloat() * 100 );
        for ( int i = 1; i < 13; i++ )
        {
            Date d = null;
            try
            {
                d = sdt.parse( String.format( "2015-%02d-01", i ) );
            }
            catch ( ParseException e )
            {
                e.printStackTrace();
            }
            f += ( rand.nextFloat() * 1000 );
            r = new ElectricityReading();
            r.setDate( d );
            r.setReading( f );
            set.add( r );
        }
        return set;
    }

}
