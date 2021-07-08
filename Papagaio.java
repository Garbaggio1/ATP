package Atp;

public class Papagaio extends Mascote {
    private static final long serialVersionUID = 1L;

    public Papagaio(String nome, int idade, String dono) {
        super(nome, idade, dono);
        this.especie = "Papagaio-de-cara-roxa";
    }

    @Override
    public String acao() {
        return "Imitar";
    }
}
