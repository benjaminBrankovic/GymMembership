import java.time.LocalDate;

/**
 * Created by Benjamin Brankovic
 * Date: 2020-10-14
 * Time: 21:35
 * Project: BigJavaMap
 */
public class Member {

    protected String fullName;
    protected String socialSecurityNumber;
    protected LocalDate dateOfMembership;

    public Member(String name, String socialSecurityNumber, LocalDate dateOfMembership){
        this.fullName = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfMembership = dateOfMembership;
    }

    public String getFullName(){
        return fullName;
    }

    public String getSocialSecurityNumber(){
        return socialSecurityNumber;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }
}