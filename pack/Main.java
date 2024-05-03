package pack;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("starting");
            GA problem1=new GA(10,10,10);
            problem1.run();
            GA problem2=new GA(100,100,100);
            problem2.run();
            GA problem3=new GA(1000,100,100);
            problem3.run();
            GA problem4=new GA(100,100,1000);
            problem4.run();
        }
    }

}
