public class Staff extends Member {
    Staff(String id, String name){
        super(id,name);
    }
    @Override 
    public int getMaxItems(){
        return 4;
    }
    @Override 
    public int getLoanPeriod(){
        return 21;
    }
    
}
