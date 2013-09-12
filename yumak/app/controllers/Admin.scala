package controllers

import play.api.mvc.Controller
import models.User
import views._

object Admin extends Controller with Secured {

  /**
   * Display dashboard user
   */
  def dashboard = IsAuthenticated { username =>
    _ =>
      User.getByUsername(username).map { user =>
        Ok(html.admin.dashboard(user))
      }.getOrElse(Forbidden)
  }
}
