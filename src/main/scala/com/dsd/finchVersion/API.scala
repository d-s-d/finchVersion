package com.dsd.finchVersion

import io.finch.syntax._
import io.finch._

object API {
  lazy val usersEndpointV1: Endpoint[UserV1] = get(
    "users"
      :: path[Int]) { id: Int =>
    User
      .get(id)
      .map(u => UserV1(u.username, s"${u.first} ${u.last}"))
      .map(Ok)
  }.handle(excHandler)

  lazy val usersEndpointV2: Endpoint[User] = get("users" :: path[Int]) {
    id: Int =>
      User.get(id).map(Ok)
  }.handle(excHandler)

  lazy val departmentsEndpointV1: Endpoint[DepartmentV1] =
    get("departments" :: path[Int]) { id: Int =>
      Department
        .get(id)
        .map(d => DepartmentV1(d.name, s"${d.street}\n${d.city}"))
        .map(Ok)
    }.handle(excHandler)

  lazy val departmentsEndpointV2: Endpoint[Department] =
    get("departments" :: path[Int]) { id: Int =>
      Department.get(id).map(Ok)
    }.handle(excHandler)

  private def excHandler[B]: PartialFunction[Throwable, Output[B]] = {
    case e: IllegalArgumentException => NotFound(e)
  }
}

final case class UserV1(username: String, name: String)
final case class DepartmentV1(name: String, address: String)
