package com.dsd.finchVersion

import com.twitter.util.Future

final case class Department(name: String,
                            abbreviation: String,
                            street: String,
                            city: String)

object Department {
  def get(i: Int): Future[Department] =
    if (0 <= i && i <= departments.size) Future { departments(i) } else
      Future.exception(new IllegalArgumentException(""))

  private lazy val departments = Seq(
    Department("Department of Defense",
               "DoD",
               "1849 C Street, N.W.",
               "Washington DC 20240"),
    Department("Department of the Interior",
               "DoI",
               "1400 Defense Pentagon",
               "Washington, DC 20301-1400")
  )
}
