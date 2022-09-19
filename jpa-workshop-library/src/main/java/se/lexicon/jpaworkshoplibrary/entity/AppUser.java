package se.lexicon.jpaworkshoplibrary.entity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.*;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appUserId;
    @Column(unique = true)
    private String username;
    private String password;
    private LocalDate regDate;

    @OneToOne(cascade = ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "details_id")
    private Details userDetails;

    @OneToMany(
            mappedBy = "borrower",
            cascade = {PERSIST, MERGE, DETACH, REFRESH},
            fetch = FetchType.LAZY
    )
    private List<BookLoan> bookLoans;

    public AppUser() {
    }

    public AppUser(int appUserId, String username, String password, LocalDate regDate, Details userDetails, List<BookLoan> bookLoans) {
        this.appUserId = appUserId;
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.userDetails = userDetails;
        this.bookLoans = bookLoans;
    }

    public AppUser(String username, String password, Details userDetails) {
        this.username = username;
        this.password = password;
        setRegDate(LocalDate.now());
        this.userDetails = userDetails;
        setBookLoans(new ArrayList<>());
    }

    public void addBookLoan(BookLoan bookLoan) {
        if (bookLoan == null) throw new IllegalArgumentException("parameter bookLoan was null");
        if (bookLoans == null) bookLoans = new ArrayList<>();


        if (!bookLoans.contains(bookLoan)) {
            bookLoan.setBorrower(this);
            bookLoans.add(bookLoan);
        }
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Details getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Details userDetails) {
        this.userDetails = userDetails;
    }

    public List<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public void setBookLoans(List<BookLoan> bookLoans) {
        this.bookLoans = bookLoans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return getAppUserId() == appUser.getAppUserId() && Objects.equals(getUsername(), appUser.getUsername()) && Objects.equals(getPassword(), appUser.getPassword()) && Objects.equals(getRegDate(), appUser.getRegDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAppUserId(), getUsername(), getPassword(), getRegDate());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", userDetails=" + userDetails +
                '}';
    }
}
