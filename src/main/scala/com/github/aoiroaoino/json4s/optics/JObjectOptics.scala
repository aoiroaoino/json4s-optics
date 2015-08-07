package com.github.aoiroaoino.json4s.optics

import monocle.Prism
import org.json4s.JsonAST.{JValue, JObject}

trait JObjectOptics {

  def jObjectPrism = Prism[JValue, Map[String, JValue]] {
    case jo: JObject => Some(jo.obj.toMap)
    case _           => None
  }(j => JObject(j.toList))
}

object JObjectOptics extends JObjectOptics
