package org.ldv.savonapi.service

import org.ldv.savonapi.DTO.LigneIngredientDTO
import org.ldv.savonapi.DTO.RecetteFormDTO
import org.ldv.savonapi.model.dao.*
import org.ldv.savonapi.model.entity.LigneIngredient
import org.ldv.savonapi.model.entity.Recette
import org.ldv.savonapi.model.entity.Resultat
import org.ldv.savonapi.model.id.LigneIngredientId
import org.ldv.savonapi.model.id.ResultatId

import org.springframework.stereotype.Service


@Service
class SimulateurService(
    val caracteristiquesDAO: CaracteristiquesDAO,
    val recetteDAO: RecetteDAO,
    val ingredientDAO: IngredientsDAO,
    val ligneIngredientDAO: LigneIngredientDAO,
    val mentionDAO : MentionDAO,
    val resultatDAO: ResultatDAO
) {

    fun toLigne(ligneIngredientDTO: LigneIngredientDTO,recette: Recette) : LigneIngredient{
         var ingredient = ingredientDAO.findById(ligneIngredientDTO.IngredientId).get()
         val ligneIngredientID :LigneIngredientId= LigneIngredientId(recette.id!!,ingredient.ingredientId)
         val ligneIngredient : LigneIngredient=LigneIngredient(ligneIngredientID,ingredient,ligneIngredientDTO.quantite,ligneIngredientDTO.pourcentage,recette)
        return ligneIngredient
    }

    fun createResultat(recette: Recette): MutableList<Resultat>{
        var caracteristiques = caracteristiquesDAO.findAll()
        if(caracteristiques.isEmpty()) {
            return mutableListOf()
        }
        var listeresultat: MutableList<Resultat> = mutableListOf()
        for (unecaracteristique in caracteristiques){
            var resultatId = ResultatId(unecaracteristique.caracteristiqueId,recette.id!!)
            var resultat = Resultat(resultatId,0.0F,recette,null,unecaracteristique)
            listeresultat.add(resultat)
        }
        return listeresultat
    }


    fun assignMentionsToResults(recette: Recette): Recette{
        for (unresultat in recette.resultats){
            if(unresultat.caracteristique != null){
                var caracteristiques = unresultat.caracteristique
                var mention = mentionDAO.findMentionsByScoreAndCaracteristique(unresultat.score,unresultat.caracteristique!!)
                if(mention == null){
                    println("Aucune mention trouvée pour le score et la caractéristique")
                } else {
                    unresultat.mention = mention
                }
            }
        }
        return recette
    }

    fun toRecette(recetteFormDTO: RecetteFormDTO) : Recette{
        var recette = Recette(description = recetteFormDTO.description, avecSoude = recetteFormDTO.avecSoude, concentrationAlcalin = recetteFormDTO.concentrationAlcalin, surgraissage = recetteFormDTO.surgraissage, titre = recetteFormDTO.titre, apportEnEau = 0.0f, qteAlcalin = 0.0f)
        recette = (this.recetteDAO.save(recette))
        for (ligneDTO in recetteFormDTO.ligneIngredient){
            var ligne  = this.toLigne(ligneDTO,recette)
            recette.ligneIngredients.add(ligne)
        }
        recette.resultats = this.createResultat(recette)
        recette.calculPondere()
        recette.calculNonPondere()
        recette.calculQteAlcalin()
        recette.calculApportEau()
        ligneIngredientDAO.saveAll(recette.ligneIngredients)
        recette = this.assignMentionsToResults(recette)
        resultatDAO.saveAll(recette.resultats)
        recette = this.recetteDAO.save(recette)
        return recette
    }




}
