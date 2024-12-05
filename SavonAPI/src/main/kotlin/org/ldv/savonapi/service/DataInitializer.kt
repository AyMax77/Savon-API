package org.ldv.savonapi.service


import org.ldv.savonapi.model.dao.*
import org.ldv.savonapi.model.entity.Caracteristique
import org.ldv.savonapi.model.entity.Ingredient
import org.ldv.savonapi.model.entity.Mention
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    val ingredientDAO: IngredientsDAO,
    val caracteristiqueDAO: CaracteristiquesDAO,
    private val mentionDAO: MentionDAO
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        //Pour importer les ingredients
        if (ingredientDAO.count() == 0L) { // Éviter les doublons
            val huileOlive= Ingredient(1,"Huile d'olive",189.0,78.0,111.0,9.26,10.192,9.838,9.152,10.144,9.298,10.194)
            val ingredients= listOf<Ingredient>(huileOlive)
            ingredientDAO.saveAll(ingredients)
        }
        if (caracteristiqueDAO.count() == 0L) { // Éviter les doublons
            val sapo = Caracteristique(1,"sapo")
            val iode = Caracteristique(2,"iode")
            val ins = Caracteristique(3,"ins")
            val douceur = Caracteristique(4,"douceur")
            val lavant = Caracteristique(5,"lavant")
            val volMousse = Caracteristique(6,"vol mousse")
            val tenueMousse = Caracteristique(7,"tenue mousse")
            val durete = Caracteristique(8,"durete")
            val solubilite = Caracteristique(9,"solubilite")
            val sechage = Caracteristique(10,"sechage")







            val caracteristiques= listOf<Caracteristique>(sapo,iode,ins,douceur,lavant,volMousse,tenueMousse,durete,solubilite,sechage)
            caracteristiqueDAO.saveAll(caracteristiques)
        }
    }
}