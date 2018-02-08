package rs.enetel.basketballleague.dao;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person
{
	private String firstName;
	private String lastName;
	private Date birthday;
}
