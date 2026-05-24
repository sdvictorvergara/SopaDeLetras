package Controlador;

import Modelo.ModeloSopa;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorSopa implements ActionListener {

    private VentanaPrincipal vistaSopa;
    private ModeloSopa modeloSopa;

    public ControladorSopa(VentanaPrincipal vistaSopa, ModeloSopa modeloSopa) {
        this.vistaSopa = vistaSopa;
        this.modeloSopa = modeloSopa;
        iniciarEventosBotones();
    }

    private void iniciarEventosBotones() {
        vistaSopa.getBAñadir().addActionListener(this);
        vistaSopa.getBEliminar().addActionListener(this);
        vistaSopa.getBConsultarPalabras().addActionListener(this);
        vistaSopa.getBGenerarSopaLetras().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        Object botonPulsado = evento.getSource();

        if (botonPulsado == vistaSopa.getBAñadir()) {
            añadirPalabraBaseDatos();

        } else if (botonPulsado == vistaSopa.getBEliminar()) {
            eliminarPalabraBaseDatos();

        } else if (botonPulsado == vistaSopa.getBConsultarPalabras()) {
            mostrarPalabrasGuardadas();

        } else if (botonPulsado == vistaSopa.getBGenerarSopaLetras()) {
            generarSopaLetras();
        }
    }

    private void añadirPalabraBaseDatos() {
        String palabraIntroducida = vistaSopa.getTfPalabra().getText().trim();

        if (!palabraValida(palabraIntroducida)) {
            return;
        }

        boolean palabraInsertada = modeloSopa.insertarPalabra(palabraIntroducida);

        if (palabraInsertada) {
            JOptionPane.showMessageDialog(vistaSopa, "Palabra añadida correctamente");
            vistaSopa.getTfPalabra().setText("");
            mostrarPalabrasGuardadas();
        } else {
            JOptionPane.showMessageDialog(vistaSopa, "No se pudo añadir la palabra. Puede que ya exista.");
        }
    }

    private boolean palabraValida(String palabraIntroducida) {

        if (palabraIntroducida.isEmpty()) {
            JOptionPane.showMessageDialog(vistaSopa, "Introduce una palabra");
            return false;
        }

        if (palabraIntroducida.length() > 20) {
            JOptionPane.showMessageDialog(vistaSopa, "La palabra supera el tamaño máximo permitido: 20 letras");
            vistaSopa.getTfPalabra().setText("");
            return false;
        }

        if (palabraIntroducida.contains(" ")) {
            JOptionPane.showMessageDialog(vistaSopa, "Las palabras no pueden contener espacios");
            vistaSopa.getTfPalabra().setText("");
            return false;
        }

        return true;
    }

    private void eliminarPalabraBaseDatos() {
        String palabraIntroducida = vistaSopa.getTfPalabra().getText().trim();

        if (palabraIntroducida.isEmpty()) {
            JOptionPane.showMessageDialog(vistaSopa, "Introduce una palabra para eliminar");
            return;
        }

        boolean palabraEliminada = modeloSopa.eliminarPalabra(palabraIntroducida);

        if (palabraEliminada) {
            JOptionPane.showMessageDialog(vistaSopa, "Palabra eliminada correctamente");
            vistaSopa.getTfPalabra().setText("");
            mostrarPalabrasGuardadas();
        } else {
            JOptionPane.showMessageDialog(vistaSopa, "La palabra no existe");
        }
    }

    private void mostrarPalabrasGuardadas() {
        String textoPalabrasGuardadas = modeloSopa.consultarPalabrasTexto();
        vistaSopa.getTaConsultarPalabras().setText(textoPalabrasGuardadas);
    }

    private void generarSopaLetras() {
        SopaLetras generadorSopaLetras = new SopaLetras();
        String textoPalabras = modeloSopa.consultarPalabrasTexto();

        if (textoPalabras.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vistaSopa, "No hay palabras en la base de datos");
            return;
        }

        String palabrasSeparadasPorComas = textoPalabras.replace("\n", ",");
        String sopaLetrasGenerada = generadorSopaLetras.generarSopa(palabrasSeparadasPorComas);

        vistaSopa.getTaSopaLetras().setText(sopaLetrasGenerada);
    }
}