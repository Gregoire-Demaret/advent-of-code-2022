package fr.game.advent.advent2022.day07;

import java.util.ArrayList;

public class Dossier {
	private Integer ID;
	private String nom;
	private Dossier dossierParent;
	private Integer taille;
	private ArrayList<Dossier> enfants;
	private Integer profondeur;
	
	public Dossier (Integer ID, String nom, Dossier dossierParent, Integer profondeurParent) {
		this.nom = nom;
		this.dossierParent = dossierParent;
		this.taille = 0;	
		this.profondeur = profondeurParent+1;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getNom() {
		return nom;
	}

	@Override
	public String toString() {
		return "Dossier [nom=" + nom + ", dossierParent=" + dossierParent + ", taille=" + taille + ", enfants="
				+ enfants + ", profondeur=" + profondeur + "]";
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Dossier getDossierParent() {
		return dossierParent;
	}

	public void setDossierParent(Dossier dossierParent) {
		this.dossierParent = dossierParent;
	}

	public Integer getTaille() {
		return taille;
	}

	public void setTaille(Integer taille) {
		this.taille = taille;
	}

	public ArrayList<Dossier> getEnfants() {
		return enfants;
	}

	public void setEnfants(ArrayList<Dossier> enfants) {
		this.enfants = enfants;
	}

	public Integer getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(Integer profondeur) {
		this.profondeur = profondeur;
	}

	public void ajouterTailleAuDossierParent() {
		this.dossierParent.setTaille(this.dossierParent.getTaille()+this.getTaille());
	}
	

}
