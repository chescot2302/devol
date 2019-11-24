package com.devol.client.view.uiregistrarusuario;

import java.util.Date;

import com.devol.client.beanproxy.UsuarioProxy;
import com.devol.client.requestfactory.ContextGestionUsuario;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.util.Notification;
import com.devol.client.util.PopupProgress;
import com.devol.client.view.uihome.UIHome;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class UIRegistrarUsuarioImpl extends UIRegistrarUsuario {
	PopupProgress popup = new PopupProgress();
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private UIHome uiHome;

	public UIRegistrarUsuarioImpl(UIHome uiHome){
		this.uiHome = uiHome;
		this.scrollPanel.refresh();				
	}
	
	@Override
	public void registrar() {
		popup.showPopup();
		// TODO Auto-generated method stub
		Date fecha = new Date();
		ContextGestionUsuario context = FACTORY.contextGestionUsuario();
		FACTORY.initialize(EVENTBUS);
		UsuarioProxy bean = context.create(UsuarioProxy.class);
		bean.setOperacion("I");
		bean.setVersion(fecha.getTime());
		bean.setIdCreateUsuario(txtCorreo.getText());
		bean.setNombres(txtNombre.getText());
		bean.setApellidos(txtApellido.getText());
		bean.setCorreo(txtCorreo.getText());
		bean.setClave(txtClave.getText());
		bean.setIsRolOwner(true);
		Request<Boolean> request = context.insertarUsuario(bean);
		request.fire(new Receiver<Boolean>() {
			
			@Override
			public void onFailure(ServerFailure error) {
				//Dialogs.alert(constants.usuario(),error.getMessage(),null);
				//Window.alert(error.getMessage());
				popup.hidePopup();
				Notification not=new Notification(Notification.ALERT,error.getMessage());
				not.showPopup();
			  }
			
			@Override
			public void onSuccess(Boolean response) {
				// TODO Auto-generated method stub
				if (response) {
					cleanForm();
					// Window.alert("Registrado Correctamente");
					//Dialogs.alert(constants.usuario(),constants.cuentaRegistrada(), null);
					//Window.alert(constants.cuentaRegistrada());
					Notification not=new Notification(Notification.INFORMATION,constants.cuentaRegistrada());
					not.showPopup();
				}
				popup.hidePopup();
			}
		});
	}
	
	@Override
	public void irIniciarSesion() {
		// TODO Auto-generated method stub
		uiHome.getContainer().showWidget(0);
		uiHome.getUiIniciarSesionImpl().scrollPanel.refresh();
	}
	

}
