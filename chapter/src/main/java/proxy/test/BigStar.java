package proxy.test;

public class BigStar implements Star{
    private String name;

    public BigStar() {
    }
    public BigStar(String name) {
        this.name = name;
    }

    //唱歌
    @Override
    public String sing(String name){
        System.out.println(this.name+"sing!"+name);
        return "谢谢！";
    }
    //跳舞
    @Override
    public void dance(){
        System.out.println(this.name+"dance!"+name);
    }

    public String getName() {
        return name;
    }
}
