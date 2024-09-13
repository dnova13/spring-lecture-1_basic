package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import
        org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.*;

public class PrototypeProviderTest {
    @Test
    void providerTest() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }


    /*
     * 싱글톤빈과 프로토타입빈을 같이 사용할 때 사용할 때마다 항상 새로운 빈을 요청하기 위해서
     * 싱글톤 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새로 요청하는 것이다.
     * */
    static class ClientBean {

        // 1. 클라이언트 빈이 그냥 오토 와이어드로 애플리케이션 컨텍스트를 받음.
        @Autowired
        private ApplicationContext ac;


        // 2. 해당 로직 메소드를 호출할 때마다 그냥 컨테이너에서 받으면 됨
        public int logic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}