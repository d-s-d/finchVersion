package com.dsd.finchVersion

import com.twitter.util.Future
import io.catbird.util.Rerunnable
import io.finch._
import shapeless.HNil

object VersionEndpoint {
  private[this] val apiVersionScheme =
    "application/vnd.example.com.v([1-9][0-9]?)\\+json".r

  def pathBased(v: String): Endpoint[HNil] = path(s"v$v")

  def contentType(v: String): Endpoint[HNil] = (input: Input) => {
    input.request.headerMap.get("Accept") match {
      case Some(apiVersionScheme(version)) if v == version =>
        EndpointResult.Matched(input, Trace.empty, EmptyOutput)
      case _ =>
        EndpointResult.NotMatched
    }
  }

  val EmptyOutput: Rerunnable[Output[HNil]] =
    new Rerunnable[Output[HNil]] {
      override val run: Future[Output[HNil]] =
        Future.value(Output.payload(HNil))
    }
}
