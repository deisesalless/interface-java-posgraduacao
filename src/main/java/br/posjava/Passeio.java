package br.posjava;

public final class Passeio extends Veiculo implements Calcular {
    private int qtdPassageiros;

    public Passeio() {
        super();
        this.qtdPassageiros = 0;
    }

    public int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 1000;
    }

    @Override
    public int calcular() {
        return super.getPlaca().length() + super.getMarca().length() + super.getModelo().length() + super.getCor().length();
    }

    @Override
    public String toString() {
        return "Passeio{" +
                "placa='" + getPlaca() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", cor='" + getCor() + '\'' +
                ", velocidade máxima=" + getVelocMax() + " km/h" +
                ", quantidade de rodas=" + getQtdRodas() +
                ", motor: quantidade de pistões=" + getMotor().getQtdPist() +
                ", potência=" + getMotor().getPotencia() +
                ", quantidade de passageiros=" + getQtdPassageiros() +
                ", quantidade total de letras=" + calcular() +
                ", velocidade máxima em m/h=" + calcVel(getVelocMax()) +
                '}';
    }
}
