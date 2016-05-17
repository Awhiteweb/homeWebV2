package uk.co.white.coutts.model;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import uk.co.white.coutts.model.data.AbstractReading;
import uk.co.white.coutts.model.data.ElectricityReading;

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
        Random rand = new Random();
        Float f = 1000f + ( rand.nextFloat() * 100 );
        for ( int i = 0; i < 12; i++ )
        {
            Calendar d = Calendar.getInstance();
            d.set( 2015, i, 1 );
            f += ( rand.nextFloat() * 1000 );
            r = new ElectricityReading();
            r.setDate( d );
            r.setReading( f );
            set.add( r );
        }
        return set;
    }

}
