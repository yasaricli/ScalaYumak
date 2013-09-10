package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import java.math.BigDecimal
import java.util.Date


case class Product(
    id: Pk[Long], 
    name: String, 
    slug: String, 
    price:BigDecimal, 
    active: Boolean, 
    description: String, 
    create_date:Date
)

object Product {

    val mapper = {
        get[Pk[Long]]("id") ~
        get[String]("name") ~
        get[String]("slug") ~
        get[BigDecimal]("price") ~
        get[Boolean]("active") ~
        get[String]("description") ~ 
        get[Date]("create_date") map {
            case id ~ name ~ slug ~ price ~ active ~ description ~ create_date => 
                Product(id, name, slug, price, active, description, create_date)
        }
    }

    // Product.all() --> product list
    def all(): List[Product] = DB.withConnection { implicit connection => 

        SQL("Select * From product").as(mapper *)
    }

    // Product.get(slug='dsds-dsd')
    def getBySlug(slug: String): Option[Product] = {
        DB.withConnection { implicit connection =>

            SQL("select * from product where slug = {slug}").on('slug -> slug).as(Product.mapper.singleOpt)
        }
    }
}
