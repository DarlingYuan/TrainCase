package fight;

import static org.junit.Assert.*;

import org.junit.Test;

public class fighttest {

   @Test
   public void test_given_12_soldier_then_kill_12_monster(){
       Soldier soldier = new Soldier(12);
       Monster monster = new Monster(12);
       assertTrue(soldier.kill(monster));
   }
   @Test
   public void test_given_12_soldier_then_unkill_13_monster(){
       Soldier soldier = new Soldier(12);
       Monster monster = new Monster(13);
       assertFalse(soldier.kill(monster));
   }
   @Test
   public void test_given_12_archmage_then_kill_18_monster(){
       Player archmage = new Archmage(12);
       Monster monster = new Monster(18);
       assertTrue(archmage.kill(monster));
   }
   @Test
   public void test_given_12_archmage_then_unkill_19_monster(){
       Player archmage = new Archmage(12);
       Monster monster = new Monster(19);
       assertFalse(archmage.kill(monster));
   }
   
   @Test
   public void test_given_12_soldier_use_storm_then_kill_18_monster(){
       Soldier soldier = new Soldier(12,Skill.STORM);
       Monster monster = new Monster(18);
       assertTrue(soldier.kill(monster));
   }
   
   @Test
   public void test_given_12_soldier_use_storm_then_unkill_19_monster(){
       Soldier soldier = new Soldier(12,Skill.STORM);
       Monster monster = new Monster(19);
       assertFalse(soldier.kill(monster));
   }
   
   @Test
   public void test_given_12_soldier_use_behead_then_kill_21_monster(){
       Soldier soldier = new Soldier(12,Skill.BEHEAD);
       Monster monster = new Monster(21);
       assertTrue(soldier.kill(monster));
   }
   
   @Test
   public void test_given_12_soldier_use_behead_then_unkill_22_monster(){
       Soldier soldier = new Soldier(12,Skill.BEHEAD);
       Monster monster = new Monster(22);
       assertFalse(soldier.kill(monster));
   }
   
   public void test_given_archmage_12_use_fire_them_kill_24_monster(){
       Archmage archmage = new Archmage(12,Skill.FIRE);
       Monster monster = new Monster(24);
       assertTrue(archmage.kill(monster));
   }
   
   public void test_given_archmage_12_use_fire_them_unkill_25_monster(){
       Archmage archmage = new Archmage(12,Skill.FIRE);
       Monster monster = new Monster(25);
       assertTrue(archmage.kill(monster));
   }
   
   public void test_given_archmage_12_use_freeze_them_kill_29_monster(){
       Archmage archmage = new Archmage(12,Skill.FREEZE);
       Monster monster = new Monster(29);
       assertTrue(archmage.kill(monster));
   }
   
   public void test_given_archmage_12_use_freeze_them_unkill_30_monster(){
       Archmage archmage = new Archmage(12,Skill.FREEZE);
       Monster monster = new Monster(30);
       assertTrue(archmage.kill(monster));
   }

}
