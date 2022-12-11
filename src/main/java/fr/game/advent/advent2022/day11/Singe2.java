package fr.game.advent.advent2022.day11;

import java.util.ArrayList;

public class Singe2 {

	private ArrayList<Long> objets;
	private Integer singeSiVrai;
	private Integer singeSiFaux;
	private Integer modulo;
	private Integer numSinge;
	private Integer nombreObjetsInspectes;

	public Singe2(Integer numSinge, ArrayList<Long> objets, Integer singeSiVrai, Integer singeSiFaux, Integer modulo) {
		this.numSinge = numSinge;
		this.objets = objets;
		this.singeSiVrai = singeSiVrai;
		this.singeSiFaux = singeSiFaux;
		this.modulo = modulo;
		this.nombreObjetsInspectes = 0;
	}

	public Integer getNumSinge() {
		return numSinge;
	}

	public Integer getNombreObjetsInspectes() {
		return nombreObjetsInspectes;
	}

	public void addObjet(Long objetRecu) {
		this.objets.add(objetRecu);
	}

	public ArrayList<Long> getObjets() {
		return objets;
	}

	public void setObjets(ArrayList<Long> objets) {
		this.objets = objets;
	}

	public Integer lancerObjet() {
		Long objetALancer = this.objets.get(0);
		this.objets.remove(0);
		if (objetALancer % modulo == 0) {
			return singeSiVrai;
		} else
			return singeSiFaux;
	}

	public void modifierObjet() {
		Long oldWorry = this.objets.get(0);
		Long newWorry = 0L;
		switch (this.numSinge) {
		case 0:
			newWorry = oldWorry * 13;
			break;
		case 1:
			newWorry = oldWorry * oldWorry;
			break;
		case 2:
			newWorry = oldWorry + 6;
			break;
		case 3:
			newWorry = oldWorry + 2;
			break;
		case 4:
			newWorry = oldWorry + 3;
			break;
		case 5:
			newWorry = oldWorry + 4;
			break;
		case 6:
			newWorry = oldWorry + 8;
			break;
		case 7:
			newWorry = oldWorry * 7;
			break;

		}
		this.objets.set(0, newWorry);
		this.nombreObjetsInspectes++;

	}

	public Long valeurObjet0() {
		return this.objets.get(0);
	}

}
