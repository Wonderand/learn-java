package com.huzhirong.ssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *  @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 * 主程序所在的包以及子包都会被扫描
 * 可以用baseComponentsScan来指定扫描的包||@ComponentScan(basePackages = {"com.huzhirong.ssm"})
 *
 */
@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RunApplication.class, args);
        // 获取容器中所有bean的名字
        // 默认所有的bean都是单实例，默认懒加载
        // 容器中有什么组件，就具有什么功能
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }

}
