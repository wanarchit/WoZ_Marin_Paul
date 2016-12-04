/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marin_paul;

/**
 *
 * @author Paul
 */
public class Battle {
    
    private NPC myEnemy;
    private Player myPlayer;
    private boolean enemyKO=false;
    
    public Battle(Player player,NPC enemy){
        myEnemy = enemy;
        myPlayer = player;
    }
    
    public void beat(){
            int hpP = myPlayer.getHP();
            int hpE = myEnemy.getHP();
            if(hpP !=0 && hpE !=0){
                playerAttack(2);
                hpE = myEnemy.getHP();
            }
            if(hpP !=0 && hpE !=0){
                enemyAttack(1);
                hpP = myPlayer.getHP();
            }
            if(hpP ==0|| hpE ==0){
            }
            if(hpP ==0){  
            }
            if(hpE ==0){
                enemyKO = true;
            }
        
    }
    
   
    public void enemyAttack(int intensity){
        int damage = (int)((myEnemy.getTotalStr()*intensity*myEnemy.getHP()*0.01)/(myPlayer.getTotalDef()*myPlayer.getHP()*0.01));
        if(damage==0){
            myPlayer.loseHP(-1);
        }
        else{
            myPlayer.loseHP(-damage);
        }
    }
    
    public void playerAttack(int intensity){
        int damage = (int)((myPlayer.getTotalStr()*intensity*myPlayer.getHP()*0.01)/(myEnemy.getTotalDef()*myEnemy.getHP()*0.01));
        if(damage==0){
            myEnemy.loseHP(-1);
        }
        else{
            myEnemy.loseHP(-damage);
        }
    }
    
    public boolean isEnemyKO(){
        return enemyKO;
    }
}
