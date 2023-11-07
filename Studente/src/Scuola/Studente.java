package Scuola;

import jdk.jshell.execution.LoaderDelegate;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;

public class Studente {
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private static int codiceMatricola = 0;
    private int matricola;
    private String corsoDiStudi;
    private float[] voti;

    public Studente(String nome, String cognome, LocalDate dataNascita, String corsoDiStudi) {
        setNome(nome);
        setCognome(cognome);
        this.dataNascita = dataNascita;
        setMatricola();
        setCorsoDiStudi(corsoDiStudi);
        this.voti = new float[10];
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    private void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getMatricola() {
        return matricola;
    }

    private void setMatricola() {
        this.matricola = codiceMatricola;
        codiceMatricola++;
    }

    public String getCorsoDiStudi() {
        return corsoDiStudi;
    }

    private void setCorsoDiStudi(String corsoDiStudi) {
        this.corsoDiStudi = corsoDiStudi;
    }

    public void AggiungiVoto(float voto) {
        for (int i = 0; i < 10; i++)
            if (voti[i] == 0) {
                voti[i] = voto;
                break;
            }
    }

    public void RimuoviVoti() {
        for (int i = 0; i < 10; i++)
            voti[i] = 0;
    }

    @Override
    public String toString() {
        return  "----------------------------------------------------------------\n"+
                "Nome: " + nome + '\n' +
                "Cognome: " + cognome + '\n' +
                "Data di nascita: " + dataNascita + '\n' +
                "Matricola: " + matricola + '\n' +
                "Corso di Studi: " + corsoDiStudi + '\n' +
                "Voti: " + Arrays.toString(voti) + '\n' +
        "----------------------------------------------------------------\n";
    }

    public float CalcolaMediaVoti() {
        int counter = 0;
        float somma = 0;
        for (int i = 0; i < 10; i++)
            if (voti[i] != 0) {
                counter++;
                somma += voti[i];
            }
        return somma / counter;
    }

    public int CalcolaEta() {
        return Period.between(dataNascita, LocalDate.now()).getYears();
    }
}
