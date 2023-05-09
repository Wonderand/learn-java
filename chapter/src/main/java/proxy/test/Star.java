package proxy.test;

public interface Star {

    //我们将所有要代理的方法都定义在接口中
    public abstract String sing(String name);
    public abstract void dance();
}
