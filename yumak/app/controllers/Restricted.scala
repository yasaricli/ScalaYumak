package controllers

import play.api.mvc.Controller
import models.User
import views._

object Restricted extends Controller with Secured {

  /**
   * Display restricted area only if user is logged in.
   */
  def index = IsAuthenticated { username =>
    _ =>
      User.getByUsername(username).map { user =>
        Ok(
          html.auth.restricted(user)
        )
      }.getOrElse(Forbidden)
  }
    
}
