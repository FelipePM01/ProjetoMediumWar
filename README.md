
# Projeto Medium War

# Equipe
* Felipe Pacheco Manoel - 215743
* Cristiano Sampaio Pinheiro - 256352

# Descrição Resumida do Projeto
O projeto será um jogo em que cada jogador posicionará as peças de sua mão no tabuleiro e em seguida as peças atacarão as peças do inimigo por meio de um determinado compartamento atribuído ao tipo da peça. Cada peça terá atributos específicos como vida , dano e alcance de ataque. A temática do jogo será baseada em classes e criaturas de um RPG.

# Vídeo do Projeto
[Link do vídeo do projeto](https://drive.google.com/open?id=12WCLHbGfRfGYytHxSpNW-8KsJQpoNzBI)

# Apresentação do Projeto
[Link para apresentação do projeto](https://drive.google.com/open?id=1aigs8xozY3tbv9r1LsFobk-ZYNi4p2eJKla_YFFc_GY)

# Diagrama Geral de Componentes
![Diagrama Geral](diagramageral.png)

# Componente Jogador

![Componente do Jogador](componentejogador.png)

## Interfaces

Interfaces associadas ao componente Jogador:

![Diagrama Interfaces do Jogador](interfacejogador.png)

Campo | Valor
----- | -----
Classe | jogador.Jogador
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar cada um dos jogadores e suas relações com os outros componenetes
Interface | 
~~~
public interface IJogadorGame {
    public void addPontos(){};
    public void comprarPeca(int id){};
    public void venderPeca(int id){};
    public void uparPeca(int id){};
    public void receberGold(int quantia){};
    public void colocarPeca(int id,String posicao){};
}

~~~


## Detalhamento das Interfaces

### Interface Jogador
Essa interface e responsável por gerenciar as ações dos jogares e sua interação com os demais componentes do jogo.

Método | Objetivo
-------| --------
addPontos | Incrementa a pontuação do jogador que ganhou a rodada
comprarPeca | Permite o jogador realiza a compra de peças(Recebe a posição da peça no Banco de peças)
venderPeca | Permite o jogador realiza a venda de peças(Recebe a posição da peça na mão do jogador)
uparPeca | Possibilita melhora atributos da peça e a subir de nível
receberGold | Atualiza o gold dos jogadores após uma rodada(Recebe inteiro para indicar a quantia)
colocarPeca | Permite o jogador posicionar suas peças no tabuleiro(Recebe a posição(id) da peça na mão do jogador e a posição(String) que a peça ocuparar no tabuleiro)

# Componente Peca

![Componente da Peca](componentepeca.png)

## Interfaces

Interfaces associadas ao componente Peca:

![Diagrama Interfaces da Peca](interfacepeca.png)

Campo | Valor
----- | -----
Classe | peca.Peca
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar cada uma das Pecas presentes no jogo
Interface | 
~~~
public interface IPecaJogador {
    public void upNivel(){};
    public int getPrecoVenda(){};
    public int getPrecoCompra(){};
}
public interface IPecaPeca {
    public void receberDano(){};    
    public String getPosition(){};


}
public interface IPecaTabuleiro {
    public void moverOuAtacar(){};
    public String getPosition(){};


}
public interface IPecaJogador extends IPecaPeca,IPecaJogador,IPecaTabuleiro{};




~~~


## Detalhamento das Interfaces

### Interface IPecaJogador
Essa interface e responsável pela interação entre as peças e o jogador.

Método | Objetivo
-------| --------
upNivel | Incrementa atributos relacionados a ataque/velocidade/vida etc
getPrecoVenda | Retorna o valor de venda da peça
getPrecoCompra | Retorna o valor de compra da peça

### Interface IPecaPeca
Essa interface e responsável pelas ações realizadas sobre as peças.

Método | Objetivo
-------| --------
receberDano | Desconta valores retirados por um ataque
getPosition | Retorna a posição da peça no tabuleiro

### Interface IPecaTabuleiro
Essa interface e responsável pela interação entre as peças e o tabuleiro.

Método | Objetivo
-------| --------
moverOuAtacar | Verifica se a peça realizará um ataque ou movimento e chama o metodo correspondente
getPosition | Retorna a posição da peça no tabuleiro

# Componente Tabuleiro

![Componente do Tabuleiro](componentetabuleiro.png)

## Interfaces

Interfaces associadas ao componente Tabuleiro:

![Diagrama Interfaces do Tabuleiro](interfacetabuleiro.png)

Campo | Valor
----- | -----
Classe | tabuleiro.Tabuleiro
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar o tabuleiro do jogo
Interface | 
~~~
public interface ITabuleiroGame {
    public void acionarPecas(){};
    public int getQuantia(String quantia){};


}
public interface ITabuleiroPeca {
    public void setPeca(String posicao){};
    public Peca getPeca(String posicao){};
    public Peca[] getPecas(String jogador){};
    public  void eliminarPeca(String posicao){};



}
public interface ITabuleiro extends ITabuleiroGame,ITabuleiroPeca {}




~~~


## Detalhamento das Interfaces

### Interface ITabuleiroGame
Permite que Game acione(movimentar e atacar) as peças do tabuleiro e de pegar informacões da rodada referentes a quantias que os jogadores irão receber.

Método | Objetivo
-------| --------
acionarPecas | realiza a interação entre as peças
getQuantia |  recebe uma string que representa um jogador e retorna a quantia inteira que o jogador representado pela String receberá ao final de uma rodada

### Interface ITabuleiroPeca
Permite que as peças interajam entre si.

Método | Objetivo
-------| --------
setPeca | recebe uma String e um objeto da classe Peca e coloca a peça recebida na posição representada pela String
getPeca | recebe uma String e recupera a peça na posição representada pela String
eliminarPeca | recebe uma String e exclui a peça na posição representada pela string passada como parâmetro
getPecas | Recebe uma String que representa um jogador e retorna um vetor de pecas que contém as pecas do jogador ,recebido como parâmetro, que estão no tabuleiro
# Componente Banco

![Componente do Banco](README_Images/ComponentBanco.png.png)

## Interfaces

Interfaces associadas ao componente Banco:

![Diagrama Interfaces do Banco](interfacebanco.png)

Campo | Valor
----- | -----
Classe | banco.Banco
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar o tabuleiro do jogo
Interface | 
~~~
public interface IBancoGame {
    public Peca[] getDisponiveis(){};


}
public interface IBancoJogador {
    public void refresh(){};



}
public interface IBanco extends IBancoGame,IBancoJogador {}




~~~


## Detalhamento das Interfaces

### Interface IBancoJogador
Permite que o jogador acesse as peças dissponíveis para a compra.

Método | Objetivo
-------| --------
getDisponiveis | retorna um vetor de peças disponíveis para a compra
refresh | atualiza o vetor de peças disponíveis (trocando as peças que estarão disponíveis)
# Componente Game

![Componente do Tabuleiro](componentegame.png)



![Diagrama Interface do Game](classegame.png)

Campo | Valor
----- | -----
Classe | game.Game
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar a classe que vai rodar o jogo
Interface | ausente



