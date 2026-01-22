import com.rpgpoo.game.Arcanista;
/*Aqueles que desejarem testar suas subclasses antes de levá-las para a parte gráfica, sintam-se a vontade em realizar
* os testes nesse arquivo. Basta instanciar a subclasse desejada e simular uma batalha de turnos e cada método!*/
public class Teste {
    public static void main(String[] args){
        Arcanista heroi = new Arcanista("maguin da luz");
        Arcanista inimigo = new Arcanista("maguin das trevas");

        System.out.println("Get Ready For The Next Battle!");
        System.out.println("Do lado da luz, ele com " + heroi.getVidaAtual() + " de vida. O imparável: " + heroi.getNome()  + "!");
        System.out.println("Do lado das trevas, ele com " + inimigo.getVidaAtual() + " de vida. O abominável: " + inimigo.getNome()  + "!");
        System.out.println("Comecem!");
        heroi.atacar(inimigo);
        inimigo.atacar(heroi);
    }
}
