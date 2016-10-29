package com.github.aoiroaoino

package object json4s_optics {

  object jvalue extends JValueOptics with JValueInstances

  object jobject extends JObjectOptics with JObjectInstances

  object jarray extends JArrayOptics with JArrayInstances

  object jstring extends JStringOptics with JStringInstances

  object jbool extends JBoolOptics with JBoolInstances

  object jnull extends JNullOptics with JNullInstances

  object jnumber extends JNumberOptics with JNullInstances

  object allOptics extends AllOptics

  object allInstances extends AllInstances

  object all extends AllOptics with AllInstances
}
