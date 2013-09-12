import play.api._

import java.util.Date
import models._
import anorm._

object Global extends GlobalSettings {
  
  override def onStart(app: Application) {
    InitialData.insert()
  }
  
}

/**
 * Initial set of data to be imported 
 */
object InitialData {
  
  def insert() = {
    
    if(User.all.isEmpty) {
        
      val d:Date = new Date();  
      Seq(
        User("test", "Test", "page", "test@gmail.com", "123456", true, d)
      ).foreach(User.create)
      
    }
    
  }
  
}
