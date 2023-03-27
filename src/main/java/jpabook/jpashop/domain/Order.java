package jpabook.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // == protected Order(){} 이랑 같음 무분별한 생성을 막기 위함 반드시 createOrder 사용할것
//코드를 제약하는 설계는 유지보수에 도움이 됨
public class Order { //Order가 persist되면 하위 객체도 persist된다. cascade의 범위는 클래스 내부의 객체만 사용할때 걸자 //
    // 외부의 객체가 자주 사용하는 객체의 경우에는 cascade를 걸지말고 별도의 repository를 만들자
    @Id@GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;//주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status;//주문상태 (order, cancel)

    //==연관관계 편의 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
    //==생성 메서드 ==//
    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비지니스 로직==//

    //주문 취소
    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    //== 조회 로직==//


    //전체 주문 가격
    public  int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem  : orderItems){ //alt enter 로 축약가능
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
