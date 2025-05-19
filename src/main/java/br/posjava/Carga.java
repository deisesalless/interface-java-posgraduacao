package br.posjava;

public final class Carga extends Veiculo implements Calcular {
    private int cargaMax;
    private int tara;

    public Carga() {
        super();
        this.cargaMax = 0;
        this.tara = 0;
    }

    public int getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getTara() {
        return tara;
    }

    public final void setTara(int tara) {
        this.tara = tara;
    }

    @Override
    public float calcVel(float velocMax) {
        return velocMax * 100000;
    }

    @Override
    public int calcular() {
        return (int) super.getVelocMax() + super.getQtdRodas() + super.getMotor().getPotencia() + super.getMotor().getQtdPist() + getCargaMax() + getTara();
    }

    @Override
    public String toString() {
        return "Carga{" +
                "placa='" + getPlaca() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", cor='" + getCor() + '\'' +
                ", velocidade máxima=" + getVelocMax() + " km/h" +
                ", quantidade de rodas=" + getQtdRodas() +
                ", motor: quantidade de pistões=" + getMotor().getQtdPist() +
                ", potência=" + getMotor().getPotencia() +
                ", carga máxima=" + getCargaMax() +
                ", tara=" + getTara() +
                ", valor total nos atributos numericos=" + calcular() +
                ", velocidade máxima em cm/h=" + calcVel(getVelocMax()) +
                '}';
    }

}
