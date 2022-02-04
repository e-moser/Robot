package frc.robot.commands;

public class CalcEngine {
    
  //Everything in Meters
  public static double velo = 0.0;
  public static double distance = 0.0;
  public static double robotHeight = 0.0;
  public static double targetHeight = 0.0;
  public static double angle = 0.0;

  public static void getVelo(){
      
    double b = angle;
    double a = distance;
    double c = targetHeight;
    double d = robotHeight;

    double initial = Math.sqrt(4.9) * a;
    double firstSqrt = Math.cos(b) * ((a * Math.sin(b)) - (c * Math.cos(b)) + (d * Math.cos(b)));
    double secondSqrt = (a * Math.cos(b) * Math.sin(b)) - (c * Math.cos(b) * Math.cos(b)) + (d * Math.cos(b) * Math.cos(b));
    velo = (initial * firstSqrt) / secondSqrt;
  }

}
