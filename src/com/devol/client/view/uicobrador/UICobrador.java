package com.devol.client.view.uicobrador;

import com.devol.client.grid.GridCobrador;
import com.devol.client.model.HeaderMenu;
import com.devol.client.model.ToolBar;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.googlecode.mgwt.ui.client.widget.input.search.MSearchBox;

public class UICobrador extends Composite implements InterUICobrador, ValueChangeHandler, ClickHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private FlowPanel main;
	private MSearchBox txtBuscar;
	protected HeaderMenu header;
	private Label lblCenter;
	protected ToolBar toolBar;
	protected GridCobrador grid;
	protected FlowPanel container;
	private PushButton btnBack;
	private PushButton btnSelect;
	private PushButton btnAsignarClientes;
	// private WidgetList widgetListTable;

	public UICobrador() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {
		// widgetListTable=new WidgetList();
		// widgetListTable.setRound(true);
		main = new FlowPanel();

		// Window.addResizeHandler(this);
		header = new HeaderMenu();
		lblCenter = new Label(constants.seleccionar());
		header.setCenterWidget(lblCenter);
		btnBack = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
		header.setLeftWidget(btnBack);
		btnSelect = new PushButton(new Image(MyResource.INSTANCE.getImgSelect32()));
		header.setRightWidget(btnSelect);
		main.add(header);
		toolBar = new ToolBar();	
		toolBar.getBtnEditar().getUpFace().setImage(new Image(MyResource.INSTANCE.getImgDesactivar32()));
		toolBar.getBtnEditar().setTitle("Desactivar");
		btnAsignarClientes=new PushButton(new Image(MyResource.INSTANCE.getImgGroupClientes32()));
		btnAsignarClientes.setTitle("Asignar Clientes");
		toolBar.getToolbar().add(btnAsignarClientes);
		main.add(toolBar);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder(constants.buscarCobrador());
		main.add(txtBuscar);

		container = new FlowPanel();
		grid = new GridCobrador();
		container.add(grid);
		main.add(container);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}
		});
		initWidget(main);
	}

	private void reCalcularWindows() {
		setWidthGrid();
		setHeightContainer(180);
	}

	@SuppressWarnings("unchecked")
	private void initWidgetListener() {
		txtBuscar.addValueChangeHandler(this);
		toolBar.getBtnNuevo().addClickHandler(this);
		toolBar.getBtnEditar().addClickHandler(this);		
		toolBar.getBtnEliminar().setVisible(false);
		toolBar.getBtnActualizar().addClickHandler(this);
		toolBar.getBtnExportar().addClickHandler(this);
		btnSelect.addClickHandler(this);
		btnBack.addClickHandler(this);
		btnAsignarClientes.addClickHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUICliente().ensureInjected();
		btnSelect.addStyleName(MyResource.INSTANCE.getStlUICliente().pushButton());
		btnBack.addStyleName(MyResource.INSTANCE.getStlUICliente().pushButton());
		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUICliente().txtBuscarUICliente());
		grid.addStyleName(MyResource.INSTANCE.getStlUICliente().gridUICliente());
		btnAsignarClientes.getElement().getStyle().setFloat(Style.Float.RIGHT);
		btnAsignarClientes.getElement().getStyle().setRight(4, Unit.PX);
		btnAsignarClientes.addStyleName(MyResource.INSTANCE.getStlUIPrestamo().pushButton());

	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		grid.setHeight((height - heightHeader) + "px");
		container.setHeight((height - heightHeader) + "px");
		grid.redraw();
	}

	@Override
	public void onValueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		filtrar();
	}

	private void filtrar() {
		grid.getDataProvider().setFilter(txtBuscar.getText());
		if (grid.getDataProvider().hasFilter()) {
			int alto = grid.getDataProvider().resulted.size() * 15;
			// grid.setHeight(alto + "mm");
		} else {
			int alto = grid.getData().size() * 15;
			// grid.setHeight(alto + "mm");
		}
		grid.getDataProvider().refresh();
	}

	private void setWidthGrid() {
		int width = Window.getClientWidth();
		width = width - 20;
		grid.setWidth(width + "px");
	}

	@Override
	public void goToUICMantCobrador(String modo) {
		// TODO Auto-generated method stub
	}

	@Override
	public void cargarTabla() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goToBack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selecciona() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(toolBar.getBtnNuevo())) {
			// Window.alert("ok");
			goToUICMantCobrador(constants.modoNuevo());
		} else if (event.getSource().equals(toolBar.getBtnEditar())) {
			goToUICMantCobrador(constants.modoDesactivar());
		} else if (event.getSource().equals(toolBar.getBtnEliminar())) {
			goToUICMantCobrador(constants.modoEliminar());
		} else if (event.getSource().equals(toolBar.getBtnActualizar())) {
			cargarTabla();
		} else if (event.getSource().equals(btnBack)) {
			goToBack();
		} else if (event.getSource().equals(btnSelect)) {
			selecciona();
		}else if (event.getSource().equals(btnAsignarClientes)) {
			goToUiCliente();
		}else if(event.getSource().equals(toolBar.getBtnExportar())){
			exportarData();
		}
	}

	@Override
	public void goToUiCliente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportarData() {
		// TODO Auto-generated method stub
		
	}

}
