package uk.co.white.coutts.ui.views;

import java.util.LinkedList;
import java.util.List;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Grid.SingleSelectionModel;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import uk.co.white.coutts.controllers.DataController;
import uk.co.white.coutts.model.MyGrid;
import uk.co.white.coutts.model.MyGridColumn;
import uk.co.white.coutts.model.MyGridRow;
import uk.co.white.coutts.model.data.Paint;

public class PaintView extends CssLayout
{
	private static final long serialVersionUID = 8615033040957756021L;
	private MyGrid grid;
	private Grid dataTable;
	private BeanContainer<String, Paint> container;
	private DataController controller;

	public PaintView()
	{
		controller = new DataController();
		setStyleName( "paint-view" );
		setSizeFull();
		createGrid();
		addComponent( dataTable );
		
//		generateGrid( new LinkedList<Paint>() );
//		addComponent( grid );
//		addComponent( generateAddButton() );
	}
	
	
	private void createGrid()
	{
		container = controller.getPaintData();
		dataTable = new Grid( container );
		dataTable.setSelectionMode( SelectionMode.SINGLE );
		dataTable.addSelectionListener(selectionEvent -> {
		    Object selected = ( (SingleSelectionModel)
		        dataTable.getSelectionModel() ).getSelectedRow();

		    if ( selected != null )
		        Notification.show( "Selected " +
		        		dataTable.getContainerDataSource().getItem( selected )
		                .getItemProperty( "room" ) );
		    else
		        Notification.show( "Nothing selected" );
		});
		dataTable.setColumnOrder( "room", "code" );
		dataTable.setStyleName( "data-grid" );
	}
	
	private MyGrid generateGrid( List<Paint> data )
	{
		Label[] headers = { new Label( "<strong>Room</strong>", ContentMode.HTML ),
							new Label( "<strong>Colour code</strong>", ContentMode.HTML ) };
		grid = new MyGrid( "paint" );
		grid.addRow( 
				new MyGridRow( "header", 
					new MyGridColumn( "left", headers[0] ),
					new MyGridColumn( "right", headers[1] ) 
				) 
			);
		for ( Paint p : data )
		{
			grid.addRow( 
					new MyGridRow( 
						new MyGridColumn( "left", new Label( p.getRoom() ) ),
						new MyGridColumn( "right", new Label( p.getCode() ) ),
						new MyGridColumn( "right", udColumn( p ) )
					)
				);
		}
		return grid;
	}

	private Button generateAddButton()
	{
		Button button = new Button( "Add new paint" );
		button.addClickListener( new AddClickListener() );
		return button;
	}
	
	private CssLayout udColumn( Paint data )
	{
		CssLayout layout = new CssLayout();
		layout.setStyleName( "ud-column" );
		layout.addComponents( editButton( data ), deleteButton( data ) );
		return layout;
	}

	private Button editButton( Paint data )
	{
		Button edit = new Button( FontAwesome.EDIT.getHtml() );
		edit.setCaptionAsHtml( true );
		edit.addClickListener( new UpdateClickListener( data ) );
		edit.setStyleName( "edit-button" );
		return edit;
	}


	private Button deleteButton( Paint data )
	{
		// TODO: need to merge element then delete
		Button delete = new Button( FontAwesome.REMOVE.getHtml() );
		delete.setCaptionAsHtml( true );
//		delete.addClickListener( e -> painter.delete( data ) );
		delete.setStyleName( "delete-button" );
		return delete;
	}
	
	class AddClickListener implements ClickListener
	{
		private static final long serialVersionUID = 2627674955189604177L;

		@Override
		public void buttonClick(Button.ClickEvent event) {
			Window w = new Window();
			w.setResizable( false );
			w.setClosable( false );
			w.center();
			w.setWidth( 150, Unit.PIXELS );
			w.setModal( true );

			VerticalLayout v = new VerticalLayout();
			v.setSpacing( true );
			v.setMargin( true );

			HorizontalLayout h = new HorizontalLayout();

			TextField r = new TextField( "Room" );

			TextField c = new TextField( "Colour code" );

			Button cancel = new Button( "Cancel" );
			cancel.addClickListener( e -> w.close() );
			Button b = new Button( "Add" );
			b.addClickListener( ce -> {
//				painter.save( new Paint( r.getValue(), c.getValue() ) );
				grid.addRow(
						new MyGridRow(
								new MyGridColumn( "left", new Label( r.getValue() ) ),
								new MyGridColumn( "right", new Label( c.getValue() ) )
						)
				);
				w.close();
			});
			h.addComponents( b, cancel );
			v.addComponents( r, c, h );
			w.setContent( v );
			UI.getCurrent().addWindow( w );
		}
	}

	class UpdateClickListener implements Button.ClickListener
	{
		private static final long serialVersionUID = 8594298821996170068L;
		private Paint data;
		public UpdateClickListener( Paint data )
		{
			this.data = data;
		}
		
		@Override
		public void buttonClick(Button.ClickEvent event) {
			Window w = new Window();
			w.setResizable( false );
			w.setClosable( false );
			w.center();
			w.setWidth( 150, Unit.PIXELS );
			w.setModal( true );

			VerticalLayout v = new VerticalLayout();
			v.setSpacing( true );
			v.setMargin( true );

			HorizontalLayout h = new HorizontalLayout();

			TextField colour = new TextField( data.getRoom() );
			colour.setValue( data.getCode() );
			colour.setTextChangeEventMode( AbstractTextField.TextChangeEventMode.LAZY );

			Button save = new Button( "Save" );
			save.addClickListener( se -> {
				data.setCode( colour.getValue() );
//				painter.save( data );
				w.close();
			});

			Button cancel = new Button( "Cancel" );
			cancel.addClickListener( ce -> w.close() );

			h.addComponents( save, cancel );
			v.addComponents( colour, h );
			w.setContent( v );
			UI.getCurrent().addWindow( w );
		}
	}

}
