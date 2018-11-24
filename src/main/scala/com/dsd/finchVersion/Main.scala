package com.dsd.finchVersion

import com.twitter.app.Flag
import com.twitter.finagle.Http
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import shapeless.HNil

object Main extends TwitterServer {

  val pathBasedVersioning: Flag[Boolean] = flag(
    "pathVersioning",
    false,
    """Indicate whether a path segment should be used for versioning.""")

  def apiVersion(v: String): Endpoint[HNil] =
    if (pathBasedVersioning()) VersionEndpoint.pathBased(v)
    else VersionEndpoint.contentType(v)

  private lazy val api = path("api") :: (
    (apiVersion("1") :: (API.usersEndpointV1 :+: API.departmentsEndpointV1)) :+:
      (apiVersion("2") :: (API.usersEndpointV2 :+: API.departmentsEndpointV2))
  )

  def main(): Unit = {
    val server = Http.server.serve(":8080", api.toServiceAs[Application.Json])
    Await.ready(server)
  }
}
