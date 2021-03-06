package org.wabase

import spray.json.DefaultJsonProtocol._
import spray.json._

trait JsonSessionEncoder[User] { this: Authentication[User] with AppProvider[User] =>
  import app.qe._
  implicit def userFormat: JsonFormat[User]
  implicit def sessionFormat = jsonFormat4(Authentication.Session[User])

  override def encodeSession(session: Authentication.Session[User]) = session.toJson.toString
  override def decodeSession(session: String) = session.parseJson.convertTo[Authentication.Session[User]]
  override def userInfo(implicit user: User): String = user.toJson.compactPrint

  def dtoJsonFormat[D <: app.qe.DTO: Manifest] = new RootJsonFormat[D] {
    def read(json: JsValue): D = fill[D](json.asJsObject)
    def write(usr: D) = toMap(usr).toJson
  }
}
