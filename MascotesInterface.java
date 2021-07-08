package Atp;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class MascotesInterface {
    private ArrayList<Mascote> mascotes;

    public MascotesInterface() {
        mascotes = new ArrayList<>();
    }

    public String[] leValores(String[] dadosIn) {
        String[] dadosOut = new String[dadosIn.length];

        for (int i = 0; i < dadosIn.length; i++)
            dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");
        return dadosOut;
    }

    public Peixe lePeixe() {
        String [] valores;
        String [] nomeVal = {"Nome", "Idade", "Dono"};
        valores = leValores (nomeVal);

        int idade = this.retornaInteiro(valores[1]);

        Peixe peixe = new Peixe (valores[0],idade,valores[2]);
        return peixe;
    }

    public Papagaio lePapagaio() {
        String[] valores;
        String[] nomeVal = {"Nome", "Idade", "Dono"};
        valores = leValores (nomeVal);

        int idade = this.retornaInteiro(valores[1]);

        Papagaio papagaio = new Papagaio (valores[0],idade,valores[2]);
        return papagaio;
    }

    private boolean intValido(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int retornaInteiro(String entrada) {
        while (!intValido(entrada)) {
            JOptionPane.showConfirmDialog(null, "Opção Inválida!!", "ERRO", -1);
            menuMascotes();
            }
        return Integer.parseInt(entrada);
    }

    public void salvaMascotes(ArrayList<Mascote> mascotes) {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\mascotes.dados"));
            for (int i=0; i < mascotes.size(); i++)
                outputStream.writeObject(mascotes.get(i));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<Mascote> recuperaMascote () {
        ArrayList<Mascote> mascotesTemp = new ArrayList<>();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream (new FileInputStream("c:\\temp\\mascotes.dados"));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Mascote) {
                    mascotesTemp.add((Mascote) obj);
                }
            }
        } catch (EOFException ex) {
            System.out.println("Fim do arquivo.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Arquivo com mascotes NÃO existe!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return mascotesTemp;
        }
    }

    public void menuMascotes (){

        String menu = "";
        String entrada;
        int opc1, opc2;

        do {
            menu = "Menu de Mascotes\n" +
                    "Opções:\n" +
                    "1. Entrar mascotes\n" +
                    "2. Exibir mascotes\n" +
                    "3. Limpar mascotes\n" +
                    "4. Gravar mascotes\n" +
                    "5. Recuperar mascotes\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog (menu + "\n\n");
            opc1 = this.retornaInteiro(entrada);

            switch (opc1) {
                case 1:
                    menu = "Entrada de Mascotes\n" +
                            "Opções:\n" +
                            "1. Peixe\n" +
                            "2. Papagaio\n";

                    entrada = JOptionPane.showInputDialog (menu + "\n\n");
                    opc2 = this.retornaInteiro(entrada);

                    switch (opc2){
                        case 1: mascotes.add((Mascote) lePeixe());
                            break;
                        case 2: mascotes.add((Mascote) lePapagaio());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Animal mamífero para entrada NÃO escolhido!");
                    }

                    break;
                case 2:
                    if (mascotes.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com um mascote primeiramente");
                        break;
                    }
                    String dados = "";
                    for (int i=0; i < mascotes.size(); i++)	{
                        dados += mascotes.get(i).toString() + "---------------\n";
                    }
                    JOptionPane.showMessageDialog(null,dados);
                    break;
                case 3:
                    if (mascotes.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com um mascote primeiramente");
                        break;
                    }
                    mascotes.clear();
                    JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
                    break;
                case 4:
                    if (mascotes.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com um mascote primeiramente");
                        break;
                    }
                    salvaMascotes(mascotes);
                    JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
                    break;
                case 5:
                    mascotes = recuperaMascote();
                    if (mascotes.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null,"Fim do aplicativo Mascotes COMPANION");
                    System.exit(0);
                    break;
            }
        } while (opc1 != 9);
    }

    public static void main(String[] args) {
        MascotesInterface mascote = new MascotesInterface();
        mascote.menuMascotes();
    }
}
