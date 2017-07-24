/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decocalc;

/**
 *
 * @author Zhirnov.AV
 */
public class Dive {
    int depth;
    int timeAtDepth;
    Body diver;
    
    double gfLow = 1;
    double gfHigh = 1;
    int lastStop = 3;
    
    public Dive(int depth, int timeAtDepth, int gfLow, int gfHigh){
        diver = new Body();
        
        this.depth = depth;
        this.gfLow = (double)gfLow/100.0d;
        this.gfHigh = (double)gfHigh/100.0d;
       
        double tmDescending = (double)(this.depth) / 20.0f;
        double tmAtDepth = (double)(timeAtDepth) - tmDescending;
        
        
        diver.writeTissuesDetails(depth);
        diver.addDepthChange(0, this.depth, tmDescending);
        System.out.println(String.format("Descending to %d. Time to descend: %.02f", this.depth, tmDescending));
        diver.writeTissuesDetails(depth);
        diver.addDepthChange(this.depth, this.depth, tmAtDepth);
        System.out.println(String.format("Staying at %d for %.02f minutes", this.depth, tmAtDepth));
        diver.writeTissuesDetails(depth);
    }
    
    public double writeAscendingPlan(){
        double summTime = 0;

        double gfDiff = (gfHigh - gfLow)/depth;
        
        double gf = gfLow;
        int currentDepth = depth;
        while (currentDepth - 10 >= diver.getCeilDepthV2(gfLow)){
            diver.addDepthChange(currentDepth, currentDepth - 10, 1.0d);
            currentDepth = currentDepth - 10;
            System.out.println(String.format("ascent to: %d time: 1", currentDepth));
            diver.writeTissuesDetails(depth);
            summTime += 1;
        }

        int decoStop = diver.getCeilDepthV2(gfLow);
        diver.addDepthChange(currentDepth, decoStop, (double)(currentDepth - decoStop)/10.0d);
        summTime += (double)(currentDepth - decoStop)/10.0d;
        while (decoStop > 0){
            gfDiff = (gfHigh - gfLow)/(currentDepth - lastStop);
            gf = gfLow + (gfDiff * (currentDepth - decoStop));
            int nextDecoStop = decoStop - 3;
            int time = 0;

            while (diver.getCeilDepthV2(gf) > nextDecoStop){
                diver.addDepthChange(decoStop, decoStop, 1);
                time++;
            }
            if (time == 0) time++;
            System.out.println(String.format("stop at: %d time: %d currentGF: %d", decoStop, time, Math.round(gf * 100)));
            summTime += time;
            diver.writeTissuesDetails(depth);
            diver.addDepthChange(decoStop, nextDecoStop, 0.5d);
            summTime += (double)(decoStop - nextDecoStop)/10.0d;
            decoStop = diver.getCeilDepthV2(gf);
        }
        return summTime;
    }
    
    
}
