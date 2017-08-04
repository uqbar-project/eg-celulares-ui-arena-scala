package ar.edu.celulares.ui

import org.uqbar.lacar.ui.model.Action

object ActionWrapper {

  implicit def lambdaToAction(action: () => Unit) = new Action() {
    override def execute() {
      action.apply()
    }
  }

}