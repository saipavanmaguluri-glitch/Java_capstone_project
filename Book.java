public class Book extends LibraryItem implements Borrowable{
    private String author;
    Book(String id, String title,String author){
        super(id,title);
        this.author = author;
    }
    @Override
    public String getType(){
        return "Book";
    }
    @Override 
    public boolean canBorrow(){
        return true;
    }
    @Override
    public int getFineRate(){
        return 2;
    }
}