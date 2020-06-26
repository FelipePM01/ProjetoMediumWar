
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
![Diagrama Geral](README_Images/DiagramaGeralComponentes.png)

# Componente Jogador

![Componente do Jogador](README_Images/ComponenteJogador.png)

## Interfaces

Interfaces associadas ao componente Jogador:

![Diagrama Interfaces do Jogador](README_Images/InterfaceJogador.png)

Campo | Valor
----- | -----
Classe | jogador.Jogador
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar cada um dos jogadores e suas relações com os outros componenetes
Interface | 
~~~
public interface IJogador extends IJogadorCard, IJogadorBanco{
	public void addPoint();
	public int getPoints();
	public int obtainCursor();
	public void hideCursor();
	
	public void pressedA() ;
	public void pressedC() ;
	public void pressedVIRGULA();
	public void pressedD() ;
	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedZ() ;
	public void pressedE();
	public void pressedX();
	public void pressedDoisPontos();
	public void pressedPONTO() ;
	public void pressedBARRA() ;
	public void pressedSPACE() ;
	public void pressedENTER() ;
	public void paintComponent(Graphics g);	

}
public interface IJogadorBanco {
	void receber(IPecaCardBanco peca);
	public int getCash();
	public void addCash(int valor);
}
public interface IJogadorCard {
	public double getScale();

	public String getCor();

	public void addCash(int value);
}


~~~


## Detalhamento das Interfaces

### Interface IJogador
Essa interface é responsável por agrupar as demais interfaces assim como estabelever a relação entre o jogador e o game

Método | Objetivo
-------| --------
addPoint | Incrementa a pontuação do jogador que ganhou a rodada
getPoints | Retorna quantos pontos de terminado jogador possui
hideCursor | Faz com que o cursor não seja mais visível
paintComponent |Imprime a mão do jogador e as suas peças armazenadas
pressedA | Movimenta o cursor da mão do jogador 1 para a esquerda
pressedD | Movimenta o cursor da mão do jogador 1 para a direita
pressedLEFT | Movimenta o cursor da mão do jogador 2 para a esquerda
pressedRIGHT | Movimenta o cursor da mão do jogador 2 para a direita
pressedC | Notifica o banco que o jogador 1 quer comprar uma peça
pressedVIRGULA | Notifica o banco que o jogador 2 quer comprar uma peça
pressedX | Coloca o cursor na mão do jogador 1 para escolher uma peça para posicioná-la e em seguida notifica o tabuleiro que o jogador 1 quer posicionar uma peça 
pressedPONTO | Coloca o cursor na mão do jogador 2 para escolher uma peça para posicioná-la e em seguida notifica o tabuleiro que o jogador 2 quer posicionar uma peça 
pressedZ | Coloca o cursor na mão do jogador 1 para escolher uma peça para vendê-la e em seguida a remove da mão e incrementa o dinheiro do jogador 1
pressedBARRA | Coloca o cursor na mão do jogador 2 para escolher uma peça para vendê-la e em seguida a remove da mão e incrementa o dinheiro do jogador 2
pressedE | Liga ou desliga a exibição dos atributos da peça do jogador 1
pressedDoisPontos | Liga ou desliga a exibição dos atributos da peça do jogador 2
pressedSPACE | Confirma a escolha do jogador 1 de qual peça será vendida ou posicionada
pressedENTER | Confirma a escolha do jogador 2 de qual peça será vendida ou posicionada

###Interface IJogadorBanco
Essa interface é responsável por representar as interações do banco no jogador
Método | Objetivo
-------| --------
receber | O banco passa a peça escolhida pelo jogador para o jogador
getCash | Retorna o dinheiro que o jogador possui 
addCash | Incrementa ou decrementa o dinheiro de um jogador a partir de um valor passado como parâmetro

###Interface IJogadorCard
Essa interface é responsável por representar as interações do card com o jogador
Método | Objetivo
-------| --------
getScale | Retorna a escala da imagem 
getCor | Retorna a cor que representa o jogador 
addCash | Incrementa ou decrementa o dinheiro de um jogador a partir de um valor passado como parâmetro


