package com.github.aoiroaoino.json4s_optics

import monocle.function.Plated

import org.json4s.JsonAST._

class JValueSpec extends TestSuite {
  import jvalue._

  val json: JValue = JObject(
    "name"      -> JString("aoino"),
    "age"       -> JInt(25),
    "favorites" -> JArray(List(
      JString("scala"),
      JString("haskell")
    )),
    "address"   -> JObject(List(
      "country"  -> JString("japan"),
      "city"     -> JString("tokyo"),
      "postcode" -> JInt(12345)
    ))
  )

  describe("Plated") {

    it("rewrite: Apply toUpperCase to all JString") {
      Plated.rewrite[JValue] {
        case JString(s) if s != s.toUpperCase => Some(JString(s.toUpperCase))
        case _                                => None
      }(json) shouldEqual JObject(
        "name"      -> JString("AOINO"),
        "age"       -> JInt(25),
        "favorites" -> JArray(List(
          JString("SCALA"),
          JString("HASKELL")
        )),
        "address"   -> JObject(List(
          "country"  -> JString("JAPAN"),
          "city"     -> JString("TOKYO"),
          "postcode" -> JInt(12345)
        ))
      )
    }

    it("children") {
      Plated.children[JValue](json) shouldEqual
        List(
          JString("aoino"),
          JInt(25),
          JArray(List(
            JString("scala"),
            JString("haskell")
          )),
          JObject(List(
            "country"  -> JString("japan"),
            "city"     -> JString("tokyo"),
            "postcode" -> JInt(12345)
          ))
        )
    }

    it("transform: JInt to JDouble") {
      Plated.transform[JValue] {
        case JInt(i) => JDouble(i.toDouble)
        case x       => x
      }(json) shouldEqual JObject(
        "name"      -> JString("aoino"),
        "age"       -> JDouble(25.0),
        "favorites" -> JArray(List(
          JString("scala"),
          JString("haskell")
        )),
        "address"   -> JObject(List(
          "country"  -> JString("japan"),
          "city"     -> JString("tokyo"),
          "postcode" -> JDouble(12345.0)
        ))
      )
    }
  }
}
