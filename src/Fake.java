import com.github.javafaker.Faker;

public class Fake {
    public static void main (String []args){

        Faker fakeData = new Faker();
        System.out.println(fakeData.internet().emailAddress());

    }
}
