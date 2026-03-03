package trash;

public class Main {

    void main(String[] args) {
        try {
            Game.start();
        } catch (Exception e){
            System.out.println("Sad things happened");
            e.printStackTrace();
        }
    }
}