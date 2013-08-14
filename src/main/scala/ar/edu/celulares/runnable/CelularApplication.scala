package ar.edu.celulares.runnable

import ar.edu.celulares.ui.BuscarCelularesWindow
import org.uqbar.arena.Application

object CelularApplication extends Application with App {
	
	override def createMainWindow() = new BuscarCelularesWindow(this)
	
	start()
}