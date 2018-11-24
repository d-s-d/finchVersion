package com.dsd.finchVersion

import com.twitter.util.Future

final case class User(username: String, first: String, last: String)

object User {
  def get(i: Int): Future[User] =
    if (0 <= i && i < users.size) Future { users(i) } else
      Future.exception(new IllegalArgumentException(""))

  private lazy val users = Seq(
    User("newton", "Isaac", "Newton"),
    User("albi", "Albert", "Einstein"),
    User("feyny", "Richard", "Feynman")
  )
}
