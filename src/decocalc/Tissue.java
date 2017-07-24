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
public class Tissue {
    public double tm;
    public double p;
    public double m;
    public double safetyPreassure;
    
    public Tissue(double tm, double m){
        this.p = 0.79d - 0.0627;
        //this.p = 0;
        this.tm = tm;
        this.m = m;
    }
    
    public void addTimeAtDepth(double t, double d){
        double ambientPressure = (d/10.0f + 1) * 0.79;
        //double ambientPressure = (d/10.0f + 1);
        double tmp = ((double)t * 0.5f / tm );
        double dt = 0;
        double dp = ( ambientPressure - p );
        
        
        
        
        if (t > tm){
            tmp = (double)t / this.tm;
            while(dt < t ){
                p = p + ( dp ) * 0.5;
                dp = ambientPressure - p;
                dt = dt + tm;
            }
            p = p + ( dp ) * ((t  - (dt - tm)) * 0.5f / tm);
        }else{
            p = p + ( dp ) * ((t  - dt) * 0.5f / tm);
        }
        
    }
    
    public void addTimeAtDepthV2(double t, double d){
        double ambientPressure = (d/10.0f + 1) * 0.79;
        //double ambientPressure = d;
        double tmp = ((double)t * 0.5f / tm );
        double dt = 0;
        double dp = ( ambientPressure - p );
        double timeConst = Math.log(2)/tm;
        p = p + dp * (1 - Math.exp(-timeConst * t));
    }

    public static double depthChangeInBarsPerMinute(double beginDepth, double endDepth, double time){
        double speed = (endDepth - beginDepth) / time;
        return speed / 10;
    }

    public static double gasRateInBarsPerMinute(double beginDepth, double endDepth, double time, double fGas){
        return depthChangeInBarsPerMinute(beginDepth, endDepth, time) * fGas;
    }
    
    public void addDepthChangeV3(double startDepth, double endDepth, double time){
        double gasRate = gasRateInBarsPerMinute(startDepth, endDepth, time, 0.79);
        double halfTime = this.tm; // half-time constant = log2/half-time in minutes
        double pGas = (startDepth/10.0f + 1) * 0.79; // initial ambient pressure
        double pBegin = this.p; // initial compartment inert gas pressure in bar
        double timeConstant = Math.log(2)/halfTime;
        p = (pGas + (gasRate * (time - (1.0/timeConstant))) - ((pGas - pBegin - (gasRate / timeConstant)) * Math.exp(-timeConstant * time)));
        
        safetyPreassure = getDepthToAscent();
        //p = p + ( ( ( endDepth/10.0d + 1 ) * 0.79d ) - pBegin) * (1 - Math.pow(2, -time/tm));
    }
    
    
    public double getDepthToAscent(){
        double a = 2 * Math.pow(this.tm, -1.0d/3.0d );
        double b = 1.005 - Math.pow(this.tm, -1.0d/2.0d );
        
        return (this.p - a)*b;
    }
    
    public double getCeiling(double gf){
        double a = 2 * Math.pow(this.tm, -1.0d/3.0d );
        double b = 1.005 - Math.pow(this.tm, -1.0d/2.0d );
        
        double bars = (this.p - a * gf)/((gf / b) + 1.0d - gf);
        
        return bars;
    }

    public double getCeilingV2(double gf){
        double a = 2 * Math.pow(this.tm, -1.0d/3.0d );
        double b = 1.005 - Math.pow(this.tm, -1.0d/2.0d );
        
        //double mValue = 
        
        double bars = (this.p - a)*b/gf;
        
        return bars;
    }
    
}

