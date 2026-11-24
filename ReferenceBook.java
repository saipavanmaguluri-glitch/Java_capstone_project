public class ReferenceBook extends LibraryItem{
    ReferenceBook(String id,String title){
        super(id,title);
    }
    @Override
    public String getType(){
        return "ReferenceBook";
    }
}