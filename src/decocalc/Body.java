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
public class Body {
    
    public Tissue[] tissues;
    
    public Body(){
        tissues = new Tissue[16];
        
//        tissues[0] = new Tissue(4, 32.4);
//        tissues[1] = new Tissue(5, 29.6);
//        tissues[2] = new Tissue(8, 25.4);
//        tissues[3] = new Tissue(12.5, 22.5);
//        tissues[4] = new Tissue(18.5, 20.3);
//        tissues[5] = new Tissue(27.0, 19.0);
//        tissues[6] = new Tissue(38.3, 17.8);
//        tissues[7] = new Tissue(54.3, 16.8);
//        tissues[8] = new Tissue(77, 15.9);
//        tissues[9] = new Tissue(109, 15.2);
//        tissues[10] = new Tissue(146, 14.6);
//        tissues[11] = new Tissue(187, 14.2);
//        tissues[12] = new Tissue(239, 13.9);
//        tissues[13] = new Tissue(305, 13.5);
//        tissues[14] = new Tissue(390, 13.2);
//        tissues[15] = new Tissue(498, 12.9);

        tissues[0] = new Tissue(4, 32.4);
        tissues[1] = new Tissue(8, 25.4);
        tissues[2] = new Tissue(12.5, 22.5);
        tissues[3] = new Tissue(18.5, 20.3);
        tissues[4] = new Tissue(27.0, 19.0);
        tissues[5] = new Tissue(38.3, 17.8);
        tissues[6] = new Tissue(54.3, 16.8);
        tissues[7] = new Tissue(77, 15.9);
        tissues[8] = new Tissue(109, 15.2);
        tissues[9] = new Tissue(146, 14.6);
        tissues[10] = new Tissue(187, 14.2);
        tissues[11] = new Tissue(239, 13.9);
        tissues[12] = new Tissue(305, 13.5);
        tissues[13] = new Tissue(390, 13.2);
        tissues[14] = new Tissue(498, 12.9);
        tissues[15] = new Tissue(635, 12.7);

        
    }
    
    public void addTimeToDepth(double time, double depth){
        for (int i = 0; i < tissues.length; i++){
            tissues[i].addTimeAtDepthV2(time, depth);
        }
    }

    public void addDepthChange(double startDepth, double endDepth, double time){
        for (int i = 0; i < tissues.length; i++){
            tissues[i].addDepthChangeV3(startDepth, endDepth, time);
        }
    }
    
    public void writeTissuesDetails(){
        for (int i = 0; i < tissues.length; i++){
            System.out.println(String.format("Tissue#%d\tp=%f", i, tissues[i].p ));
        }
    }

    public void writeTissuesDetails(int maxDepth){
        for (int i = 0; i < tissues.length; i++){
            double maxP = (double)((double)maxDepth / 10d + 1) * 0.79;
            long percents = Math.round((tissues[i].p-(0.79 - 0.0627)) * 100d / maxP);
            System.out.println(String.format("Tissue#%d\tp=%f \tpercent = %d", i, tissues[i].p, percents));
        }
    }
    
    public int getCeilDepth(){
        double maxPreassure = 1;
        
        for (int i = 0; i < tissues.length; i++){
            if (tissues[i].safetyPreassure > maxPreassure)
                maxPreassure = tissues[i].safetyPreassure;
        }
        
        String depthS = String.format("%.01f", (maxPreassure - 1) * 10);
        depthS = depthS.replace(',', '.');
        float depthF = Float.parseFloat(depthS);
        int depth = (int)Math.round( (maxPreassure - 1) * 10 );
        if ( depthF  > depth)
            depth++;
        while (depth % 3 != 0) 
            depth++;
        
        return depth;
    }
    
    public int getCeilDepthV2(double gf){
        double maxPreassure = 1;
        
        for (int i = 0; i < tissues.length; i++){
            if (tissues[i].getCeiling(gf) > maxPreassure)
                maxPreassure = tissues[i].getCeiling(gf);
        }
        
        String depthS = String.format("%.01f", (maxPreassure - 1) * 10);
        depthS = depthS.replace(',', '.');
        float depthF = Float.parseFloat(depthS);
        int depth = (int)Math.round( (maxPreassure - 1) * 10 );
        if ( depthF  > depth)
            depth++;
        while (depth % 3 != 0) 
            depth++;
        
        return depth;
    }    
    
}
