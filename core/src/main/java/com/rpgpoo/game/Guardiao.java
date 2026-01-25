package com.rpgpoo.game;

public class Guardiao extends Combatente {
    private int vigor = 50;
    private final int CUSTO_BLOQUEIO = 15;
    
    public Guardiao(String nome) {
        super(nome, 120, 10);
    }
    
    @Override
    public void atacar(Combatente alvo) {
        limparMensagem(); // Limpa antes do ataque
        setMensagem(getNome() + " ataca com martelo pesado!");
        alvo.receberDano(getDano());
        
        vigor = Math.min(vigor + 5, 50);
        setMensagem("Recuperou 5 de vigor [" + vigor + "/50]");
    }
    
    @Override
    public void receberDano(int danoRecebido) {
        limparMensagem(); // Limpa antes de receber dano
        
        if (vigor >= CUSTO_BLOQUEIO) {
            // BLOQUEIO - mensagem IMEDIATA
            setMensagem(getNome() + " BLOQUEIA o ataque com escudo!");
            setMensagem("Dano " + danoRecebido + " → 0");
            
            vigor -= CUSTO_BLOQUEIO;
            setMensagem("Consumiu " + CUSTO_BLOQUEIO + " de vigor [" + vigor + "/50]");
        } else {
            // SEM BLOQUEIO - dano normal
            setMensagem(getNome() + " não tem vigor para bloquear!");
            super.receberDano(danoRecebido);
        }
    }
    
    @Override
    protected void evoluirStats() {
        super.atualizaAtributos(3, 30);
        vigor = 50;
        setMensagem("Vigor restaurado para 50!");
    }
    
    @Override
    public boolean processaStatus() {
        boolean podeAtacar = super.processaStatus();
        
        if (podeAtacar) {
            int recuperacao = 3;
            int vigorAntes = vigor;
            vigor = Math.min(vigor + recuperacao, 50);
            
            if (vigorAntes < 50 && vigor > vigorAntes) {
                setMensagem("🔄 " + getNome() + " recupera " + (vigor - vigorAntes) + " de vigor passivamente");
            }
        }
        
        return podeAtacar;
    }
    
    public int getVigor() { return vigor; }
}