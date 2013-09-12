package models

import java.util.Date
import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class User(
    username: String, 
    first_name: String, 
    last_name: String, 
    email: String, 
    password: String,
    is_active: Boolean,
    date_joined: Date
)

object User {
  
  // -- Parsers
  
  /**
   * Parse a User from a ResultSet
   */
  val simple = {
    get[String]("username") ~
    get[String]("first_name") ~
    get[String]("last_name") ~
    get[String]("email") ~
    get[String]("password") ~
    get[Boolean]("is_active") ~
    get[Date]("date_joined") map {
      case username ~ first_name ~ last_name ~ email ~ password ~ is_active ~ date_joined => 
          User(username, first_name, last_name, email, password, is_active, date_joined)
    }
  }
  
  // -- Queries
  
  /**
   * Retrieve a User from username.
   */
  def getByUsername(username: String): Option[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from yumak_user where username = {username} and is_active = True").on(
        'username -> username
      ).as(User.simple.singleOpt)
    }
  }
  
  /**
   * Retrieve all users.
   */
  def all: Seq[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from yumak_user").as(User.simple *)
    }
  }
  
  /**
   * Authenticate a User.
   */
  def authenticate(username: String, password: String): Option[User] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
         select * from yumak_user where 
         username = {username} and password = {password} and is_active = True
        """
      ).on(
        'username -> username,
        'password -> password
      ).as(User.simple.singleOpt)
    }
  }
   
  /**
   * Create a User.
   */
  def create(user: User): User = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into yumak_user(
            username, first_name, last_name, email, password, is_active, date_joined
          ) values (
          {username}, {first_name}, {last_name}, {email}, {password}, {is_active}, {date_joined} 
          )
        """
      ).on(
        'username -> user.username,
        'first_name -> user.first_name,
        'last_name -> user.last_name,
        'email -> user.email,
        'password -> user.password,
        'is_active -> user.is_active,
        'date_joined -> user.date_joined
      ).executeUpdate()
      user
    }
  }
  
}
