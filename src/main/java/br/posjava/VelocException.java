package br.posjava;

public class VelocException extends Exception {
    public VelocException() {
        super("A velocidade máxima está fora dos limites brasileiros");
    }
}
