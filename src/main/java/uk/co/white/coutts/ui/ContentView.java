package uk.co.white.coutts.ui;

import com.vaadin.ui.CssLayout;

public class ContentView extends CssLayout
{
	private static final long serialVersionUID = -7855590404463735214L;
	private NavLoader navbar;
	private MainContent content;

	public ContentView loadContent()
	{
		setStyleName( "content" );
		setSizeFull();
		content = new MainContent().loadWelcomeView();
		navbar = new NavLoader( content ).loadMenuButtons();
		addComponents( navbar, content );
		
		return this;
	}
}
