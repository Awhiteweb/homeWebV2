package uk.co.white.coutts.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EntityChangeListener implements PropertyChangeListener
{

	@Override
	public void propertyChange( PropertyChangeEvent evt )
	{
		System.out.println( evt.getPropertyName() + ": " + evt.getNewValue() );
		// update entity in database
	}

}
