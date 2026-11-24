public class Loan {
    private Member member;
    private LibraryItem item;
    private int borrowDay;
    private int dueDay;

    public Loan(Member member, LibraryItem item, int borrowDay) {
        this.member = member;
        this.item = item;
        this.borrowDay = borrowDay;
        this.dueDay = borrowDay + member.getLoanPeriod();
    }

    public Member getMember() {
        return member;
    }

    public LibraryItem getItem() {
        return item;
    }

    public int getBorrowDay() {
        return borrowDay;
    }

    public int getDueDay() {
        return dueDay;
    }

    public int getDaysLate(int today) {
        return Math.max(0, today - dueDay);
    }

    public int getFine(int today) {
        if (item instanceof Borrowable) {
            return getDaysLate(today) * ((Borrowable) item).getFineRate();
        }
        return 0;
    }
}