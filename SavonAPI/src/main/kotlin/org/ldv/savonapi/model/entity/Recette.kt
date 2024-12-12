package org.ldv.savonapi.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Recette(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?=null,
    var description: String,
    var avecSoude : Boolean,
    var concentrationAlcalin : Float,
    var qteAlcalin : Float,

    var titre : String,
    var apportEnEau : Float,
    var surgraissage : Float,
    @OneToMany(mappedBy = "recette")
    var ligneIngredients : MutableList<LigneIngredient> = mutableListOf(),
    @OneToMany(mappedBy = "recette")
    var resultats : MutableList<Resultat> = mutableListOf()


) {
    fun calculQteAlcalin(){
        var qteAlcalinNormal = 0.0
        if(avecSoude == true){
            qteAlcalinNormal = this.ligneIngredients.sumOf{(it.quantite*it.ingredient!!.sapo*(40.0/56.0/1000.0))}
        } else {
            qteAlcalinNormal = this.ligneIngredients.sumOf { (it.quantite*it.ingredient!!.sapo)/1000.0}
        }
        var qteAlcalin = qteAlcalinNormal/(concentrationAlcalin/100.0)
        qteAlcalin = qteAlcalin - (qteAlcalin*(surgraissage/100))
        this.qteAlcalin = qteAlcalin.toFloat()
    }

    fun calculApportEau(){
        var concentrationEau =(100 - concentrationAlcalin)/100
        apportEnEau = qteAlcalin*concentrationEau
        apportEnEau = this.apportEnEau
    }

    fun calculNonPondere(){
        var ins = this.ligneIngredients.sumOf { it.ingredient!!.ins*it.pourcentage/100 }
        var iode = this.ligneIngredients.sumOf { it.ingredient!!.iode*it.pourcentage/100 }

        /*for( unResultat in this.resultats){                      |#VARIANTE
          if(unResultat.caracteristique!!.nom=="Iode")             |
          {                                                        |
              unResultat.score=iode.toFloat()                      |
          }                                                        |
        }                                                          |         */

        this.resultats.find { it.caracteristique!!.nom=="iode" }!!.score=iode.toFloat()
        this.resultats.find { it.caracteristique!!.nom=="ins" }!!.score=ins.toFloat()
    }

    fun calculPondere(){
        var douceur = this.ligneIngredients.sumOf { it.ingredient!!.douceur*it.pourcentage/100 }
        var lavant = this.ligneIngredients.sumOf { it.ingredient!!.lavant*it.pourcentage/100 }
        var volMousse = this.ligneIngredients.sumOf { it.ingredient!!.volMousse*it.pourcentage/100 }
        var tenueMousse = this.ligneIngredients.sumOf { it.ingredient!!.tenueMousse*it.pourcentage/100 }
        var durete = this.ligneIngredients.sumOf { it.ingredient!!.durete*it.pourcentage/100 }
        var solubilite = this.ligneIngredients.sumOf { it.ingredient!!.solubilite*it.pourcentage/100 }
        var sechage = this.ligneIngredients.sumOf { it.ingredient!!.sechage*it.pourcentage/100 }

        douceur = douceur*(1+0.01494*surgraissage)
        lavant = lavant*(1+-0.01203*surgraissage)
        volMousse = volMousse*(1+-0.00702*surgraissage)
        tenueMousse = tenueMousse*(1+0.01016*surgraissage)
        durete = durete*(1+-0.00602*surgraissage)
        solubilite = solubilite*(1+0.00250*surgraissage)
        sechage = sechage*(1+-0.00503*surgraissage)

        this.resultats.find { it.caracteristique!!.nom=="douceur" }!!.score=douceur.toFloat()
        this.resultats.find { it.caracteristique!!.nom=="lavant" }!!.score=lavant.toFloat()
        this.resultats.find { it.caracteristique!!.nom=="vol mousse" }!!.score=volMousse.toFloat()
        this.resultats.find { it.caracteristique!!.nom=="tenue mousse" }!!.score=tenueMousse.toFloat()
        this.resultats.find { it.caracteristique!!.nom=="durete" }!!.score=durete.toFloat()
        this.resultats.find { it.caracteristique!!.nom=="solubilite" }!!.score=solubilite.toFloat()
        this.resultats.find { it.caracteristique!!.nom=="sechage" }!!.score=sechage.toFloat()

    }
}