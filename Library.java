import java.util.*;

public class Library {
    private int curday = 0;
    private List<LibraryItem> items = new ArrayList<>();
    private List<Loan> loanitems = new ArrayList<>();
    private List<Member> memberlist = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void addMember(Member member) {
        memberlist.add(member);
    }

    public Member findMember(String memberId) throws MemberNotFoundException {
        for (Member m : memberlist) {
            if (m.getId().equals(memberId)) {
                return m;
            }
        }
        throw new MemberNotFoundException(memberId);
    }

    public LibraryItem findItem(String itemId) throws ItemNotFoundException {
        for (LibraryItem item : items) {
            if (item.getId().equals(itemId)) {
                return item;
            }
        }
        throw new ItemNotFoundException(itemId);
    }

    public Loan findLoanByItem(String itemId) {
        for (Loan loan : loanitems) {
            if (loan.getItem().getId().equals(itemId)) {
                return loan;
            }
        }
        return null;
    }

    public int countLoansByMember(String memberId) {
        int count = 0;
        for (Loan loan : loanitems) {
            if (loan.getMember().getId().equals(memberId)) {
                count++;
            }
        }
        return count;
    }

    public Loan borrow(String memberId, String itemId) throws LibraryException {
        Member member = findMember(memberId);
        LibraryItem item = findItem(itemId);
        return borrow(item, member);
    }

    public Loan borrow(LibraryItem item, Member member) throws LibraryException {
        if (countLoansByMember(member.getId()) >= member.getMaxItems()) {
            throw new BorrowLimitExceededException(member.getId(), member.getMaxItems());
        }
        if (!(item instanceof Borrowable)) {
            throw new ItemNotAvailableException(item.getId() + " cannot be borrowed");
        }
        if (findLoanByItem(item.getId()) != null) {
            throw new ItemNotAvailableException(item.getId() + " is on loan");
        }

        Loan loan = new Loan(member, item, curday);
        loanitems.add(loan);
        return loan;
    }

    public int returnItem(String itemId) throws LibraryException {
        Loan loan = findLoanByItem(itemId);
        if (loan == null) {
            throw new ItemNotOnLoanException(itemId);
        }
        int fine = loan.getFine(curday);
        loanitems.remove(loan);
        return fine;
    }

    public void advance(int days) {
        curday += days;
    }

    public int getCurDay() {
        return curday;
    }

    public List<LibraryItem> search(String text) {
        List<LibraryItem> result = new ArrayList<>();
        String query = text.toLowerCase();
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(query)) {
                result.add(item);
            }
        }
        result.sort(Comparator.comparing(LibraryItem::getTitle));
        return result;
    }

    public List<Loan> loans(Member member) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : loanitems) {
            if (loan.getMember().getId().equals(member.getId())) {
                result.add(loan);
            }
        }
        result.sort(Comparator.comparingInt(Loan::getDueDay)
                .thenComparing(l -> l.getItem().getId()));
        return result;
    }

    public List<Loan> loans(String memberId) throws MemberNotFoundException {
        Member member = findMember(memberId);
        return loans(member);
    }

    public List<Loan> overdue() {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : loanitems) {
            if (loan.getDaysLate(curday) > 0) {
                result.add(loan);
            }
        }
        result.sort(Comparator.comparingInt((Loan l) -> l.getDaysLate(curday))
                .reversed()
                .thenComparing(l -> l.getItem().getId()));
        return result;
    }

    public String getItemStatus(LibraryItem item) {
        if (!(item instanceof Borrowable)) {
            return "NOT_BORROWABLE";
        }
        Loan loan = findLoanByItem(item.getId());
        if (loan != null) {
            return "ON_LOAN|" + loan.getMember().getId();
        }
        return "AVAILABLE";
    }

    public List<LibraryItem> inventory() {
        List<LibraryItem> result = new ArrayList<>(items);
        result.sort(Comparator.comparing(LibraryItem::getId));
        return result;
    }
}
