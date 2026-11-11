public class FacultyMember extends Member {
    public FacultyMember(String memberID, String memberName) {
        super(memberID, memberName);
    }

    @Override
    public void displayMemberDetails() {
        System.out.println("Membership : Faculty");
        System.out.println("Member Name: " + getMemberName());
        System.out.println("Member ID: " + getMemberID());
    }
}
