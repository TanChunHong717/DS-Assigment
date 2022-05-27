package Titan;

import java.util.Random;

/*
 * Click nfbs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nfbs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
/**
 *
 * @author AINA SOFEA AZHAR
 */
public class NormalTitan extends Titan{
        public int index;
        private int dangerRisk;
        private int height;
        private int walkingLegs;
        private int runningSpeed;
        private String walkingPattern;
        private boolean canClimb;
        
        private static Random r = new Random();
        
        public NormalTitan(){
            Height();
            WalkingLegs();
            RunningSpeed();
            WalkingPattern();
            ClimbingSkill();
        }   
        
        private void Height() {
            height = r.nextInt(30);
            if (height > 20) {
                dangerRisk += 3;
            }else if (height > 10) {
                dangerRisk += 2;       
            }else {
                dangerRisk += 1;
            }
        }   
        
        private void WalkingLegs() {
            int type = r.nextInt(3);
            if (type == 2) {
                dangerRisk += 3;
                walkingLegs = 4;
            }else if (type == 1) {
                dangerRisk += 2;
                walkingLegs = 2;      
            }else {
                dangerRisk += 1;
                walkingLegs = 0;
            }
        }
        
        private void RunningSpeed () {
            runningSpeed = r.nextInt(30);
            if (height > 20) {
                dangerRisk += 3;
            }else if (height > 10) {
                dangerRisk += 2;       
            }else {
                dangerRisk += 1;
            }
        } 
    
        private void WalkingPattern () {
            int type = r.nextInt(3);
            if (type == 2) {
                dangerRisk += 3;
                walkingPattern = "Not repeated pattern";
            }else if (type == 1) {
                dangerRisk += 2;
                walkingPattern = "Repeated pattern";      
            }else {
                dangerRisk += 1;
                walkingPattern = "Normal pattern";
            }
        }
        
        private void ClimbingSkill (){  
            canClimb = r.nextBoolean();
            dangerRisk += (canClimb)? 3: 1;
        }
        
        
        public int getDangerRisk() {
            return dangerRisk;
        }

        public String toString(){
            return String.format("Normal Titan (%dm, %d legs, %dms, %s, %s) Risk = %d", 
                height, walkingLegs, runningSpeed, walkingPattern, (canClimb)?"Can climb":"Can not climb", dangerRisk);
        }
}

    
    