# Componente Card

![Componente do Card](README_Images/ComponenteCard.png)

## Interfaces

Interfaces associadas ao componente Card:

![Diagrama Interfaces do Card](README_Images/InterfaceCard.png)

Campo | Valor
----- | -----
Classe | card.Card
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar cada um dos cards presentes no banco e na mao do jogador
Interface | 
~~~
public interface ICardJogadorPeca extends ICardPeca{

	

	public IJogadorCard getJogador();

	public void recompensar(int giftValue);

	public void setNaoColocado(boolean value);
	
}
public interface ICardPeca {
	public int[] getGUIPosition();
}
public interface ICardJogador {
	public int getWidth();
	public void paintComponent(Graphics g, int positionX, int positionY);
	public int getHeight();
	public boolean ehNulo();
	public void setCardAtual(String cor);
	public void setPeca(IPecaCardBanco recebido);
	public IPecaCardJogador getPeca();
	public boolean getNaoColocado();
	
}
public interface ICardBanco {
	public void setCardAtual(String cor);
	public void paintComponent(Graphics g, int positionX, int positionY);
	public void setPeca(IPecaCard peca);
	public IPecaCardBanco getPeca();
	public int getWidth();
	public int getHeight();
	public int[] getGUIPosition();
}





~~~


## Detalhamento das Interfaces

### Interface ICardPeca
Essa interface é responsável pela interação da peça com um card genérico

Método | Objetivo
-------| --------
getGUIPosition | Retorna a posição do card na tela


### Interface ICardJogadorPeca
Essa interface é responsável pela interação da peça com o card da mão do jogador

Método | Objetivo
-------| --------
getJogador | Retorna o jogador o qual o card pertence
recompensar | Notifica o card que uma peca irá recompensar o jogador com uma quantia passada como parâmetro
setNaoColocado | Recebe um boolean e o coloca na variavel naoColocado que diz se o card ja foi posicionado no tabuleiro

### Interface ICardBanco
Essa interface é responsável pela interação do banco com o card do banco

Método | Objetivo
-------| --------
getWidth | Retorna a largura da imagem do card
getHeight | retorna a altura da imagem do card
paintComponent | Imprime o card e seu conteúdo na tela passando a posição no banco como parâmetro
setCardAtual | Atualiza a imagem do card para refletir na posição do cursor
setPeca | Armazena uma cópia da peça passada como parâmetro
getPeca | Retorna a peça armazenada
getGUIPosition | Retona a posição do card na tela

### Interface ICardJogador
Essa interface é responsável pela interação do jogador com o card na mão do jogador

Método | Objetivo
-------| --------
getWidth | Retorna a largura da imagem do card
getHeight | retorna a altura da imagem do card
paintComponent | Imprime o card e seu conteúdo na tela passando a posição na mão como parâmetro
ehNulo | Verifica se o card está armazenando uma peça
setCardAtual | Atualiza a imagem do card para refletir na posição do cursor
setPeca | Armazena uma cópia da peça passada como parâmetro
getPeca | Retorna a peça armazenada
getNaoColocado | Retona a variável naoColocado que indica se o card ja foi posicionado no tabuleiro

# Componente Peca

![Componente da Peca](README_Images/ComponentePeca.png)

## Interfaces

Interfaces associadas ao componente Peca:

![Diagrama Interfaces da Peca](README_Images/InterfacePeca.png)

Campo | Valor
----- | -----
Classe | peca.Peca
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar cada uma das Pecas presentes no jogo
Interface | 
~~~
public interface IPeca {
	public void paintComponent(Graphics g, int positionX, int positionY);	
	public Image[] getAnimationFramesAttack();
	public Image[] getAnimationFramesMove();
	public Image[] getCurrentAnimation();
	public double getScale();
	public int getBaseMoveAnimDuration();
	public double getSpeed();
	public double getLife();
	public double getEndurance();
	public double getAttackSpeed();
	public double getAttackDamage();
	public int getBaseAttackAnimDuration();
	public double getAlcance();
	public String getCor();
	public int getPurchaseValue();
	public int getSaleValue();
	public int getGiftValue();
}
public interface IPecaCard extends IPeca{

