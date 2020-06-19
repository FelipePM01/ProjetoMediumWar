package peca;

import java.awt.Graphics;
import java.awt.Image;

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
}
