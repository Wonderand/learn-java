package com.huzhirong.ssm.common.config;

import com.huzhirong.ssm.modules.test.pojo.Pig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration //这是一个配置类，替代以前的配置文件。配置类本身也是容器中的组件
// springboot默认值扫描自己主程序所在的包。如果导入第三包，即使组件上标注了@Component，也不会被扫描到
//@EnableConfigurationProperties(Pig.class)// 导入第三方写好的组件进行属性绑定
public class BeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "pig")
    public Pig pig() {
        return new Pig();
    }
}
