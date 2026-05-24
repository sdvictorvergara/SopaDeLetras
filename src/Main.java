import Modelo.ModeloSopa;
import Vista.VentanaPrincipal;
import Controlador.ControladorSopa;

public class Main {

    public static void main(String[] args) {

        ModeloSopa modeloSopa = new ModeloSopa();
        VentanaPrincipal vistaSopa = new VentanaPrincipal();

        ControladorSopa controladorSopa = new ControladorSopa(vistaSopa, modeloSopa);

        vistaSopa.setLocationRelativeTo(null);
        vistaSopa.setVisible(true);
    }
}