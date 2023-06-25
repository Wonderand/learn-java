package reflection.pojo;

public class Cat {

    private String name = "招财猫";

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }
    public int age = 0;

    public void hi() {
        //System.out.println("hi"+name);
    }
    public void eat() {
        System.out.println(name+"eat"+"fish!");
    }
}
