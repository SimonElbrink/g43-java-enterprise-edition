package se.lexicon.spring_bootjpalecture.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    //Fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Means AUTO_INCREMENT
    private int id;

    @Column(length = 100, nullable = false) // not null varchar(100)
    private String firstName;

    @Column(length = 100, nullable = false)
    private String lastName;

    @Column(length = 200, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(columnDefinition = "bit not null default true")
    private boolean status;

//    https://thorben-janssen.com/persist-creation-update-timestamps-hibernate/
    @CreationTimestamp //adds a timestamp when object is added to the Database.
    //@Column(name="register_date", columnDefinition="TIMESTAMP DEFAULT now()") // Also works (Dependent of Database)
    @Column(name = "register_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registerDate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    //Auto generated code by JPA = foreign key (address_id) reference address(id)
    private Address address;


    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<Book> listOfOwnedBooks;


    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH},
            mappedBy = "students")
    private Set<Course> courses;

    //Constructors
    protected Student() {}

    public Student(int id, String firstName, String lastName, String email, LocalDate birthDate, boolean status, LocalDateTime registerDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.status = status;
        this.registerDate = registerDate;
    }

    public Student(String firstName, String lastName, String email, LocalDate birthDate, boolean status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.status = status;
    }

    public Student(String firstName, String lastName, String email, LocalDate birthDate, boolean status, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.status = status;
        this.address = address;
    }

    //Custom methods

    public void addBook(Book book){

        if (book == null) throw new IllegalArgumentException("Invalid parameter: Book was null");
        if (listOfOwnedBooks == null) listOfOwnedBooks = new ArrayList<>();

        listOfOwnedBooks.add(book);
        book.setStudent(this);
    }


    public void removeBook(Book book){
        if (book == null) throw new IllegalArgumentException("Invalid parameter: Book was null");

        if (listOfOwnedBooks != null){

            if (listOfOwnedBooks.contains(book)){
                    book.setStudent(null);
                    listOfOwnedBooks.remove(book);
            }

        }
    }

    //Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Book> getListOfOwnedBooks() {
        return listOfOwnedBooks;
    }

    public void setListOfOwnedBooks(List<Book> listOfOwnedBooks) {
        this.listOfOwnedBooks = listOfOwnedBooks;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getId() == student.getId() && isStatus() == student.isStatus() && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getEmail(), student.getEmail()) && Objects.equals(getBirthDate(), student.getBirthDate()) && Objects.equals(getRegisterDate(), student.getRegisterDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getBirthDate(), isStatus(), getRegisterDate());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", status=" + status +
                ", registerDate=" + registerDate +
                ", address=" + address +
                '}';
    }
}
