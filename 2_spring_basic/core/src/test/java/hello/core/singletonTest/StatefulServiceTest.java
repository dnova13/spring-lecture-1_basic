package hello.core.singletonTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("statefull 테스트")
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10,000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB : B사용자 20,000원 주문
        statefulService1.order("userB", 20000);

        // ThreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();

        // 싱글톤 즉 같은 객체를 공유하기 때문에 b 사용자 가격이 덮어 씌어짐.
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    @Test
    @DisplayName("statefull 문제 해결 테스트")
    void statefulServiceSingleton해결() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
//        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // A사용자 10000원 주문
        int userAPrice = statefulService1.orderSolution("userA", 10000);

        // B사용자 20000원 주문
        int userBPrice = statefulService1.orderSolution("userB", 20000);

        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);


        assertThat(userBPrice).isEqualTo(20000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}