package peca;

import java.util.ArrayList;

import excecoes.FormatoInvalido;
import excecoes.MovimentoInvalido;
import tabuleiro.ITilePeca;
import tabuleiro.Tile;

public class Harpy extends Melee {

	@Override
	public double[] getCenterPosition() {
		// TODO Auto-generated method stub
		return null;
	}
	public void moveOrAttack() {
		
			
		ITilePeca[][] matriz=tile.getOtherTiles();
		double dist=0;
		ITilePeca alvo=null;
		if(attackTarget!=null&&attackTarget.getTile()!=null&&attackTarget.getTile().getPeca()==attackTarget) {
			alvo=attackTarget.getTile();
			dist=Tile.dist(alvo,tile);
		}
		else {
			for(int i=0;i<matriz.length;i++) {
				for(int j=0;j<matriz[0].length;j++) {
					if(matriz[i][j].existsPeca()&&matriz[i][j].getPeca().getCor()!=this.cor&&Tile.dist(matriz[i][j], tile)>dist) {
						dist=Tile.dist(matriz[i][j], tile);
						alvo=matriz[i][j];
					}
				}
			}
		}
		if(dist<=alcance&&dist!=0) {
			currentAction="attacking";
			
			moveTarget=null;
			
			if(alvo!=null) {
				attackTarget=alvo.getPeca();
				if(alvo.getPosition()[0]>tile.getPosition()[0]&&flipped)flipped=false;
				if(alvo.getPosition()[0]<tile.getPosition()[0]&&!flipped)flipped=true;
				
			}
			
			currentAnimation=animationFramesAttack;
			currentFrame=0;
		}
		else if(dist!=0) {
			
			
			try {
				direction=chooseDirection(alvo,tried);
				lastPosition=new int[2];
				lastPosition[0]=-direction[0];
				lastPosition[1]=-direction[1];
				
				moveTarget=tile.getOtherTiles()[tile.getPosition()[0]+direction[0]][tile.getPosition()[1]+direction[1]];
				
				attackTarget=null;
				tried=new ArrayList<int[]>();
				tried.add(lastPosition);
				currentAction="moving";
				currentAnimation=animationFramesMove;
				currentFrame=0;
				moveTarget.setMarcado();
			} catch (FormatoInvalido e) {
				System.out.println("formato invalido");
			}catch (MovimentoInvalido e) {
				
				
				if(tried.size()<4) {
					moveOrAttack();
					
					
				}
				else {
					
					esperando=true;
					tried=new ArrayList<int[]>();
					if(lastPosition!=null)tried.add(lastPosition);
					
				}
				
			}
			
		}
	
	}

}
