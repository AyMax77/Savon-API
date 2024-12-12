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
            val huileCoco = Ingredient(2,"Huile Coco",257.0,9.0,248.0,7.746,14.462,13.326,9.56,9.39,11.204,11.88)
            val ingredients= listOf<Ingredient>(huileOlive,huileCoco)
            ingredientDAO.saveAll(ingredients)
        }
        if (caracteristiqueDAO.count() == 0L) { // Éviter les doublons
            val iode = Caracteristique(1,"iode")
            val ins = Caracteristique(2,"ins")
            val douceur = Caracteristique(3,"douceur")
            val lavant = Caracteristique(4,"lavant")
            val volMousse = Caracteristique(5,"vol mousse")
            val tenueMousse = Caracteristique(6,"tenue mousse")
            val durete = Caracteristique(7,"durete")
            val solubilite = Caracteristique(8,"solubilite")
            val sechage = Caracteristique(9,"sechage")







            val caracteristiques= listOf<Caracteristique>(iode,ins,douceur,lavant,volMousse,tenueMousse,durete,solubilite,sechage)
            caracteristiqueDAO.saveAll(caracteristiques)
        }
    }
}