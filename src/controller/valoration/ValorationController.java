package controller.valoration;

public class ValorationController {
    private final int numPreguntas;
    private final int[] respuestas;

    public ValorationController(int numPreguntas) {
        this.numPreguntas = numPreguntas;
        this.respuestas = new int[numPreguntas];
    }

    public void setRespuesta(int preguntaIdx, int valor) {
        if (preguntaIdx >= 0 && preguntaIdx < numPreguntas && valor >= 1 && valor <= 5) {
            respuestas[preguntaIdx] = valor;
        }
    }

    public int[] getRespuestas() {
        return respuestas.clone();
    }

    public boolean todasRespondidas() {
        for (int r : respuestas) {
            if (r == 0) return false;
        }
        return true;
    }

    // Aquí podrías añadir lógica para guardar o enviar la valoración
} 