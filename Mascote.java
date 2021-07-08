package Atp;

import java.io.Serializable;

public abstract class Mascote implements Serializable {

    private String nome, dono;
    protected String especie;
    private int idade;
    private static final long serialVersionUID = 1L;

    public Mascote(String nome, int idade, String dono) {
        this.nome = nome;
        this.idade = idade;
        this.dono = dono;
    }

    public String getNome() {
        return nome;
    }

    public abstract String acao();

    public String toString() {
        String retorno = "";
        retorno += "Nome: "     + this.nome     + "\n";
        retorno += "Idade: "    + this.idade    + " anos\n";
        retorno += "Dono: "     + this.dono     + "\n";
        retorno += "Especie: "  + this.especie  + "\n";
        retorno += "Habilidade: "  + acao()        + "\n";
        return retorno;
    }
}
