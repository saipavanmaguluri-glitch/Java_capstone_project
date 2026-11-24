public class ReferenceBook extends LibraryItem{
    private String author;
    ReferenceBook(String id,String title,String author){
        super(id,title);
        this.author = author;
    }
    @Override
    public String getType(){
        return "REFERENCE";
    }
}