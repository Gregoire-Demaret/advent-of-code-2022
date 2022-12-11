package fr.game.advent.advent2022.day11;

import java.util.ArrayList;

public class Singe {
	
	private ArrayList<Integer> objets;
	private Integer singeSiVrai;
	private Integer singeSiFaux;
	private Integer modulo;
	private Integer numSinge;
	private Integer nombreObjetsInspectes;

	public Singe(Integer numSinge, ArrayList<Integer> objets, Integer singeSiVrai, Integer singeSiFaux,
			Integer modulo) {
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

	public void addObjet(Integer objetRecu) {
		this.objets.add(objetRecu);
	}

	public ArrayList<Integer> getObjets() {
		return objets;
	}

	public void setObjets(ArrayList<Integer> objets) {
		this.objets = objets;
	}

	public Integer lancerObjet() {
		Integer objetALancer = this.objets.get(0);
		this.objets.remove(0);
		if (objetALancer % modulo == 0) {
			return singeSiVrai;
		} else
			return singeSiFaux;
	}

	public void modifierObjet() {
		Integer oldWorry = this.objets.get(0);
		Integer newWorry = 0;
		switch (this.numSinge) {
		case 0:
			newWorry = oldWorry * 13;
			newWorry = newWorry / 3;
			break;
		case 1:
			newWorry = oldWorry * oldWorry;
			newWorry = newWorry / 3;
			break;
		case 2:
			newWorry = oldWorry + 6;
			newWorry = newWorry / 3;
			break;
		case 3:
			newWorry = oldWorry + 2;
			newWorry = newWorry / 3;
			break;
		case 4:
			newWorry = oldWorry + 3;
			newWorry = newWorry / 3;
			break;
		case 5:
			newWorry = oldWorry + 4;
			newWorry = newWorry / 3;
			break;
		case 6:
			newWorry = oldWorry + 8;
			newWorry = newWorry / 3;
			break;
		case 7:
			newWorry = oldWorry * 7;
			newWorry = newWorry / 3;
			break;

		}
		this.objets.set(0, newWorry);
		this.nombreObjetsInspectes++;

	}

	public Integer valeurObjet0() {
		return this.objets.get(0);
	}

}
