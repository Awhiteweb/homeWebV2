package uk.co.white.coutts.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme( "mytheme" )
@Widgetset( "uk.co.white.coutts.MyAppWidgetset" )
public class MyUI extends UI
{
    private static final long serialVersionUID = 3861230342699644662L;

	@WebServlet(urlPatterns = "/*", name = "IndexServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class IndexServlet extends VaadinServlet 
    {
		private static final long serialVersionUID = 1L;
    }

	@Override
	protected void init( VaadinRequest request )
	{
		setContent( new ContentView().loadContent() );
	}

}
