package com.github.aoiroaoino.json4s_optics

import monocle.{Lens, Prism, Traversal}
import monocle.macros.GenLens
import monocle.function.{At, Each, Index, FilterIndex, Empty}

import scalaz.Applicative
import scalaz.std.list._
import scalaz.syntax.traverse._

import org.json4s.JsonAST.{JValue, JObject, JField}

trait JObjectOptics {

  def _jObject: Prism[JValue, JObject] =
    jObjectPrism

  def jObjectLens: Lens[JObject, List[JField]] =
    GenLens[JObject](_.obj)

  def jObjectPrism = Prism[JValue, JObject] {
    case jo: JObject => Some(jo)
    case _           => None
  }(jValueToJObject)

  private def jValueToJObject(jo: JObject): JValue = jo
}

trait JObjectInstances {

  implicit def jObjectAt: At[JObject, String, Option[JValue]] = new At[JObject, String, Option[JValue]] {
    def at(name: String) = Lens((_: JObject).obj.toMap.get(name)) {
      optV => jo => {
        val map = jo.obj.toMap
        optV.fold(JObject(map - name toList))(v => JObject(map + (name -> v) toList))
      }
    }
  }

  implicit def jObjectIndex: Index[JObject, String, JValue] = Index.atIndex

  implicit def jObjectPrismFilterIndex: FilterIndex[JObject, String, JValue] =
    new FilterIndex[JObject, String, JValue] {
      import scalaz.syntax.applicative._ // ?
      def filterIndex(p: String => Boolean) = new Traversal[JObject, JValue] {
        def modifyF[F[_]: Applicative](f: JValue => F[JValue])(s: JObject): F[JObject] =
          s.obj.traverse { case (k, v) =>
            (if (p(k)) f(v) else v.point[F]).strengthL(k)
          }.map(JObject(_))
      }
    }

  implicit def jObjectEach: Each[JObject, JValue] = new Each[JObject, JValue] {
    def each = new Traversal[JObject, JValue] {
      def modifyF[F[_]: Applicative](f: JValue => F[JValue])(s: JObject): F[JObject] =
        s.obj.traverse { case (k, v) => f(v).strengthL(k) }.map(JObject(_))
    }
  }

  implicit def jObjectEmpty: Empty[JObject] = new Empty[JObject] {
    def empty: Prism[JObject, Unit] =
      Prism((jo: JObject) => if (jo.obj.isEmpty) Some(()) else None)(_ => JObject(List.empty[JField]))
  }
}
