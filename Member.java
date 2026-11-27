abstract class Member{
    private String id;
    private String name;
    Member(String id,String name){
        this.id=id;
        this.name = name;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    abstract int getMaxItems();
    abstract int getLoanPeriod();
}
