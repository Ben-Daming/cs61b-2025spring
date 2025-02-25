public class NBody{
    //get the universe radius in txt
    private static double readRadius(String text){
        In in = new In(text);
        //initialize the txt
        in.readInt();//filter out the first element
        double radius=in.readDouble();
        return radius;
    }
    private static Planet[] readPlanets(String text){
        In in = new In(text);//initialize the txt
        int n=in.readInt();//get the number of planets
        Planet[] planets=new Planet[n];//initialize the planet array
        in.readDouble();//filter out the universe length
        for(int i=0;i<n;i++){
           double px,py,vx,vy,m;
           String g;
           px=in.readDouble();
           py=in.readDouble();
           vx=in.readDouble();
           vy=in.readDouble();
           m=in.readDouble();
           g=in.readString();
           planets[i]=new Planet(px,py,vx,vy,m,g);
        }
        return planets;
    }
    public static void main(String[] args){
        //initialize
        StdDraw.enableDoubleBuffering();
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double universe_radius=readRadius(filename);
        Planet[] planets=readPlanets(filename);
        StdDraw.setScale(-universe_radius,universe_radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg");
        for (Planet p:planets){
            p.draw();
        }
        StdDraw.show();
        //trace animation
        double t=0;
        while(t<T){
            double[] xForces=new double[planets.length],yForces=new double[planets.length];
            for(int i=0;i<planets.length;i++){
               xForces[i]=planets[i].calcNetForceExertedByX(planets);
               yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i=0;i<planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n",universe_radius);
        for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }


    }
}
