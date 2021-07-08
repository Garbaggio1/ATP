package Atp;

public class Peixe extends Mascote {
    private static final long serialVersionUID = 1L;

    public Peixe (String nome, int idade, String dono) {
        super(nome, idade, dono);
        this.especie = "Peixe-dourado";
    }

    @Override
    public String acao() {
        return "Nadar";
    }
}
