package br.posjava;

public class VeicExistException extends Exception {
    public VeicExistException() {
        super("Já existe um veículo com esta placa");
    }
}
