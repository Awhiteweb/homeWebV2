package uk.co.white.coutts.model.context;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import uk.co.white.coutts.model.data.AbstractReading;

public interface ReadingContext extends Context<AbstractReading>
{
	public List<AbstractReading> getDateRange( Calendar start, Calendar end ) throws SQLException;
}
