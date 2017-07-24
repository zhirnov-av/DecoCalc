/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decocalc;

/**
 *
 * @author creat0r
 */
public class DecoCalc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Dive dive = new Dive(60, 30, 100, 100);
        dive.writeAscendingPlan();

        for (int i = 0; i < 200; i++){
            if ((i > 0) && (i%100 == 0)){
                System.out.println(String.format("%d %d", i%100, i));
            }

        }
    }
    
    
    
}
