package com.rpgpoo.game;

public abstract class Combatente {
    private String nome;
    private int vidaTotal;
    private int vidaAtual;
    private int nivel;
    private int dano;

    public Combatente (String nome, int vidaTotal) {
        this.nome = nome;
        this.vidaTotal = vidaTotal;
        this.vidaAtual = vidaTotal;
        this.nivel = 1;
        this.dano = 10;
    }

    abstract void atacar(Combatente alvo);

    public String getNome(){return nome;}
    public int getDano(){return dano;}
    public int getVidaAtual(){return vidaAtual;}
    public int getVidaTotal(){return vidaTotal;}

    public boolean checaVida(){
        return vidaAtual > 0;
    }

    void statusVida(){
        if (vidaAtual <= 0){
            vidaAtual = 0;
            System.out.println(this.nome + " está fora de combate!");
        }
        else{
            System.out.println(nome + " tem " + vidaAtual + " de vida!");
        }
    }

    void receberDano(int danoRecebido){
        vidaAtual -= danoRecebido;
        System.out.println(this.nome + " recebeu " + danoRecebido + " de dano!");
        statusVida();
    }

    void subirNivel(){
        nivel++;
        dano += 10;
        vidaTotal += 20;
        vidaAtual = vidaTotal;
        System.out.println(this.nome + " subiu para o nível " + this.nivel);
    }
}
