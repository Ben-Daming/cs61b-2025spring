public class Planet{
    //gravity constant
    private static double G=6.67e-11;

    //instance variables annoucement

    public double xxPos,yyPos,xxVel,yyVel,mass;
    public String imgFileName;
    //initialize
    public Planet(double xP,double yP,double xV,
            double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    //copy a Body
    public Planet(Planet b){
        this.xxPos=b.xxPos;
        this.yyPos=b.yyPos;
        this.xxVel=b.xxVel;
        this.yyVel=b.yyVel;
        this.mass=b.mass;
        this.imgFileName=b.imgFileName;
    }
    //calculate the distance
    public double calcDistance(Planet b){
        double dx,dy;
        dx=this.xxPos-b.xxPos;
        dy=this.yyPos-b.yyPos;
        return Math.sqrt(dx*dx+dy*dy);
    }
    //calculate the force by the given planet
    public double calcForceExertedBy(Planet b){
        return Planet.G*b.mass*this.mass/(calcDistance(b)*calcDistance(b));
    }
    //decompose the force by x and y
    public double calcForceExertedByX(Planet b){
        double dx=this.xxPos-b.xxPos;
        return (-1)*this.calcForceExertedBy(b)*dx/calcDistance(b);
    }
    public double calcForceExertedByY(Planet b){
        double dy=this.yyPos-b.yyPos;
        return (-1)*this.calcForceExertedBy(b)*dy/calcDistance(b);
    }
    //sumup all the force in tht net system on this planet
    public double calcNetForceExertedByX(Planet[] planets){
        double sum=0;
        for (Planet p: planets){
            if(this.equals(p)){

                continue;
            }
            else{
                sum+=calcForceExertedByX(p);
            }
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double sum=0;
        for (Planet p: planets){
            if(this.equals(p)){

                continue;
            }
            else{
                sum+=calcForceExertedByY(p);
            }
        }
        return sum;
    }
    //build an update method that 
    //change the velocity and position within dt
    public void update(double dt,double force_x,double force_y){
        //get acceleration
        double a_x=force_x/this.mass;
        double a_y=force_y/this.mass;
        //velocity update
        this.xxVel+=dt*a_x;
        this.yyVel+=dt*a_y;
        //Position update;
        this.xxPos+=this.xxVel*dt;
        this.yyPos+=this.yyVel*dt;
    }
    //make a draw method
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
    

}
