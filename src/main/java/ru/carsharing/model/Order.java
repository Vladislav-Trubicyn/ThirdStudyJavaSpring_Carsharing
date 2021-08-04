package ru.carsharing.model;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private Long carId;
    private String firstname;
    private String surname;
    private String birthday;
    private String sex;
    private String country;
    private String city;
    private String date;
    private String commentAdmin;
    private boolean status;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long user_id)
    {
        this.userId = userId;
    }

    public Long getCarId()
    {
        return carId;
    }

    public void setCarId(Long car_id)
    {
        this.carId = carId;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birhtday)
    {
        this.birthday = birhtday;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCommentAdmin()
    {
        return commentAdmin;
    }

    public void setCommentAdmin(String commentAdmin)
    {
        this.commentAdmin = commentAdmin;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }
}
