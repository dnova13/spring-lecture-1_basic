package hello.core.singletonTest;

// 상태 유지 클래스
public class StatefulService {

    private int price; // 상태를 유지하는 빌드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // 여기가 문제!!
        // 즉 쓰기 작업을 하네.
        this.price = price;
    }
    
    // 앞에 order 문제를 해결한 메소드, int로 반환 시킴
    public int orderSolution(String name, int price) {
        System.out.println("name = " + name + " price = " + price);

        return  price;
    }

    public int getPrice() {
        return price;
    }
}