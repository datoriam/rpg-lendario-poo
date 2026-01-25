package com.rpgpoo.game.entity;

public abstract class Combatente {
    private String mensagem;
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
        this.nivel = 5;
        this.dano = danoBase;
        this.xp = 0;
        this.mensagem = ""; // Inicializa mensagem vazia
    }

    public void atacar(Combatente alvo){
        // Limpa mensagem antes de cada ataque
        setMensagem("");

        String mensagemAtaque = getNome() + " atacou e causou " + getDano() + " de dano";
        setMensagem(mensagemAtaque);
        alvo.receberDano(getDano());
    }

    protected void evoluirStats(){};

    public void setMensagem(String msg){
        if (this.mensagem == null || this.mensagem.isEmpty()) {
            this.mensagem = msg;
        } else {
            this.mensagem += "\n" + msg;
        }
    }

    // Novo método para limpar mensagens
    public void limparMensagem() {
        this.mensagem = "";
    }

    public String getMensagem() {
        String msg = mensagem;
        mensagem = ""; // Limpa após pegar (opcional)
        return msg;
    }

    // Resto dos métodos permanece igual...
    public String getNome(){return nome;}
    public int getDano(){return dano;}
    public int getNivel(){return nivel;}
    public int getVidaAtual(){return vidaAtual;}
    public int getVidaTotal(){return vidaTotal;}

    public boolean checaVida(){
        return vidaAtual > 0;
    }

    public void statusVida(){
        if (vidaAtual <= 0){
            vidaAtual = 0;
            setMensagem(this.nome + " está fora de combate!");
        }
        else{
            setMensagem(nome + " tem " + vidaAtual + " de vida!");
        }
    }

    public void receberDano(int danoRecebido){
        vidaAtual -= danoRecebido;
        setMensagem(this.nome + " recebeu " + danoRecebido + " de dano!");
        statusVida();
    }

    public void ganharXP(int quantidade){
        this.xp += quantidade;
        setMensagem(this.nome + " ganhou " + quantidade + " de XP!");

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

    public void aplicarSono() {
        this.dormindo = true;
        setMensagem("Shiuu! " + this.nome + " está dormindo, ele ficará uma rodada sem jogar.");
    }

    public void queimarInimigo() {
        this.queimando = true;
        setMensagem(this.nome + " começou a pegar fogo!!");
    }

    public boolean processaStatus() {
        boolean podeAtacar = true;

        if (this.queimando) {
            int danoFogo = 5;
            this.receberDano(danoFogo);
            setMensagem(this.nome + " recebe " + danoFogo + " de dano por queimadura!");
        }

        if (this.dormindo) {
            setMensagem(this.nome + " está dormindo e perde a vez");
            this.dormindo = false;
            podeAtacar = false;
        }

        return podeAtacar;
    }

    public void subirNivel(){
        nivel++;
        vidaAtual = vidaTotal;
        setMensagem(this.nome + " subiu para o nível " + this.nivel);
        evoluirStats();
    }
}
