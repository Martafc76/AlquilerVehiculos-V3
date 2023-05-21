package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BorrarCliente extends Controlador{
	
	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();
	
	@FXML
    private Button btBorrar;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btLimpiar;

    @FXML
    private ImageView ivIconoCliente;

    @FXML
    private Label lbDniCliente;

    @FXML
    private TextField tfDniCliente;

    @FXML
    void borrarCliente(ActionEvent event) {
    	try {
    		if(Dialogos.mostrarDialogoConfirmacion("Borra cliente", "¿Estás seguro de borrar este cliente?", getEscenario())) {
    			vistaGrafica.getControlador().borrar(vistaGrafica.getControlador().buscar(Cliente.getClienteConDni(tfDniCliente.getText())));
    			Dialogos.mostrarDialogoInformacion("Usuario borrado", "El usuario ha sido borrado correctamente", getEscenario());
    		}
			getEscenario().close();
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());
		}

    }

    @FXML
    void cancelarBorrarCliente(ActionEvent event) {
    	getEscenario().close();
    }

    @FXML
    void limpiarBorrarCliente(ActionEvent event) {
    	Controles.limpiarCamposTexto(tfDniCliente);
    }
    
    @FXML
    void initialize() {
    	
    }

}
