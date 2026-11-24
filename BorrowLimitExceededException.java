public class BorrowLimitExceededException extends LibraryException {
    BorrowLimitExceededException(String memid,int max){
        super(memid + " has reached the limit of " + max);
    }
}
