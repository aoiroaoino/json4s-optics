package com.github.aoiroaoino.json4s.optics

import monocle.{Lens, Prism}
import monocle.function.At
import org.json4s.JsonAST.{JValue, JObject}

trait JObjectOptics {

  def jObjectPrism = Prism[JValue, Map[String, JValue]] {
    case jo: JObject => Some(jo.obj.toMap)
    case _           => None
  }(j => JObject(j.toList))

  implicit def jObjectAt: At[JObject, String, JValue] = new At[JObject, String, JValue] {
    def at(field: String) = Lens((_: JObject).obj.toMap.get(field)) {
      optV => jObj => {
        val map = jObj.obj.toMap
        optV.fold(JObject(map - field toList))(v => JObject((map + (field -> v) toList)))
      }
    }
  }
}

object JObjectOptics extends JObjectOptics
