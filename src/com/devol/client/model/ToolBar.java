package com.devol.client.model;

import com.devol.client.resource.MyResource;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class ToolBar extends HeaderMenu {
	private HorizontalPanel toolbar;
	private PushButton btnAtras;
	private PushButton btnNuevo;
	private PushButton btnEditar;
	private PushButton btnEliminar;
	private PushButton btnActualizar;
	private PushButton btnExportar;

	public ToolBar() {
		init();
		style();
	}

	private void init() {
		toolbar=new HorizontalPanel();
		btnAtras = new PushButton(new Image(MyResource.INSTANCE.getImgBack32()));
		btnAtras.setTitle("Atras");
		btnAtras.setVisible(false);
		toolbar.add(btnAtras);
		
		btnNuevo = new PushButton(new Image(MyResource.INSTANCE.getImgNew32()));
		btnNuevo.setTitle("Nuevo");
		toolbar.add(btnNuevo);

		btnEditar = new PushButton(new Image(MyResource.INSTANCE.getImgEdit32()));
		btnEditar.setTitle("Editar");
		toolbar.add(btnEditar);

		btnEliminar = new PushButton(
				new Image(MyResource.INSTANCE.getImgRemove32()));
		btnEliminar.setTitle("Eliminar");
		toolbar.add(btnEliminar);

		btnActualizar = new PushButton(
				new Image(MyResource.INSTANCE.getImgRefresh32()));
		btnActualizar.setTitle("Actualizar");
		toolbar.add(btnActualizar);
		
		btnExportar = new PushButton(
				new Image(MyResource.INSTANCE.getImgExportar32()));
		btnExportar.setTitle("Exportar");
		toolbar.add(btnExportar);		
		setLeftWidget(toolbar);
		
		
	}

	private void style() {
		/*MyResource.INSTANCE.getStlModel().ensureInjected();
		addStyleName(MyResource.INSTANCE.getStlModel().mainToolBar());
		setWidth("100%");
		toolbar.getElement().getStyle().setDisplay(Style.Display.INLINE_BLOCK);

		btnNuevo.getElement().getStyle().setFloat(Style.Float.LEFT);
		btnEditar.getElement().getStyle().setFloat(Style.Float.LEFT);
		btnEliminar.getElement().getStyle().setFloat(Style.Float.LEFT);
		btnActualizar.getElement().getStyle().setFloat(Style.Float.LEFT);*/
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		btnNuevo.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
		btnEditar.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
		btnEliminar.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
		btnActualizar.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
		btnExportar.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
		btnAtras.addStyleName(MyResource.INSTANCE.getStlUIHome().pushButton());
	}

	public PushButton getBtnNuevo() {
		return btnNuevo;
	}

	public PushButton getBtnEditar() {	
		return btnEditar;
	}

	public PushButton getBtnEliminar() {
		return btnEliminar;
	}

	public PushButton getBtnActualizar() {
		return btnActualizar;
	}

	public HorizontalPanel getToolbar() {
		return toolbar;
	}

	public PushButton getBtnAtras() {
		return btnAtras;
	}

	public PushButton getBtnExportar() {
		return btnExportar;
	}
	
	
	

}
