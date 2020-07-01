
# Projeto Medium War

# Equipe
* Felipe Pacheco Manoel - 215743
* Cristiano Sampaio Pinheiro - 256352

# Descrição Resumida do Projeto
O projeto será um jogo em que cada jogador posicionará as peças de sua mão no tabuleiro e em seguida as peças atacarão as peças do inimigo por meio de um determinado compartamento atribuído ao tipo da peça. Cada peça terá atributos específicos como vida , dano e alcance de ataque. A temática do jogo será baseada em classes e criaturas de um RPG.

# Vídeos do Projeto

## Vídeo da Prévia
[Link do vídeo do projeto](https://drive.google.com/open?id=12WCLHbGfRfGYytHxSpNW-8KsJQpoNzBI)

## Vídeo do Jogo
<p>[MediumWar Demo](https://drive.google.com/file/d/154s7CXjp4dG9Yf8y6I6eRETXDytXXkru/view?usp=sharing)</p>
<p>[MediumWar SpeedRun](https://drive.google.com/file/d/10O7YF57NPsBakSjG8R0hhniX1L93Q8Jn/view?usp=sharing)</p>

# Slides do Projeto

## Slides da Previa
[Link para apresentação do projeto](https://drive.google.com/open?id=1aigs8xozY3tbv9r1LsFobk-ZYNi4p2eJKla_YFFc_GY)

## Slides da apresentação

## Relatório de Evolução

<p>Inicialmente havíamos planejado em dividir os componentes em Banco, Game, Jogador, Tabuleiro e Peça. Nosso primeiro passo no projeto foi criar a interface gráfica, dispondo as imagens na tela de maneira estática. Para isso criamos as classes Window e GUI. Ademais adicionamos os componentes Tile e Card, o primeiro para representar as posições do tabuleiro e o segundo para representar as posições do banco e do jogador.</p>
<p>Na classe GUI colocamos uma imagem 960x540 que seria redimensionada para o tamanho da tela para ficar no modo fullscreen e a Window, que é um JFrame onde o  jogo ocorrerá. Inicialmente tentamos utilizar o método paint() para imprimir as imagens na tela após redimensioná-las corretamente com getScaledInstance(), mas ocorreram problemas de sobreposição de imagens, então passamos utilizar o método paintComponent(), para printar utilizamos a sequência GUI->Tabuleiro/Banco/Jogador->Card/Tile->Peça. Para testar o sistema em que nós colocamos uma imagem acima da outra incluindo a peça, instanciamos a peça em locais arbitrários.</p>
<p>Em seguida foi criada a classe Menu, para gerenciar o início do jogo e para isso utilizamos o CardLayout no entanto surgiram muitos problemas quanto à classe Game ser um Canvas ao invés de um JPanel, então simplesmente adotamos a estratégia de adicionar e remover o jogo e o menu do JFrame. No menu adicionamos botões para sair do jogo, iniciar o jogo e ir para tela de créditos que continha um botão para retornar ao menu. Em seguida definimos as animações de movimento e geramos movimentos aleatórios para isso introduzimos entradas via KeyListener para instanciar peças em posições pré-definidas, começar o movimento e limpar o tabuleiro. Até esse momento tivemos que utilizar casts para instanciar peças.</p>
<p>Em sequência, removemos os casts utilizando getters na interface que todas as peças herdam independente de estarem no banco, no jogador ou no tabuleiro.Após isso, foram definidas as teclas e suas respectivas ações, de forma que cada jogador interage com uma determinada região do teclado. Com isso os cursores do tabuleiro, banco e jogador começaram a ser implementados. Aqui percebemos uma certa demora de atualização da imagem envolvida pelo cursor, o que deixava a região branca por alguns segundos. Esse problema foi atenuado setando todas as imagens usadas já no construtor dos cards, não sendo necessário carregá-las enquanto o jogo já está em execução.</p>
<p>Conforme os cursores se mostraram consistentes, as ações e interações entre os componentes começaram a ser feitas, como a ação de compra e venda de peças, que relaciona o jogador e o banco. Algumas correções também foram feitas para que somente um curso seja exibido de cada vez e não haja conflito entre as ações.</p>
<p>Voltando-se para as peças, foi criado um sistema para definir o trajeto da peça até que possa atacar o alvo, utilizando um sistema de exceções em que são testadas posições com prioridades, que dependem da posição relativa entre a peça e o alvo, até que se encontre uma possibilidade de movimento ou não fazer nada no caso em que não há movimentos possíveis. Também foram criadas duas herdeiras da classe peça, Ranged(longo alcance) e Melee(corpo a corpo), assim as peças foram separadas de acordo com seu tipo de ataque. Com isso a movimentação das peças começou a ser implementada.</p>
<p>Em paralelo com isso, a parte monetária do jogo começou a ser desenvolvida, adicionando a parte gráfica que envolve as transações juntamente com os valores referentes a cada peça e suas ações.</p>
<p>Ainda nas peças, os ataques começaram a ser desenvolvidos juntamente com a classe Projectile, para representar os projéteis lançados pelas peças herdeiras de Ranged. As peças também receberam uma barra de vida interativa e que também é utilizada para diferenciar as peças de cada jogador no tabuleiro.</p>
<p>Com as peças já interagindo de maneira satisfatória foi introduzida a mecânica de rounds, marcadores de pontos e uma tela final exibindo o vencedor. Aqui tivemos grandes problemas ao finalizar o jogo e tentar retornar ao menu inicial e possibilitar uma nova partida. A ideia de rotacionar entre o menu e o game no frame principal não se mostrou viável nessa situação. Para corrigir isso, um JPanel foi criado para abrigar o game e a imagem final, isso possibilitou sua adição ao cardLayout e facilitou o gerenciamento quando o jogo chega ao final.</p>
<p>Por fim, foi adicionada a possibilidade de ver os principais atributos das peças antes e depois de comprá-las. Vários detalhes também foram melhorados, como a movimentação dos projéteis no tabuleiro, o gerenciamento de teclas e a composição das interfaces.</p>

# Destaques de Código

## Criação e Gerenciamento do Menu Usando CardLayout
~~~
public Menu(Window window){
		...
		setMenu();
		//Cria cardLayout e adiciona janelas
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.add(menuPage, "home");
		this.add(creditsPage, "credits");
		setVisible(true);
	}
	public void setMenu() {
		...
		//Cria Janelas
		menuPage = new JLabel();
		menuPage.setLayout(null);
		menuPage.setIcon(imgMenu);
		menuPage.setVisible(false);
		...
		//Cria Botoes
		play=new JButton(imgPlay);
		play.setBounds(1400, 400, 432, 144);
		play.addActionListener(window);
		menuPage.add(play);
		...
	}
~~~
~~~
public Window(int width,int height,String title,Game game) {
		...
		menu = new Menu(this);
		...
		this.add(menu);
	}
	 public void actionPerformed(ActionEvent evento) {
		 	//Cria a janela principal
		 	principal=new JPanel();
			principal.setOpaque(false);
			principal.setLayout(null);
			principal.setVisible(true);
			...
			//Janela principal adiciona o game
			principal.add(game);
			//Janela principal e adicionada ao cardLayout do menu 
			menu.add(principal,"principal");
			menu.cardLayout.show(menu,"principal");
		 	
		 	game.gameStart();
		 	game.start(); 	
	}
	 public void endGame() {
		endHome.setVisible(true);
		SwingUtilities.updateComponentTreeUI(this);
	}
	 public void setMenu() {
		game.stop();
		menu.cardLayout.show(menu,"home");
	}
~~~
## Controle da Movimentação da Peça
~~~
public void moveOrAttack() {
	...
	for(int i=0;i<matriz.length;i++) {
		for(int j=0;j<matriz[0].length;j++) {
			if(matriz[i][j].existsPeca()&&matriz[i][j].getPeca().getCor()!=this.cor&&Tile.dist(matriz[i][j], tile)<dist) {//achar menor distncia a peca inimiga
				dist=Tile.dist(matriz[i][j], tile);
				alvo=matriz[i][j];
			}
		}
	}
	if(dist<=alcance&&dist!=100) {
		currentAction="attacking";
		...
	}
	else if(dist!=100) {
		try {
			direction=chooseDirection(alvo,tried);
			lastPosition=new int[2];
			lastPosition[0]=-direction[0];
			lastPosition[1]=-direction[1];
			
			moveTarget=tile.getOtherTiles()[tile.getPosition()[0]+direction[0]][tile.getPosition()[1]+direction[1]];
			
			attackTarget=null;
			tried=new ArrayList<int[]>();//atualiza o vetor das direcoes que ja foram testadas no chooseDirection() que o utilizara para fazer novas tentativas
			tried.add(lastPosition);
			currentAction="moving";
			...
		} catch (FormatoInvalido e) {
			System.out.println("formato invalido");
		}catch (MovimentoInvalido e) {
			if(tried.size()<4) {
				moveOrAttack();
			}
			else {
				esperando=true;//tenta de novo no proximo tick
				tried=new ArrayList<int[]>();
				if(lastPosition!=null)tried.add(lastPosition);
			}
			
		}
		
	}
}
~~~	
## Diferentes Maneiras de Instanciar uma Peça
~~~
public Peca(double scale) {
	this.scale=scale;
}
public Peca(IPecaCardJogador peca,Tile tile) {
	set(peca);//atribui as variaveis que toda peca devera ter a partir de uma instancia anterior e copia os atributos para a nova peca
	this.tile=tile;
	inBoard=true;
	basePosition=tile.getGUIPosition();
	origem=peca;
	peca.getCard().setNaoColocado(false);
	int[] start=new int[2];
	start[0]=(int)(getCenterPosition()[0]+correction[0]*scale+scale*translation[0]);
	start[1]=(int)(basePosition[1]+scale*correction[1]+scale*translation[1]);
	barraDeVida=new BarraDeVida(start,scale,cor);
}
public Peca(IPeca peca,ICardBanco card) {
	set(peca);		
	inBoard=false;
	basePosition=card.getGUIPosition();
}
public Peca(IPecaCardBanco peca,ICardJogadorPeca card) {
	set(peca);		
	inBoard=false;
	basePosition=card.getGUIPosition();
	this.card=card;
	cor=card.getJogador().getCor();
}
~~~
## Uso do Generics
~~~
...
protected abstract <t extends IPecaCard> t getPeca();
...
~~~
~~~
...
public IPecaCardJogador getPeca() {
    	return peca;
}
...
~~~
~~~
...
public IPecaCardBanco getPeca() {
		return peca;
}
...
~~~

# Destaques de Pattern
## Diagrama 
![Diagrama Pattern Menu](assets/README_Images/MenuPattern.png)

## Código do Pattern
~~~
public class Window extends JFrame implements ActionListener{
	 ...
	 public void actionPerformed(ActionEvent evento) {
		principal=new JPanel();
		principal.setOpaque(false);
		principal.setLayout(null);
		principal.setVisible(true);
			
		imgReturn=menu.resize(new ImageIcon("assets/returnButton.png"));
		endHome=new JButton(imgReturn);
		endHome.setBounds((this.getWidth()/2)-(imgReturn.getIconWidth()/2), (HEIGHT/2)+450, 432, 144);
		endHome.addActionListener(e->setMenu());
		endHome.setVisible(false);
		principal.add(endHome);
		principal.add(game);
		
		menu.add(principal,"principal");
		menu.cardLayout.show(menu,"principal");
		 	
		game.gameStart();
		game.start(); 	
	 }
	 ...
}
~~~
~~~
public class Menu extends JPanel{
	...
	private void setMenu() {
		...
		credits=new JButton(imgCreditsButton);
		credits.setBounds(1400, 570, 432, 144);
		credits.addActionListener(e -> cardLayout.show(this, "credits"));
		menuPage.add(credits);
		
		exit=new JButton(imgExit);
		exit.setBounds(1400, 740, 432, 144);
		exit.addActionListener(e -> System.exit(0));
		menuPage.add(exit);
		
		home=new JButton(imgReturn);
		home.setBounds(120, 900, 432, 144);
		home.addActionListener(e -> cardLayout.show(this, "home"));
		creditsPage.add(home);
	}
	 ...
}
~~~
O trecho de código acima se assemelha a um design pattern Observer, no entanto, não há um ActionSubject, o que dispara as ações para as classes ouvintes são botoes.  
Dentro do menu ha 3 botões que ao serem acionados disparam eventos, como encerra o programa e trocar o JPanel exibido pelo cardLayout. Destacando o botão play, ao ser precionado a classe window, que implementa ActionListener, recebe a atualização da ação e da inicio ao jogo.

## Diagrama 
![Diagrama Pattern Game](assets/README_Images/GamePattern.png)

## Código do Pattern
~~~
public class Game extends Canvas implements Runnable, IGameTabuleiro,KeyListener{
	...
	private void render(){
         BufferStrategy bs = this.getBufferStrategy();
         if(bs==null){
             this.createBufferStrategy(3);
             return;
         }
         Graphics g = bs.getDrawGraphics();
        
         if(gui!=null)gui.paintComponent(g);
         if(tabuleiro!=null)tabuleiro.paintComponent(g);
         if(banco!=null)banco.paintComponent(g);
         if(jogador1!=null)jogador1.paintComponent(g);
         if(jogador2!=null)jogador2.paintComponent(g);
         if(redWins&&blueWins)g.drawImage(imgEndEmpate,((int)(this.getWidth()/2)-(imgEndEmpate.getWidth(null)/2)), ((int)(this.getHeight()/2)-(imgEndEmpate.getHeight(null)/2))-250, this);
         else if(redWins)g.drawImage(imgEndRed,((int)(this.getWidth()/2)-(imgEndRed.getWidth(null)/2)), ((int)(this.getHeight()/2)-(imgEndRed.getHeight(null)/2))-250, this);
         else if(blueWins)g.drawImage(imgEndBlue,((int) (this.getWidth()/2)-(imgEndBlue.getWidth(null)/2)),((int) (this.getHeight()/2)-(imgEndBlue.getHeight(null)/2))-250, this);

         g.dispose();
         bs.show();
     }
     @Override
	 public void keyTyped(KeyEvent e) {
		
	 }
	 @Override
	 public void keyPressed(KeyEvent e) {
		 int code = e.getKeyCode();
		 switch(code){
		 case KeyEvent.VK_W:
			 if(commands1)tabuleiro.pressedW();
             break;
		 case KeyEvent.VK_A:
			if(commands1) {
				jogador1.pressedA();
				tabuleiro.pressedA();
				banco.pressedA();
			}
             break;
		case KeyEvent.VK_S:
			if(commands1)tabuleiro.pressedS();
			 break;
		...
		case KeyEvent.VK_Q:
			if(tabuleiro.getIntab()[0]>0) {
				commands1=false;
				if(banco.obtainCursor("azul")!=-1)banco.hideCursor(1);
				tabuleiro.pressedQ();
			}
			 break;
		case KeyEvent.VK_M:
			if(tabuleiro.getIntab()[1]>0) {
				commands2=false;
				if(banco.obtainCursor("vermelho")!=-1)banco.hideCursor(2);
				tabuleiro.pressedM();
			}
			 break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	...
}
~~~
Novamente temos algo que se assemelha a um pattern Observer. Como pode ser observado no diagrama abaixo, a classe Game é responsável por todo o gerenciamento do jogo, ela não faz nada sozinha, mas nada funciona sem ela.  
Ao implementar KeyListener, todas as ações realizadas após o play, usando o teclado, são recebidas por ela. Tais comandos chamam metodos existentes nas classes Banco, Jogador e Tabuleiro, permitindo a interação do jogador com a interface grafica. Dessa forma, aos classes já citadas não são ouvintes diretas dos comandos recebidas pela game, no entanto, essas comandos são as responsáveis por disparar ações dentro de cada uma dessas classes.  
Na game também abriga o Graphics responsável por imprimir a maioria das dos elementos na tela usando seu metodo render, que chama o paintComponent() das demais classes.

# Diagrama Geral do Projeto
![Diagrama Geral do Projeto](assets/README_Images/DiagramaGeralProjeto.png)

## Controles de Cada Jogador
![Diagrama Fluxo jogador azul](assets/README_Images/FluxoJogadorAzul.png)
![Diagrama Fluxo jogador azul](assets/README_Images/FluxoJogadorVermelho.png)

## Recomendações
O jogo deve ser executado em uma resolução de 1920x1080, pode ser necessário alterar o Ajuste de Escala e Layput do Windows.  
E possível jogar multiplayer online fazendo uso do ![parsec](https://parsecgaming.com/), um aplicativo de captura de desktop.

# Diagrama Geral de Componentes
![Diagrama Geral](assets/README_Images/DiagramaGeralComponentes.png)

# Componente Jogador

![Componente do Jogador](assets/README_Images/ComponenteJogador.png)

## Interfaces

Interfaces associadas ao componente Jogador:

![Diagrama Interfaces do Jogador](assets/README_Images/InterfaceJogador.png)

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
	public void pressedJ();
	public void pressedD() ;
	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedZ() ;
	public void pressedE();
	public void pressedX();
	public void pressedN();
	public void pressedK() ;
	public void pressedL() ;
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
pressedJ | Notifica o banco que o jogador 2 quer comprar uma peça
pressedX | Coloca o cursor na mão do jogador 1 para escolher uma peça para posicioná-la e em seguida notifica o tabuleiro que o jogador 1 quer posicionar uma peça 
pressedK | Coloca o cursor na mão do jogador 2 para escolher uma peça para posicioná-la e em seguida notifica o tabuleiro que o jogador 2 quer posicionar uma peça 
pressedZ | Coloca o cursor na mão do jogador 1 para escolher uma peça para vendê-la e em seguida a remove da mão e incrementa o dinheiro do jogador 1
pressedL | Coloca o cursor na mão do jogador 2 para escolher uma peça para vendê-la e em seguida a remove da mão e incrementa o dinheiro do jogador 2
pressedE | Liga ou desliga a exibição dos atributos da peça do jogador 1
pressedN | Liga ou desliga a exibição dos atributos da peça do jogador 2
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

![Componente do Card](assets/README_Images/ComponenteCard.png)

## Interfaces

Interfaces associadas ao componente Card:

![Diagrama Interfaces do Card](assets/README_Images/InterfaceCard.png)

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

![Componente da Peca](assets/README_Images/ComponentePeca.png)

## Interfaces

Interfaces associadas ao componente Peca:

![Diagrama Interfaces da Peca](assets/README_Images/InterfacePeca.png)

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

### Interface IPeca
Essa interface abriga os métodos genericos da peca e é herdada pelas demais interfaces.

Método | Objetivo
-------| --------
paintComponent | Imprime a peca corrigindo sua posição
getAnimationFramesAttack | Retorna o vetor de imagens da animação de ataque
getAnimationFramesMove | Retorna o vetor de imagens da animação de movimento
getCurrentAnimation | Retorna o vetor de imagens da animação que esta sendo executada no momento
getScale | Retorna a escala da imagem usada nas peças
getBaseMoveAnimDuration | Retorna a duração da animação de movimento da peça
getSpeed | Retorna a velocidade de movimentação da peça
getLife | Retorna a vida atual da peça
getEndurance | Retorna o valor da defesa da peça
getAttackSpeed | Retorna a velocidade de ataque da peça
getAttackDamage | Retorna o valor do dano causado pela peça
getBaseAttackAnimDuration | Retorna a duração da animação de ataque da peça
getAlcance | Retorna o alcance de ataque da peça
getCor | Retorna a cor do jogador a qual uma determinada peça pertence
getPurchaseValue | Retorna o valor de compra da peça
getSaleValue | Retorna o valor de venda da peça
getGiftValue | Retorna o valor acrescida ao oponente quando derrotar a peça

### Interface IPecaCard
Essa interface e responsável pela interação entre as peças e os cards.

Método | Objetivo
-------| --------
printFeature | Imprime os valores dos principais atributos da peca
paintComponent | Imprime a peca dentro do card

### Interface IPecaCardJogador
Essa interface e responsável pela interação entre a peca e os cards do jogador.

Método | Objetivo
-------| --------
getCard | Retorna o card o qual a peça esta inserida
recompensar | Retorna o valor acrescido quando a peça e derrotada

### Interface IPecaCardBanco
Essa interface e responsável pela interação entre a peca e os cards do banco.

### Interface IPecaTile
Essa interface e responsável pela interação entre as peças e os tiles presentes no tabuleiro.

Método | Objetivo
-------| --------
moveOrAttack | Verifica qual ação a peça deve tomar, mover ou atacar
setTarget | Define para qual tile a peça deve se mover
flip | Espelha imagem da peça
getInBoard | Retorna se a peça esta no tabuleiro
getJogador | Retorna o jogador dono da peça
getTile | Retorna o tile que a peça esta ocupando no momento
getCenterPosition | Retorna a posição central da peça no frame
setTargetNull | Define como nulo o próximo tile que a peça deve assumir
getMorto | Retorna se peça esta viva ou morta
receberDano | Aplica dano a peça causado por um ataque próximo
receberDanoRanged | Aplica dano a peça causado por um ataque a distância 
getCard | Retorna o card o qual a peça pertence
getOrigem | Retorna a peca que esta presente no card
setInBoard | Define se a peça esta ou não no tabuleiro

# Componente Tabuleiro

![Componente do Tabuleiro](assets/README_Images/ComponenteTabuleiro.png)

## Interfaces

Interfaces associadas ao componente Tabuleiro:

![Diagrama Interfaces do Tabuleiro](assets/README_Images/InterfaceTabuleiro.png)

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
	public void pressedM();

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
pressedM | Notifica o banco que o jogador 2 terminou de posicionar suas peças

### Interface ITabuleiroJogador
Essa interface é responsável pela interação entre o jogaador e o tabuleiro

Método | Objetivo
-------| --------
getCursor | Retorna a posição do cursor que é representado pela cor passada como parâmetro
hideCursor | Faz com que o cursor de uma determinada cor não seja mais visível
posicionarPeca | Notifica o tabuleiro que um determinado jogador quer posicionar uma determinada peça , ambos passados como parâmetro

### Interface ITabuleiroJogador
Essa interface é responsável pela interação entre o jogaador e o tabuleiro

Método | Objetivo
-------| --------
getScale | Retorna a escala da imagem do tabuleiro
getTiles | Retorna uma matriz de tiles que represemta as posicoes do tabuleiro
eliminateInTab | Decrementa a variável que controla quantas peças cada joagdor tem no tabuleiro 
addProjectiles | Adiciona um projétil passado como parâmetro no tabuleiro

# Componente Tile

![Componente do Tile](assets/README_Images/ComponenteTile.png)

## Interfaces

Interfaces associadas ao componente Peca:

![Diagrama Interfaces do Tile](assets/README_Images/InterfaceTile.png)

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
### Interface ITile
Essa interface é responsável por agrupar as outras interfaces do tile
### Interface ITilePeca
Essa interface é responsável pela interação entre as peças e o tile.

Método | Objetivo
-------| --------
getImage | Retorna a imagem do tile
getGUIPosition | Retorna a posição do tile na tela
getOtherTiles | Retorna a matriz de tiles que representa as posições do tabuleiro
getPosition | Retorna a posição que o tile está na matriz de peças
setNull | Coloca o valor null na variável que  guarda a peça
setPeca | Guarda uma peça passada como parâmetro
clearTile | Remove a peça armazenada e retira a referência do tile existente na peça
existsPeca | Checa se o tile está ocupado por uma peça
getPeca | Retorna a peça armazenada
eliminateTab | Faz com que o tabuleiro decremente um a variável que guarda quantas peças um determinado jogador tem , passando um inteiro que representa o jogador
setMarcado | Alterna a variável marcado que indica que uma peça ja está se movimentando em direção à aquele tile
addProjectile | Adiciona um projétil passado como parâmetro no tabuleiro 

### Interface ITileTabuleiro
Essa interface e responsável pela interação entre o tabuleiro e o tile
Método | Objetivo
-------| --------
getPeca | Retorna a peça armazenada
paintPeca | Se estiver armazenada alguma peça , imprime a peça na tela
getImage | Retorna a imagem do tile
setNull | Coloca o valor null na variável que  guarda a peça
setPeca | Guarda uma peça passada como parâmetro
existsPeca | Checa se o tile está ocupado por uma peça
nullTarget | Para o movimento da peça anulando a variável que guarda o seu destino
actionPeca | Inicia as ações de movimento e ataque da peça
setTileAtual | Troca a imagem do tile que será imprimida para indicar que um cursor está no tile ou não

# Componente Banco

![Componente do Banco](assets/README_Images/ComponenteBanco.png)

## Interfaces

Interfaces associadas ao componente Banco:

![Diagrama Interfaces do Banco](assets/README_Images/InterfaceBanco.png)

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
	public void pressedLEFT();
	public void pressedRIGHT();
	public void pressedA();
	public void pressedD();
	public void pressedSPACE();
	public void pressedENTER();
	public void pressedE();
	public void pressedN();
	public void refresh();
	public void paintComponent(Graphics g);
}

public interface IBancoCard {
	public double getScale();
}
public interface IBancoJogador {

	public void comprar(IJogador jogador);
			
	public void hideCursor(int i);

	public int obtainCursor(String cor);
}


~~~

## Detalhamento das Interfaces

### Interface IBanco
Essa interface é responsável por agrupar as outras interfaces e representar as interações entre o game e o banco


Método | Objetivo
-------| --------
hideCursor | Faz com que o cursor de determinado jogador(representado por uma cor) não seja mais visível
paintComponent |Imprime o banco e as suas peças armazenadas
pressedA | Movimenta o cursor do jogador 1 para a esquerda
pressedD | Movimenta o cursor do jogador 1 para a direita
pressedLEFT | Movimenta o cursor do jogador 2 para a esquerda
pressedRIGHT | Movimenta o cursor do jogador 2 para a direita
pressedE | Liga ou desliga a exibição dos atributos da peça do jogador 1
pressedN | Liga ou desliga a exibição dos atributos da peça do jogador 2
pressedSPACE | Confirma a escolha do jogador 1 de qual peça será comprada
pressedENTER | Confirma a escolha do jogador 2 de qual peça será comprada
refresh | Troca as peças que estarão disponíveis para compra

### Inteface IBancoCard
Essa interface é responsável pela interação entre o card e o banco

Método | Objetivo
-------| --------
getScale | Retorna a escala da imagem do banco

### Interface IBancoJogador
Essa interface é responsável pela interação entre o jogador e o banco 

Método | Objetivo
-------| --------
obtainCursor | Retorna a posição do cursor de um jogador representado pela sua respectiva cor
hideCursor | Faz com que o cursor de determinado jogador(representado por uma cor) não seja mais visível
comprar | Notifica o banco que um determinado jogador deseja comprar um peça

# Componente Game

![Componente do Game](assets/README_Images/ComponenteGame.png)

## Interfaces

Interfaces associadas ao componente Game:


![Diagrama Interface do Game](assets/README_Images/InterfaceGame.png)

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

### Interface IGame
Permite que outras classes tenham acesso ao game

Método | Objetivo
-------| --------
getScale | Retorna a escala da imagem usada no frame principal

### Interface IGameTabuleiro
Permite que o tabuleiro crie novos rounds e finalize o jogo

Método | Objetivo
-------| --------
newRound | Realiza ações necessárias para iniciar um novo round
endGame | Encerra o jogo apresentando opção de retornar ao menu

# Plano de Exceções

## Diagrama da hierarquia de exceções

![Hierarquia Exceções](assets/README_Images/EsquemaException.png)

## Descrição das classes de exceção



Classe | Descrição
----- | -----
MovimentoInvalido | Engloba todas as exceções nas quais o movimento é inválido.
ForaDoTabuleiro | Indica que a posição pretendida não está dentro do tabuleiro.
PosicaoOcupada | Indica que a peça não pode se mover porque a posição pretendida ja está ocupada.
FormatoInvalido | Indica que a entrada do método de movimento é inválida.



