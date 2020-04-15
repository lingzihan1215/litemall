package org.linlinjava.litemall.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.linlinjava.litemall.core.notify.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.Executor;

/**
 * 测试邮件发送服务
 * <p>
 * 注意Litemall NotifyService采用异步线程操作
 * 因此测试的时候需要睡眠一会儿，保证任务执行
 * <p>
 * 开发者需要确保：
 * 1. 在notify.properties已经设置正确的邮件服务器信息
 * 2. 在相应的邮箱中设置正确邮件服务器，如开启pop3/smtp服务
 */
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private NotifyService notifyService;

    @Test
    public void testMail() {
        notifyService.notifyMail("litemall邮件测试", "订单1111111已付款，请发货");
    }

    @Configuration
    @Import(Application.class)
    static class ContextConfiguration {
        @Bean
        @Primary
        public Executor executor() {
            return new SyncTaskExecutor();
        }
    }


}
