package Atp;

import java.io.*;
import java.util.ArrayList;

public class MascotesApp {
    private ArrayList<Mascote> mascotes;

    public MascotesApp () {
        mascotes = new ArrayList<>();
    }

    public void addMascote(Mascote mascote) {
        mascotes.add(mascote);
    }

    public void listMascote() {
        for(Mascote masc: mascotes) {
            if (masc != null) {
                System.out.println(masc.toString());
            }
        }
        System.out.println("Total de " + mascotes.size() + " encontrados na pesquisa.");
    }

    public void removeMascote(Mascote mascote) {
        if (mascotes.contains(mascote)) {
            mascotes.remove(mascote);
            System.out.println("Animal " + mascote.getNome() + " excluído com sucesso!\n");
        }
        else
            System.out.println("Animal inexistente!\n");
    }

    public void clearMascote() {
        mascotes.clear();
        System.out.println("Lista limpa com sucesso!\n");
    }

    public void saveMascote()  {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\mascotes.dat"));
            for(Mascote mascote: mascotes) {
                outputStream.writeObject(mascote);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (outputStream != null ) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void recoverMascote() {
        ObjectInputStream inputStream = null;
        try {
            inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\mascotes.dat"));
            Object obj = null;
            while((obj = inputStream.readObject()) != null) {
                if (obj instanceof Peixe)
                    mascotes.add((Peixe) obj);
                else if (obj instanceof Papagaio)
                    mascotes.add((Papagaio)obj);
            }
        }catch (EOFException ex) {
            System.out.println ("Fim do arquivo!");
        }catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            try {
                if (inputStream != null ) {
                    inputStream.close();
                    System.out.println("Mascotes listados com sucesso!\n");
                }
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MascotesApp masc1 = new MascotesApp();
        Peixe nemo = new Peixe("Nemo", 12, "João");
        Peixe willy = new Peixe("Willy", 8, "Carlos");
        Papagaio paulie = new Papagaio("Paulie", 9, "Misha");
        Papagaio louroJose = new Papagaio("Louro José", 20, "Ana Maria Braga");

        masc1.addMascote(nemo);
        masc1.addMascote(willy);
        masc1.addMascote(paulie);
        masc1.addMascote(louroJose);

        masc1.listMascote();
        masc1.saveMascote();
        masc1.removeMascote(louroJose);
        masc1.listMascote();

        masc1.clearMascote();
        masc1.listMascote();
        masc1.recoverMascote();
        masc1.listMascote();
    }
}