	void printFeature(Graphics g, String string);

	void paintComponent(Graphics g);

}
public interface IPecaCardBanco extends IPecaCard{
	
}
public interface IPecaCardJogador extends IPecaCard {

	public ICardJogadorPeca getCard();

	public void recompensar(int giftValue);

	
	
}
public interface IPecaTile extends IPeca{
	public void moveOrAttack() ;
	public void setTarget(Tile tile);
	public void flip();
	public boolean getInBoard();
	public IJogadorCard getJogador();
	public ITilePeca getTile();
	
	public double[] getCenterPosition();
	public void setTargetNull();
	public boolean getMorto();
	public void receberDano(double attackDamage, Peca peca);
	public void receberDanoRanged(double dano,Projectile projetil);
	public ICardJogadorPeca getCard();
	public IPecaCardJogador getOrigem();
	public void setInBoard(boolean inBoard);
}





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

![Componente do Tabuleiro](README_Images/ComponenteTabuleiro.png)

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
public interface ITabuleiro extends ITabuleiroTile,ITabuleiroJogador {

	public void setJogador(IJogador jogador1);

	public void pressedSPACE();
	public void pressedENTER() ;
	public void pressedW() ;
	public void pressedS() ;
	public void pressedA() ;
	public void pressedD() ;
	public void pressedUP() ;
	public void pressedDOWN();
	public void pressedLEFT() ;
	public void pressedRIGHT() ;
	public void pressedQ();
	public void pressedAspas();

	public void removeProjectiles(Projectile projectile);
	public void paintComponent(Graphics g);
	
	public void start();
	public void clear();
}
public interface ITabuleiroJogador {

	public boolean getCursor(String cor);

	public void hideCursor(String cor);

	public void positionPeca(Jogador jogador, IPecaCardJogador peca);

}
public interface ITabuleiroTile  {
	public double getScale();
	public Tile[][] getTiles();
	public void eliminateInTab(int i);
	public void addProjectiles(Projectile projetil);
}






~~~


## Detalhamento das Interfaces

### Interface ITabuleiro
Essa interface é responsável por agrupar as outras inrfeces de tabuleiro e de fazer as interações entre o game e o tabuleiro e entre os projéteis e o tabuleiro

Método | Objetivo
-------| --------
setJogador | Conecta um jogador com o tabuleiro
removeProjectiles | Remove um determinado projetil do tabuleiro
paintComponent | Imprime o tabuleiro ,os seus tiles(e consequentemente as peças armazenadas por eles) , e os projéteis na tela
start | Inicia a interação das peças
clear | Remove todas as peças do tabuleiro
pressedSPACE | Confirma o posicionamento da peça do jogador 1
pressedW | Move o cursor do jogador 1 para cima
pressedS | Move o cursor do jogador 1 para baixo
pressedA | Move o cursor do jogador 1 para a esquerda
pressedD | Move o cursor do jogador 1 para a direita
pressedQ | Notifica o banco que o jogador 1 terminou de posicionar suas peças
pressedENTER | Confirma o posicionamento da peça do jogador 2
pressedUP | Move o cursor do jogador 2 para cima
pressedDOWN | Move o cursor do jogador 2 para baixo
pressedLEFT | Move o cursor do jogador 2 para a esquerda
pressedRIGHT | Move o cursor do jogador 2 para a direita
pressedAspas | Notifica o banco que o jogador 2 terminou de posicionar suas peças

### Interface ITabuleiroPeca
Permite que as peças interajam entre si.

Método | Objetivo
-------| --------
setPeca | recebe uma String e um objeto da classe Peca e coloca a peça recebida na posição representada pela String
getPeca | recebe uma String e recupera a peça na posição representada pela String
eliminarPeca | recebe uma String e exclui a peça na posição representada pela string passada como parâmetro
getPecas | Recebe uma String que representa um jogador e retorna um vetor de pecas que contém as pecas do jogador ,recebido como parâmetro, que estão no tabuleiro
# Componente Tile

