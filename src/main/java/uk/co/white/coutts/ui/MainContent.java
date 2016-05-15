package uk.co.white.coutts.ui;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;

public class MainContent extends CssLayout
{
	private static final long serialVersionUID = -8201804649853406369L;

	public MainContent(){}
	
	public void loadView( Layout layout )
	{
		setStyleName( "main-content" );
		removeAllComponents();
		Panel panel = new Panel();
		panel.setStyleName( "content-panel" );
		panel.setContent( layout );
		panel.setSizeFull();
		addComponent( panel );
		
		setHeight( "100%" );
	}
	
	public MainContent loadWelcomeView()
	{
		removeAllComponents();
		setHeight( "100%" );
		setStyleName( "welcome-view" );
		Label label = new Label( "<h1>Welcome, please choose a view from the menu</h1>" );
		label.setCaptionAsHtml( true );
		addComponent( label );
		
		return this;
	}
}
