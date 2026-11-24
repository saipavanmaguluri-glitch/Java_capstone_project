abstract class LibraryItem{
    private String id;
    private String title;
    LibraryItem(String id, String title){
        this.id = id;
        this.title = title;
    }
    public String getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    abstract public String getType();
}