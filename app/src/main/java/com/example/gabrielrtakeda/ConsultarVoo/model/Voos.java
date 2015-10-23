package com.example.gabrielrtakeda.ConsultarVoo.model;

import java.io.Serializable;

/**
 * Created by gabrielrtakeda on 11/09/2015.
 */
public class Voos implements Comparable<Voos>, Serializable{
    private String nome;
    private String origem;
    private String destino;
    private String imagem;
    private String data;
    private String horario;
    private String companhia;
    private double preco;
    public static final String NAO_ENCONTRADA = "Não encontrada.";

    public Voos(String nome, String origem, String destino, String imagem, String data, String horario, String companhia, double preco) {
        this.nome = nome;
        this.origem = origem;
        this.destino = destino;
        this.imagem = imagem;
        this.data = data;
        this.horario = horario;
        this.companhia = companhia;
        this.preco = preco;
    }

    public String getNome() {return nome;}

    public String getOrigem() {return origem;}

    public String getDestino() {return destino;}

    public String getImagem() {return imagem;}

    public String getData() {return data;}

    public String getHorario() {return horario;}

    public String getCompanhia() {return companhia;}

    public double getPreco() {return preco;}


    public String toString() {
        return "Voos{" +
                "nome='" + nome + '\'' +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", imagem='" + imagem + '\'' +
                ", data='" + data + '\'' +
                ", horario='" + horario + '\'' +
                ", companhia='" + companhia + '\'' +
                ", preco='" + preco + '\'' +
                '}';
    }

    public int compareTo(Voos voos) {
        if (nome.equals(voos.getNome())
                && origem.equals(voos.getOrigem())
                && destino.equals(voos.getDestino())) {
            return 0;
        }
        return this.getNome().compareTo(voos.getNome());
    }
}
