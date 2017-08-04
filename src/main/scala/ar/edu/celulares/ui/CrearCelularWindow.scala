package ar.edu.celulares.ui

import ar.edu.celulares.domain.Celular
import org.uqbar.arena.windows.WindowOwner
import ar.edu.celulares.home.RepoCelulares

class CrearCelularWindow(owner: WindowOwner) extends EditarCelularWindow(owner, new Celular) {

	override def executeTask() = {
		RepoCelulares.create(getModelObject)
		super.executeTask()
	}

}