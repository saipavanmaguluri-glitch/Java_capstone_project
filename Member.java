abstract class Member{
    private String id;
    private String name;
    Member(String id,String name){
        this.id=id;
        this.name = name;
    }
    abstract int getMaxItems();
    abstract int getLoanPeriod();
}