![Componente do Tile](README_Images/ComponenteTile.png)

## Interfaces

Interfaces associadas ao componente Peca:

![Diagrama Interfaces do Tile](interfacepeca.png)

Campo | Valor
----- | -----
Classe | tabuleiro.Tile
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar cada uma das posicoes do tabuleiro
Interface | 
~~~
public interface ITile extends ITileTabuleiro, ITilePeca{}
public interface ITilePeca {
	public Image getImage();

	public int[] getGUIPosition();
	public int[] getPosition();
	public Tile[][] getOtherTiles();
	public void setNull();
	public void setPeca(Peca peca);
	public void clearTile();
	public boolean existsPeca();
	public IPecaTile getPeca();
	public void eliminateTab(int i);
	public void setMarcado();
	public void addProjectile(Projectile projetil);
}
public interface ITileTabuleiro {
	public void paintComponent(Graphics g,Image img);

	public IPecaTile getPeca();
	public void paintPeca(Graphics g);
	public Image getImage();
	public void nullTarget();
	public void actionPeca();
	public boolean existsPeca();
	public void setTileAtual(String cor);
	public void setPeca(IPecaCardJogador peca);
	public void setNull();
}





~~~


## Detalhamento das Interfaces

### Interface IPecaJogador
Essa interface e responsável pela interação entre as peças e o jogador.

Método | Objetivo
-------| --------
upNivel | Incrementa atributos relacionados a ataque/velocidade/vida etc
getPrecoVenda | Retorna o valor de venda da peça
getPrecoCompra | Retorna o valor de compra da peça
# Componente Banco

![Componente do Banco](README_Images/ComponenteBanco.png)

## Interfaces

Interfaces associadas ao componente Banco:

![Diagrama Interfaces do Banco](README_Images/InterfaceBanco.png)

Campo | Valor
----- | -----
Classe | banco.Banco
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar o tabuleiro do jogo
Interface | 
~~~
public interface IBanco extends IBancoCard,IBancoJogador {

	void setJogador(IJogador jogador1);
	public int obtainCursor(String cor);
	public void hideCursor(int i);

	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedA();
	public void pressedD();
	public void pressedSPACE();
	public void pressedENTER();
	public void pressedE();
	public void pressedDoisPontos();
	public void refresh();
	public void paintComponent(Graphics g);
}

public interface IBancoCard {
	public double getScale();
}
public interface IBancoJogador {

	public void comprar(IJogador jogador);

	public int getCursor(int i);
	
	public void hideCursor(int i);

	public int obtainCursor(String cor);
}






~~~


## Detalhamento das Interfaces

### Interface IBancoJogador
Permite que o jogador acesse as peças dissponíveis para a compra.

Método | Objetivo
-------| --------
getDisponiveis | retorna um vetor de peças disponíveis para a compra
refresh | atualiza o vetor de peças disponíveis (trocando as peças que estarão disponíveis)
# Componente Game

![Componente do Game](README_Images/ComponenteGame.png)

## Interfaces

Interfaces associadas ao componente Game:


![Diagrama Interface do Game](README_Images/InterfaceGame.png)

Campo | Valor
----- | -----
Classe | game.Game
Autores | Felipe Pacheco Manoel e Cristiano Sampaio Pinheiro
Objetivo | representar a classe que vai rodar o jogo
Interface | 
~~~
public interface IGame {
	public double getScale();
}
public interface IGameTabuleiro extends IGame{
	public void newRound(String cor);
	public void endGame(String cor);
}
~~~
## Detalhamento das Interfaces

### Interface IBancoJogador
Permite que o jogador acesse as peças dissponíveis para a compra.

Método | Objetivo
-------| --------
getDisponiveis | retorna um vetor de peças disponíveis para a compra
refresh | atualiza o vetor de peças disponíveis (trocando as peças que estarão disponíveis)




