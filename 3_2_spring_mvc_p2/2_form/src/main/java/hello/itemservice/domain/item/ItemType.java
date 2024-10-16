package hello.itemservice.domain.item;

public enum ItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

    ItemType(String description) {
        this.description = description;
    }

//    addForm.html에서 프로퍼티 접근법으로 ${type.description} 사용하기 때문에 getter 추가
    public String getDescription() {
        return description;
    }
}