public class Magazine extends LibraryItem implements Borrowable{
    private String issue;
    Magazine(String id, String title, String issue){
        super(id,title);
        this.issue = issue;
    }
    @Override
    public String getType(){
        return "MAGAZINE";
    }
    @Override 
    public boolean canBorrow(){
        return true;
    }
    @Override 
    public int getFineRate(){
        return 1;
    }
}