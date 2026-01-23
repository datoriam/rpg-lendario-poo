package com.rpgpoo.game;
import java.nio.channels.Pipe.SourceChannel;
import java.security.Guard;
import java.util.Scanner;

public abstract class Combatente {
    private String nome;
    private int vidaTotal;
    private int vidaAtual;
    private int nivel;
    private int dano;
    private int xp;
    private boolean dormindo = false;
    private boolean queimando = false;

    public Combatente (String nome, int vidaTotal, int danoBase) {
        this.nome = nome;
        this.vidaTotal = vidaTotal;
        this.vidaAtual = vidaTotal;
        this.nivel = 1;
        this.dano = danoBase;
        this.xp = 0;
    }

    public abstract void atacar(Combatente alvo);
    protected abstract void evoluirStats();


    public String getNome(){return nome;}
    public int getDano(){return dano;}
    public int getNivel(){return nivel;}
    public int getVidaAtual(){return vidaAtual;}
    public int getVidaTotal(){return vidaTotal;}

    public static Combatente criarPersonagem(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome do seu personagem: ");
        String nomePersonagem = sc.nextLine();

        System.out.println("Escolha uma classe: ");
        System.out.println("1 - Arcanista ");
        //System.out.println("2 - Guardião ");
        int escolha = sc.nextInt();

        Combatente personagem = null;

        switch(escolha){
            case 1:
                personagem = new Arcanista(nomePersonagem);
                break;
            /*case 2:
                personagem = new Guardiao(nomePersonagem);
                break;*/
            default: System.out.println("Escolha inválida!");
        }
        return personagem;
    }

    public boolean checaVida(){
        return vidaAtual > 0;
    }

    public void statusVida(){
        if (vidaAtual <= 0){
            vidaAtual = 0;
            System.out.println(this.nome + " está fora de combate!");
        }
        else{
            System.out.println(nome + " tem " + vidaAtual + " de vida!");
        }
    }

    public void receberDano(int danoRecebido){
        vidaAtual -= danoRecebido;
        System.out.println(this.nome + " recebeu " + danoRecebido + " de dano!");
        statusVida();
    }
    //Método para o Arcanista infligir nos inimigos sono
    public void aplicarSono() {
        this.dormindo = true;
        System.out.println( " Shiuu" + this.nome + " Está dormindo, ele ficará uma rodada sem jogar.");
    }

    public void queimarInimigo() {
        this.queimando = true;
        System.out.println(this.nome + " começou a pegar fogo!!");
    }

    //Método para a arena checar antes do turno iniciar se ele pode ou não atacar
    public boolean processaStatus() {
        //primeiro vai verificar se está pegando fogo
        if (this.queimando) {
            int danoFogo = 5;
            this.receberDano(danoFogo);
            System.out.println(this.nome + " nesta rodada irá receber" + danoFogo + " por queimadura");
        } 
        // verifica se está dormindo
        if (this.dormindo) {
            System.out.println(this.nome + " está dormindo e perde a vez");
            this.dormindo = false;
            return false;
        }

        return true;
    }

    public void ganharXP(int quantidade){
        this.xp += quantidade;
        System.out.println(this.nome + " ganhou " + quantidade + " de XP!");

        if(xp >= 100){
            this.xp = this.xp - 100;
            subirNivel();
        }
    }
    protected void atualizaAtributos(int aumentaDano, int aumentaVida){
        this.dano += aumentaDano;
        this.vidaTotal += aumentaVida;
        vidaAtual = vidaTotal;
    }

    public void subirNivel(){
        nivel++;
        vidaAtual = vidaTotal;
        System.out.println(this.nome + " subiu para o nível " + this.nivel);
        evoluirStats();
    }
}
