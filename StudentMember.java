public class StudentMember extends Member{
    public StudentMember(String memberID, String memberName){
        super(memberID, memberName);
    }
    @Override
    public void displayMemberDetails() {
        System.out.println("Membership: Student");
        System.out.println("Member ID: " + getMemberID());
        System.out.println("Member Name: " + getMemberName());
    }
}
